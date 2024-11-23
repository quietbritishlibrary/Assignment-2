package amazonsystem;

public class AmazonComment {
	
	private String comment;
	private AmazonProduct product;
	private float rating;
	
	
	/**
     * AmazonComment parameterized-constructor
     * @param an AmazonProduct Object to assign the comment to.
     */
	
	public AmazonComment(AmazonProduct myProduct) {
		product = myProduct;
	}
	
	
	/**
     * Method for formatting the comment into a concise string.
     * @return the formatted comment.
     */
	
	public String toString() {
		return String.format("- Comment: %s - rating: %f",comment,rating);
	}

	//getters and setters 
	
	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public AmazonProduct getProduct() {
		return product;
	}


	public void setProduct(AmazonProduct product) {
		this.product = product;
	}


	public float getRating() {
		return rating;
	}


	public void setRating(float rating) {
		this.rating = rating;
	}
	
	

}
