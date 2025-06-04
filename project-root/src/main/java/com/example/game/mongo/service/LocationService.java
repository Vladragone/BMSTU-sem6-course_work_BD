package com.example.game.mongo.service;

import com.example.game.model.Location;
import com.example.game.mongo.model.LocationDocument;
import com.example.game.mongo.repository.LocationMongoRepository;
import com.example.game.service.interfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class LocationService implements ILocationService {

    private final LocationMongoRepository locationMongoRepository;

    @Autowired
    public LocationService(LocationMongoRepository locationMongoRepository) {
        this.locationMongoRepository = locationMongoRepository;
    }

    @Override
    public List<Location> findAll() {
        return locationMongoRepository.findAll()
            .stream()
            .map(ld -> {
                Location loc = new Location();
                loc.setId(Long.parseLong(ld.getId()));
                loc.setName(ld.getName());
                loc.setLat(ld.getLat());
                loc.setLng(ld.getLng());
                return loc;
            })
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Location> findByName(String name) {
        return locationMongoRepository.findByName(name)
            .stream()
            .findFirst()
            .map(ld -> {
                Location loc = new Location();
                loc.setId(Long.parseLong(ld.getId()));
                loc.setName(ld.getName());
                loc.setLat(ld.getLat());
                loc.setLng(ld.getLng());
                return loc;
            });
    }
}