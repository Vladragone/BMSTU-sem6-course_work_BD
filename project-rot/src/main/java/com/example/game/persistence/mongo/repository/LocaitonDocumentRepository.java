package com.example.game.persistence.mongo.repository;

import com.example.game.persistence.mongo.document.LocationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LocationDocumentRepository
        extends MongoRepository<LocationDocument, Long> {

    @Query(value = "{}", fields = "{ name: 1 }", distinct = true)
    List<String> findDistinctNames();

    List<LocationDocument> findByName(String name);
}
