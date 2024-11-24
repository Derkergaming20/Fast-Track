package FastTrackGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {

    public static void main(String[] args) {

        JFrame loginFrame = new JFrame("FastTrack Solutions Login");
        ImageIcon logo = new ImageIcon(/*"need logo"*/);
        loginFrame.setSize(600, 600);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);
        loginFrame.setResizable(false);
        loginFrame.setIconImage(logo.getImage());
        loginFrame.getContentPane().setBackground(new Color(0x87CEEB));

        JFrame signInFrame = new JFrame("FastTrack Solutions SignUp");
        ImageIcon logo1 = new ImageIcon(/*"need logo"*/);
        signInFrame.setSize(600, 600);
        signInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signInFrame.setLayout(null);
        signInFrame.setResizable(false);
        signInFrame.setIconImage(logo1.getImage());
        signInFrame.getContentPane().setBackground(new Color(0x87CEEB));

        // Create the Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.white);
        loginPanel.setBounds(50, 70, 480, 400);
        loginPanel.setLayout(null);

        JLabel loginTitleLabel = new JLabel("Welcome to FastTrack Solutions");
        loginTitleLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        loginTitleLabel.setBounds(90, 20, 300, 100);
        loginPanel.add(loginTitleLabel);
        
        JLabel loginTitleLabel1 = new JLabel("Login to your account.");
        loginTitleLabel1.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        loginTitleLabel1.setBounds(140, 60, 300, 100);
        loginPanel.add(loginTitleLabel1);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setBounds(50, 140, 200, 30);
        loginPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 170, 380, 30);
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setBounds(50, 210, 200, 30);
        loginPanel.add(passwordLabel);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(50, 240, 380, 30);
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 290, 100, 30);
        loginButton.setFocusable(false);
        loginPanel.add(loginButton);

        JButton signInButton = new JButton("Sign Up");
        signInButton.setBounds(280, 290, 100, 30);
        signInButton.setFocusable(false);
        loginPanel.add(signInButton);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                System.out.println("Username: " + username);
                System.out.println("Password: " + password);

                showCustomDialog(loginFrame, "Login Successful!");
            }
        });

        // Action listener for the sign-in button
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide login frame and show sign-in frame
                loginFrame.setVisible(false);
                signInFrame.setVisible(true);
            }
        });

        // Create the SignIn Panel
        JPanel signInPanel = new JPanel();
        signInPanel.setBackground(Color.white);
        signInPanel.setBounds(50, 70, 480, 400);
        signInPanel.setLayout(null);
        
        JLabel signInTitleLabel = new JLabel("Welcome to FastTrack Solutions");
        signInTitleLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        signInTitleLabel.setBounds(90, 20, 300, 100);
        signInPanel.add(signInTitleLabel);

        JLabel signInTitleLabel1 = new JLabel("Create Your Account");
        signInTitleLabel1.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        signInTitleLabel1.setBounds(140, 60, 300, 100);
        signInPanel.add(signInTitleLabel1);

        JLabel signInUsernameLabel = new JLabel("Username");
        signInUsernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        signInUsernameLabel.setBounds(50, 140, 200, 30);
        signInPanel.add(signInUsernameLabel);

        JTextField signInUsernameField = new JTextField();
        signInUsernameField.setBounds(50, 170, 380, 30);
        signInPanel.add(signInUsernameField);

        JLabel signInPasswordLabel = new JLabel("Password");
        signInPasswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        signInPasswordLabel.setBounds(50, 210, 200, 30);
        signInPanel.add(signInPasswordLabel);

        JTextField signInPasswordField = new JTextField();
        signInPasswordField.setBounds(50, 240, 380, 30);
        signInPanel.add(signInPasswordField);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(90, 290, 100, 30);
        signUpButton.setFocusable(false);
        signInPanel.add(signUpButton);

        JButton backToLoginButton = new JButton("Back to Login");
        backToLoginButton.setBounds(230, 290, 150, 30);
        backToLoginButton.setFocusable(false);
        signInPanel.add(backToLoginButton);

        // Action listener for the back-to-login button
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide sign-in frame and show login frame
                signInFrame.setVisible(false);
                loginFrame.setVisible(true);
            }
        });

        // Action listener for the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = signInUsernameField.getText();
                String password = signInPasswordField.getText();

                System.out.println("Sign Up - Username: " + username);
                System.out.println("Sign Up - Password: " + password);

                showCustomDialog(signInFrame, "Account Created Successfully!");
            }
        });

        // Add the panels to the frames
        loginFrame.add(loginPanel);
        signInFrame.add(signInPanel);

        // Initially, show the login frame
        loginFrame.setVisible(true);
        signInFrame.setVisible(false);
    }

    // Method to show a custom dialog without borders
    private static void showCustomDialog(JFrame parentFrame, String message) {
        JDialog dialog = new JDialog(parentFrame, "Success", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parentFrame);

        // Create a panel with no border for the message
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        JLabel label = new JLabel(message);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(label);

        dialog.add(panel, BorderLayout.CENTER);

        // Button to close the dialog
        JButton closeButton = new JButton("OK");
        closeButton.setFocusable(false);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}
