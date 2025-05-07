package com.example.game.repository;

import com.example.game.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindByUsername() {
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john@example.com");
        user.setPassword("secret");
        userRepository.save(user);

        Optional<User> found = userRepository.findByUsername("john_doe");
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("john@example.com");
    }

    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setUsername("jane_doe");
        user.setEmail("jane@example.com");
        user.setPassword("password");
        userRepository.save(user);

        Optional<User> found = userRepository.findByEmail("jane@example.com");
        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("jane_doe");
    }

    @Test
    public void testExistsByEmail() {
        User user = new User();
        user.setUsername("mark_twain");
        user.setEmail("mark@example.com");
        user.setPassword("password");
        userRepository.save(user);

        boolean exists = userRepository.existsByEmail("mark@example.com");
        assertThat(exists).isTrue();
    }
}
