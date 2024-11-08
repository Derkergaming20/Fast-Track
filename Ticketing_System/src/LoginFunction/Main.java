package LoginFunction;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("----Welcome to Fastrack----");
            System.out.println("(1) Login\n(2) Sign Up\n(3) Exit");
            System.out.print("Enter Choice: ");
            int choice = input.nextInt();
            input.nextLine();  // Consume the newline character left by nextInt()

            switch (choice) {
                case 1:
                    // Call the handleLogin method from Login class
                    Login.handleLogin(input);
                    break;
                case 2:
                    // Call the handleSignUp method from SignUp class
                    SignUp.handleSignUp(input);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }

        input.close();
    }
}
