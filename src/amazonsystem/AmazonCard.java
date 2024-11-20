package amazonsystem;

public class AmazonCard extends AmazonCredit {
	
	private String number;
	private String expiration;
	
	
	private AmazonCard(String myNumber, String myExpiration, float myAmount ) {
		super(myAmount);
		number = myNumber;
		expiration = myExpiration;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//to string: Prints the credit (showing credit type, credit number, expiration
	//and value).

}
