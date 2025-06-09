package com.example.game.persistence.postgres.repository;

import com.example.game.persistence.postgres.model.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationEntityRepository
        extends JpaRepository<LocationEntity, Long> {

    @Query("select distinct l.name from LocationEntity l")
    List<String> findDistinctNames();

    List<LocationEntity> findByName(String name);
}
