package com.example.game.persistence.postgres.repository;

import com.example.game.persistence.postgres.model.GameErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameErrorEntityRepository
        extends JpaRepository<GameErrorEntity, Long> {
}
