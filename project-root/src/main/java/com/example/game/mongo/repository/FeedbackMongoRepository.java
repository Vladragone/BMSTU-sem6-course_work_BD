package com.example.game.mongo.repository;

import com.example.game.mongo.model.FeedbackDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackMongoRepository extends MongoRepository<FeedbackDocument, String> {
}