package com.example.game.mongo.service;

import com.example.game.dto.LoginRequest;
import com.example.game.mongo.model.UserDocument;
import com.example.game.mongo.repository.UserMongoRepository;
import com.example.game.service.interfaces.IAuthService;
import com.example.game.util.JwtUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class AuthService implements IAuthService {

    private final UserMongoRepository userMongoRepository;

    public AuthService(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Optional<UserDocument> userOptional = userMongoRepository.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            UserDocument user = userOptional.get();
            String hashedInputPassword = hashPassword(loginRequest.getPassword());

            if (user.getPassword().equals(hashedInputPassword)) {
                // Здесь передаём ID как Long
                String token = JwtUtil.generateToken(
                    user.getUsername(),
                    user.getRole(),
                    user.getId()
                );
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Authentication successful");
                response.put("token", token);
                return ResponseEntity.ok(response);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
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
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
