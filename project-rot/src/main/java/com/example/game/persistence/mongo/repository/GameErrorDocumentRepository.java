package com.example.game.persistence.mongo.repository;

import com.example.game.persistence.mongo.document.GameErrorDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameErrorDocumentRepository
        extends MongoRepository<GameErrorDocument, Long> {
}
