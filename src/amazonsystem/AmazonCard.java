package amazonsystem;

import amazonsystem.AmazonCredit.PaymentType;

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
		setType(PaymentType.Card);
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
	
	public static AmazonCard createAmazonCard(String [] data) {
		   
		   if(data == null) {
			   return null;
			   
		   } else if(data.length != 3) {
			   return null;
		   }
		   
		   		
		        float amount = Float.parseFloat(data[2]);
				String number = data[0];
				String expiration = data[1];
				
				if(number.isBlank() || number.isEmpty() || expiration.isBlank() || expiration.isEmpty()) {
					return null;
				}
				
				AmazonCard card = new AmazonCard(data);
				
				
				   
				return card;
		
		}
	
	
	//getters and setters

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//to string: Prints the credit (showing credit type, credit number, expiration
	//and value).

}
