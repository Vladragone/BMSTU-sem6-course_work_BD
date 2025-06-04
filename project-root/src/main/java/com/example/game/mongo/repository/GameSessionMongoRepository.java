package com.example.game.mongo.repository;

import com.example.game.mongo.model.GameSessionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GameSessionMongoRepository extends MongoRepository<GameSessionDocument, String> {
    List<GameSessionDocument> findTop5ByUserIdOrderByIdDesc(String userId);
}