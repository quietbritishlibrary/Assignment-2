package amazonsystem;

/**
 * Represents an item in an Amazon cart, including the product and its quantity.
 */
public class AmazonCartItem {

    /**
     * The product associated with this cart item.
     */
    private AmazonProduct product;

    /**
     * The quantity of the product in the cart.
     */
    private int quantity;

    /**
     * Constructs an `AmazonCartItem` with the specified product and quantity.
     *
     * @param myProduct the product to be added to the cart
     * @param myQuantity the quantity of the product
     */
    public AmazonCartItem(AmazonProduct myProduct, int myQuantity) {
        product = myProduct;
        quantity = myQuantity;
    }

    /**
     * Calculates the subtotal for the cart item based on the product price and quantity.
     *
     * @return the subtotal as a float value
     */
    public float calcSubTotal() {
        return quantity * product.getActual_Price();
    }

    // Getters and Setters

    /**
     * Retrieves the product associated with this cart item.
     *
     * @return the `AmazonProduct` object
     */
    public AmazonProduct getProduct() {
        return product;
    }

    /**
     * Updates the product associated with this cart item.
     *
     * @param product the new `AmazonProduct` to associate with this cart item
     */
    public void setProduct(AmazonProduct product) {
        this.product = product;
    }

    /**
     * Retrieves the quantity of the product in the cart.
     *
     * @return the quantity as an integer
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the quantity of the product in the cart.
     *
     * @param quantity the new quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
