package com.example.game.service;

import com.example.game.model.Location;
import com.example.game.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationAdminServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationAdminService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addLocation_Success() {
        Location location = new Location();
        location.setName("Test Location");
        when(locationRepository.save(any())).thenReturn(location);

        Location result = service.addLocation(location, "admin");

        assertNotNull(result);
        assertEquals("Test Location", result.getName());
        verify(locationRepository).save(location);
    }
}