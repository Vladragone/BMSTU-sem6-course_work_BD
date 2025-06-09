package com.example.game.persistence.postgres.repository;

import com.example.game.persistence.postgres.model.GameSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameSessionEntityRepository
        extends JpaRepository<GameSessionEntity, Long> {
    List<GameSessionEntity> findTop5ByUserIdOrderByIdDesc(Long userId);
}
