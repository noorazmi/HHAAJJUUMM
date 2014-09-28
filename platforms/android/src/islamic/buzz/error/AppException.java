package islamic.buzz.error;
/**
 * Application Exception class
 */
public class AppException extends Exception {
    private static final long serialVersionUID = 7296322336299920931L;

    private static final String PREFIX = "ISLAMIC_BUZZ_EXCEPTION: ";

    public AppException(String message) {
        super(PREFIX + message);
    }
}
