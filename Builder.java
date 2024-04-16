// Define a class named Builder which extends the Loan class
public class Builder extends Loan {
    // Declare a private attribute to store the overpayment percentage
    private double overpaymentPercentage;

    // Parameterized constructor to initialize a Builder loan with provided values
    public Builder(String record_ID, String loan_Type, double interest_Rate, double ammount_Left, double loan_Term_Left, double overpaymentPercentage) {
        // Call the superclass constructor to initialize inherited attributes
        super(record_ID, loan_Type, interest_Rate, ammount_Left, loan_Term_Left);
        // Initialize the overpayment percentage attribute
        this.overpaymentPercentage = overpaymentPercentage;
    }

    // Default constructor to initialize a Builder loan with default values
    public Builder() {
        // Call the parameterized constructor with default values
        this("000000", "DEFAULT", 0, 0, 0, 0);
    }

    // Override the printInfo() method to display Builder loan information
    @Override
    public void printInfo() {
        // Print Builder loan information
        System.out.println("Default Loan Information:");
        System.out.println("Record ID: " + getRecord_ID());
        System.out.println("Loan Type: " + getLoan_Type());
        System.out.println("Interest Rate: " + getInterest_Rate());
        System.out.println("Amount Left: " + getAmmount_Left());
        System.out.println("Loan Term Left: " + getLoan_Term_Left());
        System.out.println("Overpayment %: " + getOverpaymentPercentage());
    }

    // Override the getOverpaymentPercentage() method to return the overpayment percentage
    @Override
    public double getOverpaymentPercentage() {
        return overpaymentPercentage;
    }

    // Define a method to set the overpayment percentage
    public void setOverpaymentPercentage(double overpaymentPercentage) {
        this.overpaymentPercentage = overpaymentPercentage;
    }
}
