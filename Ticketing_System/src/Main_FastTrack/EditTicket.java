package Main_FastTrack;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class EditTicket {
    private static final String FILENAME = "tickets.txt";

    public void editTicket(Scanner input) {
        System.out.print("Enter Ticket ID to edit: ");
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
                int urgency = CreateTicket.getUrgencyLevel(input);
                String urgencyDescription = CreateTicket.getUrgencyDescription(urgency);

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
