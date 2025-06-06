package com.example.game.mongo.service;

import com.example.game.model.GameSession;
import com.example.game.mongo.model.GameSessionDocument;
import com.example.game.mongo.repository.GameSessionMongoRepository;
import com.example.game.service.interfaces.IGameSessionService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class GameSessionService implements IGameSessionService {

    private final GameSessionMongoRepository gameSessionMongoRepository;

    public GameSessionService(GameSessionMongoRepository gameSessionMongoRepository) {
        this.gameSessionMongoRepository = gameSessionMongoRepository;
    }

    @Override
    public GameSession saveGameSession(GameSession gameSession) {
        if (gameSession.getId() == null) {
            gameSession.setId(getNextId());
        }
        GameSessionDocument doc = new GameSessionDocument(
            gameSession.getId(),
            gameSession.getUserId(),
            gameSession.getUserLat(),
            gameSession.getUserLng(),
            gameSession.getCorrectLat(),
            gameSession.getCorrectLng(),
            gameSession.getEarnedScore()
        );
        GameSessionDocument saved = gameSessionMongoRepository.save(doc);
        return toGameSession(saved);
    }

    @Override
    public List<GameSession> getLast5SessionsByUserId(Long userId) {
        return gameSessionMongoRepository.findTop5ByUserIdOrderByIdDesc(userId).stream()
            .map(this::toGameSession)
            .collect(Collectors.toList());
    }

    private GameSession toGameSession(GameSessionDocument doc) {
        GameSession session = new GameSession();
        session.setId(doc.getId());
        session.setUserId(doc.getUserId());
        session.setUserLat(doc.getUserLat());
        session.setUserLng(doc.getUserLng());
        session.setCorrectLat(doc.getCorrectLat());
        session.setCorrectLng(doc.getCorrectLng());
        session.setEarnedScore(doc.getEarnedScore());
        return session;
    }

    private Long getNextId() {
        Optional<Long> maxId = gameSessionMongoRepository.findAll()
            .stream()
            .map(GameSessionDocument::getId)
            .max(Comparator.naturalOrder());
        return maxId.map(id -> id + 1).orElse(1L);
    }
}
