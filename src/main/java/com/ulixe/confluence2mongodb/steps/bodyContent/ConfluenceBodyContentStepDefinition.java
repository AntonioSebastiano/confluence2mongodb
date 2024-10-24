package com.ulixe.confluence2mongodb.steps.bodyContent;

import com.ulixe.confluence2mongodb.adapters.CustomUnmarshaller;
import com.ulixe.confluence2mongodb.helpers.HybernateValidator;
import com.ulixe.confluence2mongodb.helpers.ResourceProvider;
import com.ulixe.confluence2mongodb.model.ConfluenceBodyContent;
import com.ulixe.confluence2mongodb.model.ConfluenceBodyContent;
import com.ulixe.confluence2mongodb.model.ConfluenceContentProperty;
import com.ulixe.confluence2mongodb.model.ConfluenceUserImpl;
import com.ulixe.confluence2mongodb.steps.bodyContent.ConfluenceBodyContentProcessor;
import com.ulixe.wikimodel.model.db.DbBodyContent;
import com.ulixe.wikimodel.model.db.DbBodyContent;
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
public class ConfluenceBodyContentStepDefinition {
    @Bean
    public Step bodyContentStep(JobRepository jobRepository,
                            DataSourceTransactionManager transactionManager,
                            StaxEventItemReader<ConfluenceBodyContent> ConfluenceBodyContentReader,
                            ItemProcessor<ConfluenceBodyContent, DbBodyContent> ConfluenceBodyContentProcessor,
                            @Qualifier("confluenceBodyContentValidationProcessor") ValidatingItemProcessor<ConfluenceBodyContent> validationProcessor,
                            ItemWriter<DbBodyContent> ConfluenceBodyContentWriter) {

        return new StepBuilder("confluenceBodyContentStep", jobRepository)
                .<ConfluenceBodyContent, DbBodyContent>chunk(10, transactionManager)
                .reader(ConfluenceBodyContentReader)
                .processor(new CompositeItemProcessor<>(validationProcessor, ConfluenceBodyContentProcessor))
                .writer(ConfluenceBodyContentWriter)
                .build();
    }

    @Bean
    public StaxEventItemReader<ConfluenceBodyContent> confluenceBodyContentReader(ResourceProvider resourceProvider) {
        StaxEventItemReader<ConfluenceBodyContent> reader = new StaxEventItemReader<>();
        reader.setResource(resourceProvider.getResource());
        reader.setFragmentRootElementName("object");

        CustomUnmarshaller customUnmarshaller = new CustomUnmarshaller(ConfluenceUserImpl.class);
        reader.setUnmarshaller(customUnmarshaller);
        return reader;
    }

    @Bean
    public ItemProcessor<ConfluenceBodyContent, DbBodyContent> confluenceBodyContentProcessor() {
        return new ConfluenceBodyContentProcessor();
    }

    @Bean
    public MongoItemWriter<DbBodyContent> confluenceBodyContentWriter(MongoTemplate mongoTemplate) {
        MongoItemWriter<DbBodyContent> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        return writer;
    }

    @Bean
    public ValidatingItemProcessor<ConfluenceBodyContent> confluenceBodyContentValidationProcessor(Validator validator) {
        final var processor = new ValidatingItemProcessor<ConfluenceBodyContent>();
        processor.setValidator(new HybernateValidator<>(validator));
        return processor;
    }
}
