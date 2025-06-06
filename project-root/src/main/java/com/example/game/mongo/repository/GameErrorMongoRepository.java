package com.example.game.mongo.repository;

import com.example.game.mongo.model.GameErrorDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameErrorMongoRepository extends MongoRepository<GameErrorDocument, Long> {
}
