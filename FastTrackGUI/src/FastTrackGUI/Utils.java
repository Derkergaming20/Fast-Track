package FastTrackGUI;

import java.io.*;
import java.util.*;

public class Utils {
    private static final String USERS_FILE = "users.txt"; // File to store user data

    // Encrypt password (example only; replace with actual encryption logic)
    public static String encryptPassword(String password) {
        // Simple placeholder encryption: Reverse the string
        return new StringBuilder(password).reverse().toString();
    }

    // Generate a unique user ID
    public static String generateUserId() {
        return UUID.randomUUID().toString();
    }

    // Sign up a user by saving their details to a file
    public static boolean signUp(String username, String encryptedPassword, String role, String userId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            if (isUserExists(username)) {
                return false; // User already exists
            }

            // Write user details to the file: username|password|role|userId
            writer.write(username + "|" + encryptedPassword + "|" + role + "|" + userId);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if a user already exists in the file
    private static boolean isUserExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(username)) {
                    return true; // Username found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Login by checking username and encrypted password
    public static String[] login(String username, String encryptedPassword) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(username) && parts[1].equals(encryptedPassword)) {
                    return parts; // Return user details
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Invalid username or password
    }

    // Display all users (for admin purposes)
    public static void displayUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            System.out.println("User List:");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                System.out.println("Username: " + parts[0] + ", Role: " + parts[2] + ", UserID: " + parts[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Modify a user's role
    public static boolean modifyUserRole(String username, String newRole) {
        File tempFile = new File("temp_users.txt");
        File originalFile = new File(USERS_FILE);
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(username)) {
                    // Modify the role
                    parts[2] = newRole;
                    found = true;
                }
                writer.write(String.join("|", parts));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Replace the original file with the updated one
        if (tempFile.renameTo(originalFile)) {
            return found;
        } else {
            return false;
        }
    }
}