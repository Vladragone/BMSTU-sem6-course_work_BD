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

class RandomLocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private RandomLocationService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRandomLocationByName_Exists() {
        Location location = new Location();
        location.setName("test");
        when(locationRepository.findByName("test")).thenReturn(List.of(location));
        
        Location result = service.getRandomLocationByName("test");
        assertNotNull(result);
        assertEquals("test", result.getName());
    }

    @Test
    void getRandomLocationByName_NotExists() {
        when(locationRepository.findByName("test")).thenReturn(List.of());
        assertNull(service.getRandomLocationByName("test"));
    }
}