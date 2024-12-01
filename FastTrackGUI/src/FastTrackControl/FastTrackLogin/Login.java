package FastTrackControl.FastTrackLogin;

import java.io.*;
import java.util.Scanner;

public class Login {

    private static final String USER_FILE = "users.txt";

    // Method to handle login functionality and return userId
    public static String handleLogin(Scanner input) {
        System.out.print("Enter Username: ");
        String userName = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();

        String userId = login(userName, password);
        if (userId != null) {
            System.out.println("Login Successful!");
            return userId;
        } else {
            System.out.println("Invalid Username or Password.");
            return null;
        }
    }

    // Method to check login credentials and return userId (username as userId)
    public static String login(String userName, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[1].equals(userName) && credentials[2].equals(password)) {
                    return credentials[0]; // Returning the userName as userId
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return null;
    }
}
