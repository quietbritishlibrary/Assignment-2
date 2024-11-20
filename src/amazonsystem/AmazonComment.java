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
		
		String report = String.format("- Comment: %s - rating: %f",comment,rating);
		return report;
	}
	
	

}
