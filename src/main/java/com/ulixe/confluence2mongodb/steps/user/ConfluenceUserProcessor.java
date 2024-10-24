package com.ulixe.confluence2mongodb.steps.user;

import com.ulixe.confluence2mongodb.model.ConfluenceUserImpl;
import com.ulixe.wikimodel.model.db.DbUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import java.util.Collections;

public class ConfluenceUserProcessor implements ItemProcessor<ConfluenceUserImpl, DbUser> {

    private static final Logger log = LoggerFactory.getLogger(ConfluenceUserProcessor.class);

    @Override
    public DbUser process(@NonNull ConfluenceUserImpl user) throws Exception {
        log.info("Processing user: {}", user);

        return new DbUser(
                user.getKey(),
                user.getName(),
                user.getLowerName(),
                user.getAtlassianAccountId()
        );
    }
}