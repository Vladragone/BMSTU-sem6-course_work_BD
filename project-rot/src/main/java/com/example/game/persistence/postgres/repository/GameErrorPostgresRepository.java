package com.example.game.persistence.postgres.repository;

import com.example.game.model.GameError;
import com.example.game.persistence.postgres.model.GameErrorEntity;
import com.example.game.repository.GameErrorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GameErrorPostgresRepository implements GameErrorRepository {
    private final GameErrorEntityRepository jpaRepo;

    public GameErrorPostgresRepository(GameErrorEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<GameError> findAll() {
        return jpaRepo.findAll().stream()
            .map(e -> {
                GameError ge = new GameError();
                BeanUtils.copyProperties(e, ge);
                return ge;
            })
            .collect(Collectors.toList());
    }
}
