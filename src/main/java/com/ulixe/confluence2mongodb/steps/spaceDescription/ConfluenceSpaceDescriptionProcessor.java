package com.ulixe.confluence2mongodb.steps.spaceDescription;

import com.ulixe.confluence2mongodb.model.ConfluenceSpaceDescription;
import com.ulixe.wikimodel.model.db.DbSpaceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

import java.util.Collections;

public class ConfluenceSpaceDescriptionProcessor implements ItemProcessor<ConfluenceSpaceDescription, DbSpaceDescription> {

    private static final Logger log = LoggerFactory.getLogger(ConfluenceSpaceDescriptionProcessor.class);

    @Override
    public DbSpaceDescription process(@NonNull ConfluenceSpaceDescription spaceDescription) throws Exception {
        log.info("Processing spaceDescription: {}", spaceDescription);

        return new DbSpaceDescription(
                spaceDescription.getId(),
                spaceDescription.getHibernateVersion(),
                spaceDescription.getTitle(),
                spaceDescription.getCreator(),
                spaceDescription.getCreationDate(),
                spaceDescription.getSpace(),
                Collections.emptyList()
        );
    }
}