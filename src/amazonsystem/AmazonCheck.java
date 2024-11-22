package amazonsystem;

public class AmazonCheck extends AmazonCredit {
	
	private String accountNumber;
	
	
	/**
     * AmazonCheck parameterized-constructor
     * @param The checks account number, as well as the amount of money on the check itself.
     */
	private AmazonCheck(String myAccountNumber, float myAmount ) {
		super(myAmount);
		accountNumber = myAccountNumber;
	}
	

	/**
     * AmazonCheck constructor used to assign substrings to appropriately parsed AmazonCheck property
     * @param String array of substrings of the check data.
     */
	private AmazonCheck(String [] data) {
		super(Float.parseFloat(data[1]));
		this.accountNumber = data[0];
		
	}
	
	/**
     * AmazonCustomer method used to create AmazonCheck objects as long as they aren't null
     * @param String array of substrings of the checks data.
     */
	
	public static AmazonCheck createAmazonCheck(String [] data) {
		   
		   if(data == null) {
			   return null;
			   
		   } else if(data.length != 2) {
			   return null;
		   }
		   

		        float amount = Float.parseFloat(data[1]);
				String accountNumber = data[0];
				
				AmazonCheck check = new AmazonCheck(data);
				
				System.out.println(check.toString());
				
				   
				return check;
		
		}
	
	
	
	
	
	
	
	
	
	//toString: Prints the credit (showing credit type, account and value).

}
