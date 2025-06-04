package com.example.game.service;

import com.example.game.model.Location;
import com.example.game.repository.LocationRepository;
import com.example.game.service.interfaces.ILocationAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "postgres", matchIfMissing = true)
public class LocationAdminService implements ILocationAdminService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location addLocation(Location location, String username) {
        return locationRepository.save(location);
    }
}
