package com.example.game.mongo.service;

import com.example.game.model.Location;
import com.example.game.mongo.model.LocationDocument;
import com.example.game.mongo.repository.LocationMongoRepository;
import com.example.game.service.interfaces.IRandomLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class RandomLocationService implements IRandomLocationService {

    private final LocationMongoRepository locationMongoRepository;
    private final Random random = new Random();

    @Autowired
    public RandomLocationService(LocationMongoRepository locationMongoRepository) {
        this.locationMongoRepository = locationMongoRepository;
    }

    @Override
    public Location getRandomLocation() {
        List<LocationDocument> docs = locationMongoRepository.findAll();
        if (docs.isEmpty()) return null;
        LocationDocument chosen = docs.get(random.nextInt(docs.size()));
        Location loc = new Location();
        loc.setId(Long.parseLong(chosen.getId()));
        loc.setName(chosen.getName());
        loc.setLat(chosen.getLat());
        loc.setLng(chosen.getLng());
        return loc;
    }
}
