package com.example.game.service.impl;

import com.example.game.dto.RegistrationRequest;
import com.example.game.model.User;
import com.example.game.repository.UserRepository;
import com.example.game.service.api.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User register(RegistrationRequest req) {
        if (repo.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("Username taken");
        }
        if (repo.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email taken");
        }
        User user = new User();
        BeanUtils.copyProperties(req, user);
        user.setRole("user");
        return repo.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
