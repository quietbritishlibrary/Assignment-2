package amazonsystem;

import amazonsystem.AmazonCredit.PaymentType;

public class AmazonCard extends AmazonCredit {
	
	//properties
	private String number;
	private String expiration;
	
	/**
     * AmazonCard parameterized constructor
     * @param myNumber - card number
     * @param myExpiration - expiration date (MM/YYYY)
     * @param myAmount - the amount on the card
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
		setType(PaymentType.Card);
	}
	
	
	
	
	
	/**
     * AmazonCard method used to create AmazonCard objects as long as they aren't null
     * @param String array of substrings of the checks data.
     */
	
	public static AmazonCard createCard(String [] data) {
		
		   
		   if(data == null || data.length != 3) {
			   return null;
			   
		   } 
		   
		   float amount;
		   
		   try {
		   		
		         amount = Float.parseFloat(data[2]);
				 
				
		   }catch(NumberFormatException e) {
			   return null;
		   }
		   
		   	 String number = data[0];
			 String expiration = data[1];	
		   
				if(number.isBlank() || number.isEmpty() || expiration.isBlank() || expiration.isEmpty() || amount < 0) {
					return null;
				}
				
				AmazonCard card = new AmazonCard(data);
				
				
				   
				return card;
		
		}
	
	@Override
    public String toString() {
        return super.toString();  
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
