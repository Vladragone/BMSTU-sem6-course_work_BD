package com.example.game.mongo.repository;

import com.example.game.mongo.model.FaqDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqMongoRepository extends MongoRepository<FaqDocument, Long> {
}
