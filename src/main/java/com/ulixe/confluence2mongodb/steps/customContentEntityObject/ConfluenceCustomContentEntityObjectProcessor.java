package com.ulixe.confluence2mongodb.steps.customContentEntityObject;

import com.ulixe.confluence2mongodb.model.ConfluenceCustomContentEntityObject;
import com.ulixe.wikimodel.model.db.DbCustomContentEntityObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

import java.util.Collections;

public class ConfluenceCustomContentEntityObjectProcessor implements ItemProcessor<ConfluenceCustomContentEntityObject, DbCustomContentEntityObject> {

    private static final Logger log = LoggerFactory.getLogger(ConfluenceCustomContentEntityObjectProcessor.class);

    @Override
    public DbCustomContentEntityObject process(@NonNull ConfluenceCustomContentEntityObject customContentEntityObject) throws Exception {
        log.info("Processing customContentEntityObject: {}", customContentEntityObject);

        return new DbCustomContentEntityObject(
                customContentEntityObject.getId(),
                customContentEntityObject.getHibernateVersion(),
                customContentEntityObject.getTitle(),
                customContentEntityObject.getLowerTitle(),
                Collections.emptyList(),
                Collections.emptyList(),
                customContentEntityObject.getVersion(),
                customContentEntityObject.getCreator(),
                customContentEntityObject.getCreationDate(),
                customContentEntityObject.getLastModifier(),
                customContentEntityObject.getLastModificationDate(),
                customContentEntityObject.getVersionComment(),
                customContentEntityObject.getContainerContent(),
                customContentEntityObject.getSpace()
        );
    }
}
