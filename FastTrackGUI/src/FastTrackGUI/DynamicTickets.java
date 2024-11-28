package FastTrackGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicTickets {

    public static void main(String[] args) {
        // Main Frame setup
        JFrame frame = new JFrame("FastTrack Solutions");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(0x87CEEB));

        // CardLayout to switch between panels
        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.setBounds(200, 0, 600, 700);

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
        createTicketLabel.setBounds(220, 30, 300, 40);
        createTicketPanel.add(createTicketLabel);

        // Add input fields to Create Ticket Panel
        JPanel details1 = new JPanel();
        details1.setLayout(null);
        details1.setBackground(Color.LIGHT_GRAY);
        details1.setBounds(25, 100, 550, 535);

        JTextField usernameField1 = new JTextField();
        usernameField1.setBounds(130, 20, 200, 30);
        details1.add(createLabeledField(details1, "Username", 20, 20));

        JTextField subjectField1 = new JTextField();
        subjectField1.setBounds(130, 70, 200, 30);
        details1.add(createLabeledField(details1, "Subject", 70, 70));

        JTextField descriptionField1 = new JTextField();
        descriptionField1.setBounds(130, 120, 340, 90);
        details1.add(descriptionField1);

        JLabel descriptionLabel1 = new JLabel("Description");
        descriptionLabel1.setBounds(20, 120, 100, 30);
        descriptionLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        details1.add(descriptionLabel1);

        JTextField priorityField1 = new JTextField();
        priorityField1.setBounds(130, 220, 200, 30);
        details1.add(createLabeledField(details1, "Priority", 220, 220));

        JTextField createdDateField1 = new JTextField();
        createdDateField1.setBounds(130, 270, 200, 30);
        details1.add(createLabeledField(details1, "Created Date", 270, 270));

        JTextField assignedToField1 = new JTextField();
        assignedToField1.setBounds(130, 320, 200, 30);
        details1.add(createLabeledField(details1, "Assigned To", 320, 320));

        // Submit and Cancel buttons
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(30, 400, 200, 30);
        submitButton.addActionListener(e -> {
            String username = usernameField1.getText();
            String subject = subjectField1.getText();
            String description = descriptionField1.getText();
            String priority = priorityField1.getText();
            String createdDate = createdDateField1.getText();
            String assignedTo = assignedToField1.getText();

            if (!username.isEmpty() && !subject.isEmpty() && !description.isEmpty()) {
                System.out.println("Ticket submitted: " + subject);
            } else {
                System.out.println("Please fill in all the fields.");
            }
        });
        details1.add(submitButton);

        JButton cancelButton1 = new JButton("Cancel");
        cancelButton1.setBounds(300, 400, 200, 30);
        cancelButton1.addActionListener(e -> {
            usernameField1.setText("");
            subjectField1.setText("");
            descriptionField1.setText("");
            priorityField1.setText("");
            createdDateField1.setText("");
            assignedToField1.setText("");
            System.out.println("Ticket creation cancelled.");
        });
        details1.add(cancelButton1);

        createTicketPanel.add(details1);

        // Edit Ticket Panel
        JPanel editTicketPanel = new JPanel();
        editTicketPanel.setBackground(Color.BLUE);
        editTicketPanel.setLayout(null);

        JLabel editLabel = new JLabel("Edit Ticket");
        editLabel.setFont(new Font("Arial", Font.BOLD, 20));
        editLabel.setForeground(Color.WHITE);
        editLabel.setBounds(220, 30, 300, 40);
        editTicketPanel.add(editLabel);

        // Details Sub-panel for Editing
        JPanel details2 = new JPanel();
        details2.setLayout(null);
        details2.setBackground(Color.LIGHT_GRAY);
        details2.setBounds(25, 100, 550, 535);

        JTextField usernameField2 = new JTextField();
        usernameField2.setBounds(130, 20, 200, 30);
        details2.add(createLabeledField(details2, "Username", 20, 20));

        JTextField subjectField2 = new JTextField();
        subjectField2.setBounds(130, 70, 200, 30);
        details2.add(createLabeledField(details2, "Subject", 70, 70));

        JTextField descriptionField2 = new JTextField();
        descriptionField2.setBounds(130, 120, 340, 90);
        details2.add(descriptionField2);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(20, 120, 100, 30);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        details2.add(descriptionLabel);

        JTextField priorityField2 = new JTextField();
        priorityField2.setBounds(130, 220, 200, 30);
        details2.add(createLabeledField(details2, "Priority", 220, 220));

        JTextField createdDateField2 = new JTextField();
        createdDateField2.setBounds(130, 270, 200, 30);
        details2.add(createLabeledField(details2, "Created Date", 270, 270));

        JTextField assignedToField2 = new JTextField();
        assignedToField2.setBounds(130, 320, 200, 30);
        details2.add(createLabeledField(details2, "Assigned To", 320, 320));

        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(30, 400, 200, 30);
        saveButton.addActionListener(e -> {
            String username = usernameField2.getText();
            String subject = subjectField2.getText();
            String description = descriptionField2.getText();
            String priority = priorityField2.getText();
            String createdDate = createdDateField2.getText();
            String assignedTo = assignedToField2.getText();

            System.out.println("Ticket edited and saved: " + subject);
        });
        details2.add(saveButton);

        // Cancel Button
        JButton cancelButton2 = new JButton("Cancel");
        cancelButton2.setBounds(300, 400, 200, 30);
        cancelButton2.addActionListener(e -> System.out.println("Edit cancelled."));
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
                } else {
                    System.out.println("No ticket selected!");
                }
            }
        });

        ticketListPanel.add(viewTicketButton, BorderLayout.SOUTH);

        // Settings Panel (simplified example)
        JPanel settingsPanel = new JPanel();
        settingsPanel.setBackground(Color.CYAN);

        // Combo box for theme selection
        JComboBox<String> themeComboBox = new JComboBox<>(new String[]{"Light", "Dark"});
        settingsPanel.add(themeComboBox);

        // Checkbox for enabling/disabling notifications
        JCheckBox notificationCheckBox = new JCheckBox("Enable Notifications");
        settingsPanel.add(notificationCheckBox);

        // Save Settings Button
        JButton saveSettingsButton = new JButton("Save Settings");
        saveSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTheme = (String) themeComboBox.getSelectedItem();
                boolean notificationsEnabled = notificationCheckBox.isSelected();
                System.out.println("Settings saved: Theme - " + selectedTheme + ", Notifications enabled: " + notificationsEnabled);
            }
        });
        settingsPanel.add(saveSettingsButton);

        // Cancel Settings Button
        JButton cancelSettingsButton = new JButton("Cancel Settings");
        cancelSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Settings changes cancelled.");
            }
        });
        settingsPanel.add(cancelSettingsButton);

        // Add Panels to the mainPanel (card layout)
        mainPanel.add(homePanel, "Home");
        mainPanel.add(createTicketPanel, "Create Ticket");
        mainPanel.add(editTicketPanel, "Edit Ticket");
        mainPanel.add(ticketListPanel, "Ticket List");
        mainPanel.add(settingsPanel, "Settings");

        // Add mainPanel to the frame
        frame.add(mainPanel);

        // Display the frame
        frame.setVisible(true);
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
