package com.ulixe.confluence2mongodb.steps.spacePermission;

import com.ulixe.confluence2mongodb.adapters.CustomUnmarshaller;
import com.ulixe.confluence2mongodb.helpers.HybernateValidator;
import com.ulixe.confluence2mongodb.helpers.ResourceProvider;
import com.ulixe.confluence2mongodb.model.ConfluenceSpacePermission;
import com.ulixe.confluence2mongodb.model.ConfluenceUserImpl;
import com.ulixe.wikimodel.model.db.DbSpacePermission;
import jakarta.validation.Validator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ConfluenceSpacePermissionStepDefinition {
    @Bean
    public Step spacePermissionStep(JobRepository jobRepository,
                            DataSourceTransactionManager transactionManager,
                            StaxEventItemReader<ConfluenceSpacePermission> ConfluenceSpacePermissionReader,
                            ItemProcessor<ConfluenceSpacePermission, DbSpacePermission> ConfluenceSpacePermissionProcessor,
                            @Qualifier("confluenceSpacePermissionValidationProcessor") ValidatingItemProcessor<ConfluenceSpacePermission> validationProcessor,
                            ItemWriter<DbSpacePermission> ConfluenceSpacePermissionWriter) {

        return new StepBuilder("confluenceSpacePermissionStep", jobRepository)
                .<ConfluenceSpacePermission, DbSpacePermission>chunk(10, transactionManager)
                .reader(ConfluenceSpacePermissionReader)
                .processor(new CompositeItemProcessor<>(validationProcessor, ConfluenceSpacePermissionProcessor))
                .writer(ConfluenceSpacePermissionWriter)
                .build();
    }

    @Bean
    public StaxEventItemReader<ConfluenceSpacePermission> confluenceSpacePermissionReader(ResourceProvider resourceProvider) {
        StaxEventItemReader<ConfluenceSpacePermission> reader = new StaxEventItemReader<>();
        reader.setResource(resourceProvider.getResource());
        reader.setFragmentRootElementName("object");

        CustomUnmarshaller customUnmarshaller = new CustomUnmarshaller(ConfluenceSpacePermission.class);
        reader.setUnmarshaller(customUnmarshaller);
        return reader;
    }

    @Bean
    public ItemProcessor<ConfluenceSpacePermission, DbSpacePermission> confluenceSpacePermissionProcessor() {
        return new ConfluenceSpacePermissionProcessor();
    }

    @Bean
    public MongoItemWriter<DbSpacePermission> confluenceSpacePermissionWriter(MongoTemplate mongoTemplate) {
        MongoItemWriter<DbSpacePermission> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        return writer;
    }

    @Bean
    public ValidatingItemProcessor<ConfluenceSpacePermission> confluenceSpacePermissionValidationProcessor(Validator validator) {
        final var processor = new ValidatingItemProcessor<ConfluenceSpacePermission>();
        processor.setValidator(new HybernateValidator<>(validator));
        return processor;
    }
}
