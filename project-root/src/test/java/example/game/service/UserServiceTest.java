package com.example.game.service;

import com.example.game.dto.UserRegistrationRequest;
import com.example.game.model.User;
import com.example.game.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_Success() {
        // Используем конструктор с параметрами
        UserRegistrationRequest request = new UserRegistrationRequest("test", "test@test.com", "pass");

        when(userRepository.save(any())).thenReturn(new User());

        User result = service.register(request);
        assertNotNull(result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void existsByEmail_True() {
        when(userRepository.existsByEmail("test@test.com")).thenReturn(true);
        assertTrue(service.existsByEmail("test@test.com"));
    }

    @Test
    void findUserByUsername_Exists() {
        User user = new User();
        user.setUsername("test");
        when(userRepository.findByUsername("test")).thenReturn(Optional.of(user));

        User result = service.findUserByUsername("test");
        assertNotNull(result);
        assertEquals("test", result.getUsername());
    }
}
