package com.example.game.service.api;

import com.example.game.dto.RegistrationRequest;
import com.example.game.model.User;

import java.util.Optional;

public interface IUserService {
    User register(RegistrationRequest req);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
