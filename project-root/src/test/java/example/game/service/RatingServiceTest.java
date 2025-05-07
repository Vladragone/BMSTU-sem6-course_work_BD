package com.example.game.service;

import com.example.game.dto.RatingResponse;
import com.example.game.model.Profile;
import com.example.game.model.User;
import com.example.game.repository.ProfileRepository;
import com.example.game.service.interfaces.ITokenParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RatingServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private com.example.game.service.interfaces.ITokenParser tokenParser;

    @InjectMocks
    private RatingService ratingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(tokenParser.getUsername(anyString())).thenReturn("user1");
    }

    @Test
    void getSortedRatingAndRank_ByPoints() {
        Profile p1 = createProfile(1L, "user1", 100);
        Profile p2 = createProfile(2L, "user2", 200);

        when(profileRepository.findAll()).thenReturn(List.of(p1, p2));

        RatingResponse response = ratingService.getSortedRatingAndRank("valid_token", "points");

        assertEquals(2, response.getYourRank());
        assertEquals("user2", response.getTop().get(0).getUser().getUsername());
        verify(tokenParser).getUsername("valid_token");
    }

    private Profile createProfile(Long id, String username, int score) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setScore(score);
        return profile;
    }
}
