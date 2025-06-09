package com.example.game.persistence.mongo.repository;

import com.example.game.model.GameSession;
import com.example.game.persistence.mongo.document.GameSessionDocument;
import com.example.game.repository.GameSessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GameSessionMongoRepository implements GameSessionRepository {
    private final GameSessionDocumentRepository mongoRepo;

    public GameSessionMongoRepository(GameSessionDocumentRepository mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public GameSession save(GameSession session) {
        GameSessionDocument d = new GameSessionDocument();
        BeanUtils.copyProperties(session, d);
        d = mongoRepo.save(d);
        GameSession out = new GameSession();
        BeanUtils.copyProperties(d, out);
        return out;
    }

    @Override
    public List<GameSession> findLast5ByUserId(Long userId) {
        return mongoRepo.findTop5ByUserIdOrderByIdDesc(userId).stream()
            .map(d -> {
                GameSession s = new GameSession();
                BeanUtils.copyProperties(d, s);
                return s;
            })
            .collect(Collectors.toList());
    }
}
