
package islamic.buzz.helpers;

import islamic.buzz.util.ConstantValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * This class will manage all interactions with Shared Preferences.
 */
public class PreferenceHelper {
    private final Context appContext;

    private Gson gson;

    private boolean acceptLic;

    public PreferenceHelper(Context context) {
        this.appContext = context;
    }

    private SharedPreferences getDefaultSharePreference() {
        return appContext.getSharedPreferences(ConstantValues.APP_PREFERENCE, Context.MODE_PRIVATE);
    }

    private Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    /**
     * This location is being saved in order to direct user to the last know
     * location in case Location settings is disabled
     * 
     * @param mLastLocation
     */
    public void saveLastUserLocation(Location mLastLocation) {
        Editor editor = getDefaultSharePreference().edit();
        String json = getGson().toJson(mLastLocation);
        editor.putString(ConstantValues.USER_LAST_LOCATION, json);
        editor.commit();
    }

    public Location getLastUserLocation() {
        String json = getDefaultSharePreference().getString(ConstantValues.USER_LAST_LOCATION, "");
        if (json != null && json.length() > 0) {
            Location mLastLocation = getGson().fromJson(json, Location.class);
            return mLastLocation;
        }
        return null;
    }

    /**
     * Save user credentials & user info in the preferences.
     * 
     * @param accessToken Access token of user.
     */
    public void saveAppCofiguration(String configString) {
        if (!TextUtils.isEmpty(configString)) {
            Editor editor = getDefaultSharePreference().edit();
            editor.putString(ConstantValues.APP_CONFIG, configString);
            editor.commit();
        }
    }

    public String getAppConfiguration() {
        return getDefaultSharePreference().getString(ConstantValues.APP_CONFIG, null);
    }

  
    
    // Twiiter Preferences

    public boolean getTwitterAuthorizationStatus() {
        return getDefaultSharePreference().getBoolean(ConstantValues.TWITTER_IS_USER_AUTHORIZED,
                false);
    }

    public void setTwitterAutorizationStatus(boolean isAuthorized) {
        Editor editor = getDefaultSharePreference().edit();
        editor.putBoolean(ConstantValues.TWITTER_IS_USER_AUTHORIZED, isAuthorized);
        editor.commit();
    }

    public void saveTwitterOauth(String tokenKey,
            String tokenSecret) {
        Editor editor = getDefaultSharePreference().edit();
        editor.putString(ConstantValues.TWITTER_OAUTH_KEY_TOKEN, tokenKey);
        editor.putString(ConstantValues.TWITTER_OAUTH_KEY_SECRET, tokenSecret);
        editor.commit();
    }

    public String getTwitterOauthTokenSecret() {
        return getDefaultSharePreference().getString(ConstantValues.TWITTER_OAUTH_KEY_SECRET, null);
    }

    public String getTwitterOauthTokenKey() {
        return getDefaultSharePreference().getString(ConstantValues.TWITTER_OAUTH_KEY_TOKEN, null);
    }

    /**
     * Save user credentials & user info in the preferences.
     * 
     * @param accessToken Access token of user.
     * @param refreshToken Refresh token after signing in.
     * @param firstName Users name.
     * @param expireTime Expire time of token in seconds.
     * @param emailAddress Email address of user.
     * @param lastName TODO
     * @param loyaltyID TODO
     */
    public void saveUserCredentialsAndInfo(String accessToken,
            String refreshToken,
            String firstName,
            long expireTime,
            String emailAddress,
            String userID,
            String digestedMessage,
            String expiryStringTime,
            String lastName,
            String loyaltyID) {
        long timeInSeconds = (System.currentTimeMillis() / 1000);
        Editor editor = getDefaultSharePreference().edit();
        editor.putString(ConstantValues.PREF_USERNAME_ID, userID);
        editor.commit();
    }

    public void saveUserName(String userName) {
        Editor editor = getDefaultSharePreference().edit();
        editor.putString(ConstantValues.PREF_USERNAME_KEY, userName);
        editor.commit();
    }

    public void saveUserLoyaltyID(String loyaltyID) {
        Editor editor = getDefaultSharePreference().edit();
        editor.putString(ConstantValues.PREF_USER_LOYALTY_ID, loyaltyID);
        editor.commit();
    }

    public String getUserLoyaltyID() {
        return getDefaultSharePreference().getString(ConstantValues.PREF_USER_LOYALTY_ID, null);
    }

    /**
     * Update user credentials tokens like refresh & accessTokens
     * 
     * @param accessToken Access token of the user.
     * @param refreshToken Refresh token for refreshing access token.
     */
    public void updateUserCredentialsTokens(String accessToken,
            String refreshToken) {
        Editor editor = getDefaultSharePreference().edit();
        editor.putString(ConstantValues.PREF_ACCESS_TOKEN_KEY, accessToken);
        editor.putString(ConstantValues.PREF_REFRESH_TOKEN_KEY, refreshToken);
        editor.commit();
    }

    /**
     * Update user's secure time with current time of the device.
     */
    public void updateAppSecureTime() {
        Editor editor = getDefaultSharePreference().edit();
        long timeInSeconds = (System.currentTimeMillis() / 1000);
        editor.putLong(ConstantValues.PREF_SECURE_TIME_KEY, timeInSeconds);
        editor.commit();
    }

    /**
     * Returns access token expire time if available else returns 0.
     */
    public long getAccessTokenExpireTime() {
        return getDefaultSharePreference().getLong(ConstantValues.PREF_EXPIRE_TIME_KEY, 0);
    }

    /**
     * Returns sign in time if available else returns 0.
     */
    public long getSignInTime() {
        return getDefaultSharePreference().getLong(ConstantValues.PREF_SIGN_IN_TIME_KEY, 0);
    }

    /**
     * Returns secure time of the user's session.
     */
    public long getSecureTime() {
        return getDefaultSharePreference().getLong(ConstantValues.PREF_SECURE_TIME_KEY, 0);
    }

    public long getIdleTimeStart() {
        return getDefaultSharePreference().getLong(ConstantValues.PREF_IDLE_TIME_KEY, 0);
    }

    /**
     * Returns sign in username.
     */

    public String getSignedInUserName() {
        return getDefaultSharePreference().getString(ConstantValues.PREF_USERNAME_KEY, null);
    }

    public String getSignedinUserLastName() {
        return getDefaultSharePreference().getString(ConstantValues.PREF_USERLASTNAME_KEY, null);
    }

    /**
     * Returns sign in username.
     */
    public String getSignedInUserId() {
        return getDefaultSharePreference().getString(ConstantValues.PREF_USERNAME_ID, null);
    }

   
    /**
     * If false means user is currently not signed in else if true means user
     * sigend in
     */
    public void setUserSignInStatus(boolean userSignedInStatus) {
        Editor editor = getDefaultSharePreference().edit();
        editor.putBoolean(ConstantValues.PREF_USER_SIGNED_IN_STATUS, userSignedInStatus);
        editor.commit();
    }

    /**
     * If false means user is currently not signed in else if true means user
     * sigend in
     */
    public boolean getUserSignInStatus() {
        boolean signedInStatus = false;
        signedInStatus = getDefaultSharePreference().getBoolean(ConstantValues.PREF_USER_SIGNED_IN_STATUS,
                false);
        return signedInStatus;
    }

    /**
     * Returns Access token.
     */
    public String getAccessToken() {
        return getDefaultSharePreference().getString(ConstantValues.PREF_ACCESS_TOKEN_KEY, null);
    }

    /**
     * Returns Refresh token.
     */
    public String getRefreshToken() {
        return getDefaultSharePreference().getString(ConstantValues.PREF_REFRESH_TOKEN_KEY, null);
    }

    /**
     * Returns User Mail ID.
     */
    public String getUserMailId() {
        return getDefaultSharePreference().getString(ConstantValues.PREF_EMAIL_KEY, null);
    }

    /**
     * Returns User Mail ID.
     */
    public void setUserMailId(String mailId) {
        Editor editor = getDefaultSharePreference().edit();
        editor.putString(ConstantValues.PREF_EMAIL_KEY, mailId);
        editor.commit();
    }

    /**
     * Clear user credentials & information from shared preferences.
     */
    public void clearUserCredentialAndInfo(boolean clearAll) {
        Editor editor = getDefaultSharePreference().edit();
        editor.remove(ConstantValues.PREF_ACCESS_TOKEN_KEY);
        editor.remove(ConstantValues.PREF_REFRESH_TOKEN_KEY);
        if (clearAll) {
            editor.remove(ConstantValues.PREF_USERNAME_KEY);
            editor.remove(ConstantValues.PREF_EMAIL_KEY);
        }
        editor.remove(ConstantValues.PREF_SIGN_IN_TIME_KEY);
        editor.remove(ConstantValues.PREF_EXPIRE_TIME_KEY);
        editor.remove(ConstantValues.PREF_SECURE_TIME_KEY);
        editor.remove(ConstantValues.PREF_USER_LOYALTY_ID);
        editor.commit();
    }

   
}
