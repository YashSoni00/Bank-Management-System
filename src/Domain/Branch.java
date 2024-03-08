package Domain;

public class Branch {
    private String bankName; // FOREIGN KEY
    private int branchManagerID;
    private String branchName;
    private String branchIFSC; // PRIMARY KEY
    private String address;
    private String phone;

    public Branch(String bankName, int branchManagerID, String branchName, String branchIFSC, String address, String phone) {
        this.bankName = bankName;
        this.branchManagerID = branchManagerID;
        this.branchName = branchName;
        this.branchIFSC = branchIFSC;
        this.address = address;
        this.phone = phone;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchIFSC() {
        return branchIFSC;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
