package FastTrackControl.FastTrackTickets;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ListTickets {
    private static final String FILENAME = "tickets.txt";

    public void listTickets(String userId) {
        List<String> tickets = readTicketsFromFile();

        boolean hasTickets = false;
        for (String ticket : tickets) {
            String[] ticketData = ticket.split(",");
            if (ticketData[7].equals(userId)) {  // Check if ticket is for the current user
                hasTickets = true;
                System.out.println("Ticket ID: " + ticketData[0]);
                System.out.println("Name: " + ticketData[1]);
                System.out.println("Subject: " + ticketData[2]);
                System.out.println("Description: " + ticketData[3]);
                System.out.println("Priority: " + ticketData[4]);
                System.out.println("Created Date: " + ticketData[5]);
                System.out.println("Assigned To: " + ticketData[6]);
                System.out.println();
            }
        }

        if (!hasTickets) {
            System.out.println("No tickets available.");
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
}
