package com.example.game.persistence.postgres.repository;

import com.example.game.model.Location;
import com.example.game.persistence.postgres.model.LocationEntity;
import com.example.game.repository.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LocationPostgresRepository implements LocationRepository {
    private final LocationEntityRepository jpaRepo;

    public LocationPostgresRepository(LocationEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<String> findDistinctNames() {
        return jpaRepo.findDistinctNames();
    }

    @Override
    public List<Location> findAll() {
        return jpaRepo.findAll().stream()
            .map(e -> {
                Location l = new Location();
                BeanUtils.copyProperties(e, l);
                return l;
            })
            .collect(Collectors.toList());
    }

    @Override
    public List<Location> findByName(String name) {
        return jpaRepo.findByName(name).stream()
            .map(e -> {
                Location l = new Location();
                BeanUtils.copyProperties(e, l);
                return l;
            })
            .collect(Collectors.toList());
    }
}
