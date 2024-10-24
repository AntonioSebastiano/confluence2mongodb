package com.ulixe.confluence2mongodb.steps.page;

import com.ulixe.confluence2mongodb.model.ConfluencePage;
import com.ulixe.wikimodel.model.db.DbPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

import java.util.Collections;

public class ConfluencePageProcessor implements ItemProcessor<ConfluencePage, DbPage> {

    private static final Logger log = LoggerFactory.getLogger(ConfluencePageProcessor.class);

    @Override
    public DbPage process(@NonNull ConfluencePage page) throws Exception {
        log.info("Processing page: {}", page);

        //TODO logica di controllo per id=null

        return new DbPage(
                page.getId(),
                page.getHibernateVersion(),
                page.getTitle(),
                page.getLowerTitle(),
                Collections.emptyList(),
                Collections.emptyList(),
                page.getVersion(),
                page.getCreator(),
                page.getCreationDate(),
                page.getLastModifier(),
                page.getLastModificationDate(),
                page.getVersionComment(),
                page.getOriginalVersionId(),
                page.getContentStatus(),
                page.getNavigationContent(),
                Collections.emptyList(),
                page.getSpace(),
                page.getParent()
        );
    }
}