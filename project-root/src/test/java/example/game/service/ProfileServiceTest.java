package com.example.game.service;

import com.example.game.model.Profile;
import com.example.game.model.User;
import com.example.game.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProfile_Exists() {
        User user = new User();
        user.setUsername("test");
        Profile profile = new Profile(user, null);
        
        when(profileRepository.findByUserUsername("test")).thenReturn(Optional.of(profile));
        Profile result = service.getProfile("test");
        assertNotNull(result);
        assertEquals("test", result.getUser().getUsername());
    }

    @Test
    void updateScore_Success() {
        User user = new User();
        user.setUsername("test");
        Profile profile = new Profile(user, null);
        profile.setScore(100);
        
        when(profileRepository.findByUserUsername("test")).thenReturn(Optional.of(profile));
        
        Map<String, Object> result = service.updateScore(Map.of("score", 50), "test");
        assertEquals(150, result.get("totalScore"));
    }
}