package com.example.game.mongo.service;

import com.example.game.dto.UserRegistrationRequest;
import com.example.game.model.User;
import com.example.game.mongo.model.UserDocument;
import com.example.game.mongo.repository.UserMongoRepository;
import com.example.game.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class UserService implements IUserService {

    private final UserMongoRepository userMongoRepository;

    @Autowired
    public UserService(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public User register(UserRegistrationRequest request) {
        UserDocument ud = new UserDocument();
        ud.setUsername(request.getUsername());
        ud.setPassword(request.getPassword());
        ud.setEmail(request.getEmail());
        ud.setRole("user");
        UserDocument saved = userMongoRepository.save(ud);
        User u = new User();
        u.setId(Long.parseLong(saved.getId()));
        u.setUsername(saved.getUsername());
        u.setEmail(saved.getEmail());
        u.setPassword(saved.getPassword());
        u.setRole(saved.getRole());
        return u;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userMongoRepository.existsByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMongoRepository.findByUsername(username)
                .map(ud -> {
                    User u = new User();
                    u.setId(Long.parseLong(ud.getId()));
                    u.setUsername(ud.getUsername());
                    u.setEmail(ud.getEmail());
                    u.setPassword(ud.getPassword());
                    u.setRole(ud.getRole());
                    return u;
                })
                .orElse(null);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMongoRepository.existsByUsername(username);
    }
}