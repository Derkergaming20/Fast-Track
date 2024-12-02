package FastTrackGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicTicket1 {

    public static void main(String[] args) {
// Main Frame
        JFrame frame = new JFrame("FastTrack Solutions");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(0x87CEEB));

// CardLayout to switch between panels
        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.setBounds(200, 0, 590, 600);

// Home Panel
        JPanel homePanel = new JPanel();
        homePanel.setBackground(Color.GREEN);
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));

        JLabel homeLabel = new JLabel("Welcome User!");
        homeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        homePanel.add(Box.createVerticalStrut(52));
        homePanel.add(homeLabel);

// Create Ticket Panel
        JPanel createTicketPanel = new JPanel();
        createTicketPanel.setBackground(Color.GRAY);
        createTicketPanel.setLayout(null);

        JLabel createTicketLabel = new JLabel("Create Ticket");
        createTicketLabel.setFont(new Font("Arial", Font.BOLD, 20));
        createTicketLabel.setBounds(225, 30, 300, 40);
        createTicketPanel.add(createTicketLabel);

// Add input fields to Create Ticket Panel
        JPanel details1 = new JPanel();
        details1.setLayout(null);
        details1.setBackground(Color.LIGHT_GRAY);
        details1.setBounds(25, 100, 540, 450);

        JTextField usernameField1 = new JTextField();
        usernameField1.setBounds(130, 20, 200, 30);
        details1.add(createLabeledField(details1, "Username", 20, 20));

        JTextField subjectField1 = new JTextField();
        subjectField1.setBounds(130, 70, 200, 30);
        details1.add(createLabeledField(details1, "Subject", 70, 70));

        JTextField descriptionField1 = new JTextField();
        descriptionField1.setBounds(130, 120, 340, 90);
        details1.add(descriptionField1);

        JLabel descriptionLabe1 = new JLabel("Description");
        descriptionLabe1.setBounds(20, 120, 100, 30);
        descriptionLabe1.setFont(new Font("Arial", Font.BOLD, 16));
        details1.add(descriptionLabe1);

        JTextField priorityField1 = new JTextField();
        priorityField1.setBounds(130, 220, 200, 30);
        details1.add(createLabeledField(details1, "Priority", 220, 220));

        JTextField createdDateField1 = new JTextField();
        createdDateField1.setBounds(130, 270, 200, 30);
        details1.add(createLabeledField(details1, "Created Date", 270, 270));

        JTextField assignedToField1 = new JTextField();
        assignedToField1.setBounds(130, 320, 200, 30);
        details1.add(createLabeledField(details1, "Assigned To", 320, 320));

// Button for Submit and Cancel
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(30, 400, 200, 30);
        submitButton.addActionListener(e -> System.out.println("Submit button clicked!"));
        details1.add(submitButton);

        JButton cancelButton1 = new JButton("Cancel");
        cancelButton1.setBounds(300, 400, 200, 30);
        cancelButton1.addActionListener(e -> System.out.println("Cancel button clicked!"));
        details1.add(cancelButton1);

        createTicketPanel.add(details1);

// Edit Ticket Panel
        JPanel editTicketPanel = new JPanel();
        editTicketPanel.setBackground(Color.BLUE);
        editTicketPanel.setLayout(null);

        JLabel editLabel = new JLabel("Edit Ticket");
        editLabel.setFont(new Font("Arial", Font.BOLD, 20));
        editLabel.setForeground(Color.WHITE);
        editLabel.setBounds(225, 30, 300, 40);
        editTicketPanel.add(editLabel);

// Details Sub-panel for Editing
        JPanel details2 = new JPanel();
        details2.setLayout(null);
        details2.setBackground(Color.LIGHT_GRAY);
        details2.setBounds(25, 100, 540, 450);

        JTextField usernameField2 = new JTextField();
        usernameField2.setBounds(130, 20, 200, 30);
        details2.add(createLabeledField(details2, "Username", 20, 20));

        JTextField subjectField2 = new JTextField();
        subjectField2.setBounds(130, 70, 200, 30);
        details2.add(createLabeledField(details2, "Subject", 70, 70));

        JTextField descriptionField2 = new JTextField();
        descriptionField2.setBounds(130, 120, 340, 90);
        details2.add(descriptionField2);

        JLabel descriptionLabe2 = new JLabel("Description");
        descriptionLabe2.setBounds(20, 120, 100, 30);
        descriptionLabe2.setFont(new Font("Arial", Font.BOLD, 16));
        details2.add(descriptionLabe2);

        JTextField priorityField2 = new JTextField();
        priorityField2.setBounds(130, 220, 200, 30);
        details2.add(createLabeledField(details2, "Priority", 220, 220));

        JTextField createdDateField2 = new JTextField();
        createdDateField2.setBounds(130, 270, 200, 30);
        details2.add(createLabeledField(details2, "Created Date", 270, 270));

        JTextField assignedToField2 = new JTextField();
        assignedToField2.setBounds(130, 320, 200, 30);
        details2.add(createLabeledField(details2, "Assigned To", 320, 320));

// Save and Cancel Button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(30, 400, 200, 30);
        saveButton.addActionListener(e -> System.out.println("Save button clicked!"));
        details2.add(saveButton);

        JButton cancelButton2 = new JButton("Cancel");
        cancelButton2.setBounds(300, 400, 200, 30);
        cancelButton2.addActionListener(e -> System.out.println("Cancel button clicked!"));
        details2.add(cancelButton2);

        editTicketPanel.add(details2);

// Ticket List Panel (new panel for displaying ticket list)
        JPanel ticketListPanel = new JPanel();
        ticketListPanel.setBackground(Color.YELLOW);
        ticketListPanel.setLayout(new BorderLayout());

        JLabel ticketListLabel = new JLabel("Ticket List");
        ticketListLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ticketListPanel.add(ticketListLabel, BorderLayout.NORTH);

// Create a sample list of tickets
        String[] columnNames = {"Ticket ID", "Username", "Subject", "Description", "Priority", "Created Date", "Assigned To"};
        Object[][] data = {
                {"1", "Derker", "Issue with login", "I Cant login", "High", "10-20-20", "Nigga"},
        };


        JTable ticketTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(ticketTable);
        ticketListPanel.add(scrollPane, BorderLayout.CENTER);

        // View Ticket from the List
        JButton viewTicketButton = new JButton("View Ticket");
        viewTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ticketTable.getSelectedRow();
                if (selectedRow != -1) {
                    String ticketID = (String) ticketTable.getValueAt(selectedRow, 0);
                    System.out.println("Viewing ticket with ID: " + ticketID);
                    // You can switch to a "View Ticket" panel with the details
                } else {
                    System.out.println("No ticket selected!");
                }
            }
        });

        ticketListPanel.add(viewTicketButton, BorderLayout.SOUTH);

// View Ticket Panel
        JPanel viewTicketPanel = new JPanel();
        viewTicketPanel.setBackground(Color.ORANGE);
        viewTicketPanel.setLayout(null);

        JLabel viewLabel = new JLabel("View Ticket");
        viewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        viewLabel.setBounds(225, 30, 300, 40);
        viewTicketPanel.add(viewLabel);

// Input field for ticket ID (to view the ticket)
        JPanel details4 = new JPanel();
        details4.setLayout(null);
        details4.setBackground(Color.LIGHT_GRAY);
        details4.setBounds(25, 100, 550, 450);

        JTextField ticketIDFieldView = new JTextField();
        ticketIDFieldView.setBounds(130, 20, 200, 30);
        details4.add(createLabeledField(details4, "Ticket ID", 20, 20));

// View and Cancel Button
        JButton viewButton = new JButton("View");
        viewButton.setBounds(30, 400, 200, 30);
        viewButton.addActionListener(e -> {
            String ticketID = ticketIDFieldView.getText();
            if (!ticketID.isEmpty()) {
                System.out.println("Viewing ticket with ID " + ticketID);
            } else {
                System.out.println("Please enter a valid Ticket ID.");
            }
        });
        details4.add(viewButton);

        JButton cancelViewButton = new JButton("Cancel");
        cancelViewButton.setBounds(300, 400, 200, 30);
        cancelViewButton.addActionListener(e -> {
            System.out.println("Cancel button clicked!");
        });
        details4.add(cancelViewButton);

        viewTicketPanel.add(details4);

// Settings Panel (New Panel for Settings)
        JPanel settingsPanel = new JPanel();
        settingsPanel.setBackground(Color.CYAN);
        settingsPanel.setLayout(null);

        JLabel settingsLabel = new JLabel("Settings");
        settingsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        settingsLabel.setBounds(220, 30, 300, 40);
        settingsPanel.add(settingsLabel);

// Add settings options
        JLabel themeLabel = new JLabel("Choose Theme:");
        themeLabel.setBounds(50, 100, 150, 30);
        settingsPanel.add(themeLabel);

        String[] themes = {"Light", "Dark"};
        JComboBox<String> themeComboBox = new JComboBox<>(themes);
        themeComboBox.setBounds(200, 100, 150, 30);
        settingsPanel.add(themeComboBox);

        JLabel notificationLabel = new JLabel("Enable Notifications:");
        notificationLabel.setBounds(50, 150, 150, 30);
        settingsPanel.add(notificationLabel);

        JCheckBox notificationCheckBox = new JCheckBox();
        notificationCheckBox.setBounds(200, 150, 50, 30);
        settingsPanel.add(notificationCheckBox);

// Save Button for Settings
        JButton saveSettingsButton = new JButton("Save Settings");
        saveSettingsButton.setBounds(50, 250, 150, 30);
        settingsPanel.add(saveSettingsButton);

// Cancel Button for Settings
        JButton cancelSettingsButton = new JButton("Cancel");
        cancelSettingsButton.setBounds(250, 250, 150, 30);
        settingsPanel.add(cancelSettingsButton);

// Add panels to CardLayout
        mainPanel.add(homePanel, "Home");
        mainPanel.add(createTicketPanel, "CreateTicket");
        mainPanel.add(editTicketPanel, "EditTicket");
        mainPanel.add(ticketListPanel, "TicketList");
        mainPanel.add(viewTicketPanel, "ViewTicket");
        mainPanel.add(settingsPanel, "Settings");

// Navigation Panel with Settings Button
        JPanel chooseTicketPanel = new JPanel();
        chooseTicketPanel.setBounds(0, 0, 200, 700);
        chooseTicketPanel.setLayout(new BoxLayout(chooseTicketPanel, BoxLayout.Y_AXIS));
        chooseTicketPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
        chooseTicketPanel.setBackground(new Color(0x87CEEB));

        JLabel firstLabelTitle = new JLabel("Navigation Panel");
        firstLabelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        firstLabelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        chooseTicketPanel.add(firstLabelTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(55));

// Buttons with ActionListeners to switch panels
        JButton homeButtonTitle = createButton("Home");
        homeButtonTitle.addActionListener(e -> switchPanel(mainPanel, "Home"));
        chooseTicketPanel.add(homeButtonTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(25));

        JButton createButtonTitle = createButton("Create Ticket");
        createButtonTitle.addActionListener(e -> switchPanel(mainPanel, "CreateTicket"));
        chooseTicketPanel.add(createButtonTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(25));

        JButton editButtonTitle = createButton("Edit Ticket");
        editButtonTitle.addActionListener(e -> switchPanel(mainPanel, "EditTicket"));
        chooseTicketPanel.add(editButtonTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(25));

        JButton viewButtonTitle = createButton("View Ticket");
        viewButtonTitle.addActionListener(e -> switchPanel(mainPanel, "ViewTicket"));
        chooseTicketPanel.add(viewButtonTitle);
        chooseTicketPanel.add(Box.createVerticalStrut(25));

        JButton ticketListButton = createButton("Ticket List");
        ticketListButton.addActionListener(e -> switchPanel(mainPanel, "TicketList"));
        chooseTicketPanel.add(ticketListButton);
        chooseTicketPanel.add(Box.createVerticalStrut(145));

// Settings Button (New)
        JButton settingsButton = createButton("Settings");
        settingsButton.addActionListener(e -> switchPanel(mainPanel, "Settings"));
        chooseTicketPanel.add(settingsButton);

// Add components to the frame
        frame.add(chooseTicketPanel);
        frame.add(mainPanel);

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

    // Helper method to switch panels in CardLayout
    private static void switchPanel(JPanel mainPanel, String panelName) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, panelName);
    }

    // Helper method to create labeled fields
    private static JTextField createLabeledField(JPanel panel, String label, int yLabel, int yField) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(20, yLabel, 100, 30);
        jLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(jLabel);

        JTextField textField = new JTextField();
        textField.setBounds(130, yField, 200, 30);
        panel.add(textField);

        return textField;
    }
}
