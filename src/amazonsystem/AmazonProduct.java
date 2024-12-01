package amazonsystem;

/**
 * Represents a product listed on Amazon, including its attributes such as ID, name, category, 
 * sub-category, pricing, ratings, and other details.
 */
public class AmazonProduct {

    /**
     * The unique identifier for the product.
     */
    private int id;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The main category of the product.
     */
    AmazonProductCategory category;

    /**
     * The sub-category of the product.
     */
    AmazonProductSubCategory subCategory;

    /**
     * The URL of the product image.
     */
    private String imageURL;

    /**
     * The URL link to the product page.
     */
    private String link;

    /**
     * The rating of the product (out of 5).
     */
    private float rating;

    /**
     * The number of ratings the product has received.
     */
    private int nRatings;

    /**
     * The discounted price of the product.
     */
    private float discountPrice;

    /**
     * The actual price of the product without discounts.
     */
    private float actualPrice;

    /**
     * Titles or additional descriptions associated with the product.
     */
    private String[] title;

    /**
     * Constructs a parameterized `AmazonProduct` with specified attributes.
     *
     * @param myId the unique ID of the product
     * @param myName the name of the product
     * @param myCategory the main category of the product
     * @param mySubCategory the sub-category of the product
     * @param myImageURL the URL of the product image
     * @param myLink the URL link to the product page
     * @param myRating the product's rating
     * @param my_nRating the number of ratings the product has
     * @param myDiscountPrice the discounted price of the product
     * @param myActualPrice the actual price of the product without discounts
     */
    private AmazonProduct(int myId, String myName, AmazonProductCategory myCategory, AmazonProductSubCategory mySubCategory, String myImageURL, String myLink, float myRating, int my_nRating, float myDiscountPrice, float myActualPrice) {
        id = myId;
        name = myName;
        category = myCategory;
        subCategory = mySubCategory;
        imageURL = myImageURL;
        link = myLink;
        rating = myRating;
        nRatings = my_nRating;
        discountPrice = myDiscountPrice;
        actualPrice = myActualPrice;
    }

    /**
     * Default no-argument constructor.
     */
    private AmazonProduct() {}

    /**
     * Creates an `AmazonProduct` object from an array of string data.
     *
     * @param data an array containing product attributes as strings
     * @return a new `AmazonProduct` object or null if the data is invalid
     */
    public static AmazonProduct createAmazonProduct(String[] data) {
        if (data == null || data.length != 10) {
            return null;
        }

        int id = Integer.parseInt(data[0]);
        String name = data[1];
        AmazonProductCategory category = new AmazonProductCategory(data[2]);
        AmazonProductSubCategory subCategory = new AmazonProductSubCategory(data[3], category);
        String imageURL = data[4];
        String link = data[5];
        float rating = Float.parseFloat(data[6]);
        int nRatings = Integer.parseInt(data[7].replace(",", ""));
        float discountPrice = Float.parseFloat(data[8]);
        float actualPrice = Float.parseFloat(data[9]);

        if (id < 1 || name.isBlank() || category == null || subCategory == null || 
            imageURL.isBlank() || link.isBlank() || rating < 0 || rating > 5 || 
            nRatings < 0 || discountPrice < 0 || actualPrice < 0 || actualPrice < discountPrice) {
            return null;
        }

        return new AmazonProduct(data);
    }

    /**
     * Constructs an `AmazonProduct` object by parsing an array of string data.
     *
     * @param data an array containing product attributes as strings
     */
    public AmazonProduct(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.category = new AmazonProductCategory(data[2]);
        this.subCategory = new AmazonProductSubCategory(data[3], category);
        this.imageURL = data[4];
        this.link = data[5];
        this.rating = AmazonUtil.convertStrToFloat(data[6]);
        this.nRatings = Integer.parseInt(data[7].replace(",", ""));
        this.discountPrice = AmazonUtil.convertStrToFloat(data[8]);
        this.actualPrice = AmazonUtil.convertStrToFloat(data[9]);
    }

    /**
     * Formats the product details as a string.
     *
     * @return a string representation of the product
     */
    @Override
    public String toString() {
        return "[" + id + ", " + name + ", " + category.getCategory() + ", " + 
               subCategory.getSubCategory() + ", " + imageURL + ", " + link + 
               ", " + rating + ", " + nRatings + ", " + discountPrice + ", " + actualPrice + "]";
    }

    /**
     * Formats the product details as a CSV string.
     *
     * @return a CSV-formatted string of the product details
     */
    public String toCSV() {
        return "\"" + id + "\",\"" + name + "\",\"" + 
               category.getCategory() + "\",\"" + subCategory.getSubCategory() + 
               "\",\"" + imageURL + "\",\"" + link + "\",\"" + rating + 
               "\",\"" + nRatings + "\",\"" + discountPrice + "\",\"" + actualPrice + "\"";
    }

    /**
     * All of the AmazonProduct getters
     */
	
	public float getActual_Price() {
		return actualPrice;
	}
	
	public float getDiscount_Price() {
		return discountPrice;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLink() {
		return link;
	}
	
	public AmazonProductCategory getMain_category() {
		return category;
	}
	
	public AmazonProductSubCategory getSub_category() {
		return subCategory;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getTitle() {
		return title;
	}
	
	public int getNo_of_ratings() {
		return nRatings;
	}
	
	public float getRatings() {
		return rating;
	}
	
	public String getUrl_Image() {
		return imageURL;
	}
	
	/**
     * All of the AmazonProduct Setters
     */
	
	public void setActual_Price(float myActualPrice) {
		actualPrice = myActualPrice;
	}
	
	public void setDiscount_Price(float myDiscountPrice) {
		discountPrice = myDiscountPrice;
	}
	
	public void setId( int myId) {
		id = myId;
	}
	
	public void setLink(String myLink) {
		link = myLink;
	}
	
	public void setMain_category(AmazonProductCategory myCategory) {
		category = myCategory;
	}
	
	public void setSub_category(AmazonProductSubCategory mySubCategory) {
		subCategory = mySubCategory;
	}
	
	public void setName(String myName) {
		 name = myName;
	}
	
	public void setTitle(String[] myTitle) {
		title = myTitle;
	}
	
	public void setNo_of_ratings(int my_nRatings) {
		nRatings = my_nRatings;
	}
	
	public void setRatings(float myRating) {
		rating = myRating;
	}
	
	public void setUrl_Image(String myImageURL) {
		imageURL = myImageURL;
	}
	


}
