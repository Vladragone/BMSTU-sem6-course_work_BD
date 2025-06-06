package com.example.game.mongo.repository;

import com.example.game.mongo.model.FeedbackDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackMongoRepository extends MongoRepository<FeedbackDocument, Long> {
}
