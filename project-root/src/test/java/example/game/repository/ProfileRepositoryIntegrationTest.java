package com.example.game.repository;

import com.example.game.model.Profile;
import com.example.game.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class ProfileRepositoryIntegrationTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindByUserUsername() {
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("test@test.com");
        user.setPassword("password");
        User savedUser = userRepository.save(user);

        Profile profile = new Profile(savedUser, LocalDateTime.now());
        profile.setScore(100);
        profileRepository.save(profile);

        Optional<Profile> fetchedProfile = profileRepository.findByUserUsername("testUser");
        assertThat(fetchedProfile).isPresent();
        assertThat(fetchedProfile.get().getUser().getUsername()).isEqualTo("testUser");
        assertThat(fetchedProfile.get().getScore()).isEqualTo(100);
    }
}
