package com.ulixe.confluence2mongodb.steps.customContentEntityObject;

import com.ulixe.confluence2mongodb.adapters.CustomUnmarshaller;
import com.ulixe.confluence2mongodb.helpers.HybernateValidator;
import com.ulixe.confluence2mongodb.helpers.ResourceProvider;
import com.ulixe.confluence2mongodb.model.ConfluenceCustomContentEntityObject;
import com.ulixe.confluence2mongodb.model.ConfluenceLabelling;
import com.ulixe.wikimodel.model.db.DbCustomContentEntityObject;
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
public class ConfluenceCustomContentEntityObjectStepDefinition {
    @Bean
    public Step customContentEntityObjectStep(JobRepository jobRepository,
                            DataSourceTransactionManager transactionManager,
                            StaxEventItemReader<ConfluenceCustomContentEntityObject> ConfluenceCustomContentEntityObjectReader,
                            ItemProcessor<ConfluenceCustomContentEntityObject, DbCustomContentEntityObject> ConfluenceCustomContentEntityObjectProcessor,
                            @Qualifier("confluenceCustomContentEntityObjectValidationProcessor") ValidatingItemProcessor<ConfluenceCustomContentEntityObject> validationProcessor,
                            ItemWriter<DbCustomContentEntityObject> ConfluenceCustomContentEntityObjectWriter) {

        return new StepBuilder("confluenceCustomContentEntityObject", jobRepository)
                .<ConfluenceCustomContentEntityObject, DbCustomContentEntityObject>chunk(10, transactionManager)
                .reader(ConfluenceCustomContentEntityObjectReader)
                .processor(new CompositeItemProcessor<>(validationProcessor, ConfluenceCustomContentEntityObjectProcessor))
                .writer(ConfluenceCustomContentEntityObjectWriter)
                .build();
    }

    @Bean
    public StaxEventItemReader<ConfluenceCustomContentEntityObject> confluenceCustomContentEntityObjectReader(ResourceProvider resourceProvider) {
        StaxEventItemReader<ConfluenceCustomContentEntityObject> reader = new StaxEventItemReader<>();
        reader.setResource(resourceProvider.getResource());
        reader.setFragmentRootElementName("object");

        CustomUnmarshaller customUnmarshaller = new CustomUnmarshaller(ConfluenceCustomContentEntityObject.class);
        reader.setUnmarshaller(customUnmarshaller);
        return reader;
    }

    @Bean
    public ItemProcessor<ConfluenceCustomContentEntityObject, DbCustomContentEntityObject> confluenceCustomContentEntityObjectProcessor() {
        return new ConfluenceCustomContentEntityObjectProcessor();
    }

    @Bean
    public MongoItemWriter<DbCustomContentEntityObject> confluenceCustomContentEntityObjectWriter(MongoTemplate mongoTemplate) {
        MongoItemWriter<DbCustomContentEntityObject> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        return writer;
    }

    @Bean
    public ValidatingItemProcessor<ConfluenceCustomContentEntityObject> confluenceCustomContentEntityObjectValidationProcessor(Validator validator) {
        final var processor = new ValidatingItemProcessor<ConfluenceCustomContentEntityObject>();
        processor.setValidator(new HybernateValidator<>(validator));
        return processor;
    }
}
