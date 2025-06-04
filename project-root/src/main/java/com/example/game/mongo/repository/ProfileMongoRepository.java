package com.example.game.mongo.repository;

import com.example.game.mongo.model.ProfileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ProfileMongoRepository extends MongoRepository<ProfileDocument, String> {
    Optional<ProfileDocument> findByUserUsername(String username);
    List<ProfileDocument> findTop10ByOrderByScoreDesc();
}