package com.example.game.mongo.service;

import com.example.game.dto.RatingResponse;
import com.example.game.mongo.model.GameSessionDocument;
import com.example.game.mongo.repository.GameSessionMongoRepository;
import com.example.game.service.interfaces.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class RatingService implements IRatingService {

    private final GameSessionMongoRepository gameSessionMongoRepository;

    @Autowired
    public RatingService(GameSessionMongoRepository gameSessionMongoRepository) {
        this.gameSessionMongoRepository = gameSessionMongoRepository;
    }

    @Override
    public List<RatingResponse> getTopRatings() {
        List<GameSessionDocument> sessions = gameSessionMongoRepository.findAll();
        return sessions.stream()
            .map(s -> new RatingResponse(
                Long.parseLong(s.getUserId()),
                s.getEarnedScore()
            ))
            .sorted((r1, r2) -> Integer.compare(r2.getScore(), r1.getScore()))
            .limit(10)
            .collect(Collectors.toList());
    }
}