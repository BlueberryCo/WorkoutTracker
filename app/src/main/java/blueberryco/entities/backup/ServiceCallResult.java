package blueberryco.entities.backup;

import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by boiko on 17-Mar-16.
 */

/**
 * Represents result from a call to google drive service. Can represents single service call or a sequence of
 * calls to google drive service.
 * @param <T> Type of result expected. It depends on what service call represents the result (backup or restore).
 */
public class ServiceCallResult<T> {

    private T result;
    private String message;

    public ServiceCallResult(){

    }

    /**
     * Gets the result from the service call.
     * @return The result from the service call or null if the status of the call is not success.
     */
    public T getResult() {
        return result;
    }

    /**
     * Sets the result from the service call.
     * @param result This should be the result returned from the drive service call or null if there
     *               is a error returned from the service.
     */
    public void setResult(@Nullable T result) {
        this.result = result;
    }

    /**
     * Gets the message from the service call.
     * @return The error message returned from the service call or null if the call is success.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message returned from the call to google drive service.
     * @param message The error message returned from the call to google drive service or
     *                null if the service call is success.
     */
    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    /**
     * Indicates whether the service call is success. For success result the message should be null
     * and the drive contents or file contents should have value.
     * @return true when the service call is success, otherwise false
     */
    public boolean isSuccess(){
        return result != null && TextUtils.isEmpty(message);
    }
}
