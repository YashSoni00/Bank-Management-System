package Domain;

public class Manager {
    private String name;
    private int managerID; // PRIMARY KEY
    private String password;
    private String email;
    private String phone;
    private String address;
    private String bankName; // FOREIGN KEY
    private String IFSC; // FOREIGN KEY

    public Manager(String name, int managerID, String password, String email, String phone, String address, String bankName, String IFSC) {
        this.name = name;
        this.managerID = managerID;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bankName = bankName;
        this.IFSC = IFSC;
    }

}
