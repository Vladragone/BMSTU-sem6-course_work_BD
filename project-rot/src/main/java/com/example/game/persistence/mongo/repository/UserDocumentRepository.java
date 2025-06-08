package com.example.game.persistence.mongo.repository;

import com.example.game.persistence.mongo.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDocumentRepository
        extends MongoRepository<UserDocument, Long> {

    Optional<UserDocument> findByUsername(String username);
    Optional<UserDocument> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
