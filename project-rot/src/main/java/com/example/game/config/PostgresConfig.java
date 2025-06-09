package com.example.game.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ConditionalOnProperty(
    name = "app.database",
    havingValue = "postgres",
    matchIfMissing = true
)
@EnableJpaRepositories(
    basePackages = "com.example.game.repository"
)
public class PostgresConfig {
}
