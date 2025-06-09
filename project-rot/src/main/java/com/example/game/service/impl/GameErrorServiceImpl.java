package com.example.game.service.impl;

import com.example.game.model.GameError;
import com.example.game.repository.GameErrorRepository;
import com.example.game.service.api.IGameErrorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameErrorServiceImpl implements IGameErrorService {
    private final GameErrorRepository repo;

    public GameErrorServiceImpl(GameErrorRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<GameError> getAllGameErrors() {
        return repo.findAll();
    }
}
