package amazonsystem;

import java.util.ArrayList;

public class AmazonCart implements Payable {
	
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
			int productId = items.get(i).getProduct().getId(); //variable points to each AmazonProduct id through AmazonCartItem class product getter
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
			AmazonProduct currentProduct = items.get(i).getProduct(); //points to current AmazonProduct through AmazonCartItem class product getter
			if (currentProduct.equals(product)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
     * AmazonCart method for checking if the amount paid is greater than or equal to the total cost of the items in their cart.
     * @param the amount the customer is paying.
     * @return true or false based on if the amount paid is or isn't enough.
     */
	
	@Override
	public boolean pay(float amountPaid) {
		 float totalCost = calcSubTotal();
		 
		   if(amountPaid >= totalCost ) {
			   return true;
		   }else {
			   return false;
		   }
		  
		}

	/**
     * AmazonCart method for adding items to the cart
     * @param the AmazonCartItem the customer wants to add.
     */
	public void addItem(AmazonCartItem item) {
		items.add(item);
	}
	
	/**
     * AmazonCart method for removing items to the cart
     * @param the AmazonProduct the customer wants to remove.
     */
	public void removeItem(AmazonProduct product) {
		int size = items.size();
		for(int i = 0; i < size; i++) {
			AmazonProduct currentProduct = items.get(i).getProduct();
			if(currentProduct.equals(product)) {
				items.remove(i);
				break;
			}
		}
		
	}
	
	
	/**
     * AmazonCart method for formatting and printing its information using StringBuilder
     * @return the information about the cart.
     */
	@Override
	public String toString() {
	    String customerName = customer.getName();
	    StringBuilder report = new StringBuilder();  
	    float totalValue = calcSubTotal();

	    
	    report.append(String.format("[Customer: %s]\n", customerName));

	    for (AmazonCartItem item : items) {
	        AmazonProduct product = item.getProduct();
	        int productId = product.getId();
	        int quantity = item.getQuantity();
	        String productName = product.getName();

	        
	        report.append(String.format("Item[ID: %d, Name: %s], quantity = %d\n", productId, productName, quantity));
	    }

	    report.append(String.format("* Total value: %.2f\n", totalValue));

	    return report.toString();  
	}

	
	
	

}
