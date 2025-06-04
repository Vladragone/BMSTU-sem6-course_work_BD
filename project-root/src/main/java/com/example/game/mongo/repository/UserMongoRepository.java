package com.example.game.mongo.repository;

import com.example.game.mongo.model.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByUsername(String username);
    Optional<UserDocument> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}