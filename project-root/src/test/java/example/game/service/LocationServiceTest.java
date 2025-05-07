package com.example.game.service;

import com.example.game.model.Location;
import com.example.game.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDistinctLocationNames_Success() {
        when(locationRepository.findDistinctNames()).thenReturn(List.of("Loc1", "Loc2"));
        List<String> result = service.getDistinctLocationNames();
        assertEquals(2, result.size());
        assertTrue(result.contains("Loc1"));
    }

    @Test
    void getAllLocations_Success() {
        Location loc1 = new Location();
        loc1.setName("Loc1");
        when(locationRepository.findAll()).thenReturn(List.of(loc1));
        
        List<Location> result = service.getAllLocations();
        assertEquals(1, result.size());
        assertEquals("Loc1", result.get(0).getName());
    }
}