package FastTrackGUI;

import javax.swing.*;
import java.awt.*;

public class DynamicTickets {

    public static void main(String[] args) {

// Choose Ticket Panel
        JPanel chooseTicketPanel = new JPanel();
        chooseTicketPanel.setBounds(0, 0, 200, 700);
        chooseTicketPanel.setLayout(new BoxLayout(chooseTicketPanel, BoxLayout.Y_AXIS));
        chooseTicketPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
        chooseTicketPanel.setBackground(new Color(0x87CEEB));

        JLabel firstLabelTitle = new JLabel("Navigation Panel");
        firstLabelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        firstLabelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        firstLabelTitle.setForeground(Color.BLACK);

        chooseTicketPanel.add(firstLabelTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(55));

        JButton homeButtonTitle = createButton("Home");
        chooseTicketPanel.add(homeButtonTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(25));

        JButton firstButtonTitle = createButton("Create Ticket");
        chooseTicketPanel.add(firstButtonTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(25));

        JButton secondButtonTitle = createButton("Edit Ticket");
        chooseTicketPanel.add(secondButtonTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(25));

        JButton thirdButtonTitle = createButton("Delete Ticket");
        chooseTicketPanel.add(thirdButtonTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(25));

        JButton fourthButtonTitle = createButton("View Ticket");
        chooseTicketPanel.add(fourthButtonTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(25));

        JButton fifthButtonTitle = createButton("Ticket List");
        chooseTicketPanel.add(fifthButtonTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(145));

        JButton SettingsButtonTitle = createButton("Settings");
        chooseTicketPanel.add(SettingsButtonTitle);

// Home Panel after login
        JPanel homePanel = new JPanel();
        homePanel.setBackground(Color.GREEN);
        homePanel.setBounds(200, 0, 600, 700);
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));

        JLabel homeLabel = new JLabel("Welcome User!");
        homeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        homeLabel.setForeground(Color.BLACK);

        homePanel.add(Box.createVerticalStrut(52));
        homePanel.add(homeLabel);

// Create Ticket Panel
        JPanel createTicketPanel = new JPanel();
        createTicketPanel.setBackground(Color.GRAY);
        createTicketPanel.setBounds(200, 0, 600, 700);
        createTicketPanel.setLayout(null);

        JLabel createTicketLabel = new JLabel("Create Ticket");
        createTicketLabel.setFont(new Font("Arial", Font.BOLD, 20));
        createTicketLabel.setForeground(Color.BLACK);
        createTicketLabel.setBounds(220, 30, 300, 40);
        createTicketPanel.add(createTicketLabel);

// Sub-panel for inputs with null layout for absolute positioning
        JPanel details = new JPanel();
        details.setLayout(null);
        details.setBackground(Color.LIGHT_GRAY);
        details.setBounds(25, 100, 550, 535);

// Username Label and TextField
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setBounds(20, 20, 100, 30);
        details.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));
        usernameField.setBounds(130, 20, 200, 30);
        details.add(usernameField);

// Description Label and TextField
        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        descriptionLabel.setBounds(20, 70, 100, 30);
        details.add(descriptionLabel);

        JTextField descriptionField = new JTextField();
        descriptionField.setPreferredSize(new Dimension(340, 90));
        descriptionField.setBounds(130, 70, 340, 90);
        details.add(descriptionField);
        
//Priority Label and TextField
        JLabel priorityLabel = new JLabel("Priority");
        priorityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priorityLabel.setBounds(20, 180, 100, 30);  // Positioned below the description
        details.add(priorityLabel);
        
//Change the priority field into DropDown
        JTextField priorityField = new JTextField();
        priorityField.setPreferredSize(new Dimension(340, 90));
        priorityField.setBounds(130, 180, 200, 30);
        details.add(priorityField);
        
//Urgency Label and TextField
        JLabel urgencyLabel = new JLabel("Urgency");
        urgencyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        urgencyLabel.setBounds(20, 220, 100, 30);  // Positioned below the description
        details.add(urgencyLabel);
        
//Change the priority field into DropDown
        JTextField urgencyField = new JTextField();
        urgencyField.setPreferredSize(new Dimension(340, 90));
        urgencyField.setBounds(130, 220, 200, 30);
        details.add(urgencyField);

        createTicketPanel.add(details);

// Third Panel (can be removed or adjusted based on your needs)
        JPanel thirdPanel = new JPanel();
        thirdPanel.setBackground(Color.ORANGE);
        thirdPanel.setBounds(800, 0, 200, 700);

// Main Frame
        JFrame frame = new JFrame("FastTrack Solutions");
        ImageIcon logo = new ImageIcon("");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(new Color(0x87CEEB));

// Add panels to the frame
        frame.add(chooseTicketPanel);
        //frame.add(homePanel);
        frame.add(createTicketPanel);
        frame.add(thirdPanel);

        frame.setVisible(true);
    }

// Helper method to create buttons with consistent styling
    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }
}
