package com.ulixe.confluence2mongodb.steps.article;
import com.ulixe.confluence2mongodb.model.ConfluenceArticle;
import com.ulixe.confluence2mongodb.helpers.ResourceProvider;
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
import com.ulixe.wikimodel.model.db.DbArticle;
import com.ulixe.confluence2mongodb.helpers.HybernateValidator;

@Configuration
public class ConfluenceArticleStepDefinition {

    @Bean
    public Step articleStep(JobRepository jobRepository,
                             DataSourceTransactionManager transactionManager,
                             StaxEventItemReader<ConfluenceArticle> confluenceArticleReader,
                             ItemProcessor<ConfluenceArticle, DbArticle> confluenceArticleProcessor,
                             @Qualifier("confluenceArticleValidationProcessor") ValidatingItemProcessor<ConfluenceArticle> validationProcessor,
                             ItemWriter<DbArticle> confluenceArticleWriter) {

        return new StepBuilder("confluenceArticleStep", jobRepository)
                .<ConfluenceArticle, DbArticle>chunk(10, transactionManager)
                .reader(confluenceArticleReader)
                .processor(new CompositeItemProcessor<>(validationProcessor, confluenceArticleProcessor))
                .writer(confluenceArticleWriter)
                .build();
    }

    @Bean
    public StaxEventItemReader<ConfluenceArticle> confluenceArticleReader(ResourceProvider resourceProvider) {
        StaxEventItemReader<ConfluenceArticle> reader = new StaxEventItemReader<>();
        reader.setResource(resourceProvider.getResource());
        reader.setFragmentRootElementName("ConfluenceArticle");

        reader.setUnmarshaller(new Jaxb2Marshaller() {{
            setClassesToBeBound(ConfluenceArticle.class);
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<ConfluenceArticle, DbArticle> confluenceArticleProcessor() {
        return new ConfluenceArticleProcessor();
    }
    
    @Bean
    public MongoItemWriter<DbArticle> confluenceArticleWriter(MongoTemplate mongoTemplate) {
        MongoItemWriter<DbArticle> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        return writer;
    }

    @Bean
    public ValidatingItemProcessor<ConfluenceArticle> confluenceArticleValidationProcessor(Validator validator) {
        final var processor = new ValidatingItemProcessor<ConfluenceArticle>();
        processor.setValidator(new HybernateValidator<>(validator));
        return processor;
    }
}
