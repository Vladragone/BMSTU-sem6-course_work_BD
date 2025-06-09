package com.example.game.repository;

import com.example.game.model.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    Profile save(Profile profile);
    Optional<Profile> findByUserId(Long userId);
    List<Profile> findTop10ByScoreDesc();
}
