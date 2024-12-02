package amazonsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer on Amazon, with details like their id, name, address, 
 * shopping cart, wishlist, credits, and comments.
 */
public class AmazonCustomer {

    // Fields
    private int id;
    private String name;
    private String address;
    
    private List<AmazonComment> comments = new ArrayList<AmazonComment>();
    private List<AmazonProduct> wishlist = new ArrayList<AmazonProduct>();
    private List<AmazonCredit> credits = new ArrayList<AmazonCredit>();
    
    private AmazonCart cart;

    /**
     * Constructs an AmazonCustomer with the given id, name, and address.
     *
     * @param myId The customer's id.
     * @param myName The customer's name.
     * @param myAddress The customer's address.
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
     * Constructs an AmazonCustomer from an array of strings representing customer data.
     *
     * @param data The customer data in a String array.
     */
    private AmazonCustomer(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.address = data[2];
        this.cart = new AmazonCart(this);
    }

    /**
     * Creates an AmazonCustomer object from an array of customer data, ensuring the data is valid.
     *
     * @param data The customer data in a String array.
     * @return A new AmazonCustomer if valid data, otherwise null.
     */
    public static AmazonCustomer createAmazonCustomer(String[] data) {
        if (data == null || data.length != 3) {
            return null;
        }

        int id = Integer.parseInt(data[0]);
        String name = data[1];
        String address = data[2];

        if (name.isBlank() || name.isEmpty() || address.isBlank() || address.isEmpty() || id < 1) {
            return null;
        }

        return new AmazonCustomer(data);
    }

    /**
     * Adds a credit to the customer's list of credits.
     *
     * @param credit The AmazonCredit to add.
     */
    public void addCredit(AmazonCredit credit) {
        credits.add(credit);
    }

    /**
     * Displays the list of credits the customer has.
     */
    public void showCredits() {
        if (credits.isEmpty()) {
            System.out.println("You have no credits");
            return;
        }
        for (AmazonCredit c : credits) {
            System.out.println(c);
        }
    }

    /**
     * Adds a product to the customer's wishlist.
     *
     * @param product The AmazonProduct to add.
     */
    public void addProductInWishList(AmazonProduct product) {
        wishlist.add(product);
    }

    /**
     * Removes a product from the customer's wishlist.
     *
     * @param product The AmazonProduct to remove.
     */
    public void removeProductFromWishList(AmazonProduct product) {
        wishlist.removeIf(currentProduct -> currentProduct.equals(product));
    }

    /**
     * Checks if a product exists in the customer's wishlist.
     *
     * @param product The AmazonProduct to check.
     * @return True if the product is in the wishlist, otherwise false.
     */
    public boolean isProductInWishList(AmazonProduct product) {
        return wishlist.contains(product);
    }

    /**
     * Displays the customer's wishlist.
     */
    public void showWishList() {
        if (wishlist.isEmpty()) {
            System.out.println("Wishlist is empty.");
            return;
        }
        System.out.println("Printing wishlist ..............");
        for (AmazonProduct p : wishlist) {
            System.out.println(p);
        }
    }

    /**
     * Adds an item to the customer's shopping cart.
     *
     * @param item The AmazonCartItem to add.
     */
    public void addItemInCart(AmazonCartItem item) {
        cart.getItems().add(item);
    }

    /**
     * Displays the customer's shopping cart.
     */
    public void showCart() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println(cart);
    }

    /**
     * Adds a comment to the customer's list of comments.
     *
     * @param comment The AmazonComment to add.
     */
    public void addComment(AmazonComment comment) {
        comments.add(comment);
    }

    /**
     * Creates or updates a comment for a specific product, with a rating.
     *
     * @param product The AmazonProduct being commented on.
     * @param newComment The text of the comment.
     * @param rating The rating associated with the comment (between 0 and 5).
     * @return True if the comment was successfully created or updated, otherwise false.
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

        // Create a new comment if none exists
        comments.add(new AmazonComment(product));
        return true;
    }

    /**
     * Displays all comments the customer has made.
     */
    public void showComments() {
        if (comments.isEmpty()) {
            System.out.println("Comments are empty.");
            return;
        }
        for (AmazonComment c : comments) {
            System.out.println(c);
        }
    }

    /**
     * Pays for the items in the customer's cart using the given payment method.
     *
     * @param money The AmazonCredit used to pay.
     * @return True if the payment was successful, otherwise false.
     */
    public boolean pay(AmazonCredit money) {
        float total = cart.calcSubTotal();
        float pay = money.getAmount();
        List<AmazonCartItem> itemsList = getCart().getItems();
        
        if (pay >= total) {
            money.setAmount(pay - total);
            // Adds a comment for each product
            for (AmazonCartItem p : itemsList) {
                AmazonProduct product = p.getProduct();
                AmazonComment comment = new AmazonComment(product);
                addComment(comment);
            }
            itemsList.clear();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * AmazonCredits method for formatting and printing the customers.
     */
	 public String toString() {
		return String.format("- Customer: [Id: %d], [Name: %s], [Address: %s]", id, name, address);
	}

    // Getters and setters

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
