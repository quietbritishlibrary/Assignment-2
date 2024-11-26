package amazonsystem;


import java.util.ArrayList;
import java.util.List;


public class AmazonCustomer {
	
	// field
	private int id;
	private String name;
	private String address;
	private List <AmazonComment> comments = new ArrayList <AmazonComment>();
	private List <AmazonProduct> wishlist = new ArrayList <AmazonProduct>();
	private List <AmazonCredit> credits = new ArrayList <AmazonCredit>();
	

	/**
     * AmazonCustomer parameterized-constructor
     * @param The customers id, name and address.
     */
	private AmazonCustomer(int myId, String myName, String myAddress) {
		id = myId;
		name = myName;
		address = myAddress;
		this.comments = new ArrayList<AmazonComment>();
        this.wishlist = new ArrayList<AmazonProduct>();
        this.credits = new ArrayList<AmazonCredit>();
	}
	
	/**
     * AmazonCustomer constructor used to assign substrings to appropriately parsed AmazonProduct property
     * @param String array of substrings of the customers data.
     */
	private AmazonCustomer(String [] data) {
		this.id = Integer.parseInt(data[0]);
		this.name = data[1];
		this.address = data[2];
	}
	

	/**
     * AmazonCustomer method used to create AmazonCustomer objects as long as they aren't null
     * @param String array of substrings of the customers data.
     */
	public static AmazonCustomer createAmazonCustomer(String [] data) {
		   
		   if(data == null) {
			   return null;
			   
		   } else if(data.length != 3) {
			   return null;
		   }
		   
		   
		   
			    int id = Integer.parseInt(data[0]);
				String name = data[1];
		        String address = data[2];
		        
		        if(name.isBlank() || name.isEmpty() || address.isBlank() || address.isEmpty() || id < 1) {
					return null;
				}
				
				AmazonCustomer customer = new AmazonCustomer(data);
				   
				return customer;
		
		}
	
	/**
     * AmazonCustomer method for adding credits to the customers profile
     * @param the AmazonCredit the customer wants to add.
     */
	public void addCredits(AmazonCredit credit) {
		credits.add(credit);
	}
	
	/**
     * AmazonCredits method for displaying the credits in the customers list
     */
	public void showCredits() {
		for(AmazonCredit c : credits) {
			System.out.println(c);
		}
	}
	
	/**
     * AmazonCredits method for adding product to wishlist
     */
	public void addProductInWishList(AmazonProduct product) {
		wishlist.add(product);
	}
	
	/**
     * AmazonCredits method for removing product from wishlist based on their ID
     * @param product that the user wishes to remove.
     */
	public void removeProductFromWishList(AmazonProduct product) {
		int size = wishlist.size();
		for(int i = 0; i < size; i++) {
			AmazonProduct currentProduct = wishlist.get(i);
			if(currentProduct.equals(product)) {
				wishlist.remove(i);
				break;
			}
		}
	}
	
	/**
     * AmazonCredits method for checking if a product is in the wishlist
     * @param AmazonProduct searched in the wishlist
     * @return The method should return true if the specified AmazonProduct is found in the collection; otherwise, it should return false, indicating that the product was not located.
     */
	public boolean isProductInWishList(AmazonProduct product) {
		int size = wishlist.size();
		
		for(int i = 0; i < size; i++) {
			AmazonProduct currentProduct = wishlist.get(i); //points to current AmazonProduct 
			if (currentProduct.equals(product)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
     * AmazonCustomer method for printing the items of the wishlist
     */
	public void showWishList() {
		for(AmazonProduct p : wishlist) {
			System.out.println(p);
		}
	}
	
	/**
     * AmazonCustomer method for adding items to the customers cart
     * @param the item that the customer wishes to add.
     */
	public void addItemInCart(AmazonCartItem item) {
		AmazonCart cart = new AmazonCart(this);
	    cart.getItems().add(item);
	}
	
	public void removeProductFromCart(AmazonProduct product) {
	
	}
	
	/**
     * AmazonCustomer method for adding comments to the comments list
     * @param The AmazonComment the user wishes to add.
     */
	public void addComment(AmazonComment comment) {
		comments.add(comment);
	}
	
	/**
     * AmazonCustomer method for creating a new comment
     * @param The AmazonComment product, comment and rating that it pertains to.
     * @return true or false based on if the AmazonComment object managed to be created.
     */
	public boolean setComment(AmazonProduct product, String newComment, float rating) {
		
		if (product == null || newComment == null || newComment.isEmpty() || rating < 0 || rating > 5) {
            return false; 
        }

		AmazonComment comment = new AmazonComment(product, newComment, rating);
		
		comments.add(comment);
		
		return true;
		
	}
	
	/**
     * AmazonCredits method for printing the comments in the comments list.
     */
	public void showComments() {
		for(AmazonComment c : comments) {
			System.out.println(c);
		}
	}
	
	/**
     * AmazonCredits method for formatting and printing the customers.
     */
	public String toString() {
		return String.format("Customer: - Customer: [Id: %d], [Name: %s], [Address: %s]", id, name, address);
	}
	

	// getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}
