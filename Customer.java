import java.util.ArrayList;

// Define a class named Customer implementing the CheckerPrinter interface
public class Customer implements CheckerPrinter {
    // Declare attributes to store customer details
    private final String customerID; // Unique identifier for the customer
    private Double annual_Income; // Customer's annual income
    private boolean isEligible; // Eligibility status
    private ArrayList<Loan> loan_Records; // List of loan records associated with the customer
    
    // Parameterized constructor to initialize customer details
    public Customer(String customerID, Double annual_Income) {
        this.customerID = customerID;
        this.annual_Income = annual_Income;
        this.loan_Records = new ArrayList<>(); // Initialize loan records list
    }
    
    // Method to add a loan record to the customer's list of loans
    public void add_Loan(Loan loan) {
        loan_Records.add(loan);
    }

    // Method to remove a loan record from the customer's list of loans
    public void remove_Loan(String element) {
        this.loan_Records.remove(XYZBank.recordIDS.get(element)); // Assuming XYZBank.recordIDS is a map linking record ID strings to Loan objects
    }

    // Getter method to retrieve customer ID
    public String getCustomerID() {
        return customerID;
    }

    // Getter method to retrieve annual income
    public Double getAnnual_Income() {
        return annual_Income;
    }

    // Setter method to set annual income
    public void setAnnual_Income(Double annual_Income) {
        this.annual_Income = annual_Income;
    }

    // Override the printDetails() method to print customer details
    @Override
    public void printDetails() {
        System.out.println("Customer ID: " + customerID);
        System.out.println("Annual Income: " + annual_Income);
        System.out.println("Eligible: " + isEligible);
        System.out.println("Loan Records:");
        for (Loan loan : loan_Records) {
            loan.printInfo(); // Assuming Loan has a printInfo() method
        }
        System.out.println();
    }

    // Override the isEligible() method to determine eligibility based on income and debt
    @Override
    public boolean isEligible() {
        if (getCurrentDebt() <= 4 * this.getAnnual_Income()) {
            return true;
        } else {
            return false;
        }
    }

    // Method to calculate the current debt of the customer
    public double getCurrentDebt() {
        double totalDebt = 0;
        for (Loan loan : loan_Records) {
            totalDebt += loan.getAmmount_Left();
        }
        return totalDebt;
    }

    // Setter method to set eligibility status
    public void setEligible(boolean isEligible) {
        this.isEligible = isEligible;
    }

    // Getter method to retrieve the list of loan records
    public ArrayList<Loan> getLoan_Records() {
        return loan_Records;
    }

    // Setter method to set the list of loan records
    public void setLoan_Records(ArrayList<Loan> loan_Records) {
        this.loan_Records = loan_Records;
    }
}
