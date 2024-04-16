// Define a class named Personal which extends the Loan class
public class Personal extends Loan {
    // Parameterized constructor to initialize a Personal loan with provided values
    public Personal(String record_ID, String loan_Type, double interest_Rate, double amount_Left, double loan_Term_Left) {
        // Call the superclass constructor to initialize inherited attributes
        super(record_ID, loan_Type, interest_Rate, amount_Left, loan_Term_Left);
    }

    // Default constructor to initialize a Personal loan with default values
    public Personal() {
        // Call the parameterized constructor with default values
        this("000000", "DEFAULT", 0, 0, 0);
    }

    // Override the printInfo() method to display Personal loan information
    @Override
    public void printInfo() {
        // Print Personal loan information
        System.out.println("Default Loan Information:");
        System.out.println("Record ID: " + getRecord_ID());
        System.out.println("Loan Type: " + getLoan_Type());
        System.out.println("Interest Rate: " + getInterest_Rate());
        System.out.println("Amount Left: " + getAmmount_Left());
        System.out.println("Loan Term Left: " + getLoan_Term_Left());
    }

    // Override the getOverpaymentPercentage() method to return 0 (as Personal loan does not have an overpayment option)
    @Override
    public double getOverpaymentPercentage() {
        return 0; 
    }
}
