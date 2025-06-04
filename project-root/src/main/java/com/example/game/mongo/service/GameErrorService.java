package com.example.game.mongo.service;

import com.example.game.model.GameError;
import com.example.game.mongo.model.GameErrorDocument;
import com.example.game.mongo.repository.GameErrorMongoRepository;
import com.example.game.service.interfaces.IGameErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class GameErrorService implements IGameErrorService {

    private final GameErrorMongoRepository gameErrorMongoRepository;

    @Autowired
    public GameErrorService(GameErrorMongoRepository gameErrorMongoRepository) {
        this.gameErrorMongoRepository = gameErrorMongoRepository;
    }

    @Override
    public List<GameError> findAll() {
        return gameErrorMongoRepository.findAll()
            .stream()
            .map(ed -> {
                GameError e = new GameError();
                e.setId(Long.parseLong(ed.getId()));
                e.setName(ed.getName());
                return e;
            })
            .collect(Collectors.toList());
    }

    @Override
    public Optional<GameError> findById(Long id) {
        Optional<GameErrorDocument> doc = gameErrorMongoRepository.findById(String.valueOf(id));
        if (doc.isEmpty()) return Optional.empty();
        GameErrorDocument ed = doc.get();
        GameError e = new GameError();
        e.setId(Long.parseLong(ed.getId()));
        e.setName(ed.getName());
        return Optional.of(e);
    }

    @Override
    public GameError save(GameError gameError) {
        GameErrorDocument ed = new GameErrorDocument();
        ed.setId(gameError.getId() == null ? null : String.valueOf(gameError.getId()));
        ed.setName(gameError.getName());
        GameErrorDocument saved = gameErrorMongoRepository.save(ed);
        gameError.setId(Long.parseLong(saved.getId()));
        return gameError;
    }

    @Override
    public void deleteById(Long id) {
        gameErrorMongoRepository.deleteById(String.valueOf(id));
    }
}