package amazonsystem;

public class AmazonCash extends AmazonCredit{
	
	/**
     * AmazonCash parameterized-constructor
     * @param The cash amount of the money.
     */
	private AmazonCash(float myAmount) {
		super(myAmount);
		setType(PaymentType.Cash);
	}
	
	/**
     * AmazonCash constructor used to assign substrings to appropriately parsed AmazonCheck property
     * @param String array of substrings of the check data.
     */
	private AmazonCash(String[] data) {
		super(Float.parseFloat(data[0]));
	}
	
	/**
     * AmazonCash method used to create AmazonCash objects as long as they aren't null
     * @param String array of substrings of the checks data.
     */
	public static AmazonCash createAmazonCash(String [] data) {
		   
		   if(data == null) {
			   return null;
			   
		   } else if(data.length != 1) {
			   return null;
		   }
		   

		        float amount = Float.parseFloat(data[0]);		
				AmazonCash cash = new AmazonCash(data);
				
				
				   
				return cash;
		
		}
	
	
	@Override
    public String toString() {
        return super.toString();  
    }
	
	
	
	

}
