
package islamic.buzz.error;

//import com.worklight.wlclient.api.WLFailResponse;

/**
 * Adapter error class that contains error code, error messages or exception and
 * error type
 */
public class Error {

    // Type of adapter operation.
    private ErrorType mErrorType;

    // Error code of application.
    private String mCode = "";

    // Error message.
    private String mMessage = "";

    // Exception object
    private Exception mException;

    public Error(String code,
            String message,
            ErrorType errorType) {
        mCode = code;
        mMessage = message;
        mErrorType = errorType;
    }

    /**
     * This constructor is to create Error object for Adapters
     * 
     * @param failResponse Adapter Failure Response
     * @param errorType Error Type of adapter
//     */
//    public Error(WLFailResponse failResponse,
//            ErrorType errorType) {
//        mCode = failResponse.getErrorCode().toString();
//        mMessage = failResponse.getErrorMsg();
//        mErrorType = errorType;
//    }

    /**
     * This constructor is used to create Error object for Application
     * Exceptions. The Error Type will be ErrorType.APPLICATION_EXCEPTION.
     * 
     * @param exception Exception generated
     */
    public Error(AppException exception) {
        this((Exception) exception);
    }
    
    public Error(Exception exception) {
        mException = exception;
        mErrorType = ErrorType.APPLICATION_EXCEPTION;
    }
    
    public ErrorType getErrorType() {
        return mErrorType;
    }

    public Exception getException() {
        return mException;
    }

    public String getErrorCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public static enum ErrorType {
        NO_NETWORK("Network not connected"),
        ADAPTER_PAYLOAD("Adapter Payload Error"),
        ADAPTER_ERROR("Adapter Error"),
        ADAPTER_FAILURE("Adapter Call Failure"),
        APPLICATION_EXCEPTION("Application Exception");

        private ErrorType(String errorDescription) {
            this.mErrorDescription = errorDescription;
        }

        // Description for Error Type
        private String mErrorDescription;

        /**
         * Returns Error Description for Error Type
         * 
         * @return Error Description
         */
        public String getErrorDescription() {
            return mErrorDescription;
        }

    }
}
