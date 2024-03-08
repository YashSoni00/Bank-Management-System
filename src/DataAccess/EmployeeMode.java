package DataAccess;

import Domain.Customer;

import java.util.Random;
import java.util.Scanner;

// Emp can log in on teller mode
public class EmployeeMode {
    Scanner sc = new Scanner(System.in);
    Customer cust = new Customer();

    public void changePassword() {
        System.out.println("Enter your customer ID: ");
        String customerID = sc.nextLine();
        System.out.println("Enter your current password: ");
        String currentPassword = sc.nextLine();
        System.out.println("Enter your new password: ");
        String newPassword = sc.nextLine();
        if (cust.customerExists(customerID, currentPassword)) {
            cust.changePassword(customerID, newPassword);
        } else {
            System.out.println("Invalid credentials");
        }
    }
    public void registerNewCustomer() {
        System.out.println("Enter your PAN: ");
        String PAN = sc.nextLine();

        if (!cust.customerExists(PAN)) {
            System.out.println("Account already exists.");
        } else {
            System.out.println("Enter your name: ");
            String name = sc.nextLine();
            System.out.println("Enter your address: ");
            String address = sc.nextLine();
            System.out.println("Enter your phone: ");
            long phone = getPhoneNumber();
            System.out.println("Enter your email: ");
            String email = getEmail();
            System.out.println("Enter your password: ");
            String password = sc.nextLine();
            Random rand = new Random();
            String customerID = "C" + rand.nextInt(1000);

            cust.addCustomer(PAN, name, address, phone, email, password, customerID);
        }
    }

    private String getEmail() {
        String email = sc.nextLine();
        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("Invalid email. Please enter a valid email: ");
            return getEmail();
        }
        return email;
    }

    private long getPhoneNumber() {
        long phone = sc.nextLong();
        if (String.valueOf(phone).length() != 10) {
            System.out.println("Invalid phone number. Please enter a valid phone number: ");
            return getPhoneNumber();
        }
        return phone;
    }
}
