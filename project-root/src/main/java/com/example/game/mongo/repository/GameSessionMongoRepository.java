package com.example.game.mongo.repository;

import com.example.game.mongo.model.GameSessionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameSessionMongoRepository extends MongoRepository<GameSessionDocument, Long> {
    List<GameSessionDocument> findTop5ByUserIdOrderByIdDesc(Long userId);
}
