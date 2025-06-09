package com.example.game.service.impl;

import com.example.game.model.Location;
import com.example.game.repository.LocationRepository;
import com.example.game.service.api.IRandomLocationService;
import org.springframework.stereotype.Service;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.List;
import java.util.Random;

@Service
public class RandomLocationServiceImpl implements IRandomLocationService {

    private final LocationRepository locationRepository;
    private final Random random = new Random();

    public RandomLocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location getRandomLocationByName(String name) {
        List<Location> locations = locationRepository.findByName(name);
        return locations.isEmpty()
            ? null
            : locations.get(random.nextInt(locations.size()));
    }
}
