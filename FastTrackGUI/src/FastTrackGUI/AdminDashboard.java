package FastTrackGUI;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDashboard extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public AdminDashboard(Login loginFrame) {
        // Set up the admin dashboard frame
        setTitle("Admin Dashboard");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(new ImageIcon("FTSLogo.png").getImage());
        getContentPane().setBackground(new Color(0x87CEEB));

        // Initialize CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create the dashboard panel
        JPanel dashboardPanel = createDashboardPanel(loginFrame);

        // Create the user table panel
        JPanel userTablePanel = createUserTablePanel();

        // Add panels to CardLayout
        cardPanel.add(dashboardPanel, "Dashboard");
        cardPanel.add(userTablePanel, "ViewUsers");

        add(cardPanel);
    }

    private JPanel createDashboardPanel(Login loginFrame) {
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(null);

        JLabel dashboardLabel = new JLabel("Admin Dashboard");
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dashboardLabel.setBounds(180, 50, 300, 30);
        dashboardPanel.add(dashboardLabel);

        JButton manageUsersButton = new JButton("Manage Users");
        manageUsersButton.setBounds(150, 150, 300, 40);
        dashboardPanel.add(manageUsersButton);

        JButton viewUsersButton = new JButton("View Users");
        viewUsersButton.setBounds(150, 200, 300, 40);
        dashboardPanel.add(viewUsersButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 250, 300, 40);
        dashboardPanel.add(logoutButton);

        // Action Listener for View Users Button
        viewUsersButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "ViewUsers"); // Switch to the user table panel
        });

        // Action Listener for Logout Button
        logoutButton.addActionListener(e -> {
            setVisible(false);
            loginFrame.setVisible(true); // Show login frame when logged out
        });

        return dashboardPanel;
    }

    private JPanel createUserTablePanel() {
        JPanel userTablePanel = new JPanel();
        userTablePanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("User List", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userTablePanel.add(titleLabel, BorderLayout.NORTH);

        // Column names for the table
        String[] columnNames = {"Username", "Role", "UserID"};

        // Data for the table
        String[][] tableData = loadUserData();

        // Create JTable with the data and column names
        JTable userTable = new JTable(tableData, columnNames);
        userTable.setFillsViewportHeight(true);

        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(userTable);
        userTablePanel.add(scrollPane, BorderLayout.CENTER);

        // Add a "Back" button to return to the dashboard
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Dashboard"));
        userTablePanel.add(backButton, BorderLayout.SOUTH);

        return userTablePanel;
    }

    // Load user data from the file
    private String[][] loadUserData() {
        List<String[]> userData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    userData.add(new String[]{parts[0], parts[2], parts[3]});
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading users.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return userData.toArray(new String[0][]);
    }
}
