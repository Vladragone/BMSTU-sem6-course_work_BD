package com.example.game.mongo.service;

import com.example.game.dto.UserRegistrationRequest;
import com.example.game.model.User;
import com.example.game.mongo.model.UserDocument;
import com.example.game.mongo.repository.UserMongoRepository;
import com.example.game.service.interfaces.IUserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class UserService implements IUserService {

    private final UserMongoRepository userMongoRepository;

    public UserService(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public User register(UserRegistrationRequest request) {
        Long userId = getNextUserId();
        UserDocument userDoc = new UserDocument(
            userId,
            request.getUsername(),
            request.getEmail(),
            request.getPassword(),
            "user"
        );
        UserDocument savedDoc = userMongoRepository.save(userDoc);
        return toUser(savedDoc);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userMongoRepository.existsByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMongoRepository.findByUsername(username)
            .map(this::toUser)
            .orElse(null);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMongoRepository.existsByUsername(username);
    }

    private User toUser(UserDocument doc) {
        User user = new User();
        user.setId(doc.getId());
        user.setUsername(doc.getUsername());
        user.setEmail(doc.getEmail());
        user.setPassword(doc.getPassword());
        user.setRole(doc.getRole());
        return user;
    }

    private Long getNextUserId() {
        Optional<Long> maxId = userMongoRepository.findAll()
            .stream()
            .map(UserDocument::getId)
            .max(Comparator.naturalOrder());
        return maxId.map(id -> id + 1).orElse(1L);
    }
}
