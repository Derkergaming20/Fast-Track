package TicketSys;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String FILENAME = "tickets.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("-----Welcome to Fastrack Ticket-----");
            System.out.print("(1)Create Ticket\n(2)Edit Ticket\n(3)Delete Ticket\n(4)View Ticket\n(5)Ticket List\n(6)Exit\n");
            System.out.print("Enter Choice: ");

            try {
                int choice = input.nextInt();
                input.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        createTicket(input);
                        break;
                    case 2:
                        editTicket(input);
                        break;
                    case 3:
                        deleteTicket(input);
                        break;
                    case 4:
                        viewTicket(input);
                        break;
                    case 5:
                        listTickets();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // Clear invalid input
            }
        }

        input.close();
    }

    private static String generateTicketId() {
        int ticketNumber = RANDOM.nextInt(1000); // Generate a number between 0 and 999 (3 digits)
        return String.format("%03d", ticketNumber); // Format to ensure 3 digits, with leading zeros if necessary
    }

    private static void createTicket(Scanner input) {
        System.out.println("------Create Ticket-----");

        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Details: ");
        String details = input.nextLine();

        int urgent = 0;
        while (true) {
            System.out.println("Choose how Urgent it is\n(1)Urgent and Important\n(2)Not urgent yet important\n(3)Urgent but not important\n(4)Not urgent and not important");
            System.out.print("Enter Choice: ");
            try {
                urgent = input.nextInt();
                if (urgent < 1 || urgent > 4) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                input.nextLine();
            }
        }

        saveTicket(name, details, urgent);
    }

    private static void saveTicket(String name, String details, int urgent) {
        String ticketId = generateTicketId();
        String urgencyDescription = getUrgencyDescription(urgent);
        String ticketInfo = ticketId + "," + name + "," + details + "," + urgencyDescription;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            writer.write(ticketInfo);
            writer.newLine();
            System.out.println("Ticket saved successfully with ID: " + ticketId);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the ticket.");
            e.printStackTrace();
        }
    }

    private static void editTicket(Scanner input) {
        System.out.print("Enter the Ticket ID to edit: ");
        String ticketId = input.nextLine();

        List<String> tickets = readTicketsFromFile();
        boolean found = false;

        for (int i = 0; i < tickets.size(); i++) {
            String[] ticketData = tickets.get(i).split(",");
            if (ticketData[0].equals(ticketId)) {
                found = true;
                System.out.print("Enter new Name: ");
                String name = input.nextLine();

                System.out.print("Enter new Details: ");
                String details = input.nextLine();

                int urgent = 0;
                while (true) {
                    System.out.println("Choose new Urgency\n(1)Urgent and Important\n(2)Not urgent yet important\n(3)Urgent but not important\n(4)Not urgent and not important");
                    System.out.print("Enter Choice: ");
                    try {
                        urgent = input.nextInt();
                        input.nextLine(); // Consume newline
                        if (urgent < 1 || urgent > 4) {
                            throw new IllegalArgumentException();
                        }
                        break;
                    } catch (InputMismatchException | IllegalArgumentException e) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                        input.nextLine();
                    }
                }

                String urgencyDescription = getUrgencyDescription(urgent);
                tickets.set(i, ticketId + "," + name + "," + details + "," + urgencyDescription);
                saveTicketsToFile(tickets);
                System.out.println("Ticket updated successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Ticket ID not found.");
        }
    }

    private static void deleteTicket(Scanner input) {
        System.out.print("Enter the Ticket ID to delete: ");
        String ticketId = input.nextLine();

        List<String> tickets = readTicketsFromFile();
        boolean found = false;

        for (int i = 0; i < tickets.size(); i++) {
            String[] ticketData = tickets.get(i).split(",");
            if (ticketData[0].equals(ticketId)) {
                found = true;
                tickets.remove(i);
                saveTicketsToFile(tickets);
                System.out.println("Ticket deleted successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Ticket ID not found.");
        }
    }

    private static void viewTicket(Scanner input) {
        System.out.print("Enter the Ticket ID to view: ");
        String ticketId = input.nextLine();

        List<String> tickets = readTicketsFromFile();
        for (String ticket : tickets) {
            String[] ticketData = ticket.split(",");
            if (ticketData[0].equals(ticketId)) {
                System.out.println("Ticket ID: " + ticketData[0]);
                System.out.println("Name: " + ticketData[1]);
                System.out.println("Details: " + ticketData[2]);
                System.out.println("Urgency: " + ticketData[3]);
                return;
            }
        }

        System.out.println("Ticket ID not found.");
    }

    private static void listTickets() {
        List<String> tickets = readTicketsFromFile();

        if (tickets.isEmpty()) {
            System.out.println("No tickets available.");
        } else {
            for (String ticket : tickets) {
                String[] ticketData = ticket.split(",");
                System.out.println("Ticket ID: " + ticketData[0] + "\nName: " + ticketData[1] + "\nDetails: " + ticketData[2] + "\nUrgency: " + ticketData[3]);
            }
        }
    }

    private static List<String> readTicketsFromFile() {
        List<String> tickets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tickets.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading tickets.");
            e.printStackTrace();
        }
        return tickets;
    }

    private static void saveTicketsToFile(List<String> tickets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (String ticket : tickets) {
                writer.write(ticket);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tickets.");
            e.printStackTrace();
        }
    }

    private static String getUrgencyDescription(int urgent) {
        switch (urgent) {
            case 1: return "Urgent and Important";
            case 2: return "Not urgent yet important";
            case 3: return "Urgent but not important";
            case 4: return "Not urgent and not important";
            default: return "Unknown";
        }
    }
}
