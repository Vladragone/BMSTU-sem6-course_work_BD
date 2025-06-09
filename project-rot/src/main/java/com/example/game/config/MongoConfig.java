package com.example.game.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@ConditionalOnProperty(
    name = "app.database",
    havingValue = "mongo"
)
@EnableMongoRepositories(
    basePackages = "com.example.game.mongo.repository"
)
public class MongoConfig {
}
