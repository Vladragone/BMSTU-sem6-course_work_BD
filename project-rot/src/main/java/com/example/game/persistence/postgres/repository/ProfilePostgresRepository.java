package com.example.game.persistence.postgres.repository;

import com.example.game.model.Profile;
import com.example.game.persistence.postgres.model.ProfileEntity;
import com.example.game.repository.ProfileRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProfilePostgresRepository implements ProfileRepository {
    private final ProfileEntityRepository jpaRepo;

    public ProfilePostgresRepository(ProfileEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Profile save(Profile profile) {
        ProfileEntity e = new ProfileEntity();
        BeanUtils.copyProperties(profile, e);
        e = jpaRepo.save(e);
        Profile out = new Profile();
        BeanUtils.copyProperties(e, out);
        return out;
    }

    @Override
    public Optional<Profile> findByUserId(Long userId) {
        return jpaRepo.findByUserId(userId)
            .map(e -> {
                Profile p = new Profile();
                BeanUtils.copyProperties(e, p);
                return p;
            });
    }

    @Override
    public List<Profile> findTop10ByScoreDesc() {
        return jpaRepo.findTop10ByOrderByScoreDesc().stream()
            .map(e -> {
                Profile p = new Profile();
                BeanUtils.copyProperties(e, p);
                return p;
            })
            .collect(Collectors.toList());
    }
}
