package FastTrackControl.FastTrackTickets;

import java.io.*;
import java.security.SecureRandom;
import java.util.Scanner;

public class CreateTicket {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String FILENAME = "tickets.txt";

    private static String generateTicketId() {
        int ticketNumber = RANDOM.nextInt(1000); // Generates a 3-digit ID
        return String.format("%03d", ticketNumber);
    }

    public static void createTicket(Scanner input, String userId) {
        System.out.println("------ Create Ticket ------");

        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Subject: ");
        String subject = input.nextLine();

        System.out.print("Description: ");
        String description = input.nextLine();

        int priorityLevel = getPriorityLevel(input);

        System.out.print("Date Created (MM-DD-YYYY): ");
        String date = input.nextLine();

        System.out.print("Assigned To: ");
        String assigned = input.nextLine();

        saveTicket(name, subject, description, priorityLevel, date, assigned, userId);
    }

    public static int getPriorityLevel(Scanner input) {
        int priority = 0;
        while (true) {
            System.out.println("Choose Priority:");
            System.out.println("(1) High Priority");
            System.out.println("(2) Medium Priority");
            System.out.println("(3) Low Priority");
            System.out.println("(4) No Priority");
            System.out.print("Enter Choice: ");
            try {
                priority = Integer.parseInt(input.nextLine());
                if (priority < 1 || priority > 4) {
                    throw new IllegalArgumentException("Invalid priority level.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
        return priority;
    }

    public static String getPriorityDescription(int priorityLevel) {
        switch (priorityLevel) {
            case 1:
                return "High Priority";
            case 2:
                return "Medium Priority";
            case 3:
                return "Low Priority";
            case 4:
                return "No Priority";
            default:
                return "Unknown Priority";
        }
    }

    private static void saveTicket(String name, String subject, String description, int priorityLevel, String date, String assigned, String userId) {
        String ticketId = generateTicketId();
        String priorityDescription = getPriorityDescription(priorityLevel);
        String ticketInfo = String.join(",", ticketId, name, subject, description, priorityDescription, date, assigned, userId);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            writer.write(ticketInfo);
            writer.newLine();
            System.out.println("Ticket saved successfully with ID: " + ticketId);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the ticket.");
            e.printStackTrace();
        }
    }
}
