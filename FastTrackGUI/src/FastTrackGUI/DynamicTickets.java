package FastTrackGUI;

import javax.swing.*;
import java.awt.*;

public class DynamicTickets {

    public static void main(String[] args) {

        // Left Panel: Navigation
        JPanel chooseTicketPanel = new JPanel();
        chooseTicketPanel.setBounds(0, 0, 200, 700);
        chooseTicketPanel.setLayout(new BoxLayout(chooseTicketPanel, BoxLayout.Y_AXIS));
        chooseTicketPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
        chooseTicketPanel.setBackground(new Color(0x87CEEB));

     // Label in the first panel
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
        chooseTicketPanel.add(SettingsButtonTitle );
        
        
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

        JPanel createTicketPanel = new JPanel();
        createTicketPanel.setBackground(Color.GREEN);
        createTicketPanel.setBounds(200, 0, 600, 700);
        createTicketPanel.setLayout(new BoxLayout(createTicketPanel, BoxLayout.Y_AXIS));

        JLabel createTicketLabel = new JLabel("Create Ticket");
        createTicketLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createTicketLabel.setFont(new Font("Arial", Font.BOLD, 20));
        createTicketLabel.setForeground(Color.BLACK);

        createTicketPanel.add(Box.createVerticalStrut(52));
        createTicketPanel.add(createTicketLabel);
        
        JPanel thirdPanel = new JPanel();
        thirdPanel.setBackground(Color.ORANGE);
        thirdPanel.setBounds(800, 0, 200, 700);

        // Main Frame
        JFrame frame = new JFrame("FastTrack Solutions");
        ImageIcon logo = new ImageIcon(""); // Replace with the actual path to your logo
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