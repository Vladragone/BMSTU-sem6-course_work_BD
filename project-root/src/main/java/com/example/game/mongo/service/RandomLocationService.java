package com.example.game.mongo.service;

import com.example.game.model.Location;
import com.example.game.mongo.model.LocationDocument;
import com.example.game.mongo.repository.LocationMongoRepository;
import com.example.game.service.interfaces.IRandomLocationService;
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

    public RandomLocationService(LocationMongoRepository locationMongoRepository) {
        this.locationMongoRepository = locationMongoRepository;
    }

    @Override
    public Location getRandomLocationByName(String name) {
        List<LocationDocument> docs = locationMongoRepository.findByName(name);
        if (docs.isEmpty()) {
            return null;
        }
        LocationDocument doc = docs.get(random.nextInt(docs.size()));
        Location location = new Location();
        location.setId(doc.getId());
        location.setName(doc.getName());
        location.setLat(doc.getLat());
        location.setLng(doc.getLng());
        return location;
    }
}
