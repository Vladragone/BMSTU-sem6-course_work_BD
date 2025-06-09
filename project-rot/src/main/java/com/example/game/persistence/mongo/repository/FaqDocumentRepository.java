package com.example.game.persistence.mongo.repository;

import com.example.game.persistence.mongo.document.FaqDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FaqDocumentRepository
        extends MongoRepository<FaqDocument, Long> {
}
