package amazonsystem;

/**
 * Represents a check payment in the Amazon system, extending the `AmazonCredit` class.
 */
public class AmazonCheck extends AmazonCredit {

    /**
     * The account number associated with this check.
     */
    private String accountNumber;

    /**
     * Constructs an `AmazonCheck` object with the specified account number and amount.
     *
     * @param myAccountNumber the account number associated with the check
     * @param myAmount the amount of money on the check
     */
    private AmazonCheck(String myAccountNumber, float myAmount) {
        super(myAmount);
        accountNumber = myAccountNumber;
        setType(PaymentType.Check);
    }

    /**
     * Constructs an `AmazonCheck` object using data parsed from a string array.
     *
     * @param data a string array containing the account number and check amount
     */
    private AmazonCheck(String[] data) {
        super(Float.parseFloat(data[1]));
        this.accountNumber = data[0];
        setType(PaymentType.Check);
    }

    /**
     * Creates an `AmazonCheck` object from a string array, validating the input data.
     *
     * @param data a string array containing the account number and check amount
     * @return an `AmazonCheck` object if the input is valid, or `null` otherwise
     */
    public static AmazonCheck createCheck(String[] data) {
        if (data == null || data.length != 2) {
            return null;
        }

        try {
            float amount = Float.parseFloat(data[1]);
        } catch (NumberFormatException e) {
            return null;
        }

        String accountNumber = data[0];
        if (accountNumber.isBlank()) {
            return null;
        }

        return new AmazonCheck(data);
    }

    /**
     * Returns a string representation of the check payment.
     *
     * @return a formatted string representing the check payment details
     */
    @Override
    public String toString() {
        return super.toString();
    }

    // Getters and Setters

    /**
     * Retrieves the account number associated with this check.
     *
     * @return the account number as a string
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Updates the account number associated with this check.
     *
     * @param accountNumber the new account number to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
