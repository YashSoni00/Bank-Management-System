package Domain;

import java.sql.*;

public class Account {
    private String accountNumber; // PRIMARY KEY
    private String bankIFSC; // FOREIGN KEY
    private String PAN; // FOREIGN KEY
    private double balance;

    public void withdraw(String accountNumber, double amount) {
        try {
            // Build the connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
                    "password");
            Statement stmt = conn.createStatement();

            // Ready the query and execute
            String query = "SELECT balance FROM account WHERE accountNumber = '" + accountNumber + "'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            balance = rs.getDouble(1);

            if (balance < amount) {
                System.out.println("Insufficient Balance");
            } else {
                balance -= amount;
                query = "UPDATE account SET balance = " + balance + " WHERE accountNumber = '" + accountNumber + "'";
                stmt.executeUpdate(query);
                System.out.println("Withdrawal Successful");
                System.out.println("New Balance: " + balance);
            }

            // Close the connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in Withdrawing");
            e.printStackTrace();
        }
    }

    public void transfer(String accountNumber, String receiverAccountNumber, double amount) {
        try {
            // Build the connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
                    "password");
            Statement stmt = conn.createStatement();

            // Ready the query and execute
            String query = "SELECT balance FROM account WHERE accountNumber = '" + accountNumber + "'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            double balance = rs.getDouble(1);

            // Check if the receiver account exists
            query = "SELECT balance FROM account WHERE accountNumber = '" + receiverAccountNumber + "'";
            rs = stmt.executeQuery(query);
            if (!rs.next()) {
                System.out.println("Receiver Account does not exist");
                return;
            }

            if (balance < amount) {
                System.out.println("Insufficient Balance");
            } else {
                balance -= amount;

                // Update the sender's balance
                stmt.executeQuery("UPDATE account SET balance = " + balance + " WHERE accountNumber = '" + accountNumber + "'");

                // Update the receiver's balance
                stmt.executeQuery("SELECT balance FROM account WHERE accountNumber = '" + receiverAccountNumber + "'");
                rs.next();
                double receiverBalance = rs.getDouble(1) + amount;
                stmt.executeQuery("UPDATE account SET balance = " + receiverBalance + " WHERE accountNumber = '" + receiverAccountNumber + "'");

                System.out.println("Transfer Successful");
                System.out.println("New Balance: " + balance);
            }

            // Close the connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in Transferring");
            e.printStackTrace();
        }
    }

    public void deposit(String accountNumber, double amount) {
        try {
            // Build the connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
                    "password");
            Statement stmt = conn.createStatement();

            // Ready the query and execute
            String query = "SELECT balance FROM account WHERE accountNumber = '" + accountNumber + "'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            double balance = rs.getDouble(1);

            balance += amount;
            query = "UPDATE account SET balance = " + balance + " WHERE accountNumber = '" + accountNumber + "'";
            stmt.executeUpdate(query);
            System.out.println("Deposit Successful");
            System.out.println("New Balance: " + balance);

            // Close the connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in Depositing");
            e.printStackTrace();
        }
    }

    public void getAccounts(String customerID, String bankIFSC) {
        try {
            // Build the connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
                    "password");
            Statement stmt = conn.createStatement();

            // Ready the query and execute
            String query = "SELECT * FROM account WHERE customerID = '" + customerID + "' AND bankIFSC = '" + bankIFSC + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("Account Number: " + rs.getString(1) + ", IFSC: " + rs.getString(2) + ", PAN: " + rs.getString(3) + ", Balance: " + rs.getDouble(4));
            }

            // Close the connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in Getting Accounts");
            e.printStackTrace();
        }
    }

    public void closeAccount(String accountNumber) {
        try {
            // Build the connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
                    "password");
            Statement stmt = conn.createStatement();

            // Ready the query and execute
            String query = "DELETE FROM account WHERE accountNumber = '" + accountNumber + "'";
            stmt.executeUpdate(query);
            System.out.println("Account Closed Successfully");

            // Close the connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in Closing Account");
            e.printStackTrace();
        }
    }
}
