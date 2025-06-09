package com.example.game.persistence.mongo.repository;

import com.example.game.persistence.mongo.document.FeedbackDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackDocumentRepository
        extends MongoRepository<FeedbackDocument, Long> {
}
