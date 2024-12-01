package amazonsystem;

/**
 * Represents a comment or review for an Amazon product, including a comment text and a rating.
 */
public class AmazonComment {

    /**
     * The comment text provided by the customer.
     */
    private String comment;

    /**
     * The product associated with the comment.
     */
    private AmazonProduct product;

    /**
     * The rating given to the product, typically a float value between 0 and 5.
     */
    private float rating;

    /**
     * Constructs an `AmazonComment` object for a specified product.
     * Initializes the comment text to an empty string and the rating to 0.
     * 
     * @param myProduct the `AmazonProduct` object associated with this comment
     */
    public AmazonComment(AmazonProduct myProduct) {
        product = myProduct;
        this.comment = "";
        this.rating = 0;
    }

    /**
     * Default constructor for an empty `AmazonComment`.
     */
    public AmazonComment() {}

    /**
     * Formats the comment into a concise string representation.
     * 
     * @return a formatted string containing the product ID, comment, and rating
     */
    public String toString() {
        return String.format("ProdId: %d - Comment: %s - rating: %.2f", product.getId(), comment, rating);
    }

    // Getters and Setters

    /**
     * Retrieves the comment text.
     * 
     * @return the comment text associated with this product
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment text.
     * 
     * @param comment the comment text to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Retrieves the product associated with this comment.
     * 
     * @return the `AmazonProduct` object associated with the comment
     */
    public AmazonProduct getProduct() {
        return product;
    }

    /**
     * Sets the product associated with this comment.
     * 
     * @param product the `AmazonProduct` to set for this comment
     */
    public void setProduct(AmazonProduct product) {
        this.product = product;
    }

    /**
     * Retrieves the rating for the product.
     * 
     * @return the rating (float value) given to the product
     */
    public float getRating() {
        return rating;
    }

    /**
     * Sets the rating for the product.
     * 
     * @param rating the rating to set for the product
     */
    public void setRating(float rating) {
        this.rating = rating;
    }
}
