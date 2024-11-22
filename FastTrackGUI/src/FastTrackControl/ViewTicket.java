package FastTrackControl;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewTicket {
    private static final String FILENAME = "tickets.txt";

    public void viewTicket(Scanner input) {
        System.out.print("Enter Ticket ID to view: ");
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
