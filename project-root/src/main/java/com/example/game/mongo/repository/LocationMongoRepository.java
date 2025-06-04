package com.example.game.mongo.repository;

import com.example.game.mongo.model.LocationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface LocationMongoRepository extends MongoRepository<LocationDocument, String> {
    @Query(value = "{}", fields = "{ 'name' : 1 }")
    List<String> findDistinctNames();
    List<LocationDocument> findByName(String name);
}