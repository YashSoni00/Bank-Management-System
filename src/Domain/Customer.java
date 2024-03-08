package Domain;

import java.sql.*;

public class Customer {
    private String name;
    private String PAN; // PRIMARY KEY
    private String address;
    private String phone;
    private String email;
    private String customerID;
    private String password;

    public void signIn(String customerID, String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system",
                    "root", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer WHERE customerID = '" + customerID + "' AND password = '" + password + "'");
            if (rs.next()) {
                System.out.println("Sign In Successful");
                // Get the customer details
                this.PAN = rs.getString("PAN");
                this.name = rs.getString("name");
                this.address = rs.getString("address");
                this.phone = rs.getString("phone");
                this.email = rs.getString("email");
                this.customerID = rs.getString("customerID");
                this.password = rs.getString("password");
            } else {
                System.out.println("Invalid Credentials");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCustomer(String PAN, String name, String address, long phone, String email, String password, String customerID) {
        try {
            // Build the connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
                    "password");
            Statement stmt = conn.createStatement();

            // Ready the query and execute
            String query = "INSERT INTO customer (PAN, name, address, phone, email, password, customerID) VALUES ('" + PAN + "', '"
                    + name + "', '" + address + "', '" + phone + "', '" + email + "', '" + password + "' , '" + customerID + "')";
            stmt.executeUpdate(query);
            System.out.println("Customer Added Successfully");
            System.out.println("Your Customer ID is: " + customerID);
            System.out.println("Please remember this ID for future reference");

            // Close the connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in Creating New Customer");
            e.printStackTrace();
        }
    }

    public boolean customerExists(String pan) {
        // Check if account exists in database
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system",
                    "root", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer WHERE PAN = '" + pan + "'");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean customerExists(String customerID, String currentPassword) {
        // Check if account exists in database
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system",
                    "root", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer WHERE customerID = '" + customerID + "' AND password = '" + currentPassword + "'");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void changePassword(String customerID, String newPassword) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system",
                    "root", "password");
            Statement stmt = conn.createStatement();
            String query = "UPDATE customer SET password = '" + newPassword + "' WHERE customerID = '" + customerID + "'";
            stmt.executeUpdate(query);
            System.out.println("Password Changed Successfully");
        } catch (SQLException e) {
            System.out.println("Error in Changing Password");
            e.printStackTrace();
        }
    }

    public void updateCustomer(String customerID, String name, String address, String phone, String email) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system",
                    "root", "password");
            Statement stmt = conn.createStatement();
            String query = "UPDATE customer SET name = '" + name + "', address = '" + address + "', phone = '" + phone + "', email = '" + email + "' WHERE customerID = '" + customerID + "'";
            stmt.executeUpdate(query);
            System.out.println("Customer Updated Successfully");
        } catch (SQLException e) {
            System.out.println("Error in Updating Customer");
            e.printStackTrace();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}