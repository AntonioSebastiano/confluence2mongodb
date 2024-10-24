package com.ulixe.confluence2mongodb.steps.bodyContent;

import com.ulixe.confluence2mongodb.model.ConfluenceBodyContent;
import com.ulixe.wikimodel.model.db.DbBodyContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

import java.util.Collections;

public class ConfluenceBodyContentProcessor implements ItemProcessor<ConfluenceBodyContent, DbBodyContent> {

    private static final Logger log = LoggerFactory.getLogger(ConfluenceBodyContentProcessor.class);

    @Override
    public DbBodyContent process(@NonNull ConfluenceBodyContent bodyContent) throws Exception {
        log.info("Processing bodyContent: {}", bodyContent);

        return new DbBodyContent(
                bodyContent.getId(),
                bodyContent.getBody(),
                bodyContent.getContent(),
                bodyContent.getBodyType()
        );
    }
}