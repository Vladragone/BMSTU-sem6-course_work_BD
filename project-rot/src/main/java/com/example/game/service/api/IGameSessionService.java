package com.example.game.service.api;

import com.example.game.model.GameSession;

import java.util.List;

public interface IGameSessionService {
    GameSession saveGameSession(GameSession session);
    List<GameSession> getLast5SessionsByUserId(Long userId);
}
