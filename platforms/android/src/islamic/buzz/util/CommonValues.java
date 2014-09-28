
package islamic.buzz.util;

import islamic.buzz.app.BuzzApplication;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * Provides and contains application wide modifiable common values
 */
public class CommonValues {
    private static CommonValues instance;

    private boolean mEnableBugSense;

    private boolean mEnableAnalytics;

    private boolean mEnableDigby;

    private boolean mEnableForseeFeedback;

    private String mKohlsPasscode;

    private int mIdleTimeout;

    private int mSigninTimeout;

    private int mAccessTokenExpiryTimeout;

    private CommonValues() {

    	try{
    		
//    		mSigninTimeout = Integer.parseInt(BuzzApplication.getInstance().getConfigurationUtils().getConfig().getSigninTimeout());
//            mAccessTokenExpiryTimeout = ConstantValues.APP_ACCESS_TOKEN_EXPIRY;
//            mIdleTimeout = Integer.parseInt(BuzzApplication.getInstance().getConfigurationUtils().getConfig().getIdleTimeout());
            // Private constructor to make class singleton
    		
    	}catch(NumberFormatException numberFormatException){
    		Logger.debug(numberFormatException.getMessage(), numberFormatException.getMessage());
    	}
    	
    }

    public static CommonValues getInstance() {
        if (instance == null) {
            instance = new CommonValues();
        }
        return instance;
    }

    public boolean isEnableBugSense() {
        try {
            ApplicationInfo applicationInfo = BuzzApplication.getInstance()
                    .getPackageManager()
                    .getApplicationInfo(BuzzApplication.getInstance().getPackageName(),
                            PackageManager.GET_META_DATA);
            boolean enableBugSenseQA = applicationInfo.metaData.getBoolean("enable_bugsense_qa");
            return enableBugSenseQA;
        } catch (NameNotFoundException e) {
            // Don't do anything
        }
        return mEnableBugSense;
    }

    public void setEnableBugSense(boolean mEnableBugSense) {
        this.mEnableBugSense = mEnableBugSense;
    }

    public boolean isEnableDigby() {
        return mEnableDigby;
    }

    public void setEnableDigby(boolean mEnableDigby) {
        this.mEnableDigby = mEnableDigby;
    }

    public boolean isEnableAnalytics() {
        return mEnableAnalytics;
    }

    public void setEnableAnalytics(boolean mEnableAnalytics) {
        this.mEnableAnalytics = mEnableAnalytics;
    }

    public String getKohlsPasscode() {
        return mKohlsPasscode;
    }

    public void setKohlsPasscode(String mKohlsPasscode) {
        this.mKohlsPasscode = mKohlsPasscode;
    }

    public void setIdleTimeOut(int idleTimeout) {
        mIdleTimeout = idleTimeout;
    }

    public int getIdleTimeOut() {
        return mIdleTimeout;
    }

    public void setSignInTimeOut(int mSigninTimeout) {
        this.mSigninTimeout = mSigninTimeout;
    }

    public int getSignInTimeOut() {
        return mSigninTimeout;
    }

    public void setAccesstokenExpiry(int mAccessTokenExpiryTimeout) {
        this.mAccessTokenExpiryTimeout = mAccessTokenExpiryTimeout;
    }

    public int getAccesstokenExpiry() {
        return mAccessTokenExpiryTimeout;
    }

}
