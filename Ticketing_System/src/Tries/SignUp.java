package Tries;

import java.io.*;
import java.util.Scanner;

public class SignUp {

    private static final String USER_FILE = "users.txt";

    // Method to handle sign-up functionality
    public static void handleSignUp(Scanner input) {
        System.out.print("Enter Username: ");
        String userName = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();

        if (signUp(userName, password)) {
            System.out.println("Sign up successful!");
        } else {
            System.out.println("Username already exists. Try a different one.");
        }
    }

    // Method to register a new user
    public static boolean signUp(String userName, String password) {
        try {
            // Check if user already exists
            if (Login.login(userName, password)) {
                return false;
            }

            // Append new user to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
                writer.write(userName + "," + password);
                writer.newLine();
                return true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
        return false;
    }
}
