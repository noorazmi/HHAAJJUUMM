/**
 * This is Application class of the application
 */

package islamic.buzz.app;

import islamic.buzz.helpers.PreferenceHelper;
import islamic.buzz.toast.CustomToast;
import islamic.buzz.util.AuthUtil;
import islamic.buzz.util.ConfigurationUtils;
import islamic.buzz.util.UtilityMethods;
import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.eybsolution.islamic.buzz.BuildConfig;
import com.report.crash.CrashReport;

/**
 *
 */
public class BuzzApplication extends Application {
    private final String TAG = BuzzApplication.class.getSimpleName();

    private final int INDEX_HPROF = 0;

    private static BuzzApplication mInstance;

    private PreferenceHelper mAppPref;


    private boolean mIsClientConnect;

    private AuthUtil mAuthenticationUtils;


    private RequestQueue mRequestQueue;

    private CustomToast mCsutomToast;

    private ConfigurationUtils configurationUtils = null;

	private CrashReport mCrashReport;


    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        mAppPref = new PreferenceHelper(mInstance);
        mIsClientConnect = false;
        configurationUtils = new ConfigurationUtils();
        configurationUtils.initAppConfig();
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

   
   

    public PreferenceHelper getAppPref() {
        return mAppPref;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    
    /**
     * Returns crash reporting instance.
     * 
     * @return a instance for CrashReporting
     */
    public CrashReport getCrashReporting() {
        return mCrashReport;
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

    public CustomToast getCustomToast() {
        if (mCsutomToast == null) {
            mCsutomToast = new CustomToast(BuzzApplication.getContext());
        }
        return mCsutomToast;
    }

}
