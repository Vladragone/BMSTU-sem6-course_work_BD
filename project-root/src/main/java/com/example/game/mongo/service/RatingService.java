package com.example.game.mongo.service;

import com.example.game.dto.RatingResponse;
import com.example.game.model.Profile;
import com.example.game.model.User;
import com.example.game.mongo.model.ProfileDocument;
import com.example.game.mongo.model.UserDocument;
import com.example.game.mongo.repository.ProfileMongoRepository;
import com.example.game.mongo.repository.UserMongoRepository;
import com.example.game.service.interfaces.IRatingService;
import com.example.game.service.interfaces.ITokenParser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class RatingService implements IRatingService {

    private final ProfileMongoRepository profileMongoRepository;
    private final UserMongoRepository userMongoRepository;
    private final ITokenParser tokenParser;

    public RatingService(ProfileMongoRepository profileMongoRepository,
                         UserMongoRepository userMongoRepository,
                         ITokenParser tokenParser) {
        this.profileMongoRepository = profileMongoRepository;
        this.userMongoRepository = userMongoRepository;
        this.tokenParser = tokenParser;
    }

    @Override
    @Transactional(readOnly = true)
    public RatingResponse getSortedRatingAndRank(String token, String sortBy) {
        String currentUsername = tokenParser.getUsername(token);

        List<ProfileDocument> docs = profileMongoRepository.findAll();
        List<Profile> profiles = docs.stream()
            .map(this::toProfile)
            .collect(Collectors.toList());

        Comparator<Profile> comparator = getComparator(sortBy);
        List<Profile> sortedProfiles = profiles.stream()
            .sorted(comparator.reversed())
            .collect(Collectors.toList());

        List<Profile> top = sortedProfiles.stream()
            .limit(3)
            .collect(Collectors.toList());

        int rank = findUserRank(sortedProfiles, currentUsername);

        return new RatingResponse(top, rank, sortBy);
    }

    private Comparator<Profile> getComparator(String sortBy) {
        return "games".equals(sortBy)
            ? Comparator.comparingInt(Profile::getGameNum)
            : Comparator.comparingInt(Profile::getScore);
    }

    private int findUserRank(List<Profile> profiles, String username) {
        return profiles.stream()
            .map(profile -> profile.getUser().getUsername())
            .toList()
            .indexOf(username) + 1;
    }

    private Profile toProfile(ProfileDocument doc) {
        Optional<UserDocument> userDocOpt = userMongoRepository.findById(doc.getUserId());
        if (userDocOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "User not found for profile: " + doc.getUserId());
        }
        UserDocument userDoc = userDocOpt.get();

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
