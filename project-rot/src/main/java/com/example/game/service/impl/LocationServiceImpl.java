package com.example.game.service.impl;

import com.example.game.model.Location;
import com.example.game.repository.LocationRepository;
import com.example.game.service.api.ILocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements ILocationService {
    private final LocationRepository repo;

    public LocationServiceImpl(LocationRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<String> getDistinctLocationNames() {
        return repo.findDistinctNames();
    }

    @Override
    public List<Location> getAllLocations() {
        return repo.findAll();
    }

    @Override
    public List<Location> getByName(String name) {
        return repo.findByName(name);
    }
}
