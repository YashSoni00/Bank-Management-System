package Domain;

public class Employee {
    private String name;
    private int employeeID; // PRIMARY KEY
    private String email;
    private String phone;
    private String bankName; // FOREIGN KEY
    private String IFSC; // FOREIGN KEY

    public Employee(String name, int employeeID, String email, String phone, String bankName, String IFSC) {
        this.name = name;
        this.employeeID = employeeID;
        this.email = email;
        this.phone = phone;
        this.bankName = bankName;
        this.IFSC = IFSC;
    }

    public void signIn(String login, String password) {
    }

    public void changePassword() {
    }

    public void registerNewCustomer() {
    }

    public void viewCustomerInformation() {
    }

    public void manageCustomerAccounts() {
    }
}
