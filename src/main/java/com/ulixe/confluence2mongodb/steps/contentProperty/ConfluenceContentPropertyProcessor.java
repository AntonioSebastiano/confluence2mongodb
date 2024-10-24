package com.ulixe.confluence2mongodb.steps.contentProperty;


import com.ulixe.confluence2mongodb.model.ConfluenceContentProperty;
import com.ulixe.wikimodel.model.db.DbContentProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class ConfluenceContentPropertyProcessor implements ItemProcessor<ConfluenceContentProperty, DbContentProperty> {

    private static final Logger log = LoggerFactory.getLogger(ConfluenceContentPropertyProcessor.class);

    @Override
    public DbContentProperty process(@NonNull ConfluenceContentProperty contentProperty) throws Exception {
        log.info("Processing contentProperty: {}", contentProperty);

        return new DbContentProperty(
                contentProperty.getId(),
                contentProperty.getName(),
                contentProperty.getStringValue(),
                contentProperty.getLongValue(),
                contentProperty.getDataValue()
        );
    }
}
