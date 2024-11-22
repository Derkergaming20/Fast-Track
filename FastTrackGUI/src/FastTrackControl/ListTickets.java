package FastTrackControl;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ListTickets {
    private static final String FILENAME = "tickets.txt";

    public void listTickets() {
        List<String> tickets = readTicketsFromFile();

        if (tickets.isEmpty()) {
            System.out.println("No tickets available.");
        } else {
            for (String ticket : tickets) {
                String[] ticketData = ticket.split(",");
                System.out.println("Ticket ID: " + ticketData[0]);
                System.out.println("Name: " + ticketData[1]);
                System.out.println("Details: " + ticketData[2]);
                System.out.println("Urgency: " + ticketData[3]);
                System.out.println();
            }
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
