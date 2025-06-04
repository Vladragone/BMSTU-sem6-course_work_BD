package com.example.game.mongo.service;

import com.example.game.model.Profile;
import com.example.game.mongo.model.ProfileDocument;
import com.example.game.mongo.repository.ProfileMongoRepository;
import com.example.game.service.interfaces.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class ProfileService implements IProfileService {

    private final ProfileMongoRepository profileMongoRepository;

    @Autowired
    public ProfileService(ProfileMongoRepository profileMongoRepository) {
        this.profileMongoRepository = profileMongoRepository;
    }

    @Override
    public Optional<Profile> findById(Long id) {
        Optional<ProfileDocument> doc = profileMongoRepository.findById(String.valueOf(id));
        if (doc.isEmpty()) return Optional.empty();
        ProfileDocument pd = doc.get();
        Profile p = new Profile();
        p.setId(Long.parseLong(pd.getId()));
        p.setUserId(Long.parseLong(pd.getUserId()));
        p.setRegDate(pd.getRegDate());
        p.setGameNum(pd.getGameNum());
        return Optional.of(p);
    }

    @Override
    public Profile save(Profile profile) {
        ProfileDocument pd = new ProfileDocument();
        pd.setId(profile.getId() == null ? null : String.valueOf(profile.getId()));
        pd.setUserId(String.valueOf(profile.getUserId()));
        pd.setRegDate(profile.getRegDate());
        pd.setGameNum(profile.getGameNum());
        ProfileDocument saved = profileMongoRepository.save(pd);
        profile.setId(Long.parseLong(saved.getId()));
        return profile;
    }

    @Override
    public void deleteById(Long id) {
        profileMongoRepository.deleteById(String.valueOf(id));
    }

    @Override
    public List<Profile> findAll() {
        return profileMongoRepository.findAll()
            .stream()
            .map(pd -> {
                Profile p = new Profile();
                p.setId(Long.parseLong(pd.getId()));
                p.setUserId(Long.parseLong(pd.getUserId()));
                p.setRegDate(pd.getRegDate());
                p.setGameNum(pd.getGameNum());
                return p;
            })
            .collect(Collectors.toList());
    }
}