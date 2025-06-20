package com.example.game.persistence.postgres.repository;

import com.example.game.persistence.postgres.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository
        extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
