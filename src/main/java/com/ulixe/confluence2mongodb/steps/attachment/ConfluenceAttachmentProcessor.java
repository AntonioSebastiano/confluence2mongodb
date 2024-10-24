package com.ulixe.confluence2mongodb.steps.attachment;

import com.ulixe.confluence2mongodb.model.ConfluenceAttachment;
import com.ulixe.wikimodel.model.db.DbAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

import java.util.Collections;

public class ConfluenceAttachmentProcessor implements ItemProcessor<ConfluenceAttachment, DbAttachment> {

    private static final Logger log = LoggerFactory.getLogger(ConfluenceAttachmentProcessor.class);

    @Override
    public DbAttachment process(@NonNull ConfluenceAttachment attachment) throws Exception {
        log.info("Processing attachment: {}", attachment);

        return new DbAttachment(
                attachment.getId(),
                attachment.getHibernateVersion(),
                attachment.getTitle(),
                attachment.getLowerTitle(),
                attachment.getCreator(),
                attachment.getCreationDate(),
                attachment.getSpace(),
                attachment.getContainerContent()
        );
    }
}