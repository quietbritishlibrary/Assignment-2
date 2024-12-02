package amazonsystem;

/**
 * Custom exception class for Amazon-related errors.
 * This class extends {@link Exception} and is used to represent
 * specific exceptions that may occur within the Amazon system.
 */
@SuppressWarnings("serial")
public class AmazonException extends Exception {

    /**
     * Constructs a new AmazonException with the specified error message.
     *
     * @param errorMessage The detail message for the exception, which can later be retrieved
     *                     using {@link Exception#getMessage()}.
     */
    public AmazonException(String errorMessage) {
        super(errorMessage); 
    }

}
