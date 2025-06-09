package com.example.game.persistence.mongo.repository;

import com.example.game.persistence.mongo.document.GameSessionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameSessionDocumentRepository
        extends MongoRepository<GameSessionDocument, Long> {
    List<GameSessionDocument> findTop5ByUserIdOrderByIdDesc(Long userId);
}
