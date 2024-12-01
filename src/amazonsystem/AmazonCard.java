package amazonsystem;

/**
 * Represents a card payment in the Amazon system, extending the `AmazonCredit` class.
 */
public class AmazonCard extends AmazonCredit {

    /**
     * The card number associated with this payment.
     */
    private String number;

    /**
     * The expiration date of the card, in MM/YYYY format.
     */
    private String expiration;

    /**
     * Constructs an `AmazonCard` object with the specified card number, expiration date, and amount.
     *
     * @param myNumber the card number
     * @param myExpiration the expiration date of the card in MM/YYYY format
     * @param myAmount the amount of money available on the card
     */
    private AmazonCard(String myNumber, String myExpiration, float myAmount) {
        super(myAmount);
        number = myNumber;
        expiration = myExpiration;
        setType(PaymentType.Card);
    }

    /**
     * Constructs an `AmazonCard` object using data parsed from a string array.
     *
     * @param data a string array containing the card number, expiration date, and amount
     */
    private AmazonCard(String[] data) {
        super(Float.parseFloat(data[2]));
        this.number = data[0];
        this.expiration = data[1];
        setType(PaymentType.Card);
    }

    /**
     * Creates an `AmazonCard` object from a string array, validating the input data.
     *
     * @param data a string array containing the card number, expiration date, and amount
     * @return an `AmazonCard` object if the input is valid, or `null` otherwise
     */
    public static AmazonCard createCard(String[] data) {
        if (data == null || data.length != 3) {
            return null;
        }

        float amount;
        try {
            amount = Float.parseFloat(data[2]);
        } catch (NumberFormatException e) {
            return null;
        }

        String number = data[0];
        String expiration = data[1];

        if (number.isBlank() || number.isEmpty() || expiration.isBlank() || expiration.isEmpty() || amount < 0) {
            return null;
        }

        return new AmazonCard(data);
    }

    /**
     * Returns a string representation of the card payment.
     *
     * @return a formatted string representing the card payment details
     */
    @Override
    public String toString() {
        return super.toString();
    }

    // Getters and Setters

    /**
     * Retrieves the card number associated with this payment.
     *
     * @return the card number as a string
     */
    public String getNumber() {
        return number;
    }

    /**
     * Updates the card number associated with this payment.
     *
     * @param number the new card number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Retrieves the expiration date of the card.
     *
     * @return the expiration date in MM/YYYY format
     */
    public String getExpiration() {
        return expiration;
    }

    /**
     * Updates the expiration date of the card.
     *
     * @param expiration the new expiration date to set (MM/YYYY format)
     */
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
