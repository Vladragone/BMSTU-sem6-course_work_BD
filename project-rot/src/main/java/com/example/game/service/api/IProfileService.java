package com.example.game.service.api;

import com.example.game.model.Profile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IProfileService {
    Optional<Profile> getProfile(Long userId);
    Map<String, Object> updateScore(Long userId, int delta);
    List<Profile> getTop10();
}
