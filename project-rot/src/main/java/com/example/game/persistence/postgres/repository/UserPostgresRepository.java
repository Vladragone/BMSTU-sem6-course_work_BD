package com.example.game.persistence.postgres.repository;

import com.example.game.model.User;
import com.example.game.persistence.postgres.model.UserEntity;
import com.example.game.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserPostgresRepository implements UserRepository {
    private final UserEntityRepository jpaRepo;

    public UserPostgresRepository(UserEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(user, entity);
        entity = jpaRepo.save(entity);
        User out = new User();
        BeanUtils.copyProperties(entity, out);
        return out;
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepo.findById(id)
            .map(e -> {
                User u = new User();
                BeanUtils.copyProperties(e, u);
                return u;
            });
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepo.findByUsername(username)
            .map(e -> {
                User u = new User();
                BeanUtils.copyProperties(e, u);
                return u;
            });
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepo.findByEmail(email)
            .map(e -> {
                User u = new User();
                BeanUtils.copyProperties(e, u);
                return u;
            });
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepo.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepo.existsByEmail(username);
    }
}
