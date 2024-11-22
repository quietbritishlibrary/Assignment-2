package amazonsystem;

public class AmazonCard extends AmazonCredit {
	
	//properties
	private String number;
	private String expiration;
	
	/**
     * AmazonCard parameterized-constructor
     * @param The card number, expiration date and total balance.
     */
	private AmazonCard(String myNumber, String myExpiration, float myAmount ) {
		super(myAmount);
		number = myNumber;
		expiration = myExpiration;
	}
	
	/**
     * AmazonCard constructor for assigning the substrings to variables
     * @param String array of substrings of the check data.
     */
	private AmazonCard(String [] data ) {
		
		super(Float.parseFloat(data[2]));
		String number = data[0];
		String expiration = data[1];
	}
	
	
	
	
	
	/**
     * AmazonCard method used to create AmazonCard objects as long as they aren't null
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//to string: Prints the credit (showing credit type, credit number, expiration
	//and value).

}
