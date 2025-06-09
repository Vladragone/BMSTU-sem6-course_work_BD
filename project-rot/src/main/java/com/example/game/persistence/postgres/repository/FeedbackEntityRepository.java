package com.example.game.persistence.postgres.repository;

import com.example.game.persistence.postgres.model.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackEntityRepository
        extends JpaRepository<FeedbackEntity, Long> {
}
