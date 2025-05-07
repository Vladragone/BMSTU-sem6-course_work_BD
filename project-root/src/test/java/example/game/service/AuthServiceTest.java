package com.example.game.service;

import com.example.game.dto.LoginRequest;
import com.example.game.model.User;
import com.example.game.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticateUser_Success() {
        LoginRequest request = new LoginRequest();
        request.setUsername("test");
        request.setPassword("pass");
        
        User user = new User();
        user.setUsername("test");
        user.setPassword(authService.hashPassword("pass"));
        user.setRole("user");

        when(userRepository.findByUsername("test")).thenReturn(Optional.of(user));

        ResponseEntity<?> response = authService.authenticateUser(request);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertNotNull(((Map<?, ?>) response.getBody()).get("token"));
    }

    @Test
    void authenticateUser_WrongPassword() {
        LoginRequest request = new LoginRequest();
        request.setUsername("test");
        request.setPassword("wrong");
        
        User user = new User();
        user.setUsername("test");
        user.setPassword(authService.hashPassword("correct"));

        when(userRepository.findByUsername("test")).thenReturn(Optional.of(user));

        Exception exception = assertThrows(ResponseStatusException.class,
            () -> authService.authenticateUser(request));
        
        assertEquals(HttpStatus.UNAUTHORIZED, ((ResponseStatusException) exception).getStatusCode());
        assertTrue(exception.getMessage().contains("Неверный пароль"));
    }

    @Test
    void hashPassword_Consistent() {
        String hash1 = authService.hashPassword("test");
        String hash2 = authService.hashPassword("test");
        assertEquals(hash1, hash2);
    }
}