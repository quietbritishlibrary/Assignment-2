package amazonsystem;

/**
 * Represents a sub-category of products in the Amazon system.
 * Each sub-category is associated with a specific product category.
 */
public class AmazonProductSubCategory {

    /**
     * The name of the sub-category.
     */
    private String subCategoryName;

    /**
     * The product category associated with this sub-category.
     */
    private AmazonProductCategory category;

    /**
     * Default constructor for creating an empty AmazonProductSubCategory object.
     */
    public AmazonProductSubCategory() {}

    /**
     * Constructs an AmazonProductSubCategory with the specified name and category.
     *
     * @param mySubCategoryName the name of the sub-category
     * @param myCategory the product category associated with this sub-category
     */
    public AmazonProductSubCategory(String mySubCategoryName, AmazonProductCategory myCategory) {
        subCategoryName = mySubCategoryName;
        category = myCategory;
    }

    /**
     * Retrieves the product category associated with this sub-category.
     * @return the product category associated with this sub-category
     */
    public AmazonProductCategory getCategory() {
        return category;
    }

    /**
     * Retrieves the name of the sub-category.
     * @return the name of the sub-category
     */
    public String getSubCategory() {
        return subCategoryName;
    }

    /**
     * Updates the product category associated with this sub-category.
     * @param myCategory the new product category to associate with this sub-category
     */
    public void setCategory(AmazonProductCategory myCategory) {
        category = myCategory;
    }

    /**
     * Updates the name of the sub-category.
     * @param mySubCategoryName the new name for the sub-category
     */
    public void setSubCategory(String mySubCategoryName) {
        subCategoryName = mySubCategoryName;
    }
}
