package com.wirecard.repository;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig {

    private static final String MONGO_DB_URL = "localhost";
    private static final Integer MONGO_DB_PORT = 27017;
    private static final String MONGO_DB_NAME = "wirecard";
    
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        MongoClient mongoClient = new MongoClient(MONGO_DB_URL, MONGO_DB_PORT);
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        return mongoTemplate;
    }
}