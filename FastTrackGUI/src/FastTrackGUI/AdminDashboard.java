package FastTrackGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminDashboard extends JFrame {
    private Login loginFrame;

    public AdminDashboard(Login loginFrame) {
        this.loginFrame = loginFrame;

        // Set up the frame
        setTitle("Admin Dashboard");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon("FTSLogo.png").getImage());
        getContentPane().setBackground(new Color(0x87CEEB));

        // Add components
        JLabel dashboardTitle = new JLabel("Admin Dashboard");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 24));
        dashboardTitle.setBounds(200, 30, 250, 30);
        add(dashboardTitle);

        JButton manageUsersButton = new JButton("Manage Users");
        manageUsersButton.setBounds(100, 100, 400, 40);
        add(manageUsersButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(100, 200, 400, 40);
        add(logoutButton);

        // Action listeners
        manageUsersButton.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "User Management options here.")
        );

        logoutButton.addActionListener(e -> {
            setVisible(false);       // Hide this frame
            loginFrame.setVisible(true);  // Show the login frame
        });
    }
}
