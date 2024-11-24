package FastTrackControl;

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
            input.nextLine();

            switch (choice) {
                case 1:
                    if (Login.handleLogin(input)) {
                        boolean managingTickets = true;
                        while (managingTickets) {
                            System.out.println("-----Welcome to Fastrack Ticket-----");
                            System.out.println("(1) Create Ticket\n(2) Edit Ticket\n(3) Delete Ticket\n(4) View Ticket\n(5) Ticket List\n(6) Logout");
                            System.out.print("Enter Choice: ");
                            int ticketChoice = input.nextInt();
                            input.nextLine();

                            switch (ticketChoice) {
                                case 1:
                                    CreateTicket.createTicket(input);
                                    break;
                                case 2:
                                    new EditTicket().editTicket(input);
                                    break;
                                case 3:
                                    new DeleteTicket().deleteTicket(input);
                                    break;
                                case 4:
                                    new ViewTicket().viewTicket(input);
                                    break;
                                case 5:
                                    new ListTickets().listTickets();
                                    break;
                                case 6:
                                    managingTickets = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please select a valid option.");
                            }
                        }
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                    break;
                case 2:
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
