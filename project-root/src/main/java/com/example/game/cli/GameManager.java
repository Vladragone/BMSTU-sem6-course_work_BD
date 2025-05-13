package com.example.game.cli;

import com.example.game.model.Location;
import com.example.game.service.LocationService;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GameManager {

    private final LocationService locationService;

    public GameManager(LocationService locationService) {
        this.locationService = locationService;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        List<Location> allLocations = locationService.getAllLocations();
        List<String> uniqueNames = allLocations.stream()
            .map(Location::getName)
            .distinct()
            .toList();

        System.out.println("Choose a location:");
        for (int i = 0; i < uniqueNames.size(); i++) {
            System.out.println((i + 1) + " - " + uniqueNames.get(i));
        }

        System.out.print("Enter the location number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > uniqueNames.size()) {
            System.out.println("Invalid choice. Try again.");
            return;
        }

        String selectedName = uniqueNames.get(choice - 1);
        Location selectedLocation = allLocations.stream()
            .filter(loc -> loc.getName().equals(selectedName))
            .findFirst()
            .orElseThrow();

        System.out.println("You chose: " + selectedLocation.getName());

        System.out.print("Enter coordinates (lat lng): ");
        String[] coords = scanner.nextLine().split("\\s+");

        if (coords.length != 2) {
            System.out.println("Invalid input. Expected two numbers.");
            return;
        }

        try {
            double userLat = Double.parseDouble(coords[0]);
            double userLng = Double.parseDouble(coords[1]);

            System.out.println("Original coordinates: " + selectedLocation.getLat() + " " + selectedLocation.getLng());
            System.out.println("Your coordinates: " + userLat + " " + userLng);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        }
}

}
