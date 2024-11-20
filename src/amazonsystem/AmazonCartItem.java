package amazonsystem;

public class AmazonCartItem {
	
	private AmazonProduct product;
	private int quantity;
	
	/**
     * AmazonCartItem parameterized-constructor
     * @param an AmazonProduct object and the quantity of items.
     */
	
	public AmazonCartItem(AmazonProduct myProduct, int myQuantity) {
		product = myProduct;
		quantity = myQuantity;
	}
	
	/**
     * Method for calculating the sub-total of an AmazonProduct based on price and the quantity.
     * @return The float value of the price for the given quantity of items.
     */
	
	public float calcSubTotal() {
		return quantity * product.getActual_Price();
	}

	// getter and setters 
	
	public AmazonProduct getProduct() {
		return product;
	}

	public void setProduct(AmazonProduct product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
	

}
