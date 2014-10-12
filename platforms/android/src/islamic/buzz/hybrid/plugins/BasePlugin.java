/**
 * 
 */

package islamic.buzz.hybrid.plugins;

import islamic.buzz.db.helper.DBOperationsHelper;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Native Hybrid Bridge Plugin Base Class
 */
public abstract class BasePlugin extends CordovaPlugin {
    protected String TAG = this.getClass().getSimpleName();

    private CallbackContext callbackContext;

    private DBOperationsHelper helper;

    private void sendPluginResult(PluginResult.Status status,
            String data) {
        PluginResult pluginResult = new PluginResult(status, data);
        callbackContext.sendPluginResult(pluginResult);
    }

    protected void sendSuccess(String data) {
        sendPluginResult(PluginResult.Status.OK, data);
    }

    protected void sendError(String data) {
        sendPluginResult(PluginResult.Status.ERROR, data);
    }

    protected void sendSuccess() {
        sendSuccess("{}");
    }

    protected void sendError() {
        sendError("");
    }

    protected DBOperationsHelper getHelper() {
        if (helper == null) {
            helper = new DBOperationsHelper();
        }
        return helper;
    }

    @Override
    public boolean execute(String action,
            JSONArray args,
            CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        return performAction(action, args);
    }

    /**
     * Method to perform action. If action is not matching the actions served
     * return false signifying no matching action
     * 
     * @param action
     * @param args
     * @param callbackContext
     * @return
     */
    public abstract boolean performAction(final String action,
            final JSONArray args) throws JSONException;
}
