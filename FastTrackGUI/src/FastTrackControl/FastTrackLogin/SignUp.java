package FastTrackControl.FastTrackLogin;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class SignUp {

    private static final String USER_FILE = "users.txt";
    private static final String ADMIN_USERNAME = "admin";  // Set admin username
    private static final String ADMIN_PASSWORD = "admin123";  // Set admin password
    private static final Random RANDOM = new Random();

    // Method to handle sign-up functionality
    public static void handleSignUp(Scanner input) {
        System.out.print("Enter Username: ");
        String userName = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();

        // If admin is registering, assign admin role, otherwise assign User role
        String role = "User";  // Default role is User
        if (userName.equals(ADMIN_USERNAME)) {
            role = "Admin";  // Sole admin role
        }

        // Generate unique userId
        String userId = generateUserId();

        if (signUp(userName, password, role, userId)) {
            System.out.println("Sign up successful!");
        } else {
            System.out.println("Username already exists. Try a different one.");
        }
    }

    // Method to generate a unique userId
    private static String generateUserId() {
        int userNumber = RANDOM.nextInt(1000);  // Generate a random number for userId
        return String.format("%03d", userNumber);  // Format as 3 digits, e.g., "001"
    }

    // Method to register a new user
    public static boolean signUp(String userName, String password, String role, String userId) {
        // Check if the username already exists
        if (userExists(userName)) {
            return false;
        }

        // Append new user to the file with the role and userId
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(userId + "," + userName + "," + password + "," + role);  // Store userId in the file
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
                if (credentials[1].equals(userName)) {  // Check username, not userId
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while checking user existence.");
            e.printStackTrace();
        }
        return false;
    }

    // Method for Admin to change a user's role
    public static void changeUserRole(String adminUsername, String targetUsername, String newRole) {
        if (!adminUsername.equals(ADMIN_USERNAME)) {
            System.out.println("Only the admin can change user roles.");
            return;
        }

        // Modify the role of the specified user
        try {
            File inputFile = new File(USER_FILE);
            File tempFile = new File("temp_users.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[1].equals(targetUsername)) {
                    // Update the user's role
                    line = credentials[0] + "," + credentials[1] + "," + credentials[2] + "," + newRole;
                }
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            // Delete the original file and rename the temp file
            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            } else {
                System.out.println("Error updating the user roles.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while changing the user role.");
            e.printStackTrace();
        }
    }

    // Method to display all users (For testing purposes, can be removed later)
    public static void displayUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                System.out.println("UserID: " + credentials[0] + " | Username: " + credentials[1] + " | Role: " + credentials[3]);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while displaying users.");
            e.printStackTrace();
        }
    }

    // Main method for testing (You can remove this part later when integrating into a larger system)
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Simulate the signup process
        handleSignUp(input);
        
        // Display users
        displayUsers();

        // Example of admin changing a user's role
        changeUserRole(ADMIN_USERNAME, "john", "Admin");
        
        // Display users again to see the updated roles
        displayUsers();

        input.close();
    }
}
