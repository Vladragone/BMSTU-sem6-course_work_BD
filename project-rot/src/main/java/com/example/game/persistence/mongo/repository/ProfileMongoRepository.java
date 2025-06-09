package com.example.game.persistence.mongo.repository;

import com.example.game.model.Profile;
import com.example.game.persistence.mongo.document.ProfileDocument;
import com.example.game.repository.ProfileRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProfileMongoRepository implements ProfileRepository {
    private final ProfileDocumentRepository mongoRepo;

    public ProfileMongoRepository(ProfileDocumentRepository mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public Profile save(Profile profile) {
        ProfileDocument d = new ProfileDocument();
        BeanUtils.copyProperties(profile, d);
        d = mongoRepo.save(d);
        Profile out = new Profile();
        BeanUtils.copyProperties(d, out);
        return out;
    }

    @Override
    public Optional<Profile> findByUserId(Long userId) {
        return mongoRepo.findByUserId(userId)
            .map(d -> {
                Profile p = new Profile();
                BeanUtils.copyProperties(d, p);
                return p;
            });
    }

    @Override
    public List<Profile> findTop10ByScoreDesc() {
        return mongoRepo.findTop10ByOrderByScoreDesc().stream()
            .map(d -> {
                Profile p = new Profile();
                BeanUtils.copyProperties(d, p);
                return p;
            })
            .collect(Collectors.toList());
    }
}
