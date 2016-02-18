package blueberryco.database;

/**
 * Created by boiko on 18-Feb-16.
 */
public class OwnerExistsException extends Exception {

    public OwnerExistsException(String message) {
        super(message);
    }

    public OwnerExistsException(Throwable cause) {
        super(cause);
    }

    public OwnerExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
