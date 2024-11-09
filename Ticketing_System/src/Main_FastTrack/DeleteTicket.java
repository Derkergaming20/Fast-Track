package Main_FastTrack;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DeleteTicket {
    private static final String FILENAME = "tickets.txt";

    public void deleteTicket(Scanner input) {
        System.out.print("Enter Ticket ID to delete: ");
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

    private void saveTicketsToFile(List<String> tickets) {
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
}
