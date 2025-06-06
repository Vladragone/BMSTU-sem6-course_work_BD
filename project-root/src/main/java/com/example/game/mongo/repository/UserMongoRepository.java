package com.example.game.mongo.repository;

import com.example.game.mongo.model.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMongoRepository extends MongoRepository<UserDocument, Long> {
    Optional<UserDocument> findByUsername(String username);
    Optional<UserDocument> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
