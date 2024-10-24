package com.ulixe.confluence2mongodb.steps.labelling;

import com.ulixe.confluence2mongodb.adapters.CustomUnmarshaller;
import com.ulixe.confluence2mongodb.helpers.HybernateValidator;
import com.ulixe.confluence2mongodb.helpers.ResourceProvider;
import com.ulixe.confluence2mongodb.model.ConfluenceLabelling;
import com.ulixe.confluence2mongodb.model.ConfluencePage;
import com.ulixe.wikimodel.model.db.DbLabelling;
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
public class ConfluenceLabellingStepDefinition {
    @Bean
    public Step labellingStep(JobRepository jobRepository,
                            DataSourceTransactionManager transactionManager,
                            StaxEventItemReader<ConfluenceLabelling> ConfluenceLabellingReader,
                            ItemProcessor<ConfluenceLabelling, DbLabelling> ConfluenceLabellingProcessor,
                            @Qualifier("confluenceLabellingValidationProcessor") ValidatingItemProcessor<ConfluenceLabelling> validationProcessor,
                            ItemWriter<DbLabelling> ConfluenceLabellingWriter) {

        return new StepBuilder("confluenceLabellingStep", jobRepository)
                .<ConfluenceLabelling, DbLabelling>chunk(10, transactionManager)
                .reader(ConfluenceLabellingReader)
                .processor(new CompositeItemProcessor<>(validationProcessor, ConfluenceLabellingProcessor))
                .writer(ConfluenceLabellingWriter)
                .build();
    }

    @Bean
    public StaxEventItemReader<ConfluenceLabelling> confluenceLabellingReader(ResourceProvider resourceProvider) {
        StaxEventItemReader<ConfluenceLabelling> reader = new StaxEventItemReader<>();
        reader.setResource(resourceProvider.getResource());
        reader.setFragmentRootElementName("object");

        CustomUnmarshaller customUnmarshaller = new CustomUnmarshaller(ConfluenceLabelling.class);
        reader.setUnmarshaller(customUnmarshaller);
        return reader;
    }

    @Bean
    public ItemProcessor<ConfluenceLabelling, DbLabelling> confluenceLabellingProcessor() {
        return new ConfluenceLabellingProcessor();
    }

    @Bean
    public MongoItemWriter<DbLabelling> confluenceLabellingWriter(MongoTemplate mongoTemplate) {
        MongoItemWriter<DbLabelling> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        return writer;
    }

    @Bean
    public ValidatingItemProcessor<ConfluenceLabelling> confluenceLabellingValidationProcessor(Validator validator) {
        final var processor = new ValidatingItemProcessor<ConfluenceLabelling>();
        processor.setValidator(new HybernateValidator<>(validator));
        return processor;
    }
}
