package com.example.game.mongo.service;

import com.example.game.model.GameSession;
import com.example.game.mongo.model.GameSessionDocument;
import com.example.game.mongo.repository.GameSessionMongoRepository;
import com.example.game.service.interfaces.IGameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class GameSessionService implements IGameSessionService {

    private final GameSessionMongoRepository gameSessionMongoRepository;

    @Autowired
    public GameSessionService(GameSessionMongoRepository gameSessionMongoRepository) {
        this.gameSessionMongoRepository = gameSessionMongoRepository;
    }

    @Override
    public List<GameSession> findAllByUserId(Long userId) {
        List<GameSessionDocument> docs = gameSessionMongoRepository.findTop5ByUserIdOrderByIdDesc(String.valueOf(userId));
        return docs.stream()
            .map(sd -> {
                GameSession gs = new GameSession();
                gs.setId(Long.parseLong(sd.getId()));
                gs.setUserId(Long.parseLong(sd.getUserId()));
                gs.setUserLat(sd.getUserLat());
                gs.setUserLng(sd.getUserLng());
                gs.setCorrectLat(sd.getCorrectLat());
                gs.setCorrectLng(sd.getCorrectLng());
                gs.setEarnedScore(sd.getEarnedScore());
                gs.setErrorId(Long.parseLong(sd.getErrorId()));
                return gs;
            })
            .collect(Collectors.toList());
    }

    @Override
    public GameSession save(GameSession gameSession) {
        GameSessionDocument sd = new GameSessionDocument();
        sd.setId(gameSession.getId() == null ? null : String.valueOf(gameSession.getId()));
        sd.setUserId(String.valueOf(gameSession.getUserId()));
        sd.setUserLat(gameSession.getUserLat());
        sd.setUserLng(gameSession.getUserLng());
        sd.setCorrectLat(gameSession.getCorrectLat());
        sd.setCorrectLng(gameSession.getCorrectLng());
        sd.setEarnedScore(gameSession.getEarnedScore());
        sd.setErrorId(String.valueOf(gameSession.getErrorId()));
        GameSessionDocument saved = gameSessionMongoRepository.save(sd);
        gameSession.setId(Long.parseLong(saved.getId()));
        return gameSession;
    }
}