package com.example.game.persistence.mongo.repository;

import com.example.game.persistence.mongo.document.ProfileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.List;

public interface ProfileDocumentRepository
        extends MongoRepository<ProfileDocument, Long> {

    Optional<ProfileDocument> findByUserId(Long userId);
    List<ProfileDocument> findTop10ByOrderByScoreDesc();
}
