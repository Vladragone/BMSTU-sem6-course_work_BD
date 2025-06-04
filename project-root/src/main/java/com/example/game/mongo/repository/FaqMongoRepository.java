package com.example.game.mongo.repository;

import com.example.game.mongo.model.FaqDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FaqMongoRepository extends MongoRepository<FaqDocument, String> {
}