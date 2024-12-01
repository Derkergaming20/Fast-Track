package FastTrackControl.FastTrackTickets;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class EditTicket {

    private static final String FILENAME = "tickets.txt";

    public void editTicket(Scanner input, String userId) {
        System.out.print("Enter Ticket ID to edit: ");
        String ticketId = input.nextLine();

        List<String> tickets = readTicketsFromFile();
        List<String> updatedTickets = new ArrayList<>();

        boolean ticketFound = false;

        // Iterate over all tickets and edit the one that belongs to the current user and matches the ID
        for (String ticket : tickets) {
            String[] ticketData = ticket.split(",");
            if (ticketData[0].equals(ticketId) && ticketData[7].equals(userId)) {
                // Ticket found, allow user to edit details
                ticketFound = true;
                System.out.println("Editing Ticket ID: " + ticketData[0]);
                System.out.print("New Name (current: " + ticketData[1] + "): ");
                String name = input.nextLine();
                System.out.print("New Subject (current: " + ticketData[2] + "): ");
                String subject = input.nextLine();
                System.out.print("New Description (current: " + ticketData[3] + "): ");
                String description = input.nextLine();
                int priorityLevel = getPriorityLevel(input);
                System.out.print("New Date Created (current: " + ticketData[5] + "): ");
                String date = input.nextLine();
                System.out.print("New Assigned To (current: " + ticketData[6] + "): ");
                String assigned = input.nextLine();

                // Update the ticket with new data
                String updatedTicket = String.join(",", ticketData[0], name, subject, description, getPriorityDescription(priorityLevel), date, assigned, userId);
                updatedTickets.add(updatedTicket);
            } else {
                // If not the ticket to edit, just add to updated list
                updatedTickets.add(ticket);
            }
        }

        // If ticket was found and edited, update the file
        if (ticketFound) {
            writeTicketsToFile(updatedTickets);
            System.out.println("Ticket updated successfully.");
        } else {
            System.out.println("Ticket not found or you are not the owner of this ticket.");
        }
    }

    private int getPriorityLevel(Scanner input) {
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

    private String getPriorityDescription(int priorityLevel) {
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

    private List<String> readTicketsFromFile() {
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

    private void writeTicketsToFile(List<String> tickets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (String ticket : tickets) {
                writer.write(ticket);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing tickets.");
            e.printStackTrace();
        }
    }
}
