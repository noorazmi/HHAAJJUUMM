
package islamic.buzz.util;

import islamic.buzz.app.BuzzApplication;
import islamic.buzz.encryption.CryptUtil;
import islamic.buzz.helpers.PreferenceHelper;
import android.app.Activity;
import android.provider.ContactsContract.Profile;
import android.text.TextUtils;

import com.eybsolution.islamic.buzz.R;

/**
 * Utility class to handle all the tasks of authentication & handling of user's
 * access & refresh tokens.
 */

public class AuthUtil {

    private static AuthUtil mAuthUtil;

    // Checks whether user is signed in or not.
    public static boolean mIsUserSignedIn;

    // User name to be shown in action bar after signing in.
    private String mUserName;

    // Secure timer of the user's session.
    private SecureTimer mSecureTimer;

    // Idle timer of the user's session.
   // private IdleTimer mIdleTimer;

    // Instance of the activity to perform context operations
    private final Activity mAct;


    private AuthUtil(Activity mAct) {
        this.mAct = mAct;
    }

    /**
     * Returns the singleton instance of AuthUtil
     * 
     * @param mAct
     * @return AuthUtil
     */
    public static synchronized AuthUtil getInstance(Activity mAct) {
        if (mAuthUtil == null) {
            mAuthUtil = new AuthUtil(mAct);
        }
        return mAuthUtil;
    }

    /**
     * Authenticate user through adapter.
     * 
     * @param userId user's id.
     * @param password password of the user.
     * @param listener listener to be invoked after completion of
     *            authentication.
     */
    public void authenticateUser(String userId,
            String password
            /*IAdapterListener listener*/) {

        // Params params = signInAndProfilePO.new Params();
        // params.setuserId(userId);
        // params.setpassword(password);
        // params.setgrant_type(ConstantValues.GRANT_TYPE_PASSWORD);
        // signInAndProfilePO.setparams(params);

//        AdapterHelper adapterTask = new AdapterHelper(AdapterProcedure.SIGNIN_AND_PROFILE,
//                signInAndProfilePO,
//                listener);
//        adapterTask.performTask();
    }

    /**
     * Save user credentials and information in application preferences after
     * successful sign in.
     * 
     * @param accessToken access token of the user.
     * @param refreshToken refresh token of the user.
     * @param firstName user's name.
     * @param expireTime expiry time of the token.
     * @param email email of user.
     * @param expiryDigestedTime TODO
     * @param expiryStringTime TODO
     * @param profileID TODO
     * @param lastName TODO
     */

    public void saveUserCredentialsAndInfo(String accessToken,
            String refreshToken,
            String firstName,
            long expireTime,
            String email,
            String expiryDigestedTime,
            String expiryStringTime,
            String profileID,
            String lastName) {
        CryptUtil kohlsCryptoUtil = new CryptUtil("passcode");
        String encryptAccessToken = kohlsCryptoUtil.doCrypto(accessToken);
        String encryptRefreshToken = kohlsCryptoUtil.doCrypto(refreshToken);
        String encryptUserName = kohlsCryptoUtil.doCrypto(firstName);
        String encryptEmail = kohlsCryptoUtil.doCrypto(email);
        lastName = kohlsCryptoUtil.doCrypto(lastName);
        profileID = kohlsCryptoUtil.doCrypto(profileID);
//        BuzzApplication.getInstance()
//                .getAppPref()
//                .saveUserCredentialsAndInfo(encryptAccessToken,
//                        encryptRefreshToken,
//                        encryptUserName,
//                        expireTime,
//                        encryptEmail,
//                        profileID,
//                        expiryDigestedTime,
//                        expiryStringTime,
//                        lastName);
//        updateSecureTime();
    }

    /**
     * Upate user credentials tokens in application preferences after refreshing
     * a new access token.
     * 
     * @param accessToken Access token of the user.
     * @param refreshToken Refresh token of the access token.
     */
    public void updateUserCredentialsTokens(String accessToken,
            String refreshToken) {
        PreferenceHelper kohlsPreference = BuzzApplication.getInstance().getAppPref();
        CryptUtil kohlsCryptoUtil = new CryptUtil("passcode");
        String encryptAccessToken = kohlsCryptoUtil.doCrypto(accessToken);
        String encryptRefreshToken = kohlsCryptoUtil.doCrypto(refreshToken);
        kohlsPreference.updateUserCredentialsTokens(encryptAccessToken, encryptRefreshToken);
    }

    /**
     * Clear user credentials & information.
     */
    public void clearUserCredentialAndInfo() {
        if (mSecureTimer != null && mSecureTimer.isTimerRunning()) {
            mSecureTimer.stopTimer();
            mSecureTimer = null;
        }
        BuzzApplication.getInstance().getAppPref().clearUserCredentialAndInfo(true);
        setUserSignInStatus(false);
    }

    /**
     * Sets true if user is signed in else set false. Also set login state for
     * analytics.
     * 
     * @param signedIn contains true if user is signed in else false.
     */
    public void setUserSignInStatus(boolean signedInStatus) {
        BuzzApplication.getInstance().getAppPref().setUserSignInStatus(signedInStatus);

//        if (!signedInStatus) {
//            // delete all current Shopping Bag Items since they will be
//            // replaced
//            // by the items from getCart
//
//            DBShoppingBagHelper shoppingBagHelper = new DBShoppingBagHelper();
//            shoppingBagHelper.deleteAll(DBTablesDef.T_SHOPPING_BAG);
//        }
        // BuzzApplication.getInstance().getAnalytics().setLoginStatus(signedIn);
        // BuzzApplication.getInstance()
        // .getAnalytics()
        // .setUserId(BuzzApplication.getInstance().getKohlsPref().getSignedInUserId());
    }

    /**
     * Returns true if user is signed in else false.
     */
    public boolean isUserSignedIn() {
        return BuzzApplication.getInstance().getAppPref().getUserSignInStatus();
        // return mIsUserSignedIn;
    }

    public void setUserName(String userName) {
        CryptUtil kohlsCryptoUtil = new CryptUtil("passcode");

        userName = kohlsCryptoUtil.doCrypto(userName);
        BuzzApplication.getInstance().getAppPref().saveUserName(userName);
    }

    public String getSignedInUserID() {
        CryptUtil kohlsCryptoUtil = new CryptUtil("password");
        String encryptedUserID = BuzzApplication.getInstance()
                .getAppPref()
                .getSignedInUserId();
        if (!TextUtils.isEmpty(encryptedUserID)) {
            encryptedUserID = kohlsCryptoUtil.doDeCrypto(encryptedUserID);
        }
        return encryptedUserID;
    }

    public String getSignedInUsernameFromPref() {
        CryptUtil kohlsCryptoUtil = new CryptUtil("password");
        String encryptedUsername = BuzzApplication.getInstance()
                .getAppPref()
                .getSignedInUserName();
        String userName = "";
        if (!TextUtils.isEmpty(encryptedUsername)) {
            userName = kohlsCryptoUtil.doDeCrypto(encryptedUsername);
        }
        return userName;
    }

    public String getSignedInUserLastnameFromPref() {
        CryptUtil kohlsCryptoUtil = new CryptUtil("password");
        String encryptedUserlastname = BuzzApplication.getInstance()
                .getAppPref()
                .getSignedinUserLastName();
        String lastName = "";
        if (!TextUtils.isEmpty(encryptedUserlastname)) {
            lastName = kohlsCryptoUtil.doDeCrypto(encryptedUserlastname);
        }
        return lastName;
    }

    public void updateResetMyPasswordDetails(String password
            /*,IAdapterListener listener*/) {

//        ResetMyPasswordPO passwordPO = new ResetMyPasswordPO();
//
//        Params params = passwordPO.new Params();
//        PayLoad payload = params.new PayLoad();
//        Profile profile = payload.new Profile();
//        CustomerName custname = profile.new CustomerName();
//
//        passwordPO.setAccess_token(getUserAccessTokenFromPref());
//        custname.setFirstName(getSignedInUsernameFromPref());
//        custname.setLastName(getSignedInUserLastnameFromPref());
//        profile.setPassword(password);
//        profile.setEmail(getPreviousUserMailIdFromPref());
//
//        profile.setCustomerName(custname);
//        payload.setProfile(profile);
//        params.setPayload(payload);
//        passwordPO.setParams(params);

//        AdapterHelper helper = new AdapterHelper(AdapterProcedure.UPDATE_ACCOUNT,
//                passwordPO,
//                listener);
//        helper.performTask();
    }

    public void updateSignInInfoDetails(/*IAdapterListener listener,*/
            String firstName,
            String lastName,
            String emailId) {
//
//        SignInInfoPO signInInfoPO = new SignInInfoPO();
//
//        SignInInfoPO.Params params = signInInfoPO.new Params();
//        SignInInfoPO.Params.PayLoad payload = params.new PayLoad();
//        SignInInfoPO.Params.PayLoad.Profile profile = payload.new Profile();
//        SignInInfoPO.Params.PayLoad.Profile.CustomerName custname = profile.new CustomerName();
//
//        signInInfoPO.setAccess_token(getUserAccessTokenFromPref());
//        custname.setFirstName(firstName);
//        custname.setLastName(lastName);
//        profile.setEmail(emailId);
//
//        profile.setCustomerName(custname);
//        payload.setProfile(profile);
//        params.setPayload(payload);
//        signInInfoPO.setParams(params);
//
//        AdapterHelper helper = new AdapterHelper(AdapterProcedure.UPDATE_ACCOUNT,
//                signInInfoPO,
//                listener);
//        helper.performTask();
    }

    public String getPreviousUserMailIdFromPref() {
        CryptUtil kohlsCryptoUtil = new CryptUtil("password");
        String encryptedUsermail = BuzzApplication.getInstance()
                .getAppPref()
                .getUserMailId();
        String userMail = "";
        if (!TextUtils.isEmpty(encryptedUsermail)) {
            userMail = kohlsCryptoUtil.doDeCrypto(encryptedUsermail);
        }
        return userMail;
    }

    public void setUserNewMailIdInPref(String mailId) {
        CryptUtil kohlsCryptoUtil = new CryptUtil("password");
        String encryptedUsermail = kohlsCryptoUtil.doCrypto(mailId);
        BuzzApplication.getInstance().getAppPref().setUserMailId(encryptedUsermail);
    }

    /**
     * @return Access token
     */
    public String getUserAccessTokenFromPref() {
        CryptUtil kohlsCryptoUtil = new CryptUtil("password");
        String encryptAccessToken = BuzzApplication.getInstance()
                .getAppPref()
                .getAccessToken();
        String accessToken = "";
        if (!TextUtils.isEmpty(encryptAccessToken)) {
            accessToken = kohlsCryptoUtil.doDeCrypto(encryptAccessToken);
        }
        return accessToken;
    }

    /**
     * @return Refresh token
     */
    public String getRefreshTokenFromPref() {
        CryptUtil kohlsCryptoUtil = new CryptUtil("password");
        String encryptRefreshToken = BuzzApplication.getInstance()
                .getAppPref()
                .getRefreshToken();
        String refreshToken = "";
        if (!TextUtils.isEmpty(encryptRefreshToken)) {
            refreshToken = kohlsCryptoUtil.doDeCrypto(encryptRefreshToken);
        }
        return refreshToken;
    }

    

    /**
     * Checks whether user session is valid or not. It checks whether user
     * session time is less than expired time.
     * 
     * @return true if session is is valid else returns false.
     */
    public boolean checkUserSessionValid() {
        PreferenceHelper PreferenceHelper = BuzzApplication.getInstance().getAppPref();
        long expireTime = PreferenceHelper.getAccessTokenExpireTime();
        long signInTime = PreferenceHelper.getSignInTime();
        long currentTime = (System.currentTimeMillis() / 1000);
        if (expireTime != 0 && currentTime != 0 && signInTime != 0) {
            expireTime = expireTime - CommonValues.getInstance().getAccesstokenExpiry();
            boolean signedIn = ((currentTime - signInTime) < expireTime) ? true : false;
            if (!signedIn && mSecureTimer != null && mSecureTimer.isTimerRunning()) {
                return false;
            }
            setUserSignInStatus(signedIn);
            return signedIn;
        }
        return false;

    }

    // This is the IDLE TIMER - This works for both guest and signed in user,
    // after this is complete user will be pushed out of secure zones
    // this will only be reset on user touch event
//    private static class IdleTimer extends Thread {
//
//        // Idle timeout of the user's session in seconds.
//        private long mIdleTimeout;
//
//        // Indicates timer is running
//        private boolean mIsRunning = true;
//
//        public IdleTimer(long mIdleTimeout) {
//            this.mIdleTimeout = mIdleTimeout;
//        }
//
//        @Override
//        public void run() {
//            while (mIsRunning) {
//                long currentTime = (System.currentTimeMillis() / 1000);
//                long idleTime = BuzzApplication.getInstance()
//                        .getAppPref()
//                        .getIdleTimeStart();
//                if (currentTime - idleTime > mIdleTimeout) {
//                    BuzzApplication.getInstance()
//                            .getAuthenticationUtils()
//                            .checkAndUpdateSecureScreen();
//                    break;
//                } else {
//                    try {
//                        sleep(mIdleTimeout * 1000);
//                    } catch (InterruptedException e) {
//                        // Do nothing & let loop to continue.
//                    }
//                }
//            }
//            mIsRunning = false;
//        }
//
//        /**
//         * Stops the timer.
//         */
//        public void stopIdleTimer() {
//            mIsRunning = false;
//        }
//
//        /**
//         * Starts the timer.
//         */
//        public void startIdleTimer() {
//            mIsRunning = true;
//            start();
//        }
//
//        /**
//         * Returns true if timer is running else returns false.
//         * 
//         * @return
//         */
//        public boolean isIdleTimerRunning() {
//            return mIsRunning;
//        }
//    }

//    /**
//     * Sends refresh token request to adapter.
//     * 
//     * @param listener adapter listener to be invoked after completion of
//     *            request.
//     */
//    public void sendRefreshTokenReq(IAdapterListener adapterListener) {
//        String refreshToken = getRefreshTokenFromPref();
//        TokenRegisrationPO tokenRegisrationPO = new TokenRegisrationPO();
//        tokenRegisrationPO.setGrant_type(ConstantValues.GRANT_TYPE_REFRESH_TOKEN);
//        tokenRegisrationPO.setRefresh_token(refreshToken);
//        AdapterHelper adapterTask = new AdapterHelper(AdapterProcedure.TOKEN_REGISRATION,
//                tokenRegisrationPO,
//                adapterListener);
//        adapterTask.performTask();
//    }

    /**
     * Show Unsecure screen if User is in Secure zone for more than the idle
     * timeout time
     */
//    public void checkAndUpdateSecureScreen() {
//        if (secureActivity != null && !secureActivity.isFinishing()
//                && mAct != null
//                && !mAct.isFinishing()) {
//            secureActivity.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (secureActivity != null && !secureActivity.isFinishing()) {
//                        if (secureActivity.isSecurePageShowing()) {
//                            secureActivity.finish();
//                            UtilityMethods.openShoppingBagActivity(mAct);
//                        }
//                    }
//                }
//            });
//        }
//
//    }

    /**
     * Update secure time of the user's session.
     */
    public void updateSecureTime() {
        if (isUserSignedIn()) {
            startSigninTimer();
            BuzzApplication.getInstance().getAppPref().updateAppSecureTime();
        }
    }

    public void startSigninTimer() {
        // If secure timer is null or secure timer is not running then
        // launch new timer.
        if (mSecureTimer == null || !mSecureTimer.isTimerRunning()) {
            mSecureTimer = new SecureTimer(CommonValues.getInstance().getSignInTimeOut());
            mSecureTimer.startTimer();
        }
    }

//    public void startIdleTimer(WLActivity secureActivity) {
//        this.secureActivity = secureActivity;
//        // If idle timer is null or idle timer is not running then
//        // launch new timer.
//        if (mIdleTimer == null || !mIdleTimer.isIdleTimerRunning()) {
//            mIdleTimer = new IdleTimer(CommonValues.getInstance().getIdleTimeOut());
//            mIdleTimer.startIdleTimer();
//        }
//    }

    // Reset the Idle Timeout value in Preferences
//    public void resetIdleTimer() {
//        BuzzApplication.getInstance().getAppPref().setIdleTime();
//    }

    /**
     * Returns secure time of the user's session.
     */
    public long getSecureTime() {
        return BuzzApplication.getInstance().getAppPref().getSecureTime();
    }

    private static class SecureTimer extends Thread {

        // Idle timeout of the user's session in seconds.
        private long mSecureTimeOut;

        // Indicates timer is running
        private boolean mIsRunning = true;

        public SecureTimer(long mSecureTimeOut) {
            this.mSecureTimeOut = mSecureTimeOut;
        }

        @Override
        public void run() {
            while (mIsRunning) {
                long currentTime = (System.currentTimeMillis() / 1000);
                long secureTime = BuzzApplication.getInstance()
                        .getAuthenticationUtils()
                        .getSecureTime();
                if (currentTime - secureTime > mSecureTimeOut) {
                    // Sarnab TODO - commenting out - since sign out imer is in
                    // Sprint 4
                    // BuzzApplication.getInstance().getAuthenticationUtils().clearUserSession();
                    break;
                } else {
                    try {
                        sleep(mSecureTimeOut * 1000);
                    } catch (InterruptedException e) {
                        // Do nothing & let loop to continue.
                    }
                }
            }
            mIsRunning = false;
        }

        /**
         * Stops the timer.
         */
        public void stopTimer() {
            mIsRunning = false;
        }

        /**
         * Starts the timer.
         */
        public void startTimer() {
            mIsRunning = true;
            start();
        }

        /**
         * Returns true if timer is running else returns false.
         * 
         * @return
         */
        public boolean isTimerRunning() {
            return mIsRunning;
        }
    }

}
