
package islamic.buzz.controllers;

import islamic.buzz.interfaces.listeners.IAdapterListener;


/**
 * Base class for all controllers
 */
public abstract class BaseControllerImpl implements IAdapterListener {
    protected final String TAG = this.getClass().getSimpleName();
}
