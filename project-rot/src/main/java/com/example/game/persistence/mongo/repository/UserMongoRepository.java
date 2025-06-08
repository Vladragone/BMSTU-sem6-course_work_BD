package com.example.game.persistence.mongo.repository;

import com.example.example.game.model.User;
import com.example.game.persistence.mongo.document.UserDocument;
import com.example.game.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserMongoRepository implements UserRepository {
    private final UserDocumentRepository mongoRepo;

    public UserMongoRepository(UserDocumentRepository mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public User save(User user) {
        UserDocument doc = new UserDocument();
        BeanUtils.copyProperties(user, doc);
        doc = mongoRepo.save(doc);
        User out = new User();
        BeanUtils.copyProperties(doc, out);
        return out;
    }

    @Override
    public Optional<User> findById(Long id) {
        return mongoRepo.findById(id)
            .map(d -> {
                User u = new User();
                BeanUtils.copyProperties(d, u);
                return u;
            });
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return mongoRepo.findByUsername(username)
            .map(d -> {
                User u = new User();
                BeanUtils.copyProperties(d, u);
                return u;
            });
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return mongoRepo.findByEmail(email)
            .map(d -> {
                User u = new User();
                BeanUtils.copyProperties(d, u);
                return u;
            });
    }

    @Override
    public boolean existsByUsername(String username) {
        return mongoRepo.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return mongoRepo.existsByEmail(email);
    }
}
