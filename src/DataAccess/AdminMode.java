package DataAccess;

import java.sql.*;
import java.util.Scanner;

public class AdminMode {
    private Connection conn;
    public void signIn(String login, String password) throws SQLException {
        if (login.equals("admin") && password.equals("password")) {
            System.out.println("Welcome, Admin!");
            adminMenu();

        } else {
            System.out.println("Invalid login or password.");
        }
    }

    private void adminMenu() throws SQLException {
        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("--- Admin Menu ---");
            System.out.println("1. View details of manager");
            System.out.println("2. View details of customer");
            System.out.println("3. Add bank branch");
            System.out.println("4. Update bank branch");
            System.out.println("5. Update manager");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewDetailsOfManager();
                    break;
                case 2:
                    viewDetailsOfCustomer();
                    break;
                case 3:
                    addBankBranch();
                    break;
                case 4:
                    updateBankBranch();
                    break;
                case 5:
                    updateManager();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    public void viewDetailsOfManager() throws SQLException {
        System.out.println("Enter manager's ID: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        // Find manager in database and display details
        System.out.println("Manager's details go here.");
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:330",
                    "root", "password");

            Statement stmt = conn.createStatement();
            stmt.executeQuery("SELECT * FROM manager WHERE id = " + id);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Address: " + rs.getString("address"));
            }

            // Close the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void viewDetailsOfCustomer() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:330",
                    "root", "password");
            Statement stmt = conn.createStatement();

            System.out.println("Enter customer's ID: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.next();
            stmt.executeQuery("SELECT * FROM customer WHERE id = " + id);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Address: " + rs.getString("address"));
            }

            // Close the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addBankBranch() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:330",
                    "root", "password");
            Statement stmt = conn.createStatement();
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter bank branch's name: ");
            String name = sc.next();
            System.out.println("Enter bank branch's address: ");
            String address = sc.next();
            stmt.executeUpdate("INSERT INTO bank_branch (name, address) VALUES ('" + name + "', '" + address + "')");

            // Close the connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    public void updateBankBranch() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter bank branch's ID: ");
        int id = sc.nextInt();
        System.out.println("Enter bank branch's name: ");
        String name = sc.next();
        System.out.println("Enter bank branch's address: ");
        String address = sc.next();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:330",
                    "root", "password");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE bank_branch SET name = '" + name + "', address = '" + address + "' WHERE id = " + id);
            // Close the connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addManager() {
    }
    public void updateManager() {
    }
}
