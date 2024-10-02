package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice;

        Scanner input = new Scanner(System.in);
        while (true) {
            clearScreen(); // Clear the screen on each iteration
            System.out.println("1. Register\n2. Login\n3. Exit");
            System.out.print("Choose an option: ");
            choice = input.nextInt();
            input.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    RegisterPage registerPage = new RegisterPage();
                    registerPage.displayRegistration(input); // Call the registration method
                    break;
                case 2:
                    LoginPage loginPage = new LoginPage();
                    loginPage.displayLogin(input); // Call the displayLogin method
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void clearScreen() {
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