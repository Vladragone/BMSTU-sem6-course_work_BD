package com.example.game.mongo.service;

import com.example.game.model.Location;
import com.example.game.mongo.model.LocationDocument;
import com.example.game.mongo.repository.LocationMongoRepository;
import com.example.game.service.interfaces.ILocationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class LocationService implements ILocationService {

    private final LocationMongoRepository locationMongoRepository;

    public LocationService(LocationMongoRepository locationMongoRepository) {
        this.locationMongoRepository = locationMongoRepository;
    }

    @Override
    public List<String> getDistinctLocationNames() {
        return locationMongoRepository.findDistinctNames();
    }

    @Override
    public List<Location> getAllLocations() {
        return locationMongoRepository.findAll().stream()
            .map(this::toLocation)
            .collect(Collectors.toList());
    }

    private Location toLocation(LocationDocument doc) {
        Location location = new Location();
        location.setId(doc.getId());
        location.setName(doc.getName());
        location.setLat(doc.getLat());
        location.setLng(doc.getLng());
        return location;
    }
}
