package com.example.game.mongo.service;

import com.example.game.model.User;
import com.example.game.mongo.model.UserDocument;
import com.example.game.mongo.repository.UserMongoRepository;
import com.example.game.service.interfaces.IAuthService;
import com.example.game.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class AuthService implements IAuthService {

    private final UserMongoRepository userMongoRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserMongoRepository userMongoRepository, JwtUtil jwtUtil) {
        this.userMongoRepository = userMongoRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Optional<String> authenticate(String username, String password) {
        Optional<UserDocument> doc = userMongoRepository.findByUsername(username);
        if (doc.isEmpty()) return Optional.empty();
        UserDocument ud = doc.get();
        if (!ud.getPassword().equals(password)) return Optional.empty();
        User u = new User();
        u.setId(Long.parseLong(ud.getId()));
        u.setUsername(ud.getUsername());
        u.setRole(ud.getRole());
        return Optional.of(jwtUtil.generateToken(u));
    }
}