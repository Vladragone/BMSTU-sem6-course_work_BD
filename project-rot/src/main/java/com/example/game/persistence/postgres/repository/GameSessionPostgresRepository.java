package com.example.game.persistence.postgres.repository;

import com.example.game.model.GameSession;
import com.example.game.persistence.postgres.model.GameSessionEntity;
import com.example.game.repository.GameSessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GameSessionPostgresRepository implements GameSessionRepository {
    private final GameSessionEntityRepository jpaRepo;

    public GameSessionPostgresRepository(GameSessionEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public GameSession save(GameSession session) {
        GameSessionEntity e = new GameSessionEntity();
        BeanUtils.copyProperties(session, e);
        e = jpaRepo.save(e);
        GameSession out = new GameSession();
        BeanUtils.copyProperties(e, out);
        return out;
    }

    @Override
    public List<GameSession> findLast5ByUserId(Long userId) {
        return jpaRepo.findTop5ByUserIdOrderByIdDesc(userId).stream()
            .map(e -> {
                GameSession s = new GameSession();
                BeanUtils.copyProperties(e, s);
                return s;
            })
            .collect(Collectors.toList());
    }
}
