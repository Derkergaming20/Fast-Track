package FastTrackControl.FastTrackTickets;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DeleteTicket {

    private static final String FILENAME = "tickets.txt";

    public void deleteTicket(Scanner input, String userId) {
        System.out.print("Enter Ticket ID to delete: ");
        String ticketId = input.nextLine();

        List<String> tickets = readTicketsFromFile();
        List<String> updatedTickets = new ArrayList<>();

        boolean ticketFound = false;

        // Iterate over all tickets and keep only those not owned by the current user or with a different ID
        for (String ticket : tickets) {
            String[] ticketData = ticket.split(",");
            if (ticketData[0].equals(ticketId) && ticketData[7].equals(userId)) {
                // Ticket found and belongs to the current user, so don't add it to updatedTickets (delete it)
                ticketFound = true;
            } else {
                // Add to updated list if it's not the ticket to delete
                updatedTickets.add(ticket);
            }
        }

        // If ticket is found, update the file
        if (ticketFound) {
            writeTicketsToFile(updatedTickets);
            System.out.println("Ticket deleted successfully.");
        } else {
            System.out.println("Ticket not found or you are not the owner of this ticket.");
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
