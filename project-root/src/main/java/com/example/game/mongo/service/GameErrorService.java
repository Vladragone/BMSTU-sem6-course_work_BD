package com.example.game.mongo.service;

import com.example.game.model.GameError;
import com.example.game.mongo.model.GameErrorDocument;
import com.example.game.mongo.repository.GameErrorMongoRepository;
import com.example.game.service.interfaces.IGameErrorService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class GameErrorService implements IGameErrorService {

    private final GameErrorMongoRepository gameErrorMongoRepository;

    public GameErrorService(GameErrorMongoRepository gameErrorMongoRepository) {
        this.gameErrorMongoRepository = gameErrorMongoRepository;
    }

    @Override
    public List<GameError> getAllGameErrors() {
        return gameErrorMongoRepository.findAll().stream()
            .map(this::toGameError)
            .collect(Collectors.toList());
    }

    private GameError toGameError(GameErrorDocument doc) {
        GameError error = new GameError();
        error.setId(doc.getId());
        error.setName(doc.getName());
        return error;
    }
}
