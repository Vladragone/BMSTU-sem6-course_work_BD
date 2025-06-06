package com.example.game.mongo.repository;

import com.example.game.mongo.model.LocationDocument;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationMongoRepository extends MongoRepository<LocationDocument, Long> {

    @Aggregation(pipeline = {
        "{ '$group': { '_id': '$name' } }",
        "{ '$project': { '_id': 0, 'name': '$_id' } }"
    })
    List<String> findDistinctNames();

    List<LocationDocument> findByName(String name);
}
