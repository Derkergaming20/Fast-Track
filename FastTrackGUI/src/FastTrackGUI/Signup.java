package FastTrackGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {
    private JFrame loginFrame;

    public Signup(JFrame loginFrame) {
        this.loginFrame = loginFrame;

        // Set up the sign-up frame
        setTitle("FastTrack Solutions Sign Up");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon("FTSLogo.png").getImage());
        getContentPane().setBackground(new Color(0x87CEEB));

        // Set up the sign-up panel
        JPanel signUpPanel = new JPanel();
        signUpPanel.setBackground(Color.white);
        signUpPanel.setBounds(50, 70, 480, 400);
        signUpPanel.setLayout(null);

        JLabel signUpTitle = new JLabel("Create Your FastTrack Account");
        signUpTitle.setFont(new Font("Arial", Font.BOLD, 20));
        signUpTitle.setBounds(90, 20, 300, 30);
        signUpPanel.add(signUpTitle);

        JLabel newUsernameLabel = new JLabel("Username:");
        newUsernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        newUsernameLabel.setBounds(50, 100, 200, 30);
        signUpPanel.add(newUsernameLabel);

        JTextField newUsernameField = new JTextField();
        newUsernameField.setBounds(50, 130, 380, 30);
        signUpPanel.add(newUsernameField);

        JLabel newPasswordLabel = new JLabel("Password:");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        newPasswordLabel.setBounds(50, 180, 200, 30);
        signUpPanel.add(newPasswordLabel);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBounds(50, 210, 380, 30);
        signUpPanel.add(newPasswordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 270, 100, 30);
        signUpPanel.add(registerButton);

        JButton backToLoginButton = new JButton("Back to Login");
        backToLoginButton.setBounds(280, 270, 140, 30);
        signUpPanel.add(backToLoginButton);

        // Register action listener
        registerButton.addActionListener(e -> {
            String username = newUsernameField.getText().trim();
            String password = new String(newPasswordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                showCustomDialog("Username and Password cannot be empty.");
                return;
            }

            String role = username.equalsIgnoreCase("admin") ? "Admin" : "User";
            String userId = Utils.generateUserId();
            String encryptedPassword = Utils.encryptPassword(password);

            if (Utils.signUp(username, encryptedPassword, role, userId)) {
                showCustomDialog("Account created successfully!");
            } else {
                showCustomDialog("Username already exists. Please try another.");
            }
        });

        // Back to login action
        backToLoginButton.addActionListener(e -> {
            setVisible(false);
            loginFrame.setVisible(true); // Show login screen
        });

        add(signUpPanel);
    }

    private void showCustomDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
}
