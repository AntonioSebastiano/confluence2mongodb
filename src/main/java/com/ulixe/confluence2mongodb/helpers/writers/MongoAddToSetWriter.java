package com.ulixe.confluence2mongodb.helpers.writers;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.function.Function;

public class MongoAddToSetWriter<T, D> implements ItemWriter<T> {

    private final MongoTemplate mongoTemplate;
    private final Class<D> collectionClass;
    private final String setFieldName;
    private final Function<T, Object> parentIdentifier;
    private final String parentIdentifierField;

    public MongoAddToSetWriter(
        MongoTemplate mongoTemplate,
        Class<D> collectionClass,
        String setFieldName,
        Function<T, Object> parentIdentifier,
        String parentIdentifierField
    ) {
        this.mongoTemplate = mongoTemplate;
        this.collectionClass = collectionClass;
        this.setFieldName = setFieldName;
        this.parentIdentifier = parentIdentifier;
        this.parentIdentifierField = parentIdentifierField;
    }
    public MongoAddToSetWriter(
            MongoTemplate mongoTemplate,
            Class<D> collectionClass,
            String setFieldName
    ) {
        this(mongoTemplate, collectionClass, setFieldName, null, null);
    }
    public MongoAddToSetWriter(
            MongoTemplate mongoTemplate,
            Class<D> collectionClass,
            String setFieldName,
            Function<T, Object> parentIdentifier
    ) {
        this(mongoTemplate, collectionClass, setFieldName, parentIdentifier, "_id");
    }

    @Override
    public void write(Chunk<? extends T> chunk) throws Exception {
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, collectionClass);

        for (T item : chunk) {
            if (parentIdentifier != null) {
                // Prepare the query to find the document
                Query query = new Query(Criteria.where(parentIdentifierField).is(parentIdentifier.apply(item)));

                // Create the update to add the item to the specified array field
                Update update = new Update().addToSet(setFieldName, item);
                // Add the update operation to the bulk operations
                bulkOperations.updateOne(query, update);
            } else {
                // Create the update to add the item to the specified array field
                Update update = new Update().addToSet(setFieldName, item);
                // Add the update operation to the bulk operations
                bulkOperations.updateMulti(new Query(), update);
            }
        }

        // Execute the bulk operations
        bulkOperations.execute();
    }
}
