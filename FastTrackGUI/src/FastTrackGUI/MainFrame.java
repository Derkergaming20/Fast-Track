package FastTrackGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MainFrame {

    private static final String USER_FILE = "users.txt";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        // Main Login Frame
        JFrame loginFrame = new JFrame("FastTrack Solutions Login");
        ImageIcon logo = new ImageIcon("FTSLogo.png");
        loginFrame.setSize(600, 600);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);
        loginFrame.setResizable(false);
        loginFrame.setIconImage(logo.getImage());
        loginFrame.getContentPane().setBackground(new Color(0x87CEEB));
        
        try {
            ImageIcon logoIcon = new ImageIcon("FTSLogo.png"); // Replace with the path to your logo
            loginFrame.setIconImage(logoIcon.getImage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading logo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Sign-Up Frame
        JFrame signUpFrame = new JFrame("FastTrack Solutions Sign Up");
        ImageIcon logo1 = new ImageIcon("FTSLogo.png");
        signUpFrame.setSize(600, 600);
        signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpFrame.setLayout(null);
        signUpFrame.setResizable(false);
        loginFrame.setIconImage(logo1.getImage());
        signUpFrame.getContentPane().setBackground(new Color(0x87CEEB));
        
        try {
            ImageIcon logoIcon = new ImageIcon("FTSLogo.png"); // Replace with the path to your logo
            signUpFrame.setIconImage(logoIcon.getImage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading logo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Admin Actions Frame
        JFrame adminFrame = new JFrame("Admin Actions");
        adminFrame.setSize(600, 600);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setLayout(null);
        adminFrame.setResizable(false);
        adminFrame.getContentPane().setBackground(new Color(0x87CEEB));

        // Login Panel
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

        // Login Action Listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    showCustomDialog(loginFrame, "Username and Password cannot be empty.");
                    return;
                }

                String encryptedPassword = encryptPassword(password);
                String[] userDetails = login(username, encryptedPassword);

                if (userDetails != null) {
                    if ("Admin".equals(userDetails[3])) {
                        showCustomDialog(loginFrame, "Admin login successful!");
                        loginFrame.setVisible(false);
                        adminFrame.setVisible(true);
                    } else {
                        showCustomDialog(loginFrame, "Login successful! Welcome, " + userDetails[1]);
                    }
                } else {
                    showCustomDialog(loginFrame, "Invalid Username or Password. Please try again.");
                }
            }
        });

        // Open Sign-Up Frame
        signUpButton.addActionListener(e -> {
            loginFrame.setVisible(false);
            signUpFrame.setVisible(true);
        });

        // Admin Panel
        JButton displayUsersButton = new JButton("Display All Users");
        displayUsersButton.setBounds(100, 100, 200, 40);
        adminFrame.add(displayUsersButton);

        JButton modifyRoleButton = new JButton("Modify User Role");
        modifyRoleButton.setBounds(100, 160, 200, 40);
        adminFrame.add(modifyRoleButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(100, 220, 200, 40);
        adminFrame.add(logoutButton);

        // Display Users Action
        displayUsersButton.addActionListener(e -> displayUsers());

        // Modify Role Action
        modifyRoleButton.addActionListener(e -> {
            String targetUsername = JOptionPane.showInputDialog(adminFrame, "Enter username to modify role:");
            String newRole = JOptionPane.showInputDialog(adminFrame, "Enter new role (User/Admin):");

            if (targetUsername != null && newRole != null) {
                changeUserRole(ADMIN_USERNAME, targetUsername, newRole);
                showCustomDialog(adminFrame, "Role updated successfully!");
            }
        });

        // Logout Action
        logoutButton.addActionListener(e -> {
            adminFrame.setVisible(false);
            loginFrame.setVisible(true);
        });

        // Sign-Up Panel
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

        // Register Action Listener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = newUsernameField.getText().trim();
                String password = new String(newPasswordField.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    showCustomDialog(signUpFrame, "Username and Password cannot be empty.");
                    return;
                }

                String role = ADMIN_USERNAME.equals(username) ? "Admin" : "User";
                String userId = generateUserId();
                String encryptedPassword = encryptPassword(password);

                if (signUp(username, encryptedPassword, role, userId)) {
                    showCustomDialog(signUpFrame, "Account created successfully!");
                } else {
                    showCustomDialog(signUpFrame, "Username already exists. Please try another.");
                }
            }
        });

        // Back to Login
        backToLoginButton.addActionListener(e -> {
            signUpFrame.setVisible(false);
            loginFrame.setVisible(true);
        });

        loginFrame.add(loginPanel);
        signUpFrame.add(signUpPanel);

        loginFrame.setVisible(true);
        signUpFrame.setVisible(false);
        adminFrame.setVisible(false);
    }

    // Utility Methods

    private static void showCustomDialog(JFrame parentFrame, String message) {
        JOptionPane.showMessageDialog(parentFrame, message, "Notification", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static boolean signUp(String username, String password, String role, String userId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equalsIgnoreCase(username)) return false;
            }
            writer.write(userId + "," + username + "," + password + "," + role);
            writer.newLine();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private static String[] login(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equalsIgnoreCase(username) && parts[2].equals(password)) {
                    return parts;
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    private static void displayUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            StringBuilder users = new StringBuilder("All Registered Users:\n\n");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                users.append("ID: ").append(parts[0])
                        .append(", Username: ").append(parts[1])
                        .append(", Role: ").append(parts[3])
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, users.toString(), "Users", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading user data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void changeUserRole(String adminUsername, String targetUsername, String newRole) {
        File inputFile = new File(USER_FILE);
        File tempFile = new File("users_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equalsIgnoreCase(targetUsername)) {
                    parts[3] = newRole;
                    line = String.join(",", parts);
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating user data!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }

    private static String generateUserId() {
        return "I" + (100 + RANDOM.nextInt(999));
    }
}
