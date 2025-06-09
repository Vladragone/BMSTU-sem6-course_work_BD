package com.example.game.service.impl;

import com.example.game.model.Profile;
import com.example.game.repository.ProfileRepository;
import com.example.game.service.api.IProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements IProfileService {
    private final ProfileRepository repo;

    public ProfileServiceImpl(ProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Profile> getProfile(Long userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public Map<String, Object> updateScore(Long userId, int delta) {
        Optional<Profile> op = repo.findByUserId(userId);
        if (op.isEmpty()) {
            return Map.of("error", "Profile not found");
        }
        Profile p = op.get();
        p.setScore(p.getScore() + delta);
        p.setGameNum(p.getGameNum() + 1);
        repo.save(p);
        return Map.of("totalScore", p.getScore());
    }

    @Override
    public List<Profile> getTop10() {
        return repo.findTop10ByScoreDesc();
    }
}
