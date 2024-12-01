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
	
	private AmazonCart cart;
	

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
        this.cart = new AmazonCart(this);
	}
	
	/**
     * AmazonCustomer constructor used to assign substrings to appropriately parsed AmazonProduct property
     * @param String array of substrings of the customers data.
     */
	private AmazonCustomer(String [] data) {
		this.id = Integer.parseInt(data[0]);
		this.name = data[1];
		this.address = data[2];
		this.cart = new AmazonCart(this);
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
	public void addCredit(AmazonCredit credit) {
		credits.add(credit);
	}
	
	/**
     * AmazonCredits method for displaying the credits in the customers list
     */
	public void showCredits() {
		
		if(credits.isEmpty()) {
			System.out.println("You have no credits");
			return;
		}
		
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
		
		if(wishlist.isEmpty()) {
			System.out.println("Wishlist is empty.");
			return;
		}
		System.out.println("Printing wishlist ..............");
		for(AmazonProduct p : wishlist) {
			System.out.println(p);
		}
	}
	
	/**
     * AmazonCustomer method for adding items to the customers cart
     * @param the item that the customer wishes to add.
     */
	public void addItemInCart(AmazonCartItem item) {
	    cart.getItems().add(item);
	}
	
	public void removeProductFromCart(AmazonProduct product) {
	
	}
	
	public void showCart() {
		
		AmazonCart cart = getCart(); 
		if(cart.getItems().isEmpty()) {
			System.out.println("cart is empty.");
			return;
		}
		System.out.println(cart);	
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
		if (product == null || newComment == null || rating < 0 || rating > 5) {
            return false; 
        }

		for (AmazonComment existingComment : comments) {
	        if (existingComment.getProduct().getId() == product.getId()) {
	            existingComment.setComment(newComment);
	            existingComment.setRating(rating);
	            return true;
	        }
	    }
	       

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
		return String.format("- Customer: [Id: %d], [Name: %s], [Address: %s]", id, name, address);
	}
	 
	public boolean pay(AmazonCredit money) {
		 float total = cart.calcSubTotal();
		 float pay = money.getAmount();
		 List <AmazonCartItem> itemsList = getCart().getItems();
		 AmazonComment comment = null;
		 
		 
		 
		   if(pay >= total ) {
			   money.setAmount(pay - total);
			   //makes 2 comments
			   for(AmazonCartItem p: itemsList) { 
				   AmazonProduct product = p.getProduct();
				   comment = new AmazonComment(product);
				   addComment(comment);
			   }
			   itemsList.clear();
	
			   return true;
		   }else {
			   return false;
		   }
		  
		 }
	
	
	public boolean pay(int index) {
		 float total = cart.calcSubTotal();
		 AmazonCredit credit = credits.get(index);
		 float pay = credit.getAmount();
		 List <AmazonCartItem> itemsList = getCart().getItems();
		 AmazonComment comment = null;
		 
		 
		 
		   if(pay >= total ) {
			   //makes 2 comments
			   for(AmazonCartItem p: itemsList) { 
				   AmazonProduct product = p.getProduct();
				   comment = new AmazonComment(product);
				   addComment(comment);
			   }
			   itemsList.clear();
	
			   return true;
		   }else {
			   return false;
		   }
		  
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

	public AmazonCart getCart() {
		return cart;
	}

	public void setCart(AmazonCart cart) {
		this.cart = cart;
	}

	public List<AmazonComment> getComments() {
		return comments;
	}

	public void setComments(List<AmazonComment> comments) {
		this.comments = comments;
	}

	public List<AmazonProduct> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<AmazonProduct> wishlist) {
		this.wishlist = wishlist;
	}

	public List<AmazonCredit> getCredits() {
		return credits;
	}

	public void setCredits(List<AmazonCredit> credits) {
		this.credits = credits;
	}

	public Integer getCartSize() {
		return cart.getItems().size();
	}

	public Integer getNumberOfComments() {
		return comments.size();
	}

	public int getNumberOfCredits() {
		return credits.size();
	}

	public Integer getWishlistSize() {
		return wishlist.size();
	}

	public AmazonComment getComment(int i) {
		return comments.get(i);
	}
	
	
	

	
	
  
}
