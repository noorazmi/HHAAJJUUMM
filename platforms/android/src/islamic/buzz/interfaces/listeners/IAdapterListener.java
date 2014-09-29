
package islamic.buzz.interfaces.listeners;

import islamic.buzz.vo.IValueObject;

/**
 * Network listener that will be implemented by classes to notify components
 * that performed network task is successful or not.
 */
public interface IAdapterListener {
    /**
     * Called when performed network task is successful.
     */
    public void onSuccess(IValueObject valueObject);

    /**
     * Called when performed network task is not successful.
     */
    public void onFailure(Error ex);

    /**
     * @param payload
     * @return
     */
    public Error getPayloadError(IValueObject payload);

    /**
     * @param payload
     * @return
     */
    public long getCacheHeader(IValueObject payload);
}
