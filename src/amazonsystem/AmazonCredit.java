package amazonsystem;

/**
 * Represents an abstract Amazon credit, providing common functionality for handling credit payments.
 */
public abstract class AmazonCredit {

    /**
     * Enum representing the types of payments supported.
     */
    enum PaymentType {
        Cash, Check, Card
    }

    /**
     * The amount of the credit or payment.
     */
    private float amount;

    /**
     * The type of payment associated with this credit.
     */
    private PaymentType type;

    /**
     * Constructs an `AmazonCredit` object with the specified amount.
     *
     * @param myAmount the amount of the credit or payment
     */
    public AmazonCredit(float myAmount) {
        amount = myAmount;
    }

    /**
     * Returns a formatted string representing the details of the credit.
     * @return a string containing the payment type and amount in a structured format
     */
    @Override
    public String toString() {
        // Find index of the enum
        int enumIndex = type.ordinal();

        // Format AmazonCredit info into a string
        return String.format("- Credit [%d]: Type: %s, Value: %.2f", enumIndex, type, amount);
    }

    // Getters and Setters

    /**
     * Retrieves the amount associated with this credit.
     * @return the amount as a float
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Updates the amount associated with this credit.
     * @param amount the new amount to set
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Retrieves the type of payment associated with this credit.
     * @return the `PaymentType` of this credit
     */
    public PaymentType getType() {
        return type;
    }

    /**
     * Updates the type of payment associated with this credit.
     * @param type the new `PaymentType` to set
     */
    public void setType(PaymentType type) {
        this.type = type;
    }
}
