 package FastTrackControl;

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
        // Check if the username already exists
        if (userExists(userName)) {
            return false;
        }

        // Append new user to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(userName + "," + password);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
        return false;
    }

    // Check if the username already exists
    private static boolean userExists(String userName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(userName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while checking user existence.");
            e.printStackTrace();
        }
        return false;
    }
}
