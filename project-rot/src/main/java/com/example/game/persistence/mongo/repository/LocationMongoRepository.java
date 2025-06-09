package com.example.game.persistence.mongo.repository;

import com.example.game.model.Location;
import com.example.game.persistence.mongo.document.LocationDocument;
import com.example.game.repository.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LocationMongoRepository implements LocationRepository {
    private final LocationDocumentRepository mongoRepo;

    public LocationMongoRepository(LocationDocumentRepository mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public List<String> findDistinctNames() {
        return mongoRepo.findDistinctNames();
    }

    @Override
    public List<Location> findAll() {
        return mongoRepo.findAll().stream()
            .map(d -> {
                Location l = new Location();
                BeanUtils.copyProperties(d, l);
                return l;
            })
            .collect(Collectors.toList());
    }

    @Override
    public List<Location> findByName(String name) {
        return mongoRepo.findByName(name).stream()
            .map(d -> {
                Location l = new Location();
                BeanUtils.copyProperties(d, l);
                return l;
            })
            .collect(Collectors.toList());
    }
}
