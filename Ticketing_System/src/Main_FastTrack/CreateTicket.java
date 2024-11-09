package Main_FastTrack;

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

    public static void createTicket(Scanner input) {
        System.out.println("------Create Ticket-----");
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Details: ");
        String details = input.nextLine();

        int urgencyLevel = getUrgencyLevel(input);
        saveTicket(name, details, urgencyLevel);
    }

    public static int getUrgencyLevel(Scanner input) {
        int urgency = 0;
        while (true) {
            System.out.println("Choose Urgency:\n(1) Urgent and Important\n(2) Not urgent but important\n(3) Urgent but not important\n(4) Not urgent and not important");
            System.out.print("Enter Choice: ");
            try {
                urgency = input.nextInt();
                input.nextLine(); // Consume newline
                if (urgency < 1 || urgency > 4) throw new IllegalArgumentException();
                break;
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                input.nextLine(); // Clear the invalid input
            }
        }
        return urgency;
    }

    private static void saveTicket(String name, String details, int urgencyLevel) {
        String ticketId = generateTicketId();
        String urgencyDescription = getUrgencyDescription(urgencyLevel);
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

    public static String getUrgencyDescription(int urgencyLevel) {
        switch (urgencyLevel) {
            case 1: return "Urgent and Important";
            case 2: return "Not urgent but important";
            case 3: return "Urgent but not important";
            case 4: return "Not urgent and not important";
            default: return "Unknown";
        }
    }
}
