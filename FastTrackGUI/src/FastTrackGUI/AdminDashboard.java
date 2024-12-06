package FastTrackGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDashboard extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JTable userTable;

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

        // Create the user management panel
        JPanel userManagementPanel = createUserManagementPanel();

        // Add panels to CardLayout
        cardPanel.add(dashboardPanel, "Dashboard");
        cardPanel.add(userManagementPanel, "UserManagement");

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

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 200, 300, 40);
        dashboardPanel.add(logoutButton);

        // Action Listener for Manage Users Button
        manageUsersButton.addActionListener(e -> cardLayout.show(cardPanel, "UserManagement"));

        // Action Listener for Logout Button
        logoutButton.addActionListener(e -> {
            setVisible(false);
            loginFrame.setVisible(true); // Show login frame when logged out
        });

        return dashboardPanel;
    }

    private JPanel createUserManagementPanel() {
        JPanel userManagementPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Manage Users", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userManagementPanel.add(titleLabel, BorderLayout.NORTH);

        // Column names for the table
        String[] columnNames = {"Username", "Role", "UserID"};

        // Create JTable with initial data
        userTable = new JTable(new DefaultTableModel(loadUserData(), columnNames));
        JScrollPane scrollPane = new JScrollPane(userTable);
        userManagementPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout());

        JTextField usernameField = new JTextField(10);
        JTextField roleField = new JTextField(10);
        JButton editButton = new JButton("Edit Role");
        JButton deleteButton = new JButton("Delete User");
        JButton backButton = new JButton("Back");

        actionPanel.add(new JLabel("Username:"));
        actionPanel.add(usernameField);
        actionPanel.add(new JLabel("Role:"));
        actionPanel.add(roleField);
        actionPanel.add(editButton);
        actionPanel.add(deleteButton);
        actionPanel.add(backButton);

        userManagementPanel.add(actionPanel, BorderLayout.SOUTH);

        // Action Listener for Edit Button
        editButton.addActionListener(e -> {
            String username = usernameField.getText();
            String newRole = roleField.getText();
            if (!username.isEmpty() && !newRole.isEmpty()) {
                boolean success = Utils.modifyUserRole(username, newRole);
                if (success) {
                    JOptionPane.showMessageDialog(this, "User role updated successfully!");
                    reloadUserTable();
                } else {
                    JOptionPane.showMessageDialog(this, "User not found!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username and role are required!");
            }
        });

        // Action Listener for Delete Button
        deleteButton.addActionListener(e -> {
            String username = usernameField.getText();
            if (!username.isEmpty()) {
                boolean success = Utils.deleteUser(username);
                if (success) {
                    JOptionPane.showMessageDialog(this, "User deleted successfully!");
                    reloadUserTable();
                } else {
                    JOptionPane.showMessageDialog(this, "User not found!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username is required!");
            }
        });

        // Action Listener for Back Button
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Dashboard"));

        return userManagementPanel;
    }

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
        }
        return userData.toArray(new String[0][]);
    }

    private void reloadUserTable() {
        String[] columnNames = {"Username", "Role", "UserID"};
        userTable.setModel(new DefaultTableModel(loadUserData(), columnNames));
    }
}
