package com.example.game.mongo.service;

import com.example.game.model.Location;
import com.example.game.mongo.model.LocationDocument;
import com.example.game.mongo.repository.LocationMongoRepository;
import com.example.game.service.interfaces.ILocationAdminService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class LocationAdminService implements ILocationAdminService {

    private final LocationMongoRepository locationMongoRepository;

    public LocationAdminService(LocationMongoRepository locationMongoRepository) {
        this.locationMongoRepository = locationMongoRepository;
    }

    @Override
    public Location addLocation(Location location, String username) {
        if (location.getId() == null) {
            location.setId(getNextId());
        }
        LocationDocument doc = new LocationDocument(
            location.getId(),
            location.getName(),
            location.getLat(),
            location.getLng()
        );
        LocationDocument saved = locationMongoRepository.save(doc);
        Location result = new Location();
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setLat(saved.getLat());
        result.setLng(saved.getLng());
        return result;
    }

    private Long getNextId() {
        Optional<Long> maxId = locationMongoRepository.findAll()
            .stream()
            .map(LocationDocument::getId)
            .max(Comparator.naturalOrder());
        return maxId.map(id -> id + 1).orElse(1L);
    }
}
