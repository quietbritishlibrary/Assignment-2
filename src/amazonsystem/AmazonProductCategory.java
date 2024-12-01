package amazonsystem;

/**
 * Represents a product category in the Amazon system.
 */
public class AmazonProductCategory {

    /**
     * The name of the product category.
     */
    private String categoryName;

    /**
     * Default constructor for creating an empty AmazonProductCategory object.
     */
    public AmazonProductCategory() {}

    /**
     * Constructs an AmazonProductCategory with the specified category name.
     * @param myCategory the name of the product category
     */
    public AmazonProductCategory(String myCategory) {
        categoryName = myCategory;
    }

    /**
     * Retrieves the name of the product category.
     * @return the name of the product category
     */
    public String getCategory() {
        return categoryName;
    }

    /**
     * Updates the name of the product category.
     * @param myCategory the new name for the product category
     */
    public void setCategory(String myCategory) {
        categoryName = myCategory;
    }
}

