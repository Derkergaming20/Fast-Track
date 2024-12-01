package FastTrackControl.FastTrackTickets;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewTicket {
    private static final String FILENAME = "tickets.txt";

    public void viewTicket(Scanner input, String userId) {
        System.out.print("Enter Ticket ID to view: ");
        String ticketId = input.nextLine();

        List<String> tickets = readTicketsFromFile();
        for (String ticket : tickets) {
            String[] ticketData = ticket.split(",");
            if (ticketData[0].equals(ticketId) && ticketData[7].equals(userId)) {  // Check if the ticket is for the current user
                System.out.println("Ticket ID: " + ticketData[0]);
                System.out.println("Name: " + ticketData[1]);
                System.out.println("Subject: " + ticketData[2]);
                System.out.println("Description: " + ticketData[3]);
                System.out.println("Priority: " + ticketData[4]);
                System.out.println("Created Date: " + ticketData[5]);
                System.out.println("Assigned To: " + ticketData[6]);
                return;
            }
        }

        System.out.println("Ticket ID not found or not owned by you.");
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
}
