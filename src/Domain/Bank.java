package Domain;

public class Bank {
    private String name; // PRIMARY KEY
    private String address;
    public Bank(String name, String address, String IFSC) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
