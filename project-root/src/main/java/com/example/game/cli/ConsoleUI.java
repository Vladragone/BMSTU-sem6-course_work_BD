package com.example.game.cli;

import org.springframework.stereotype.Component;

@Component
public class ConsoleUI {

    private final AuthManager authManager;
    private final RegistrationManager registrationManager;
    private final GameManager gameManager;

    public ConsoleUI(AuthManager authManager, RegistrationManager registrationManager, GameManager gameManager) {
        this.authManager = authManager;
        this.registrationManager = registrationManager;
        this.gameManager = gameManager;
    }

    public void start() {
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Play");
            System.out.print("Enter your choice (1, 2, 3): ");
            String input = new java.util.Scanner(System.in).nextLine();

            switch (input) {
                case "1":
                    authManager.authenticate();
                    break;
                case "2":
                    registrationManager.registerUser();
                    break;
                case "3":
                    gameManager.startGame();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
}
