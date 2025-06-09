package com.example.game.persistence.postgres.repository;

import com.example.game.persistence.postgres.model.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface ProfileEntityRepository
        extends JpaRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByUserId(Long userId);
    List<ProfileEntity> findTop10ByOrderByScoreDesc();
}
