
package islamic.buzz.hybrid.plugins;

import com.kohlsphone.BuildConfig;
import com.kohlsphone.common.util.Logger;
import com.kohlsphone.common.util.UtilityMethods;
import com.kohlsphone.common.value.CommonValues;
import com.kohlsphone.common.value.ConstantValues;
import com.kohlsphone.framework.view.activity.HomeActivity;
import com.kohlsphone.wl.WLActivity;

import org.apache.cordova.CordovaActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class NativeHybridCommPlugin extends BasePlugin {
    private static final String TAG = NativeHybridCommPlugin.class.getSimpleName();

    private static final String ACTION_GET_VALUES = "GET_VALUES";

    private static final String ACTION_SCREEN_URL = "SCREEN_URL";

    private static final String ACTION_SET_SCREEN_TITLE = "SET_SCREEN_TITLE";

    private static final String ACTION_PERFORM_GO_SHOPPING = "PERFORM_GO_SHOPPING";

    private static final String NATIVE_VAL_PLATFORM = "platform";

    private static final String NATIVE_VAL_DEBUG_MODE = "debugMode";

    private static final String NATIVE_VAL_BUGSENSE_ENABLED = "bugsenseEnabled";

    private static final String NATIVE_VAL_VERSION = "version";

    private static final String NATIVE_OPEN_DIALER = "openDialer";

    @Override
    public boolean performAction(String action,
            JSONArray args) throws JSONException {
        if (ACTION_GET_VALUES.equals(action)) {
            try {
                String nativeValues = getNativeValues();
                sendSuccess(nativeValues);
            } catch (Exception e) {
                handleError(e);
            }
            return true;
        } else if (ACTION_SCREEN_URL.equals(action)) {
            try {
                String screenUrl = getScreenUrl();
                sendSuccess(screenUrl);
            } catch (Exception e) {
                handleError(e);
            }
            return true;
        } else if (ACTION_SET_SCREEN_TITLE.equals(action)) {
            try {
                setScreenTitle(args.getString(0));
                sendSuccess();
            } catch (Exception e) {
                handleError(e);
            }
            return true;
        } else if (ACTION_PERFORM_GO_SHOPPING.equals(action)) {
            try {
                final WLActivity activity = getWLActivity();
                performGoShopping(activity);
                sendSuccess();
            } catch (Exception e) {
                handleError(e);
            }
            return true;
        } else if (NATIVE_OPEN_DIALER.equals(action)) {
            try {
                UtilityMethods.makeCall(getWLActivity(), args.getString(0));
                sendSuccess();
            } catch (Exception e) {
                handleError(e);
            }
            return true;
        }
        return false;
    }

    private WLActivity getWLActivity() throws Exception {
        final WLActivity activity = (WLActivity) this.cordova.getActivity();
        if (activity == null) {
            throw new Exception("Activity instance is null");
        }
        return activity;
    }

    private void handleError(Exception e) {
        Logger.error(TAG, e.getMessage());
        e.getStackTrace();
        sendError();
    }

    private String getScreenUrl() throws Exception {
        final WLActivity activity = (WLActivity) this.cordova.getActivity();
        return activity.getScreenUrl();
    }

    public void goToHomeScreen(WLActivity activity) throws Exception {
        performGoShopping(activity);
    }

    private void performGoShopping(final WLActivity activity) throws Exception {
        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(activity, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
            }
        });

        activity.endActivity();
    }

    private void setScreenTitle(final String screenTitle) throws Exception {
        final WLActivity activity = getWLActivity();

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                activity.setActionBarTitle(screenTitle);
            }
        });
    }

    private String getNativeValues() throws Exception {
        String returnObject = null;
        JSONObject json = new JSONObject();
        WLActivity activity = getWLActivity();

        json.put(NATIVE_VAL_PLATFORM, ConstantValues.APP_PLATFORM);
        json.put(NATIVE_VAL_DEBUG_MODE, String.valueOf(BuildConfig.DEBUG));
        json.put(NATIVE_VAL_BUGSENSE_ENABLED,
                String.valueOf(CommonValues.getInstance().isEnableBugSense()));
        json.put(NATIVE_VAL_VERSION, getAppVersionNumber(activity));

        returnObject = json.toString();

        return returnObject;
    }

    /**
     * Obtain Application version number to be shown in Hybrid Screen
     * 
     * @return String version number
     */
    private String getAppVersionNumber(CordovaActivity activity) {
        String appVersionNumber = "";
        try {
            PackageInfo pInfo =
                    activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            appVersionNumber = pInfo.versionName + "." + pInfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersionNumber;
    }
}
