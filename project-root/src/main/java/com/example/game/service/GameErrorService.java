package com.example.game.service;

import com.example.game.model.GameError;
import com.example.game.repository.GameErrorRepository;
import com.example.game.service.interfaces.IGameErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.List;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "postgres", matchIfMissing = true)
public class GameErrorService implements IGameErrorService {

    private final GameErrorRepository gameErrorRepository;

    @Autowired
    public GameErrorService(GameErrorRepository gameErrorRepository) {
        this.gameErrorRepository = gameErrorRepository;
    }

    @Override
    public List<GameError> getAllGameErrors() {
        return gameErrorRepository.findAll();
    }
}
