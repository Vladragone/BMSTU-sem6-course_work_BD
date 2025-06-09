package com.example.game.repository;

import com.example.game.model.GameSession;

import java.util.List;
import java.util.Optional;

public interface GameSessionRepository {
    GameSession save(GameSession session);
    List<GameSession> findLast5ByUserId(Long userId);
}
