package amazonsystem;

import java.util.ArrayList;

/**
 * Represents an Amazon shopping cart, containing items and functionality for checkout.
 * Implements the `Payable` interface for payment processing.
 */
public class AmazonCart implements Payable {
    
    /**
     * The customer to whom this cart belongs.
     */
    private AmazonCustomer customer;

    /**
     * A list of items in the cart, each represented by an `AmazonCartItem` object.
     */
    private ArrayList<AmazonCartItem> items = new ArrayList<AmazonCartItem>();

    /**
     * The total value of all items in the cart (subtotal).
     */
    private float totalValue;

    /**
     * Constructs an `AmazonCart` object for the specified customer.
     * 
     * @param myCustomer the customer to whom the cart belongs
     */
    public AmazonCart(AmazonCustomer myCustomer) {
        customer = myCustomer;
    }

    /**
     * Default constructor for an empty cart.
     */
    public AmazonCart() {}

    /**
     * Calculates the subtotal of the cart by summing the subtotal of each item.
     * 
     * @return the sum of each item's subtotal in the cart
     */
    public float calcSubTotal() {
        float total = 0;
        for (AmazonCartItem i : items) {
            if (i != null) {
                total += i.calcSubTotal();
            }
        }
        return total;
    }

    /**
     * Finds and returns the `AmazonCartItem` with the specified product ID.
     * 
     * @param id the product ID of the item to find
     * @return the `AmazonCartItem` associated with the specified product ID, or `null` if not found
     */
    public AmazonCartItem getItem(int id) {
        int size = items.size();
        for (int i = 0; i < size; i++) {
            int productId = items.get(i).getProduct().getId();
            if (productId == id) {
                return items.get(i);
            }
        }
        return null;
    }

    /**
     * Checks if a given product exists in the cart.
     * 
     * @param product the product to check for in the cart
     * @return `true` if the product is in the cart, otherwise `false`
     */
    public boolean hasItem(AmazonProduct product) {
        int size = items.size();
        for (int i = 0; i < size; i++) {
            AmazonProduct currentProduct = items.get(i).getProduct();
            if (currentProduct.equals(product)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the payment amount is sufficient to cover the total cost of the cart.
     * 
     * @param amountPaid the amount of money being paid
     * @return `true` if the payment is sufficient, otherwise `false`
     */
    @Override
    public boolean pay(float amountPaid) {
        float totalCost = calcSubTotal();
        return amountPaid >= totalCost;
    }

    /**
     * Adds an `AmazonCartItem` to the cart.
     * 
     * @param item the `AmazonCartItem` to add to the cart
     */
    public void addItem(AmazonCartItem item) {
        items.add(item);
    }

    /**
     * Removes an `AmazonProduct` from the cart by matching it with an item in the cart.
     * 
     * @param product the `AmazonProduct` to remove from the cart
     */
    public void removeItem(AmazonProduct product) {
        int size = items.size();
        for (int i = 0; i < size; i++) {
            AmazonProduct currentProduct = items.get(i).getProduct();
            if (currentProduct.equals(product)) {
                items.remove(i);
                break;
            }
        }
    }

    /**
     * Returns a string representation of the cart, including customer name, items, and total value.
     * 
     * @return a formatted string showing the cart details
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
            report.append(String.format("- Item[ID: %d, Name: %s], quantity = %d\n", productId, productName, quantity));
        }

        report.append(String.format("* Total value: %.2f", totalValue));

        return report.toString();
    }

    // Getters and Setters

    /**
     * Retrieves the customer associated with this cart.
     * 
     * @return the `AmazonCustomer` object associated with this cart
     */
    public AmazonCustomer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer associated with this cart.
     * 
     * @param customer the `AmazonCustomer` to set as the cart's owner
     */
    public void setCustomer(AmazonCustomer customer) {
        this.customer = customer;
    }

    /**
     * Retrieves the list of items in the cart.
     * 
     * @return an `ArrayList` of `AmazonCartItem` objects in the cart
     */
    public ArrayList<AmazonCartItem> getItems() {
        return items;
    }

    /**
     * Sets the list of items in the cart.
     * 
     * @param items the `ArrayList` of `AmazonCartItem` objects to set in the cart
     */
    public void setItems(ArrayList<AmazonCartItem> items) {
        this.items = items;
    }

    /**
     * Retrieves the total value of the cart.
     * 
     * @return the total value (subtotal) of all items in the cart
     */
    public float getTotalValue() {
        return totalValue;
    }

    /**
     * Sets the total value of the cart.
     * 
     * @param totalValue the total value (subtotal) to set for the cart
     */
    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }
}
