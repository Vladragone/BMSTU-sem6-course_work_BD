package com.example.game.mongo.service;

import com.example.game.model.Profile;
import com.example.game.model.User;
import com.example.game.mongo.model.ProfileDocument;
import com.example.game.mongo.model.UserDocument;
import com.example.game.mongo.repository.ProfileMongoRepository;
import com.example.game.mongo.repository.UserMongoRepository;
import com.example.game.service.interfaces.IProfileService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class ProfileService implements IProfileService {

    private final ProfileMongoRepository profileMongoRepository;
    private final UserMongoRepository userMongoRepository;

    public ProfileService(ProfileMongoRepository profileMongoRepository,
                          UserMongoRepository userMongoRepository) {
        this.profileMongoRepository = profileMongoRepository;
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public Profile getProfile(String username) {
        Optional<UserDocument> userDocOpt = userMongoRepository.findByUsername(username);
        if (userDocOpt.isEmpty()) {
            return null;
        }
        UserDocument userDoc = userDocOpt.get();
        Optional<ProfileDocument> profileDocOpt = profileMongoRepository.findByUserId(userDoc.getId());
        if (profileDocOpt.isEmpty()) {
            return null;
        }
        return toProfile(profileDocOpt.get(), userDoc);
    }

    @Override
    public Map<String, Object> updateScore(Map<String, Integer> scoreMap, String username) {
        Optional<UserDocument> userDocOpt = userMongoRepository.findByUsername(username);
        if (userDocOpt.isEmpty()) {
            return Map.of("error", "Пользователь не найден");
        }
        UserDocument userDoc = userDocOpt.get();
        Optional<ProfileDocument> profileDocOpt = profileMongoRepository.findByUserId(userDoc.getId());
        if (profileDocOpt.isEmpty()) {
            return Map.of("error", "Профиль не найден");
        }
        ProfileDocument profileDoc = profileDocOpt.get();

        int additionalScore = scoreMap.getOrDefault("score", 0);
        profileDoc.setScore(profileDoc.getScore() + additionalScore);
        profileDoc.setGameNum(profileDoc.getGameNum() + 1);
        ProfileDocument saved = profileMongoRepository.save(profileDoc);

        return Map.of("totalScore", saved.getScore());
    }

    private Profile toProfile(ProfileDocument doc, UserDocument userDoc) {
        Profile profile = new Profile();
        profile.setId(doc.getId());

        User user = new User();
        user.setId(userDoc.getId());
        user.setUsername(userDoc.getUsername());
        user.setEmail(userDoc.getEmail());
        user.setPassword(userDoc.getPassword());
        user.setRole(userDoc.getRole());

        profile.setUser(user);
        profile.setScore(doc.getScore());
        profile.setRegDate(doc.getRegDate());
        profile.setGameNum(doc.getGameNum());
        return profile;
    }
}
