package com.wirecard.repository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
@PropertySource("classpath:application.properties")
public class MongoConfig {

	@Value("${mongo.db.url}")
    private String MONGO_DB_URL;
    
	@Value("${mongo.db.port}")
	private Integer MONGO_DB_PORT;
	
	@Value("${mongo.db.name}")
	private String MONGO_DB_NAME;
    
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        MongoClient mongoClient = new MongoClient(MONGO_DB_URL, MONGO_DB_PORT);
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        return mongoTemplate;
    }
}