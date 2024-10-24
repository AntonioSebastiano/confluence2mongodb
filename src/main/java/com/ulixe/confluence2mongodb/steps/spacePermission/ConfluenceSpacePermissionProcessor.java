package com.ulixe.confluence2mongodb.steps.spacePermission;

import com.ulixe.confluence2mongodb.model.ConfluenceSpacePermission;
import com.ulixe.wikimodel.model.db.DbSpacePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class ConfluenceSpacePermissionProcessor implements ItemProcessor<ConfluenceSpacePermission, DbSpacePermission> {

    private static final Logger log = LoggerFactory.getLogger(ConfluenceSpacePermissionProcessor.class);

    @Override
    public DbSpacePermission process(@NonNull ConfluenceSpacePermission spacePermission) throws Exception {
        log.info("Processing spacePermission: {}", spacePermission);

        return new DbSpacePermission(
                spacePermission.getName()
        );
    }
}