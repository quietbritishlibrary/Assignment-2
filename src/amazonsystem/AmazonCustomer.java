package amazonsystem;


import java.util.ArrayList;
import java.util.List;

public class AmazonCustomer {
	
	// field
	private int id;
	private String name;
	private String address;
	private List <AmazonComment> comments = new ArrayList <AmazonComment>();
	private List <AmazonProduct> wishlist = new ArrayList <AmazonProduct>();
	
	/**
     * AmazonCustomer parameterized-constructor
     * @param The customers id, name and address.
     */
	private AmazonCustomer(int myId, String myName, String myAddress) {
		id = myId;
		name = myName;
		address = myAddress;
	}
	
	/**
     * AmazonCustomer constructor used to assign substrings to appropriately parsed AmazonProduct property
     * @param String array of substrings of the customers data.
     */
	private AmazonCustomer(String [] data) {
		this.id = Integer.parseInt(data[0]);
		this.name = data[1];
		this.address = data[2];
	}
	
	
	
	/**
     * AmazonCustomer method used to create AmazonCustomer objects as long as they aren't null
     * @param String array of substrings of the customers data.
     */
	public static AmazonCustomer createAmazonCustomer(String [] data) {
		   
		   if(data == null) {
			   return null;
			   
		   } else if(data.length != 3) {
			   return null;
		   }
		   
		   
		   
			    int id = Integer.parseInt(data[0]);
				String name = data[1];
		        String address = data[2];
				
				AmazonCustomer customer = new AmazonCustomer(data);
				
				System.out.println(customer.toString());
				
				   
				return customer;
		
		}
	
	//public void addCredit
	
	
	

}
