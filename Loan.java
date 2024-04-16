// Define an abstract class named Loan
public abstract class Loan {
    // Declare final attributes to store loan details
    private final String record_ID;
    private String loan_Type;
    private double interest_Rate;
    private double ammount_Left;
    private double loan_Term_Left;

    // Parameterized constructor to initialize loan details
    public Loan(String record_ID, String loan_Type, double interest_Rate, double ammount_Left, double loan_Term_Left) {
        this.record_ID = record_ID;
        this.loan_Type = loan_Type;
        this.interest_Rate = interest_Rate;
        this.ammount_Left = ammount_Left;
        this.loan_Term_Left = loan_Term_Left;
    }

    // Abstract method to print loan information
    public abstract void printInfo();

    // Abstract method to get overpayment percentage
    public abstract double getOverpaymentPercentage();

    // Getter methods to retrieve loan details
    public String getRecord_ID() {
        return record_ID;
    }

    public String getLoan_Type() {
        return loan_Type;
    }

    public double getInterest_Rate() {
        return interest_Rate;
    }

    public double getAmmount_Left() {
        return ammount_Left;
    }

    public double getLoan_Term_Left() {
        return loan_Term_Left;
    } 
}
