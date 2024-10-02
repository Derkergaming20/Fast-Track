package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoginPage {

    public static final String FILENAME = "users.txt";

    public void displayLogin(Scanner input) {
        String username;
        String password;

        clearScreen(); // Clear screen before login
        System.out.println("=== Login ===");
        System.out.print("Enter your username: ");
        username = input.nextLine();

        System.out.print("Enter your password: ");
        password = input.nextLine();

        if (checkCredentials(username, password)) {
            System.out.println("Login successful! Going to main screen...");
        } else {
            System.out.println("Incorrect username or password, please try again.");
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