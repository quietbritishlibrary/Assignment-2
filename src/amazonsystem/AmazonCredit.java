package amazonsystem;

public abstract class AmazonCredit {
	
	private enum PaymentType {Cash, Check, Card}
	private float amount;
	private PaymentType type;
	
	/**
     * superclass super() AmazonCredit's constructor 
     * @param amount of money on the payment type.
     */
	public AmazonCredit(float myAmount){
		amount = myAmount;
		//type = myType;
		//, PaymentType myType
	}
	
	/**
     * Abstract class AmazonCredit's toString() method
     * @return formatted AmazonCredit information based on user inputs.
     */
	public String toString() {
		//find index of the enum
		int enumIndex = type.ordinal();
		
		//format AmazonCredit info into string
		String report = String.format("- Credit %d: Type: %s, value: %.2f .",enumIndex, type, amount);
		return report;
	}

}
