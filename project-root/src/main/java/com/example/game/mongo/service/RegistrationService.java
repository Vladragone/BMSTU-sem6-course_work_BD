package com.example.game.mongo.service;

import com.example.game.dto.RegistrationRequest;
import com.example.game.mongo.model.ProfileDocument;
import com.example.game.mongo.model.UserDocument;
import com.example.game.mongo.repository.ProfileMongoRepository;
import com.example.game.mongo.repository.UserMongoRepository;
import com.example.game.service.interfaces.IRegistrationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class RegistrationService implements IRegistrationService {

    private final UserMongoRepository userMongoRepository;
    private final ProfileMongoRepository profileMongoRepository;

    public RegistrationService(UserMongoRepository userMongoRepository,
                               ProfileMongoRepository profileMongoRepository) {
        this.userMongoRepository = userMongoRepository;
        this.profileMongoRepository = profileMongoRepository;
    }

    @Override
    public void register(RegistrationRequest request) {
        try {
            if (userMongoRepository.existsByUsername(request.getUsername())) {
                throw new IllegalArgumentException("Пользователь с таким именем уже существует");
            }

            if (userMongoRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Пользователь с таким email уже существует");
            }

            String hashedPassword = hashPassword(request.getPassword());

            Long userId = getNextUserId();
            UserDocument userDoc = new UserDocument(
                userId,
                request.getUsername(),
                request.getEmail(),
                hashedPassword,
                "user"
            );
            UserDocument savedUser = userMongoRepository.save(userDoc);

            Long profileId = getNextProfileId();
            ProfileDocument profileDoc = new ProfileDocument(
                profileId,
                savedUser.getId(),
                LocalDateTime.now()
            );
            profileMongoRepository.save(profileDoc);

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

    private Long getNextUserId() {
        Optional<Long> maxId = userMongoRepository.findAll()
            .stream()
            .map(UserDocument::getId)
            .max(Comparator.naturalOrder());
        return maxId.map(id -> id + 1).orElse(1L);
    }

    private Long getNextProfileId() {
        Optional<Long> maxId = profileMongoRepository.findAll()
            .stream()
            .map(ProfileDocument::getId)
            .max(Comparator.naturalOrder());
        return maxId.map(id -> id + 1).orElse(1L);
    }
}
