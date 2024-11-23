package amazonsystem;

public class AmazonCash extends AmazonCredit{
	
	/**
     * AmazonCash parameterized-constructor
     * @param The cash amount of the money.
     */
	private AmazonCash(float myAmount) {
		super(myAmount);
	}
	
	/**
     * AmazonCash constructor used to assign substrings to appropriately parsed AmazonCheck property
     * @param String array of substrings of the check data.
     */
	private AmazonCash(String[] data) {
		super(Float.parseFloat(data[0]));
	}
	
	

}
