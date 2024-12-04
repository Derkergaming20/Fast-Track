package FastTrackGUI;

public class Main {
    public static void main(String[] args) {
        // Create instances of the main GUI components
        Login loginFrame = new Login(); 
        Signup signUpFrame = new Signup(loginFrame);  // Pass the login frame reference to the signup frame
        AdminDashboard adminFrame = new AdminDashboard(loginFrame);  // Pass the login frame reference to the admin dashboard

        // Establish relationships between the frames
        loginFrame.setSignUpFrame(signUpFrame);
        loginFrame.setAdminFrame(adminFrame);

        // Display the login frame as the starting point
        loginFrame.setVisible(true);
    }
}
