package com.ulixe.confluence2mongodb.steps.labelling;

import com.ulixe.confluence2mongodb.model.ConfluenceLabelling;
import com.ulixe.wikimodel.model.db.DbLabelling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class ConfluenceLabellingProcessor implements ItemProcessor<ConfluenceLabelling, DbLabelling> {

    private static final Logger log = LoggerFactory.getLogger(ConfluenceLabellingProcessor.class);

    @Override
    public DbLabelling process(@NonNull ConfluenceLabelling labelling) throws Exception {
        log.info("Processing labelling: {}", labelling);

        return new DbLabelling(
                labelling.getId(),
                labelling.getLabel(),
                labelling.getContent(),
                labelling.getOwningUser(),
                labelling.getCreationDate()
        );
    }
}
