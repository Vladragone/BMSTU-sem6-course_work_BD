package com.example.game.cli;

import com.example.game.service.AuthService;
import com.example.game.dto.LoginRequest;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class AuthManager {

    private final AuthService authService;

    public AuthManager(AuthService authService) {
        this.authService = authService;
    }

    public void authenticate() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        try {
            var response = authService.authenticateUser(loginRequest);
            System.out.println("Authentication successful!");
            System.out.println(response.getBody());
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }
    }
}
