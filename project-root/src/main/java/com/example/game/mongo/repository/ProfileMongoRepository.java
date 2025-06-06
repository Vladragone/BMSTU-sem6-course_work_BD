package com.example.game.mongo.repository;

import com.example.game.mongo.model.ProfileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileMongoRepository extends MongoRepository<ProfileDocument, Long> {
    Optional<ProfileDocument> findByUserId(Long userId);
    List<ProfileDocument> findTop10ByOrderByScoreDesc();
}
