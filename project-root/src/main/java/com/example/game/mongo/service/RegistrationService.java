package com.example.game.mongo.service;

import com.example.game.dto.RegistrationRequest;
import com.example.game.mongo.model.ProfileDocument;
import com.example.game.mongo.model.UserDocument;
import com.example.game.mongo.repository.ProfileMongoRepository;
import com.example.game.mongo.repository.UserMongoRepository;
import com.example.game.service.interfaces.IRegistrationService;
import com.example.game.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class RegistrationService implements IRegistrationService {

    private final UserMongoRepository userMongoRepository;
    private final ProfileMongoRepository profileMongoRepository;
    private final IUserService userService;

    @Autowired
    public RegistrationService(UserMongoRepository userMongoRepository,
                               ProfileMongoRepository profileMongoRepository,
                               IUserService userService) {
        this.userMongoRepository = userMongoRepository;
        this.profileMongoRepository = profileMongoRepository;
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
            UserDocument ud = new UserDocument();
            ud.setUsername(request.getUsername());
            ud.setPassword(hashedPassword);
            ud.setEmail(request.getEmail());
            ud.setRole("user");
            UserDocument savedUser = userMongoRepository.save(ud);

            ProfileDocument pd = new ProfileDocument();
            pd.setUserId(savedUser.getId());
            pd.setRegDate(LocalDateTime.now());
            pd.setGameNum(0);
            profileMongoRepository.save(pd);
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