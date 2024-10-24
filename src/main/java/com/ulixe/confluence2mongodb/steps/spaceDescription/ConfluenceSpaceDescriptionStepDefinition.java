package com.ulixe.confluence2mongodb.steps.spaceDescription;

import com.ulixe.confluence2mongodb.adapters.CustomUnmarshaller;
import com.ulixe.confluence2mongodb.helpers.HybernateValidator;
import com.ulixe.confluence2mongodb.helpers.ResourceProvider;
import com.ulixe.confluence2mongodb.model.ConfluenceSpaceDescription;
import com.ulixe.confluence2mongodb.model.ConfluenceSpaceDescription;
import com.ulixe.confluence2mongodb.model.ConfluenceSpacePermission;
import com.ulixe.confluence2mongodb.steps.spaceDescription.ConfluenceSpaceDescriptionProcessor;
import com.ulixe.wikimodel.model.db.DbSpaceDescription;
import com.ulixe.wikimodel.model.db.DbSpaceDescription;
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
public class ConfluenceSpaceDescriptionStepDefinition {
    @Bean
    public Step spaceDescriptionStep(JobRepository jobRepository,
                            DataSourceTransactionManager transactionManager,
                            StaxEventItemReader<ConfluenceSpaceDescription> ConfluenceSpaceDescriptionReader,
                            ItemProcessor<ConfluenceSpaceDescription, DbSpaceDescription> ConfluenceSpaceDescriptionProcessor,
                            @Qualifier("confluenceSpaceDescriptionValidationProcessor") ValidatingItemProcessor<ConfluenceSpaceDescription> validationProcessor,
                            ItemWriter<DbSpaceDescription> ConfluenceSpaceDescriptionWriter) {

        return new StepBuilder("confluenceSpaceDescriptionStep", jobRepository)
                .<ConfluenceSpaceDescription, DbSpaceDescription>chunk(10, transactionManager)
                .reader(ConfluenceSpaceDescriptionReader)
                .processor(new CompositeItemProcessor<>(validationProcessor, ConfluenceSpaceDescriptionProcessor))
                .writer(ConfluenceSpaceDescriptionWriter)
                .build();
    }

    @Bean
    public StaxEventItemReader<ConfluenceSpaceDescription> confluenceSpaceDescriptionReader(ResourceProvider resourceProvider) {
        StaxEventItemReader<ConfluenceSpaceDescription> reader = new StaxEventItemReader<>();
        reader.setResource(resourceProvider.getResource());
        reader.setFragmentRootElementName("object");

        CustomUnmarshaller customUnmarshaller = new CustomUnmarshaller(ConfluenceSpaceDescription.class);
        reader.setUnmarshaller(customUnmarshaller);
        return reader;
    }

    @Bean
    public ItemProcessor<ConfluenceSpaceDescription, DbSpaceDescription> confluenceSpaceDescriptionProcessor() {
        return new ConfluenceSpaceDescriptionProcessor();
    }

    @Bean
    public MongoItemWriter<DbSpaceDescription> confluenceSpaceDescriptionWriter(MongoTemplate mongoTemplate) {
        MongoItemWriter<DbSpaceDescription> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        return writer;
    }

    @Bean
    public ValidatingItemProcessor<ConfluenceSpaceDescription> confluenceSpaceDescriptionValidationProcessor(Validator validator) {
        final var processor = new ValidatingItemProcessor<ConfluenceSpaceDescription>();
        processor.setValidator(new HybernateValidator<>(validator));
        return processor;
    }
}
