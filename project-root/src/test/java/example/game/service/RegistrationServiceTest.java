package com.example.game.service;

import com.example.game.dto.RegistrationRequest;
import com.example.game.repository.ProfileRepository;
import com.example.game.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_Success() {
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("test");
        request.setEmail("test@test.com");
        request.setPassword("password");

        registrationService.register(request);

        verify(userRepository).save(any());
        verify(profileRepository).save(any());
    }
}