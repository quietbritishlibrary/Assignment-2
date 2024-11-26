package amazonsystem;

@SuppressWarnings("serial")
public class AmazonException extends Exception {
	

	/**
	 * Constructs a new AmazonException with the specified error message.
	 * @param errorMessage The detail message for the exception, which can later be retrieved
	 *                     using {@link Exception#getMessage()}.
	 */
		public AmazonException(String errorMessage) {
			super(errorMessage); // Pass the error message to the parent Exception class
		}

	

}
