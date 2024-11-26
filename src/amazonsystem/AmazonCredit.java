package amazonsystem;

public abstract class AmazonCredit {
	
	enum PaymentType {Cash, Check, Card}
	private float amount;
	private PaymentType type;
	
	/**
     * superclass super() AmazonCredit's constructor 
     * @param amount of money on the payment type.
     */
	public AmazonCredit(float myAmount){
		amount = myAmount;

	}
	
	/**
     * Abstract class AmazonCredit's toString() method
     * @return formatted AmazonCredit information based on user inputs.
     */
	public String toString() {
		//find index of the enum
		int enumIndex = type.ordinal();
		
		//format AmazonCredit info into string
		return String.format("- Credit [%d]: Type: %s, value: %.2f .",enumIndex, type, amount);
		
	}
	
	//getters and setters
	
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}
	
	

}
