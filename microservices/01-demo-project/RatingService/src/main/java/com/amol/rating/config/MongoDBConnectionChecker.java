package com.amol.rating.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDBConnectionChecker {

    @Autowired
    private MongoTemplate mongoTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void checkMongoDBConnection() {
        try {
            mongoTemplate.executeCommand("{ ping: 1 }");
            System.out.println("MongoDB connection established.");
        } catch (Exception e) {
            System.out.println("Failed to establish MongoDB connection: " + e.getMessage());
        }
    }
}
