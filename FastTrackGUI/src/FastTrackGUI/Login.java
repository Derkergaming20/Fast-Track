package FastTrackGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private Signup signUpFrame;
    private AdminDashboard adminFrame;

    public Login() {
        // Set up the login frame
        setTitle("FastTrack Solutions Login");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon("FTSLogo.png").getImage());
        getContentPane().setBackground(new Color(0x87CEEB));

        // Set up the login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.white);
        loginPanel.setBounds(50, 70, 480, 400);
        loginPanel.setLayout(null);

        JLabel loginTitle = new JLabel("Login to FastTrack Solutions");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 20));
        loginTitle.setBounds(120, 20, 300, 30);
        loginPanel.add(loginTitle);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setBounds(50, 100, 200, 30);
        loginPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 130, 380, 30);
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setBounds(50, 180, 200, 30);
        loginPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 210, 380, 30);
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 270, 100, 30);
        loginPanel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(280, 270, 100, 30);
        loginPanel.add(signUpButton);

        // Add login action listener
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                showCustomDialog("Username and Password cannot be empty.");
                return;
            }

            String encryptedPassword = Utils.encryptPassword(password);
            String[] userDetails = Utils.login(username, encryptedPassword);

            if (userDetails != null) {
                if ("Admin".equals(userDetails[2])) { // Check if role is Admin
                    showCustomDialog("Admin login successful!");
                    setVisible(false);
                    adminFrame.setVisible(true); // Show admin dashboard
                } else {
                    showCustomDialog("Login successful! Welcome, " + userDetails[0]);
                }
            } else {
                showCustomDialog("Invalid Username or Password. Please try again.");
            }
        });

        // Open Sign-Up Frame
        signUpButton.addActionListener(e -> {
            setVisible(false);
            signUpFrame.setVisible(true); // Show sign-up screen
        });

        add(loginPanel);
    }

    public void setSignUpFrame(Signup signUpFrame) {
        this.signUpFrame = signUpFrame;
    }

    public void setAdminFrame(AdminDashboard adminFrame) {
        this.adminFrame = adminFrame;
    }

    private void showCustomDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
}
