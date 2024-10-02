package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RegisterPage {

    public static final String FILENAME = "users.txt";

    public void displayRegistration(Scanner input) {
        String username;
        String password;

        while (true) { // Loop until a valid username is entered
            clearScreen(); // Clear screen before registration
            System.out.println("=== Registration ===");
            System.out.print("Enter a username: ");
            username = input.nextLine();

            // Check if username already exists
            if (checkCredentials(username, "")) {
                System.out.println("Username already exists. Please choose a different one.");
            } else {
                System.out.print("Enter a password: ");
                password = input.nextLine();
                registerUser(username, password);
                System.out.println("Registration successful!");
                break; // Exit the loop after successful registration
            }
        }

        // Pause to allow user to see the result before clearing the screen again
        System.out.println("\nPress Enter to continue...");
        input.nextLine(); // Wait for Enter
    }

    private boolean checkCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(" ");
                String fileUsername = credentials[0];
                String filePassword = credentials[1];

                // Check if the entered credentials match any in the file
                if (username.equals(fileUsername) && password.equals(filePassword)) {
                    return true; // Credentials match
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to open file: " + e.getMessage());
            System.exit(1);
        }
        return false; // No match found
    }

    private void registerUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            writer.write(username + " " + password);
            writer.newLine(); // Save username and password
        } catch (IOException e) {
            System.out.println("Unable to open file: " + e.getMessage());
            System.exit(1);
        }
    }

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Error clearing screen: " + e.getMessage());
        }
    }
}
