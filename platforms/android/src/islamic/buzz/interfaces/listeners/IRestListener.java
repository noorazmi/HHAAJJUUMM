
package islamic.buzz.interfaces.listeners;


/**
 * Network listener that will be implemented by classes to notify components
 * that performed network task is successful or not.
 */
public interface IRestListener {
    /**
     * Called when performed network task is successful.
     */
    public void onSuccess(Object valueObject);

    /**
     * Called when performed network task is not successful.
     */
    public void onFailure(Error error);

}
