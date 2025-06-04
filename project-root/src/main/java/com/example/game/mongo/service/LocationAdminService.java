package com.example.game.mongo.service;

import com.example.game.model.Location;
import com.example.game.mongo.model.LocationDocument;
import com.example.game.mongo.repository.LocationMongoRepository;
import com.example.game.service.interfaces.ILocationAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class LocationAdminService implements ILocationAdminService {

    private final LocationMongoRepository locationMongoRepository;

    @Autowired
    public LocationAdminService(LocationMongoRepository locationMongoRepository) {
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
    public Optional<Location> findById(Long id) {
        Optional<LocationDocument> doc = locationMongoRepository.findById(String.valueOf(id));
        if (doc.isEmpty()) return Optional.empty();
        LocationDocument ld = doc.get();
        Location loc = new Location();
        loc.setId(Long.parseLong(ld.getId()));
        loc.setName(ld.getName());
        loc.setLat(ld.getLat());
        loc.setLng(ld.getLng());
        return Optional.of(loc);
    }

    @Override
    public Location save(Location location) {
        LocationDocument ld = new LocationDocument();
        ld.setId(location.getId() == null ? null : String.valueOf(location.getId()));
        ld.setName(location.getName());
        ld.setLat(location.getLat());
        ld.setLng(location.getLng());
        LocationDocument saved = locationMongoRepository.save(ld);
        location.setId(Long.parseLong(saved.getId()));
        return location;
    }

    @Override
    public void deleteById(Long id) {
        locationMongoRepository.deleteById(String.valueOf(id));
    }
}