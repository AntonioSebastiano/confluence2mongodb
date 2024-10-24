package com.ulixe.confluence2mongodb.steps.space;

import com.ulixe.confluence2mongodb.model.ConfluenceSpace;
import com.ulixe.wikimodel.model.db.DbSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import java.util.Collections;

public class ConfluenceSpaceProcessor implements ItemProcessor<ConfluenceSpace, DbSpace> {

    private static final Logger log = LoggerFactory.getLogger(ConfluenceSpaceProcessor.class);

    @Override
    public DbSpace process(@NonNull ConfluenceSpace space) throws Exception {
        log.info("Processing space: {}", space);

        return new DbSpace(
                space.getId(),
                space.getName(),
                space.getKey(),
                space.getLowerKey(),
                space.getDescription(),
                space.getHomePage(),
                Collections.emptyList(),
                space.getCreator(),
                space.getCreationDate(),
                space.getLastModifier(),
                space.getLastModificationDate(),
                space.getSpaceType(),
                space.getSpaceStatus()
        );
    }
}