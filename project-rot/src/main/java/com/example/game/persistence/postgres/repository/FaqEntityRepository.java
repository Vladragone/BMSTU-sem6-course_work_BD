package com.example.game.persistence.postgres.repository;

import com.example.game.persistence.postgres.model.FaqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqEntityRepository
        extends JpaRepository<FaqEntity, Long> {
}
