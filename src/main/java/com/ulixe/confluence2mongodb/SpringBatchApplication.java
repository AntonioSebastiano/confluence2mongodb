package com.ulixe.confluence2mongodb;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication()
@EnableMongoRepositories
public class SpringBatchApplication {
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(SpringBatchApplication.class, args)));
    }

    @Bean
    public Job importJob(JobRepository jobRepository,
                         @Qualifier("userStep") Step userStep,
                         @Qualifier("spacePermissionStep") Step spacePermission,
                         @Qualifier("spaceDescriptionStep") Step spaceDescription,
                         @Qualifier("spaceStep") Step spaceStep,
                         @Qualifier("pageStep") Step pageStep,
                         @Qualifier("labellingStep") Step labellingStep,
                         @Qualifier("customContentEntityObjectStep") Step customContentEntityObjectStep,
                         @Qualifier("contentPropertyStep") Step contentPropertyStep,
                         @Qualifier("bodyContentStep") Step bodyContentStep,
                         @Qualifier("attachmentStep") Step attachmentStep,
//                         @Qualifier("articleStep") Step articleStep,
                         JobCompletionNotificationListener listener
                            ) {
        return new JobBuilder("importJob", jobRepository)
                .listener(listener)
                .start(userStep)
                .next(spacePermission)
                .next(spaceDescription)
                .next(spaceStep)
                .next(pageStep)
                .next(labellingStep)
                .next(customContentEntityObjectStep)
                .next(contentPropertyStep)
                .next(bodyContentStep)
                .next(attachmentStep)
//                .next(articleStep)
                .build();
    }
}