package FastTrack_Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginWithAdmin {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("----Welcome to Fastrack----");
			System.out.println("(1) Login\n(2) Sign Up\n(3) Update Password\n(4) Delete User\n(5) Exit");
			System.out.print("Enter Choice: ");
			int choice = input.nextInt();
			input.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter Username: ");
				String userName = input.nextLine();
				System.out.print("Enter Password: ");
				String password = input.nextLine();

				if (login(userName, password)) {
					System.out.println("Login Successful!");
				} else {
					System.out.println("Invalid Username or Password.");
				}
				break;

			case 2:
				System.out.print("Enter Username: ");
				userName = input.nextLine();
				System.out.print("Enter Password: ");
				password = input.nextLine();

				if (signUp(userName, password)) {
					System.out.println("Sign up successful!");
				} else {
					System.out.println("Username already exists. Try a different one.");
				}
				break;

			case 3:
				System.out.print("Enter Username to Update: ");
				userName = input.nextLine();
				System.out.print("Enter New Password: ");
				password = input.nextLine();

				if (updatePassword(userName, password)) {
					System.out.println("Password updated successfully.");
				} else {
					System.out.println("User not found.");
				}
				break;

			case 4:
				System.out.print("Enter Username to Delete: ");
				userName = input.nextLine();

				if (deleteUser(userName)) {
					System.out.println("User deleted successfully.");
				} else {
					System.out.println("User not found.");
				}
				break;

			case 5:
				System.out.println("Exiting...");
				running = false;
				break;

			default:
				System.out.println("Invalid choice. Please select 1, 2, 3, 4, 5, or 6.");
			}
		}

		input.close();
	}

	public static boolean login(String userName, String password) {
		try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] credentials = line.split(",");
				if (credentials[0].equals(userName) && credentials[1].equals(password)) {
					return true;
				}
			}
		} catch (IOException e) {
			System.out.println("An error occurred while reading the file.");
			e.printStackTrace();
		}
		return false;
	}

	public static boolean signUp(String userName, String password) {
		try {

			if (login(userName, password)) {
				return false;
			}

			try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
				writer.write(userName + "," + password);
				writer.newLine();
				return true;
			}
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}
		return false;
	}

	
	//User Admin line 130 - 209
	public static boolean updatePassword(String userName, String newPassword) {
		File file = new File("users.txt");
		List<String> users = new ArrayList<>();
		boolean updated = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] credentials = line.split(",");
				if (credentials[0].equals(userName)) {
					users.add(userName + "," + newPassword);
					updated = true;
				} else {
					users.add(line);
				}
			}
		} catch (IOException e) {
			System.out.println("An error occurred while reading the file.");
			e.printStackTrace();
		}

		if (updated) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
				for (String user : users) {
					writer.write(user);
					writer.newLine();
				}
			} catch (IOException e) {
				System.out.println("An error occurred while writing to the file.");
				e.printStackTrace();
			}
		}
		return updated;
	}

	public static boolean deleteUser(String userName) {
		File file = new File("users.txt");
		List<String> users = new ArrayList<>();
		boolean deleted = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] credentials = line.split(",");
				if (!credentials[0].equals(userName)) {
					users.add(line);
				} else {
					deleted = true;
				}
			}
		} catch (IOException e) {
			System.out.println("An error occurred while reading the file.");
			e.printStackTrace();
		}

		if (deleted) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
				for (String user : users) {
					writer.write(user);
					writer.newLine();
				}
			} catch (IOException e) {
				System.out.println("An error occurred while writing to the file.");
				e.printStackTrace();
			}
		}
		return deleted;
	}
}
