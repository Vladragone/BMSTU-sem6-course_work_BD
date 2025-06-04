package com.example.game.mongo.repository;

import com.example.game.mongo.model.GameErrorDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameErrorMongoRepository extends MongoRepository<GameErrorDocument, String> {
}