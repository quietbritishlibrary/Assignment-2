package amazonsystem;

/**
 * Represents a cash payment in the Amazon system, extending the `AmazonCredit` class.
 */
public class AmazonCash extends AmazonCredit {

    /**
     * Constructs an `AmazonCash` object with the specified cash amount.
     *
     * @param myAmount the amount of cash payment
     */
    private AmazonCash(float myAmount) {
        super(myAmount);
        setType(PaymentType.Cash);
    }

    /**
     * Constructs an `AmazonCash` object using data parsed from a string array.
     *
     * @param data a string array containing the cash amount as a single element
     */
    private AmazonCash(String[] data) {
        super(Float.parseFloat(data[0]));
        setType(PaymentType.Cash);
    }

    /**
     * Creates an `AmazonCash` object from a string array, validating the input data.
     *
     * @param data a string array containing the cash amount as a single element
     * @return an `AmazonCash` object if the input is valid, or `null` otherwise
     */
    public static AmazonCash createCash(String[] data) {
        if (data == null || data.length != 1) {
            return null;
        }

        float amount;
        try {
            amount = Float.parseFloat(data[0]);
        } catch (NumberFormatException e) {
            return null;
        }

        return new AmazonCash(data);
    }

    /**
     * Returns a string representation of the cash payment.
     *
     * @return a formatted string representing the cash payment details
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
