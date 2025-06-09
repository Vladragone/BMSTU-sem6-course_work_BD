package com.example.game.service.impl;

import com.example.game.dto.RegistrationRequest;
import com.example.game.model.Profile;
import com.example.game.model.User;
import com.example.game.repository.ProfileRepository;
import com.example.game.repository.UserRepository;
import com.example.game.service.api.IRegistrationService;
import com.example.game.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "postgres", matchIfMissing = true)
public class RegistrationServiceImpl implements IRegistrationService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final IUserService userService;

    public RegistrationServiceImpl(UserRepository userRepository,
                                   ProfileRepository profileRepository,
                                   IUserService userService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.userService = userService;
    }

    @Override
    public void register(RegistrationRequest request) {
        try {
            if (userService.existsByUsername(request.getUsername())) {
                throw new IllegalArgumentException("Пользователь с таким именем уже существует");
            }
            if (userService.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Пользователь с таким email уже существует");
            }

            String hashedPassword = hashPassword(request.getPassword());

            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(hashedPassword);
            user.setEmail(request.getEmail());
            user.setRole("user");

            User savedUser = userRepository.save(user);

            Profile profile = new Profile(savedUser.getId(), savedUser.getId(), 0, LocalDateTime.now(), 0);
            profileRepository.save(profile);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка при хешировании пароля", e);
        }
    }
}
