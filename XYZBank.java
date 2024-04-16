//Importing libraries I will be using
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class XYZBank {
    /**
     * Instanciates XYZBank class
     * Calls runFirstMethod as you can't call it from the static main class directly
     */
    public static void main(String[] args) {
        XYZBank xyzBank = new XYZBank();
        xyzBank.runFirstMethod();
    }

    /**
     * Calls provideuserOptions from non static class
     */
    private void runFirstMethod() {
        this.provideuserOptions();
    }

    /**
     * Creates a scanner
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Creates hashmap linking Customer objects and customerID's
     */
    private final Map<String, Customer> customers = new HashMap<>();

    /**
     * Creates a hashmap linking loan objects and recordID's
     */
    public static final Map<String, Loan> recordIDS = new HashMap<>();

    /**
     * Method to act as the user menu by using a switch case to call all other user-functional methods
     * This switch loops infinitely until it is quit
     *
     */
    private void provideuserOptions() {
        looper: while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Register new user.");
            System.out.println("2. Register new loan.");
            System.out.println("3. Display existing loans.");
            System.out.println("4. Modify Existing values.");
            System.out.println("5. Quit");
            String user_choice = getUserInput();

            switch (user_choice) {
                case "1":
                    registerNewUser();
                    break;
                case "2":
                    createLoan();
                    break;
                case "3":
                    displayLoans();
                    break;
                case "4":
                    modifyValues();
                    break;
                case "5":
                    break looper;
                default:
                    throw new IllegalStateException("Unexpected value: " + user_choice);
            }
        }
    }

    /**
    * Method to register a new user in the XYZBank system.
     */
    private void registerNewUser() {
        try { 
            // Get and validate customerID
            System.out.print("Enter customerID (in format 'AAAXXX', where A is a capital letter A-Z, and X is a digit 0-9): ");
            String customerID = getUserInput();
            customerID = validate_input(customerID, "[A-Z]{3}\\d{3}");

            // Get and validate annual_Income
            System.out.print("Enter annual income (a number in thousands of pounds): ");
            String annual_Income = getUserInput();
            annual_Income = validate_input(annual_Income, "\\b\\d+\\b");
            Double customer_Double = Double.parseDouble(annual_Income); 

            Customer customer = new Customer(customerID, customer_Double);
            System.out.println(customerID);
            customers.put(customerID, customer);
            System.out.println("Successfully registered new user");
            System.out.println();
        } catch (Exception ignnoredException) {
            System.out.println("Invalid input");
        }
    }


    /**
     * Method that handles the validation and creation of new loans
     */
    private void createLoan() {
        try {
            String customerID;
            System.out.print("Enter CustomerID for the record to be added to: ");
            customerID = getUserInput(); 

            //Ensures the input is in the specified format
            if (validate_input(customerID, "[A-Z]{3}\\d{3}") == customerID) {
                //ensures the customer already exists
                if (customers.containsKey(customerID)) {
                    //ensures the customer is eligible to take on another loan
                    if (customers.get(customerID).isEligible() == true) {

                        //displays information about the selected customer and what they can borrow
                        System.out.println("Customers annual income is: " + customers.get(customerID).getAnnual_Income()+"k");
                        System.out.println("This customer can borrow up to: " + customers.get(customerID).getAnnual_Income()*4+"k");
                        System.out.println("Customer is currently borrowing: " + customers.get(customerID).getCurrentDebt()+"k");
                        
                        String recordID;
                        System.out.print("Enter RecordID (in the format 'XXXXXX', where X is a digit 0-9): ");
                        recordID = validate_input(getUserInput(), "\\d{6}");

                        //validation to make sure all records are unique
                        if (recordIDS.containsKey(recordID)) {
                            System.out.println("RecordID already exists. Enter a new value.");
                        } else {
                            //if all constraints are met then proceed with asking for all loan details
                            String loan_Type;
                            System.out.print("Enter loan type (Auto, Builder, Mortgage, Personal, Other): ");
                            loan_Type = getUserInput();

                            String user_choice;
                            System.out.print("Enter interest rate (a number), or for a default loan enter 'DEFUALT': ");
                            user_choice = getUserInput();

                            //if a default loan is requested, the type will be taken and a default loan of this type will be made and added to the customers loan records
                            if (user_choice.equalsIgnoreCase("DEFAULT")) { 

                                if (loan_Type.equalsIgnoreCase("Auto")) {
                                    Loan loan = new Auto();
                                    customers.get(customerID).add_Loan(loan);

                                } else if (loan_Type.equalsIgnoreCase("Builder")) {
                                    Loan loan = new Builder();
                                    customers.get(customerID).add_Loan(loan);

                                } else if (loan_Type.equalsIgnoreCase("Mortgage")) {
                                    Loan loan = new Builder();
                                    customers.get(customerID).add_Loan(loan);

                                } else if (loan_Type.equalsIgnoreCase("Personal")) {
                                    Loan loan = new Personal();
                                    customers.get(customerID).add_Loan(loan);

                                } else if (loan_Type.equalsIgnoreCase("Other")) {
                                    Loan loan = new Other();
                                    customers.get(customerID).add_Loan(loan);

                                } else {
                                    System.out.println("Invalid loan type");
                                }

                            //for custom loans, all values are taken and saved
                            } else {
                                Double interest_Rate = Double.parseDouble(user_choice);

                                double ammount_Left;
                                System.out.print("Enter ammount left (in thousands of pounds): ");
                                ammount_Left = Double.parseDouble(getUserInput());

                                //validation to check if the requested loan is elligible for the current customer 
                                if (ammount_Left + customers.get(customerID).getCurrentDebt() > customers.get(customerID).getAnnual_Income()*4) {
                                    System.out.println("Inellegible for loan: you may only borrow up to 4x your annual income");

                                //if it is valid more details are taken
                                } else {
                                    double loan_Term_Left;
                                    System.out.print("Enter term left (a number): ");
                                    loan_Term_Left = Double.parseDouble(getUserInput());

                                    // finds loan type and instanciates the loan based on the loan subclass. 
                                    // loan is added to hashmap with customerID allowing for quicklinking
                                    // loan is added to hashmap with creditID to allow for remvoving a loan via its ID more simply
                                    // system prints statement with loan approval
                                    if (loan_Type.equalsIgnoreCase("Mortgage")) {
                                        System.out.print("Enter overpayment percentage (a number between 0-2): ");
                                        double overpaymentPercentage;
                                        overpaymentPercentage = Double.parseDouble(getUserInput());
                                        Loan loan = new Mortgage(recordID, loan_Type, interest_Rate, ammount_Left, loan_Term_Left, overpaymentPercentage);
                                        customers.get(customerID).add_Loan(loan);
                                        recordIDS.put(recordID, loan);
                                        System.out.println("Successfully added loan record");

                                    } else if (loan_Type.equalsIgnoreCase("Builder")) {
                                        System.out.print("Enter overpayment percentage (a number between 0-2): ");
                                        double overpaymentPercentage;
                                        overpaymentPercentage = Double.parseDouble(getUserInput());
                                        Loan loan = new Builder(recordID, loan_Type, interest_Rate, ammount_Left, loan_Term_Left, overpaymentPercentage);
                                        customers.get(customerID).add_Loan(loan);
                                        recordIDS.put(recordID, loan);
                                        System.out.println("Successfully added loan record");

                                    } else if (loan_Type.equalsIgnoreCase("Auto")){
                                        Loan loan = new Auto(recordID, loan_Type, interest_Rate, ammount_Left, loan_Term_Left);
                                        customers.get(customerID).add_Loan(loan);  
                                        recordIDS.put(recordID, loan);
                                        System.out.println("Successfully added loan record");

                                    } else if (loan_Type.equalsIgnoreCase("Personal")){
                                        Loan loan = new Personal(recordID, loan_Type, interest_Rate, ammount_Left, loan_Term_Left);
                                        customers.get(customerID).add_Loan(loan);
                                        recordIDS.put(recordID, loan);
                                        System.out.println("Successfully added loan record");

                                    } else if (loan_Type.equalsIgnoreCase("Other")){
                                        Loan loan = new Other(recordID, loan_Type, interest_Rate, ammount_Left, loan_Term_Left);
                                        customers.get(customerID).add_Loan(loan);  
                                        recordIDS.put(recordID, loan);
                                        System.out.println("Successfully added loan record");
                                    }
                                } 
                            }
                            
                        }

                        
                // validation for invalid customerID
                } else { 
                    System.out.println("User not found");
                }
            // validation for invalid customerID
            } else {
                System.out.println("User not recognised");
            }
        } 
    // catch statement for invalid inputs
    } catch (Exception ignoredException) {
            System.out.println("Invalid input");
        }
    }


    /**
     * interface for printing out all loans 
     * either from all users
     * or a specific user
     */
    private void displayLoans() {
        String user_choice;
        System.out.print("Enter CustomerID to see customer specific loans or enter 'ALL' to view all loans: ");
        user_choice = getUserInput();
        if (user_choice.equalsIgnoreCase("ALL")) {
            for (Customer c : customers.values()) {
                //string formatting for nice output
                System.out.println("===============================");
                System.out.println("CustomerID: " + c.getCustomerID());
                System.out.println("Eligible to arrange new loans - " + c.isEligible());
                System.out.println("Current income: "+c.getAnnual_Income());
                System.out.println(String.format("%-12s%-12s%-12s%-12s%-12s%-12s", "RecordID", "LoanType", "IntRate", "AmmountLeft", "TimeLeft", "Overpay"));
                System.out.println();
                for (Loan l : c.getLoan_Records()) {
                    if (l==null) continue;
                    String formattedOutput = String.format("%-12s%-12s%-12s%-12s%-12s%-12s", l.getRecord_ID(), l.getLoan_Type(), l.getInterest_Rate(), l.getAmmount_Left(), l.getLoan_Term_Left(), l.getOverpaymentPercentage());
                    System.out.println(formattedOutput);    
                }
                System.out.println();
            }
            
        } else {
            //implimentation for the user only wanting a personal record displayed
            customers.get(user_choice).getLoan_Records();
            displayPersonalLoans(user_choice);
            
        }
    }


    /**
     * method for displaying personal loans
     * iterates through customer attribute of loan records in order to output every record
     * text formatting for nicer output
     */
    private void displayPersonalLoans(String customerID) {
        System.out.println();
        System.out.println("===============================");
        System.out.println("CustomerID "+customerID);
        System.out.println("Eligible to arrange new loans - " + customers.get(customerID).isEligible());
        System.out.println("Current income: "+customers.get(customerID).getAnnual_Income());
        System.out.println(String.format("%-12s%-12s%-12s%-12s%-12s%-12s", "RecordID", "LoanType", "IntRate", "AmmountLeft", "TimeLeft", "Overpay"));
        System.out.println();

        for (Loan l : customers.get(customerID).getLoan_Records()) {
            if (l==null) continue;
            String formattedOutput = String.format("%-12s%-12s%-12s%-12s%-12s%-12s", l.getRecord_ID(), l.getLoan_Type(), l.getInterest_Rate(), l.getAmmount_Left(), l.getLoan_Term_Left(), l.getOverpaymentPercentage());
            System.out.println(formattedOutput);
        }
        System.out.println();

    }

    /**
     * method to provide a menu for all modification options
     * you can change income details and remove loans
     * non infinite switch statement so escape abck to main menu
     */
    private void modifyValues() {
        System.out.println("Select an option: ");
        System.out.println("1. Change income details for customer");
        System.out.println("2. Remove Loan");
        String user_choice = getUserInput();

        switch (user_choice) {
            case "1":
                updateIncome();
                break;
            case "2":
                removeLoan();
                break;
            default:
                break;
        }
    }

    /**
     * method to provide implimentation for updating income
     */
    private void updateIncome() {
        System.out.print("Enter customerID of which you wish to update income: ");
        String customerID = getUserInput();
        System.out.print("Enter new income (a number in thousands of pounds): ");
        try {
            double annual_Income = Double.parseDouble(getUserInput());
            customers.get(customerID).setAnnual_Income(annual_Income);    
        } catch (Exception ignoredException){
            System.out.println("User does not exist");
        }
    }

    /**
     * method to provide implimentation for removing loans from a customer
     */
    private void removeLoan() {
        System.out.println("Enter customerID you want to remove a loan from: ");
        String customerID = getUserInput();
        displayPersonalLoans(customerID);
        System.out.println("Enter RecordID of loan you want to remove.");
        String recordIDString = getUserInput();
        customers.get(customerID).remove_Loan(recordIDString);
    }

    /**
     * @return just returns next line so i dont have to keep opening and closing scanners
     */
    private String getUserInput() {
        return scanner.next();
    }

    /**
     * method to accept input and regex and compares them
     * @param input 
     * @param regex 
     * @return returns validated input
     */
    public String validate_input(String input, String regex) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        while (!input.matches(regex)) {
            System.out.println(input + " cannot be validated using {" + regex + "} regex");
            System.out.print("Please enter a valid input: ");
            input = scanner.next();
        }
        return input;
    }   
}

