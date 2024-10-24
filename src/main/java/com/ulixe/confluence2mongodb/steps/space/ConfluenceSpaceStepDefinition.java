package com.ulixe.confluence2mongodb.steps.space;

import com.ulixe.confluence2mongodb.adapters.CustomUnmarshaller;
import com.ulixe.confluence2mongodb.helpers.HybernateValidator;
import com.ulixe.confluence2mongodb.helpers.ResourceProvider;
import com.ulixe.confluence2mongodb.model.ConfluencePage;
import com.ulixe.confluence2mongodb.model.ConfluenceSpace;
import com.ulixe.wikimodel.model.db.DbSpace;
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
public class ConfluenceSpaceStepDefinition {

    @Bean
    public Step spaceStep(JobRepository jobRepository,
                            DataSourceTransactionManager transactionManager,
                            StaxEventItemReader<ConfluenceSpace> ConfluenceSpaceReader,
                            ItemProcessor<ConfluenceSpace, DbSpace> ConfluenceSpaceProcessor,
                            @Qualifier("confluenceSpaceValidationProcessor") ValidatingItemProcessor<ConfluenceSpace> validationProcessor,
                            ItemWriter<DbSpace> ConfluenceSpaceWriter) {

        return new StepBuilder("confluenceSpaceStep", jobRepository)
                .<ConfluenceSpace, DbSpace>chunk(10, transactionManager)
                .reader(ConfluenceSpaceReader)
                .processor(new CompositeItemProcessor<>(validationProcessor, ConfluenceSpaceProcessor))
                .writer(ConfluenceSpaceWriter)
                .build();
    }

    @Bean
    public StaxEventItemReader<ConfluenceSpace> confluenceSpaceReader(ResourceProvider resourceProvider) {
        StaxEventItemReader<ConfluenceSpace> reader = new StaxEventItemReader<>();
        reader.setResource(resourceProvider.getResource());
        reader.setFragmentRootElementName("object");

        CustomUnmarshaller customUnmarshaller = new CustomUnmarshaller(ConfluenceSpace.class);
        reader.setUnmarshaller(customUnmarshaller);
        return reader;
    }

    @Bean
    public ItemProcessor<ConfluenceSpace, DbSpace> confluenceSpaceProcessor() {
        return new ConfluenceSpaceProcessor();
    }

    @Bean
    public MongoItemWriter<DbSpace> confluenceSpaceWriter(MongoTemplate mongoTemplate) {
        MongoItemWriter<DbSpace> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        return writer;
    }

    @Bean
    public ValidatingItemProcessor<ConfluenceSpace> confluenceSpaceValidationProcessor(Validator validator) {
        final var processor = new ValidatingItemProcessor<ConfluenceSpace>();
        processor.setValidator(new HybernateValidator<>(validator));
        return processor;
    }
}
