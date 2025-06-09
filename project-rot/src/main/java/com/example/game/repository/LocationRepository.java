package com.example.game.repository;

import com.example.game.model.Location;

import java.util.List;

public interface LocationRepository {
    List<String> findDistinctNames();
    List<Location> findAll();
    List<Location> findByName(String name);
}
