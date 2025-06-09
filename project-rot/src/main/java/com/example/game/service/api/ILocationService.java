package com.example.game.service.api;

import com.example.game.model.Location;

import java.util.List;

public interface ILocationService {
    List<String> getDistinctLocationNames();
    List<Location> getAllLocations();
    List<Location> getByName(String name);
}
