package com.example.game.service.impl;

import com.example.game.model.GameSession;
import com.example.game.repository.GameSessionRepository;
import com.example.game.service.api.IGameSessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameSessionServiceImpl implements IGameSessionService {
    private final GameSessionRepository repo;

    public GameSessionServiceImpl(GameSessionRepository repo) {
        this.repo = repo;
    }

    @Override
    public GameSession saveGameSession(GameSession session) {
        return repo.save(session);
    }

    @Override
    public List<GameSession> getLast5SessionsByUserId(Long userId) {
        return repo.findLast5ByUserId(userId);
    }
}
