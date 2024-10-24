package com.ulixe.confluence2mongodb.steps.article;

import com.ulixe.confluence2mongodb.model.ConfluenceArticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import com.ulixe.wikimodel.model.db.DbArticle;

import java.util.Collections;

public class ConfluenceArticleProcessor implements ItemProcessor<ConfluenceArticle, DbArticle> {

    private static final Logger log = LoggerFactory.getLogger(ConfluenceArticleProcessor.class);

    @Override
    public DbArticle process(@NonNull ConfluenceArticle article) throws Exception {
        log.info("Processing article: {}", article);

        return new DbArticle(
                article.getname(),
                article.getcontent(),
                article.getspace(),
                article.getParent(),
                Collections.emptyList(),
                article.getcreator(),
                Collections.emptyList()
        );
    }
}

