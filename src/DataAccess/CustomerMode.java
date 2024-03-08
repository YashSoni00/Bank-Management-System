package DataAccess;

import Domain.Customer;

import java.sql.*;
import java.util.Scanner;

public class CustomerMode {
    Customer customer = new Customer();
    public void signIn(String customerID, String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system",
                    "root", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer WHERE customerID = '" + customerID + "' AND password = '" + password + "'");
            if (rs.next()) {
                System.out.println("Sign In Successful");
                // Get the customer details
                customer.setPAN(rs.getString("PAN"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setCustomerID(rs.getString("customerID"));
                customer.setPassword(rs.getString("password"));
            } else {
                System.out.println("Invalid Credentials");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateCustomerDetails(String field, String value) {
        updateCustomerDetailsMenu();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
                    "password");
            Statement stmt = conn.createStatement();
            String query = "UPDATE customer SET " + field + " = '" + value + "' WHERE customerID = '" + customer.getCustomerID() + "'";
            stmt.executeUpdate(query);
            System.out.println("Customer details updated successfully");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in updating customer details");
            e.printStackTrace();
        }
    }

    private void updateCustomerDetailsMenu() {
        // Update name, address, phone, email
        System.out.println("1. Update Name");
        System.out.println("2. Update Address");
        System.out.println("3. Update Phone");
        System.out.println("4. Update Email");
        System.out.println("5. Exit");
        System.out.println("Enter your choice: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        do {
            switch (choice) {
                case 1:
                    System.out.println("Enter your new name: ");
                    String name = sc.nextLine();
                    customer.setName(name);
                    updateCustomerDetails("name", name);
                    break;
                case 2:
                    System.out.println("Enter your new address: ");
                    String address = sc.nextLine();
                    customer.setAddress(address);
                    updateCustomerDetails("address", address);
                    break;
                case 3:
                    System.out.println("Enter your new phone: ");
                    String phone = sc.nextLine();
                    customer.setPhone(phone);
                    updateCustomerDetails("phone", phone);
                    break;
                case 4:
                    System.out.println("Enter your new email: ");
                    String email = sc.nextLine();
                    customer.setEmail(email);
                    updateCustomerDetails("email", email);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);
    }

    public void changePassword() {
        System.out.println("Enter your current password: ");
        Scanner sc = new Scanner(System.in);
        String currentPassword = sc.nextLine();
        System.out.println("Enter your new password: ");
        String newPassword = sc.nextLine();
        if (customer.getPassword().equals(currentPassword)) {
            customer.setPassword(newPassword);
            updateCustomerDetails("password", newPassword);
            System.out.println("Password changed successfully");
        } else {
            System.out.println("Invalid credentials");
        }
    }
    public void viewAccountBalance() {

    }
    public void transferMoney() {
    }
    public void withdrawFunds() {
    }
    public void submitCashDeposit() {
    }
    public void contactBankEmployees() {

    }
}
