package com.ulixe.confluence2mongodb.steps.contentProperty;

import com.ulixe.confluence2mongodb.adapters.CustomUnmarshaller;
import com.ulixe.confluence2mongodb.helpers.HybernateValidator;
import com.ulixe.confluence2mongodb.helpers.ResourceProvider;
import com.ulixe.confluence2mongodb.model.ConfluenceContentProperty;
import com.ulixe.confluence2mongodb.model.ConfluenceContentProperty;
import com.ulixe.confluence2mongodb.model.ConfluenceCustomContentEntityObject;
import com.ulixe.confluence2mongodb.steps.contentProperty.ConfluenceContentPropertyProcessor;
import com.ulixe.wikimodel.model.db.DbContentProperty;
import com.ulixe.wikimodel.model.db.DbContentProperty;
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
public class ConfluenceContentPropertyStepDefinition {
    @Bean
    public Step contentPropertyStep(JobRepository jobRepository,
                            DataSourceTransactionManager transactionManager,
                            StaxEventItemReader<ConfluenceContentProperty> ConfluenceContentPropertyReader,
                            ItemProcessor<ConfluenceContentProperty, DbContentProperty> ConfluenceContentPropertyProcessor,
                            @Qualifier("confluenceContentPropertyValidationProcessor") ValidatingItemProcessor<ConfluenceContentProperty> validationProcessor,
                            ItemWriter<DbContentProperty> ConfluenceContentPropertyWriter) {

        return new StepBuilder("confluenceContentPropertyStep", jobRepository)
                .<ConfluenceContentProperty, DbContentProperty>chunk(10, transactionManager)
                .reader(ConfluenceContentPropertyReader)
                .processor(new CompositeItemProcessor<>(validationProcessor, ConfluenceContentPropertyProcessor))
                .writer(ConfluenceContentPropertyWriter)
                .build();
    }

    @Bean
    public StaxEventItemReader<ConfluenceContentProperty> confluenceContentPropertyReader(ResourceProvider resourceProvider) {
        StaxEventItemReader<ConfluenceContentProperty> reader = new StaxEventItemReader<>();
        reader.setResource(resourceProvider.getResource());
        reader.setFragmentRootElementName("object");

        CustomUnmarshaller customUnmarshaller = new CustomUnmarshaller(ConfluenceContentProperty.class);
        reader.setUnmarshaller(customUnmarshaller);
        return reader;
    }

    @Bean
    public ItemProcessor<ConfluenceContentProperty, DbContentProperty> confluenceContentPropertyProcessor() {
        return new ConfluenceContentPropertyProcessor();
    }

    @Bean
    public MongoItemWriter<DbContentProperty> confluenceContentPropertyWriter(MongoTemplate mongoTemplate) {
        MongoItemWriter<DbContentProperty> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        return writer;
    }

    @Bean
    public ValidatingItemProcessor<ConfluenceContentProperty> confluenceContentPropertyValidationProcessor(Validator validator) {
        final var processor = new ValidatingItemProcessor<ConfluenceContentProperty>();
        processor.setValidator(new HybernateValidator<>(validator));
        return processor;
    }
}
