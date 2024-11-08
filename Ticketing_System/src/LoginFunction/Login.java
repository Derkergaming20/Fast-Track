package LoginFunction;

import java.io.*;
import java.util.Scanner;

public class Login {

    private static final String USER_FILE = "users.txt";

    // Method to handle login functionality
    public static void handleLogin(Scanner input) {
        System.out.print("Enter Username: ");
        String userName = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();

        if (login(userName, password)) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Invalid Username or Password.");
        }
    }

    // Method to check login credentials
    public static boolean login(String userName, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(userName) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return false;
    }
}
