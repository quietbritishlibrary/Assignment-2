package amazonsystem;

/**
 * Interface representing a payable entity, allowing payment processing.
 */
public interface Payable {

    /**
     * Processes a payment for the amount provided.
     * 
     * @param amountPaid the amount being paid
     * @return true if the payment was successful, false otherwise
     */
    boolean pay(float amountPaid);
}
