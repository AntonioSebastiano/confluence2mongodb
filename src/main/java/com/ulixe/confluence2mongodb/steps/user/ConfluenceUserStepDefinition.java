package com.ulixe.confluence2mongodb.steps.user;

import com.ulixe.confluence2mongodb.adapters.CustomUnmarshaller;
import com.ulixe.confluence2mongodb.helpers.HybernateValidator;
import com.ulixe.confluence2mongodb.helpers.ResourceProvider;
import com.ulixe.confluence2mongodb.model.ConfluenceUserImpl;
import com.ulixe.wikimodel.model.db.DbUser;
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
public class ConfluenceUserStepDefinition {

    @Bean
    public Step userStep(JobRepository jobRepository,
                          DataSourceTransactionManager transactionManager,
                          StaxEventItemReader<ConfluenceUserImpl> ConfluenceUserImplReader,
                          ItemProcessor<ConfluenceUserImpl, DbUser> ConfluenceUserImplProcessor,
                          @Qualifier("confluenceUserImplValidationProcessor") ValidatingItemProcessor<ConfluenceUserImpl> validationProcessor,
                          ItemWriter<DbUser> ConfluenceUserImplWriter) {

        return new StepBuilder("confluenceUserImplStep", jobRepository)
                .<ConfluenceUserImpl, DbUser>chunk(10, transactionManager)
                .reader(ConfluenceUserImplReader)
                .processor(new CompositeItemProcessor<>(validationProcessor, ConfluenceUserImplProcessor))
                .writer(ConfluenceUserImplWriter)
                .build();
    }

    @Bean
    public StaxEventItemReader<ConfluenceUserImpl> confluenceUserImplReader(ResourceProvider resourceProvider) {
        StaxEventItemReader<ConfluenceUserImpl> reader = new StaxEventItemReader<>();
        reader.setResource(resourceProvider.getResource());
        reader.setFragmentRootElementName("object");

        CustomUnmarshaller customUnmarshaller = new CustomUnmarshaller(ConfluenceUserImpl.class);
        reader.setUnmarshaller(customUnmarshaller);
        return reader;
    }

    @Bean
    public ItemProcessor<ConfluenceUserImpl, DbUser> confluenceUserImplProcessor() {
        return new ConfluenceUserProcessor();
    }

    @Bean
    public MongoItemWriter<DbUser> confluenceUserImplWriter(MongoTemplate mongoTemplate) {
        MongoItemWriter<DbUser> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        return writer;
    }

    @Bean
    public ValidatingItemProcessor<ConfluenceUserImpl> confluenceUserImplValidationProcessor(Validator validator) {
        final var processor = new ValidatingItemProcessor<ConfluenceUserImpl>();
        processor.setValidator(new HybernateValidator<>(validator));
        return processor;
    }
}
