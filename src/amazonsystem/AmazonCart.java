package amazonsystem;

import java.util.ArrayList;

import CST8132Lab05.LabA.Item;

public class AmazonCart {
	
	//AmazonCart Properties
	private AmazonCustomer customer;
	private ArrayList <AmazonCartItem> items = new ArrayList <AmazonCartItem>();
	private float totalValue;
	
	/**
     * AmazonCart parameterized-constructor
     * @param The customer to whom the cart belongs.
     */
	public AmazonCart(AmazonCustomer myCustomer){
		customer = myCustomer;
	}
	
	/**
     * AmazonCart sub-total calculation method.
     * @return the sum of each items sub-total in the users cart.
     */
	public float calcSubTotal() {
		float total = 0;
		
		for(AmazonCartItem i: items) {
			if(i != null) {
				total += i.calcSubTotal();
			}
			
		}
		
		return total;
	}
	
	/**
     * AmazonCart item finding method.
     * @param the id related to the product that the user wants to get.
     * @return the associated product in the AmazonCartItem list.
     */
	public AmazonCartItem  getItem(int id) {
		int size = items.size();
		
		for(int i = 0; i < size; i++ ) {
			int productId = items.get(i).getProduct().getId(); //variable points to each AmazonProduct id through AmazonCartItem class
			if(productId == id) {
				return items.get(i);
			}
		}
		
		return null;
	}
	
	/**
     * AmazonCart method for checking if a product exists within the AmazonCartItem ArrayList.
     * @param the product that the user wants to check.
     * @return true or false based on if the product is or isn't in the list.
     */
	public boolean hasItem(AmazonProduct product) {
		int size = items.size();
		
		for(int i = 0; i < size; i++) {
			AmazonProduct currentProduct = items.get(i).getProduct();
			if (currentProduct.equals(product)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	

}
