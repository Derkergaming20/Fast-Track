package FastTrackGUI;

import javax.swing.*;
import java.awt.*;

public class DynamicTickets {

    public static void main(String[] args) {
        
        JPanel chooseTicketPanel = new JPanel();
        chooseTicketPanel.setBounds(0, 0, 200, 800);       
        chooseTicketPanel.setLayout(new BoxLayout(chooseTicketPanel, BoxLayout.Y_AXIS));        
        chooseTicketPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));

        // Label in the first panel
        JLabel firstLabelTitle = new JLabel("Navigation Panel");
        firstLabelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the label horizontally
        firstLabelTitle.setFont(new Font("Arial", Font.BOLD, 20)); // Set font for the label (larger for emphasis)
        firstLabelTitle.setForeground(Color.BLACK);  // Make the label text white for better contrast
        chooseTicketPanel.add(firstLabelTitle);

        // Add some vertical space between label and buttons
        chooseTicketPanel.add(Box.createVerticalStrut(80));
        
        // Create buttons and add them to the panel
        JButton firstButtonTitle = createButton("Create Ticket");
        chooseTicketPanel.add(firstButtonTitle);
        // Add vertical spacing between buttons
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

        // Second Panel (Green color panel for content)
        JPanel secondPanel = new JPanel();
        secondPanel.setBackground(Color.green);
        secondPanel.setBounds(200, 0, 600, 800);

        // Third Panel (Orange color panel for additional content)
        JPanel thirdPanel = new JPanel();
        thirdPanel.setBackground(Color.orange);
        thirdPanel.setBounds(800, 0, 200, 800);

        // Main frame setup
        JFrame frame = new JFrame("FastTrack Solutions");
        ImageIcon logo = new ImageIcon(""); // Replace with actual path if needed
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);  // Use absolute positioning for panels
        frame.setResizable(false);
        frame.setIconImage(logo.getImage());  // Set logo if available
        frame.getContentPane().setBackground(new Color(0x87CEEB));  // Set background color

        // Add panels to the frame
        frame.add(chooseTicketPanel);
        frame.add(secondPanel);
        frame.add(thirdPanel);        
    }

    // Helper method to create buttons with consistent styling
    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the button horizontally
        button.setPreferredSize(new Dimension(150, 40));  // Set a preferred button size
        button.setBackground(new Color(0xffffff)); // Set background color to a green shade
        button.setForeground(Color.BLACK); // Set text color to white for contrast
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Set button font
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));  // Set button border
        return button;
    }
}
