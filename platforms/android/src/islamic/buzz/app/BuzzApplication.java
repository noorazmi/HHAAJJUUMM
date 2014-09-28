/**
 * This is Application class of the application
 */

package islamic.buzz.app;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.kohlsphone.BuildConfig;
import com.kohlsphone.R;
import com.kohlsphone.common.ui.toast.KohlsToast;
import com.kohlsphone.common.util.ConfigurationUtils;
import com.kohlsphone.common.util.UtilityMethods;
import com.kohlsphone.common.util.auth.AuthUtil;
import com.kohlsphone.common.value.CommonValues;
import com.kohlsphone.common.value.ConstantValues;
import com.kohlsphone.helper.preference.PreferenceHelper;
import com.worklight.wlclient.api.WLClient;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/**
 *
 */
public class BuzzApplication extends Application {
    private final String TAG = BuzzApplication.class.getSimpleName();

    private final int INDEX_HPROF = 0;

    private static BuzzApplication mInstance;

    private PreferenceHelper mKohlsPref;

    private WLClient mClient;

    private boolean mIsClientConnect;

    private AuthUtil mAuthenticationUtils;

    private KohlsCrashReporting mKohlsCrashReporting;

    private RequestQueue mRequestQueue;

    private KohlsToast mKohlsToast;

    private ConfigurationUtils configurationUtils = null;

    private KohlsAnalytics mKohlsAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        // Logger.debug(TAG, "KohlsApp.onCreate was called");

        mInstance = this;

        mKohlsPref = new PreferenceHelper(mInstance);
        mClient = WLClient.createInstance(this);
        // ConfigurationUtils configUtils = new ConfigurationUtils();
        // configUtils.initAppConfig();
        mIsClientConnect = false;
        configurationUtils = new ConfigurationUtils();
        configurationUtils.initAppConfig();
        initAppCrashReporting(getResources().getString(R.string.bugSenseKey));
    }

    public static BuzzApplication getInstance() {
        return mInstance;
    }

    public ConfigurationUtils getConfigurationUtils() {
        return configurationUtils;
    }

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }

    /**
     * Returns crash reporting instance.
     * 
     * @return a instance for CrashReporting
     */
    public KohlsCrashReporting getCrashReporting() {
        return mKohlsCrashReporting;
    }

    /**
     * Creates a instance of Kohls Analytics
     * 
     * @param mOmnitureServer
     * @return
     */
    public KohlsAnalytics getAnalytics() {
        if (mKohlsAnalytics == null) {
            // TODO(sanchit.gupta) remove hard coded boolean value
            mKohlsAnalytics = new KohlsAnalytics(getContext(),
                    getKohlsPref().getOmnitureServer(),
                    ConstantValues.OMNITURE_CONFIGURATION_RSSID,
                    true);
            AnalyticsPrefObject analyticsPrefObject = new AnalyticsPrefObject();
            analyticsPrefObject.setCartStatus(CartStatus.ORIGINAL.toString());
            getKohlsPref().saveAnalyticsPrefObject(analyticsPrefObject);
        }
        return mKohlsAnalytics;
    }

    public PreferenceHelper getKohlsPref() {
        return mKohlsPref;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public WLClient getWLClient() {
        if (mClient == null) {
            mClient = WLClient.createInstance(BuzzApplication.getContext());
        }
        return mClient;
    }

    @Override
    public void onLowMemory() {
        if (BuildConfig.DEBUG) {
            UtilityMethods.recordHprof(INDEX_HPROF);
        }
        System.gc();
        super.onLowMemory();
    }

    public boolean isClientConnect() {
        return mIsClientConnect;
    }

    public void setClientConnect(boolean isClientConnect) {
        this.mIsClientConnect = isClientConnect;
    }

    /**
     * Returns Authentication utils of the application.
     * 
     * @return
     */
    public AuthUtil getAuthenticationUtils() {
        return mAuthenticationUtils;
    }

    public void setAuthenticationUtils(Activity currAct) {

        mAuthenticationUtils = AuthUtil.getInstance(currAct);
        if (mAuthenticationUtils.isUserSignedIn()) {
            mAuthenticationUtils.startSigninTimer();
        }
    }

    /**
     * Initialize app crash reporting.
     * 
     * @param bugSenseKey bug sense key.
     */
    public void initAppCrashReporting(String bugSenseKey) {
        mKohlsCrashReporting = new KohlsCrashReporting(getContext(), CommonValues.getInstance()
                .isEnableBugSense(), bugSenseKey);
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public KohlsToast getKohlsToast() {
        if (mKohlsToast == null) {
            mKohlsToast = new KohlsToast(BuzzApplication.getContext());
        }
        return mKohlsToast;
    }

}
