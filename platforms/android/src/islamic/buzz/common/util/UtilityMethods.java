
package islamic.buzz.common.util;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kohls.analytics.objects.datatypes.PageNames;
import com.kohls.analytics.objects.models.AnalyticsObject;
import com.kohls.analytics.utils.AnalyticsConstants;
import com.kohls.analytics.utils.ScreenTracker;
import com.kohlsphone.R;
import com.kohlsphone.common.app.KohlsPhoneApplication;
import com.kohlsphone.common.po.RecommendationsPO;
import com.kohlsphone.common.po.StoreLocatorPO;
import com.kohlsphone.common.value.ConstantValues;
import com.kohlsphone.framework.controller.ControllerFactory;
import com.kohlsphone.framework.view.activity.AccountActivity;
import com.kohlsphone.framework.view.activity.HomeActivity;
import com.kohlsphone.framework.view.activity.ProductDetailsActivity;
import com.kohlsphone.framework.view.activity.RatingAndReviewActivity;
import com.kohlsphone.framework.view.activity.ScanActivity;
import com.kohlsphone.framework.view.activity.ScanHelpActivity;
import com.kohlsphone.framework.view.activity.StoreDetailActivity;
import com.kohlsphone.framework.view.activity.WebViewActivity;
import com.kohlsphone.framework.view.fragment.BaseDialogFragment;
import com.kohlsphone.framework.vo.GeoCoderVO;
import com.kohlsphone.framework.vo.GeoCoderVO.GeoCodeResult;
import com.kohlsphone.framework.vo.GeoCoderVO.GeoCodeResult.AddressComponent;
import com.kohlsphone.framework.vo.StoreLocatorVO.Payload.Store;
import com.kohlsphone.helper.adapter.AdapterHelper;
import com.kohlsphone.helper.adapter.AdapterProcedure;
import com.kohlsphone.helper.adapter.RestClient;
import com.kohlsphone.helper.adapter.listener.IAdapterListener;
import com.kohlsphone.helper.cache.inmemory.CacheFlushHelper;
import com.kohlsphone.helper.db.DBTablesDef;
import com.kohlsphone.helper.db.custom.DBShoppingBagHelper;
import com.kohlsphone.helper.error.AppException;
import com.kohlsphone.wl.HybridScreen;
import com.kohlsphone.wl.WLActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * All the utility functions are defined in this class
 * 
 * @author Perminder.Singh
 */
public final class UtilityMethods {
    public static final String EMAIL_MIME_TYPE = "text/plain";

    private static final String ELLIPSES = "..";

    /********************************
     * // Sort Ids.
     *********************************/
    public static final int ID_SORT_AVAILABILITY = 1000;

    public static final int ID_SORT_DISTANCE = ID_SORT_AVAILABILITY + 1;

    public static final int ID_SORT_STORENAME = ID_SORT_DISTANCE + 1;

    /***************************************
     * // Inventory Strings mapped to the data returned from Open API
     ***************************************/

    public static final String IN_STOCK = "In";

    public static final String LOW_STOCK = "Low";

    public static final String OUT_STOCK = "Out";

    /**
     * Defines a Google servie error dialog fragment that displays the google
     * service error dialog
     * 
     * @author Ankit Grover
     */

    public static class GoogleServiceErrorDialogFragment extends DialogFragment {

        // Global field to contain the error dialog
        private Dialog mDialog;

        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }

        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(ConstantValues.GOOGLE_SERVICE_DIALOG_ERROR);
            int requestResolveError = this.getArguments()
                    .getInt(ConstantValues.RESOLUTION_REQUEST_ERROR_KEY);
            Dialog dialogError = GooglePlayServicesUtil.getErrorDialog(errorCode,
                    this.getActivity(),
                    requestResolveError);
            dialogError.setCanceledOnTouchOutside(true);
            return dialogError;

        }
    }

    @SuppressWarnings("unchecked")
    public static final Object getModelFromJsonString(String response,
            @SuppressWarnings("rawtypes") Class modelClass) {
        Gson gson = new GsonBuilder().create();

        return gson.fromJson(response, modelClass);
    }

    /**
     * @param Object
     * @return Json String
     */
    public static final String createJsonFromModel(Object response) {
        Gson gson = new GsonBuilder().create();
        String gsonString = gson.toJson(response);
        return gsonString;
    }

    public static final Builder getAlertDialog(Context ctx,
            String message,
            String positiveMessage,
            String negativeMessage,
            OnClickListener listener) {
        Builder dialog = new AlertDialog.Builder(ctx);
        if (message != null) {
            dialog.setMessage(message);
        }
        if (positiveMessage != null && listener != null) {
            dialog.setPositiveButton(positiveMessage, listener);
        }
        if (negativeMessage != null && listener != null) {
            dialog.setNegativeButton(negativeMessage, listener);
        }
        return dialog;
    }

    /**
     * Helper Function to Load json From Assets Folder
     */
    public static String loadJSONFromAsset(Context c,
            String fileName) {
        String json = null;
        try {
            InputStream is = c.getAssets().open(fileName + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static String getShortDayName(String dayName) {
        if (dayName.equalsIgnoreCase(ConstantValues.DAY_MONDAY)) {
            return "Mon.";
        }
        if (dayName.equalsIgnoreCase(ConstantValues.DAY_TUESDAY)) {
            return "Tue.";
        }
        if (dayName.equalsIgnoreCase(ConstantValues.DAY_WEDNESDAY)) {
            return "Wed.";
        }
        if (dayName.equalsIgnoreCase(ConstantValues.DAY_THURSDAY)) {
            return "Thu.";
        }
        if (dayName.equalsIgnoreCase(ConstantValues.DAY_FRIDAY)) {
            return "Fri.";
        }
        if (dayName.equalsIgnoreCase(ConstantValues.DAY_SATURDAY)) {
            return "Sat.";
        }
        if (dayName.equalsIgnoreCase(ConstantValues.DAY_SUNDAY)) {
            return "Sun.";
        }
        return null;
    }

    public static int getPixelsFromDp(Context context,
            float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * get String from an Google Address
     * 
     * @param shortAddress - if the string that is required is a Short String
     * @param onlyCityState - If the string wanted is only city and state
     * @param Android Location address
     * @return
     */
    public static StringBuilder getAddressLine(Address address,
            boolean shortAddress,
            boolean onlyCityState) {

        if (address == null) {
            return null;
        }

        StringBuilder addressString = new StringBuilder("");

        String streetName = address.getAddressLine(0);

        if (streetName != null && !onlyCityState) {
            addressString.append(streetName);
        }

        String cityName = address.getLocality();

        if (cityName != null) {
            if (streetName != null) {
                addressString.append(",");
                streetName = null;
            }
            addressString.append(cityName);
        }

        if (shortAddress && addressString.length() > 0) {
            return addressString;
        }

        String stateName = address.getAdminArea();

        if (stateName != null) {
            if (cityName != null) {
                addressString.append(',');
                cityName = null;
            }
            addressString.append(stateName);
        }

        String countryName = address.getCountryName();

        if (countryName != null) {
            if (stateName != null) {
                addressString.append(',');
                stateName = null;
            }
            addressString.append(countryName);
        }

        return addressString;
    }

    /**
     * get String from an STORE VO Address
     * 
     * @param shortAddress if the string that is required is a Short String
     * @param onlyCityState TODO
     * @param StoreVO address
     * @return
     */
    public static StringBuilder
            getAddressLine(com.kohlsphone.framework.vo.StoreLocatorVO.Payload.Store.Address address,
                    boolean shortAddress,
                    boolean onlyCityState) {

        if (address == null) {
            return null;
        }

        StringBuilder addressString = new StringBuilder("");

        String streetName = address.getAddr1();

        if (streetName != null && !onlyCityState) {
            addressString.append(streetName);
        }

        if (shortAddress && addressString.length() > 0) {
            return addressString;
        }

        String cityName = address.getCity();

        if (cityName != null) {
            if (streetName != null) {
                addressString.append(',');
                streetName = null;
            }
            addressString.append(cityName);
        }

        if (shortAddress && addressString.length() > 0) {
            return addressString;
        }

        String stateName = address.getState();

        if (stateName != null) {
            if (cityName != null) {
                addressString.append(',');
                cityName = null;
            }
            addressString.append(stateName);
        }

        return addressString;
    }

    /**
     * Display the Alert Dialog
     * 
     * @param dialogID
     * @param resultReceiver
     * @param title
     * @param message
     * @param drawableId
     * @param positiveBtnText
     * @param negativeBtnText
     * @param neutralBtnText
     * @param isCustomView
     * @param isCancellable
     */
    public static void showAlertDialog(int dialogID,
            ResultReceiver resultReceiver,
            String title,
            String message,
            int drawableId,
            String positiveBtnText,
            String negativeBtnText,
            String neutralBtnText,
            boolean isCustomView,
            boolean isCancellable,
            Activity activity) {
        if (activity != null) {
            DialogFragment dialogFragment = new BaseDialogFragment();

            Bundle args = new Bundle();
            args.putInt(ConstantValues.DIALOG_ID, dialogID);

            args.putString(ConstantValues.DIALOG_TITLE, title);

            args.putString(ConstantValues.DIALOG_MESSAGE, message);

            args.putInt(ConstantValues.DIALOG_DRAWABLE_ID, drawableId);

            if (!TextUtils.isEmpty(positiveBtnText)) {
                args.putString(ConstantValues.DIALOG_POSITIVE_BUTTON_TEXT, positiveBtnText);
            }
            if (!TextUtils.isEmpty(negativeBtnText)) {
                args.putString(ConstantValues.DIALOG_NEGATIVE_BUTTON_TEXT, negativeBtnText);
            }
            if (!TextUtils.isEmpty(neutralBtnText)) {
                args.putString(ConstantValues.DIALOG_NEUTRAL_BUTTON_TEXT, neutralBtnText);
            }

            args.putBoolean(ConstantValues.IS_DIALOG_CANCELLABLE, isCancellable);
            args.putBoolean(ConstantValues.IS_CUSTOM_DIALOG_VIEW, isCustomView);

            if (null != resultReceiver) {
                args.putParcelable(ConstantValues.RESULTANT_RECEIVER, resultReceiver);
            }

            dialogFragment.setArguments(args);
            if (!activity.isFinishing()) {
                dialogFragment.show(activity.getFragmentManager(), "alert");
            }

        }

    }

    /**
     * Display the common progress dialog which is attached to the view, this
     * will not dismissed or leaked the window on the device configuration
     * change, the context should be set on the All the parameter of the dialog
     * are optional except dialogID.
     * 
     * @param dialogID id of the dialog to be displayed
     * @param message message to be display on the alert dialog
     * @param resultReceiver callback receiver when any cancel action is
     *            performed in the dialog button
     * @param isCancellable set whether the dialog is cancellable or not
     * @param isIndeterminate set whether the dialog is Indeterminate or not
     * @param isCanceledOnTouchOutside set whether the dialog is dismissed to
     *            touch outside the dialog window or not
     * @param subDialogID if the caller is having multiple progress dialog in
     *            chain and cancel listener with having different operation then
     *            user should pass the unique sub dialog id and handle the
     *            result in the ResultReceiver
     */
    public static void showKohlsProgressDialog(int dialogID,
            String title,
            String message,
            ResultReceiver resultReceiver,
            boolean isCancellable,
            boolean isIndeterminate,
            boolean isCanceledOnTouchOutside,
            int subDialogID,
            Activity activity) {

        DialogFragment dialogFragment = new BaseDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ConstantValues.DIALOG_ID, dialogID);
        args.putInt(ConstantValues.SUB_DIALOG_ID, subDialogID);
        args.putString(ConstantValues.DIALOG_MESSAGE, message);
        args.putString(ConstantValues.DIALOG_TITLE, title);
        args.putBoolean(ConstantValues.IS_DIALOG_CANCELLABLE, isCancellable);
        args.putBoolean(ConstantValues.IS_DIALOG_INDETERMINATE, isIndeterminate);
        args.putBoolean(ConstantValues.IS_DIALOG_CANCEL_ON_OUTSIDE_TOUCH, isCanceledOnTouchOutside);

        if (null != resultReceiver) {
            args.putParcelable(ConstantValues.RESULTANT_RECEIVER, resultReceiver);
        }

        dialogFragment.setArguments(args);
        if (activity != null) {
            dialogFragment.show(activity.getFragmentManager(), "progress");
        }
    }

    /**
     * Display the Image Dialog. This Dialog is used to display the Product
     * Images with Zoom In/ Zoom Out functionality. This will not dismissed or
     * leaked the window on the device configuration change, the context should
     * be set on the All the parameter of the dialog are optional except
     * dialogID.
     * 
     * @param dialogID id of the dialog to be displayed
     * @param message message to be display on the alert dialog
     * @param resultReceiver callback receiver when any cancel action is
     *            performed in the dialog button
     * @param isCancellable set whether the dialog is cancellable or not
     * @param isIndeterminate set whether the dialog is Indeterminate or not
     * @param isCanceledOnTouchOutside set whether the dialog is dismissed to
     *            touch outside the dialog window or not
     */
    public static void showImageDisplayDialog(int dialogID,
            String title,
            String message,
            ResultReceiver resultReceiver,
            boolean isCancellable,
            boolean isIndeterminate,
            boolean isCanceledOnTouchOutside,
            int subDialogID,
            Activity activity) {

        DialogFragment dialogFragment = new BaseDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ConstantValues.DIALOG_ID, dialogID);
        args.putInt(ConstantValues.SUB_DIALOG_ID, subDialogID);
        args.putString(ConstantValues.DIALOG_MESSAGE, message);
        args.putString(ConstantValues.DIALOG_TITLE, title);
        args.putBoolean(ConstantValues.IS_DIALOG_CANCELLABLE, isCancellable);
        args.putBoolean(ConstantValues.IS_DIALOG_INDETERMINATE, isIndeterminate);
        args.putBoolean(ConstantValues.IS_DIALOG_CANCEL_ON_OUTSIDE_TOUCH, isCanceledOnTouchOutside);

        if (null != resultReceiver) {
            args.putParcelable(ConstantValues.RESULTANT_RECEIVER, resultReceiver);
        }

        dialogFragment.setArguments(args);
        if (activity != null) {
            dialogFragment.show(activity.getFragmentManager(), "imageDialog");
        }
    }

    /**
     * Show google service error dialog.
     * 
     * @param errorCode error code that is returned from google service
     * @param resolutionRequestError request code to use when launching the
     *            resolution activity.
     * @param activity activity on which dialog will be shown.
     */
    public static void showGoogleServiceErrorDialog(int errorCode,
            int resolutionRequestError,
            Activity activity) {
        // Create a fragment for the error dialog
        GoogleServiceErrorDialogFragment dialogFragment = new GoogleServiceErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(ConstantValues.GOOGLE_SERVICE_DIALOG_ERROR, errorCode);
        args.putInt(ConstantValues.RESOLUTION_REQUEST_ERROR_KEY, resolutionRequestError);
        dialogFragment.setArguments(args);
        dialogFragment.show(activity.getFragmentManager(), "Location updates");
    }

    /**
     * Dismiss the Dialog Based on ID
     * 
     * @param dialogID
     */
    public static void dismissDialog(int dialogID) {
        BaseDialogFragment.dismissDialog(dialogID);
    }

    /**
     * Check if the Dialog is visible
     * 
     * @param dialogID
     */
    public static boolean updateProgressTest(int dialogID,
            String statusText) {
        if (BaseDialogFragment.isDialogVisible(dialogID)) {
            BaseDialogFragment.getProgressDialog().setMessage(statusText);
            return true;
        }
        return false;
    }

    /**
     * Check if the Dialog is visible
     * 
     * @param dialogID
     */
    public static boolean isDialogVisible(int dialogID) {
        return BaseDialogFragment.isDialogVisible(dialogID);
    }

    /**
     * Show toast for a given message.
     * 
     * @param context context of the activity.
     * @param message message to be displayed.
     * @param duration duration of the toast.
     */
    public static void showToast(Context context,
            String message,
            int duration) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(context, message, duration).show();
        }
    }

    /**
     * Hide Keyboard on View Called From
     * 
     * @param mContext
     * @param view View on which to hide Soft Keyboard
     */
    public static void hideKeyboard(Context mContext,
            View view) {
        if (mContext != null && view != null) {
            final InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    /**
     * Filter out all those locations which has does not have a city name
     * associated with it. Also filters out duplicate entries.
     * 
     * @param mGeoAddress
     * @param mSearchText
     * @return
     */
    public static List<Address> getFilteredAddresses(List<Address> mGeoAddress,
            String mSearchText) {
        List<Address> mFilteredAddresses = new ArrayList<Address>();

        String mLastFeatureName = "";

        for (Address mAddress : mGeoAddress) {
            if (addressHasCityandStateInfo(mAddress, mLastFeatureName, mSearchText)) {
                mFilteredAddresses.add(mAddress);
                mLastFeatureName = mAddress.getLocality();
            }
        }

        return mFilteredAddresses;
    }

    /**
     * * Check whether the address has city/state name and whether this
     * city/state Name is not a duplicate then we show the location in the
     * suggestion dropdown. If the text is only Numeric ie zipCode, then the
     * returned list of addresses should have zipCode which matches the data
     * entered.
     * 
     * @param mAddress
     * @param mLastFeatureName
     * @param mSearchText
     * @return
     */
    private static boolean addressHasCityandStateInfo(Address mAddress,
            String mLastFeatureName,
            String mSearchText) {
        if (mAddress == null || mSearchText == null) {
            return false;
        }
        if (mAddress.getFeatureName() != null) {
            if (mLastFeatureName != null && mAddress.getFeatureName()
                    .equalsIgnoreCase(mLastFeatureName)) {
                return false;
            }
            if (mSearchText != null && isTextOnlyNumeric(mSearchText)) {
                if (mAddress.getPostalCode() == null) {
                    return false;
                } else {
                    if (mAddress.getPostalCode().contains(mSearchText)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            if (mSearchText != null && mAddress != null) {
                if (mAddress.getLocality() != null || mAddress.getAdminArea() != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isTextOnlyNumeric(String mText) {
        String mZipRegex = "^\\d{5}(-\\d{4})?$";
        if (mText.length() == 0) {
            return false;
        }
        if (Pattern.matches(mZipRegex, mText)) {
            return true;
        }

        return false;
    }

    private static boolean isTelephonyEnabled(Context mContext) {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null && tm.getSimState() == TelephonyManager.SIM_STATE_READY;
    }

    /**
     * A phone call utility mehtod, which will be made when Telephony service
     * will be avaialble on the device
     * 
     * @param value
     */
    public static void makeCall(Context mContext,
            String mPhoneNumber) {
        if (isTelephonyEnabled(mContext)) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mPhoneNumber));
            mContext.startActivity(intent);
        }
    }

    /**
     * This is a utility method to take in string input ex 5671232345 and
     * convert it into +1(567)123-2345
     * 
     * @param mPhoneNumber
     * @return
     */
    public static String formatPhoneNumber(String mPhoneNumber) {
        if (mPhoneNumber == null) {
            return null;
        }

        StringBuilder mFormattedNumber = new StringBuilder(mPhoneNumber);

        if (mFormattedNumber.length() < 10) {
            return mPhoneNumber;
        }

        mFormattedNumber.insert(0, '(');
        mFormattedNumber.insert(4, ')');
        mFormattedNumber.insert(8, '-');

        return mFormattedNumber.toString();
    }

    public static <C> ArrayList<C> asList(SparseArray<C> sparseArray) {
        if (sparseArray == null) {
            return null;
        }
        ArrayList<C> arrayList = new ArrayList<C>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add(sparseArray.valueAt(i));
        }
        return arrayList;
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device
     * density.
     * 
     * @param dp A value in dp (density independent pixels) unit. Which we need
     *            to convert into pixels
     * @param context Context to get resources and device specific display
     *            metrics
     * @return A float value to represent px equivalent to dp depending on
     *         device density
     */

    public static float convertDpToPixel(float dp,
            Context context) {
        // Resources resources = context.getResources();
        // DisplayMetrics metrics = resources.getDisplayMetrics();
        // float px = dp * (metrics.densityDpi / 160f);
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics());
        return px;
    }

    /**
     * This method converts device specific pixels to density independent
     * pixels.
     * 
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display
     *            metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px,
            Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    public static void recordHprof(int index) {
        try {
            String file = null;
            if (index < 0) {
                file = "/sdcard/kohls_prof.hprof";
            } else {
                file = "/sdcard/kohls_prof_" + index + ".hprof";
            }
            Debug.dumpHprofData(file);
        } catch (IOException e) {
        }
    }

    /**
     * This method gets all the results from the search of Google MAP API and
     * filters any data(No POI data should not be showed , only data which were
     * entered into the textbox and that matches zipcode/city returned will be
     * used)
     * 
     * @param mGeoCoderModel
     * @param searchString
     * @return
     */

    public static List<Address> getAddressFromGeoCode(GeoCoderVO mGeoCoderModel) {

        List<Address> mAddressList = new ArrayList<Address>();
        boolean isPOI = false;
        if (mGeoCoderModel != null) {
            for (GeoCodeResult mGeoCodeResult : mGeoCoderModel.getResults()) {
                isPOI = false;
                if (mGeoCodeResult.getAddress_components() != null && mGeoCodeResult.getAddress_components()
                        .size() > 0) {
                    Address mAddress = new Address(Locale.US);
                    for (AddressComponent mAddressComponent : mGeoCodeResult.getAddress_components()) {
                        if (mAddressComponent.getTypes() != null && mAddressComponent.getTypes()
                                .size() > 0) {
                            // If this is a POI, do not show it in the results.
                            if (mAddressComponent.getTypes()
                                    .get(0)
                                    .equalsIgnoreCase(ConstantValues.GEOCODE_POI) || mAddressComponent.getTypes()
                                    .get(0)
                                    .equalsIgnoreCase(ConstantValues.GEOCODE_ESTABLISHMENT)
                                    || mAddressComponent.getTypes()
                                            .get(0)
                                            .equalsIgnoreCase(ConstantValues.GEOCODE_ROUTE)) {
                                isPOI = true;
                            }
                            if (mAddressComponent.getTypes()
                                    .get(0)
                                    .equalsIgnoreCase(ConstantValues.GEOCODE_CITYNAME)) {
                                mAddress.setLocality(mAddressComponent.getLong_name());
                            }
                            if (mAddressComponent.getTypes()
                                    .get(0)
                                    .equalsIgnoreCase(ConstantValues.GEOCODE_STATENAME)) {
                                mAddress.setAdminArea(mAddressComponent.getLong_name());
                            }
                            if (mAddressComponent.getTypes()
                                    .get(0)
                                    .equalsIgnoreCase(ConstantValues.GEOCODE_COUNTRY)) {
                                if (mAddressComponent.getLong_name() != null && mAddressComponent.getLong_name()
                                        .equalsIgnoreCase(ConstantValues.US_CODE)) {
                                    mAddress.setCountryName(mAddressComponent.getLong_name());
                                } else {
                                    isPOI = true;
                                }
                            }

                            if (mAddressComponent.getTypes()
                                    .get(0)
                                    .equalsIgnoreCase(ConstantValues.GEOCODE_PINCODE)) {
                                mAddress.setPostalCode(mAddressComponent.getLong_name());
                            }
                        }
                    }

                    if (mGeoCodeResult.getGeometry() != null && mGeoCodeResult.getGeometry()
                            .getLocation() != null) {
                        mAddress.setLatitude(mGeoCodeResult.getGeometry().getLocation().getLat());
                        mAddress.setLongitude(mGeoCodeResult.getGeometry().getLocation().getLon());
                    }
                    mAddress.setFeatureName(mGeoCodeResult.getFormatted_address());
                    if (!isPOI) {
                        mAddressList.add(mAddress);
                    }
                }
            }
        }
        return mAddressList;
    }

    /**
     * Function to get values of variables defined in the POJO
     * 
     * @param object
     * @param objectClass
     * @return
     */
    public static final Map<String, String> getMapFromObject(Object object,
            Class objectClass) {

        if (object == null) {
            return null;
        }

        Map<String, String> map = new HashMap<String, String>(6);

        if (object != null) {
            Method[] methods = objectClass.getMethods();

            for (Method m : methods) {
                Class<?> returnType = m.getReturnType();
                if (m.getName().startsWith("get") && !returnType.equals(Class.class)) {
                    m.setAccessible(true);

                    String key = m.getName().substring(3, 4).toLowerCase() + m.getName()
                            .substring(4);
                    Object retVal = null;
                    try {
                        retVal = m.invoke(object, (Object[]) null);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    if (null != retVal) {
                        map.put(key, String.valueOf(retVal));
                    }
                }
            }
        }

        return map;
    }

    /**
     * Common method to perform regex check
     * 
     * @param s
     * @param pattern
     * @return
     */
    public static boolean IsMatch(String s,
            String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
            Logger.debug("REGEX", "REGEX Error");
            return false;
        }
    }

    /**
     * This method creates an email intent
     * 
     * @param email Email to be included in the intent
     * @return Intent to be sent
     */
    public static Intent getEmailIntent(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType(EMAIL_MIME_TYPE);
        if (email != null) {
            intent.setData(Uri.parse("mailto:" + email));
        } else {
            intent.setData(Uri.parse("mailto:"));
        }
        return intent;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    /**
     * Validate email address in the pattern of xyz@domain.com
     * 
     * @param email email address of the user.
     * @return true email address is valid else false.
     */
    public static boolean isEmailValid(String email) {
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return UtilityMethods.IsMatch(email, emailPattern);
    }

    public static boolean isValidUserName(String name) {
        String namePattern = "^[\\p{L} .'-]+$";
        return UtilityMethods.IsMatch(name, namePattern);
    }

    public static String parseHmlToString(StringBuilder description) {
        return description.toString()
                .replace("\n", "")
                .replace("<ul>", "")
                .replace("</ul>", "")
                .replace("<li>", "<li> &#8226;  ")
                .replace("<li>", "<span>")
                .replace("</li>", "</span><br>")
                .replace("</strong>", "</strong><br>")
                .replace("<span> &#8226;  Details:", "<span> <p><strong> Details:</strong></p>");
    }

    /**
     * This is the utility method to truncate a String to a maximum length, and
     * add ... to it.
     * 
     * @param text
     * @param maxLengthofEditText
     * @return
     */
    public static CharSequence truncateMaxLength(CharSequence text,
            TextPaint mTextPaint,
            int maxLengthofEditText) {
        if (text == null) {
            return null;
        }
        if (text.length() <= maxLengthofEditText) {
            return text;
        }
        return text.subSequence(0, maxLengthofEditText - 4) + ELLIPSES;
    }

    public static int getScreenHeight(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    /**
     * Get Value Added Icon URL
     * 
     * @param valueAddedIcons
     * @return
     */
    public static String getValueAddedIconUrl(String valueAddedIcons) {

        if (valueAddedIcons.indexOf("Online") != -1) {
            return ConstantValues.VALUE_ADDED_ICON_ONLINE;
        } else if (valueAddedIcons.indexOf("morecolors") != -1) {
            return ConstantValues.VALUE_ADDED_ICON_MORE_COLORS;
        } else if (valueAddedIcons.indexOf("bogo_1_1_D_100") != -1) {
            return ConstantValues.VALUE_ADDED_ICON_BOGO_1_1_D_100;
        } else if (valueAddedIcons.indexOf("bogo_1_1_P_50") != -1) {
            return ConstantValues.VALUE_ADDED_ICON_BOGO_1_1_P_50;
        } else if (valueAddedIcons.indexOf("bogo_1_1") != -1) {
            return ConstantValues.VALUE_ADDED_ICON_BOGO_1_1;
        } else if (valueAddedIcons.indexOf("bogo_2_1") != -1) {
            return ConstantValues.VALUE_ADDED_ICON_BOGO_2_1;
        } else if (valueAddedIcons.indexOf("BUY_1_GET_0_50_PERCENTAGE") != -1 || valueAddedIcons.indexOf("BUY_1_GET_1_50_PERCENTAGE") != -1) {
            return ConstantValues.VALUE_ADDED_ICON_BUY_1_GET_0_50_PERCENTAGE;
        } else if (valueAddedIcons.indexOf("rebate") != -1) {
            return ConstantValues.VALUE_ADDED_ICON_REBATE;
        } else if (valueAddedIcons.indexOf("warning") != -1) {
            return ConstantValues.VALUE_ADDED_ICON_WARNING;
        }

        return null;
    }

    public static String removeSingleQuotes(String input) {
        String result = input;
        if (result != null) {
            result = input.replaceAll("'", "");
        }
        return result;
    }

    public static boolean hasSDCard(Context ctx) {
        if (ctx != null && ctx.getExternalCacheDir() != null) {
            return true;
        }
        return false;
    }

    public static File getFileData(Context ctx,
            int type) {
        switch (type) {
            case ConstantValues.CACHE_DIR:
                return ctx.getCacheDir();

            case ConstantValues.EXTERNAL_CACHE_DIR:
                return ctx.getExternalCacheDir();
        }

        return null;
    }

    /**
     * This is a utility method to return current GMT Time + any minutes passed
     * in the paramter
     * 
     * @param elapseTime - This is in minutes,If passed returns a future time
     *            (current time+ time in minutes)
     * @return
     */
    public static Date getGMTTime(long elapseTime) {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

        // Local time zone
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

        // Time in GMT
        try {
            if (elapseTime == 0) {
                Date currTime = dateFormatLocal.parse(dateFormatGmt.format(new Date()));
                return currTime;
            } else {
                long currentTime = dateFormatLocal.parse(dateFormatGmt.format(new Date()))
                        .getTime();
                Date afterMins = new Date(currentTime + (elapseTime * ConstantValues.ONE_MINUTE_IN_MILLIS));
                return afterMins;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Compare date/time with current time
     * 
     * @param getmExpiryTime
     * @return
     */
    public static boolean checkDataExpired(long getmExpiryTime) {
        Date mSavedTime = new Date(getmExpiryTime);
        Date currTime = new Date();
        if (mSavedTime.after(currTime)) {
            return false;
        }
        return true;
    }

    public static File createFile(String mFileName) {
        // Get the file object - 1) Check if there is SD card present, if
        // present - use it, or move to the application Internal Memory cache
        File mFile = new File(UtilityMethods.getFileData(KohlsPhoneApplication.getContext(),
                UtilityMethods.hasSDCard(KohlsPhoneApplication.getContext()) ? ConstantValues.EXTERNAL_CACHE_DIR
                        : ConstantValues.CACHE_DIR),
                mFileName);
        // IF file object does not exists, create it.
        if (!mFile.exists()) {
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return mFile;
    }

    public static HashMap<String, Long> getDirSize(File dir) {

        HashMap<String, Long> dirSize = new HashMap<String, Long>();
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().equalsIgnoreCase(ConstantValues.CATALOG_FILENAME)) {
                dirSize.put(file.getName(), file.length());
            }
        }

        return dirSize;
    }

    /**
     * Open shopping bag activity
     * 
     * @param activity
     */
    public static void openShoppingBagActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, WLActivity.class);
        intent.putExtra("screenToLoad", HybridScreen.SHOPPINGBAG);
        activity.startActivity(intent);
    }

    /**
     * Open WebView activity
     * 
     * @param activity
     */
    public static void openWebViewActivity(Activity activity,
            String url,
            String screenName) {
        Intent intent = new Intent();
        intent.setClass(activity, WebViewActivity.class);
        intent.putExtra(ConstantValues.EXTRA_KEY_URL, url);
        intent.putExtra(ConstantValues.EXTRA_KEY_SCREENNAME, screenName);

        activity.startActivity(intent);
    }

    public static String getUpdatedURL(String imageURL,
            int width,
            int height) {
        if (imageURL.indexOf("?") == -1) {
            return imageURL;
        } else {
            StringBuilder imageBuilder = new StringBuilder(imageURL.substring(0,
                    imageURL.indexOf("?")));
            imageBuilder.append("?wid=" + width);
            imageBuilder.append("&hei=" + height);
            imageBuilder.append("&op_sharpen=1");
            return imageBuilder.toString();
        }

    }

    /**
     * Remove focus from search box when any other part on screen is touched.
     * 
     * @param view Provide parent view of the layout
     */
    public static void clearEditTextViewFocusOnOutsideTouch(final View view,
            final Activity activity) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v,
                        MotionEvent event) {
                    UtilityMethods.hideKeyboard(activity, view);

                    if (activity.getCurrentFocus() != null) {
                        activity.getCurrentFocus().clearFocus();
                    }
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                clearEditTextViewFocusOnOutsideTouch(innerView, activity);
            }
        }
    }

    /**
     * This method is used to get Address values for Latitude/Longitude and
     * Zipcode e.g. For Zip Code: parameter will be <URL>?address=<Zipcode> For
     * Lat/Long: parameter will be <URL>?address=<LatLongString>
     * 
     * @param params
     * @return
     * @throws AppException
     */
    public static GeoCoderVO getGeocodeFromAddress(String params) throws AppException {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(ConstantValues.GEOCODE_ADDRESS_PARAMS, params);
        paramMap.put(ConstantValues.GEOCODE_CLIENTID, ConstantValues.GOOGLE_APIS_CLIENT_ID);

        String mResponse = RestClient.getHttpGetApiResponseAsJSON(ConstantValues.GEOCODE_URL,
                paramMap,
                false,
                true,
                "sensor=false",
                true);
        if (!TextUtils.isEmpty(mResponse)) {
            return (GeoCoderVO) UtilityMethods.getModelFromJsonString(mResponse, GeoCoderVO.class);
        } else {
            throw new AppException("No response from GeoCoder Api.");
        }
    }

    public static int getShopingBagItemsCount() {
        DBShoppingBagHelper db = new DBShoppingBagHelper();
        return db.getItemsCountFromShoppingBag();
    }

    public static int getDataItemsCountIfSkuAlreadyAdded(String skucode) {
        DBShoppingBagHelper db = new DBShoppingBagHelper();
        return db.isSkuCodeAvailable(skucode);
    }

    /**
     * Returns the Color spannable for a given string
     * 
     * @param string
     * @param colorId
     * @return
     */
    public static Spannable getColorSpanable(String string,
            int colorId) {
        Spannable spannable = new SpannableString(string);
        spannable.setSpan(new ForegroundColorSpan(colorId),
                0,
                spannable.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;

    }

    // Check if the Wifi of GPS enabled and location lat long can be accessed.
    public static boolean canGetLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // getting GPS status
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            return false;
        }

        return true;
    }

    /**
     * Function to show settings alert dialog
     */
    public static void showSettingsAlert(final Context mContext) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle(R.string.GPSAlertDialogTitle);

        // Setting Dialog Message
        alertDialog.setMessage(R.string.GPSAlertDialogMessage);

        // On Pressing Setting button
        alertDialog.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                    int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // On pressing cancel button
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                    int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    // Remves the non numeric lieterals from the phone number
    public static String getPhoneNumberNumerals(String phoneNumber) {

        return phoneNumber.replaceAll("[^\\d]", "");
    }

    // Initiate a phone call
    public static void initiateCall(final String phoneNumber,
            final Context context) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    public static List<Store> sortArrayStore(List<Store> storeData,
            int mSortType) {
        switch (mSortType) {
            case ID_SORT_AVAILABILITY:
                Collections.sort(storeData, new Comparator<Store>() {
                    @Override
                    public int compare(Store first,
                            Store second) {

                        if (first.getAvailability() == null && second.getAvailability() == null) {
                            return 0;
                        } else {
                            if (first.getAvailability() == null) {
                                return 1;
                            } else if (second.getAvailability() == null) {
                                return -1;
                            }
                        }

                        // first case - both stores have In Stock, One which has
                        // more stock will take precendence
                        if (first.getAvailability().contains(IN_STOCK) && second.getAvailability()
                                .contains(IN_STOCK)) {
                            // If available Stock is missing on both, they are
                            // same
                            if (first.getAvailableStock() == null && second.getAvailableStock() == null) {
                                return 0;
                            } else {
                                if (first.getAvailableStock() == null) {
                                    return 1;
                                } else if (second.getAvailableStock() == null) {
                                    return -1;
                                }

                            }

                            if (Integer.parseInt(first.getAvailableStock()) > Integer.parseInt(second.getAvailableStock())) {
                                return -1;
                            } else if (Integer.parseInt(first.getAvailableStock()) == Integer.parseInt(second.getAvailableStock())) {
                                return 0;
                            } else {
                                return 1;
                            }
                        }

                        // Second case - whichever one has in stock
                        if (first.getAvailability().contains(IN_STOCK)) {
                            return -1;
                        } else if (second.getAvailability().contains(IN_STOCK)) {
                            return 1;
                        }

                        // Third case - both stores have Low Stock, One which
                        // has
                        // more stock will take precendence
                        if (first.getAvailability().contains(LOW_STOCK) && second.getAvailability()
                                .contains(LOW_STOCK)) {
                            // If available Stock is missing on both, they are
                            // same
                            if (first.getAvailableStock() == null && second.getAvailableStock() == null) {
                                return 0;
                            } else {
                                if (first.getAvailableStock() == null) {
                                    return 1;
                                } else if (second.getAvailableStock() == null) {
                                    return -1;
                                }

                            }

                            if (Integer.parseInt(first.getAvailableStock()) > Integer.parseInt(second.getAvailableStock())) {
                                return -1;
                            } else if (Integer.parseInt(first.getAvailableStock()) == Integer.parseInt(second.getAvailableStock())) {
                                return 0;
                            } else {
                                return 1;
                            }
                        }

                        // Fourth case - whichever one has low stock
                        if (first.getAvailability().contains(LOW_STOCK)) {
                            return -1;
                        } else if (second.getAvailability().contains(LOW_STOCK)) {
                            return 1;
                        }

                        // Fifth case , both OUT OF STOCK
                        return 0;

                    }
                });
                break;

            case ID_SORT_DISTANCE:
                Collections.sort(storeData, new Comparator<Store>() {
                    @Override
                    public int compare(Store first,
                            Store second) {
                        if (first.getDistanceFromOrigin() == null && first.getDistanceFromOrigin() == null) {
                            return 0;
                        } else {
                            if (first.getDistanceFromOrigin() == null) {
                                return -1;
                            } else if (second.getDistanceFromOrigin() == null) {
                                return 1;
                            }
                        }
                        return Double.compare(Double.valueOf(first.getDistanceFromOrigin()),
                                Double.valueOf(second.getDistanceFromOrigin()));

                    }
                });
                break;

            case ID_SORT_STORENAME:
                Collections.sort(storeData, new Comparator<Store>() {
                    @Override
                    public int compare(Store first,
                            Store second) {
                        if (first.getStoreName() == null && first.getStoreName() == null) {
                            return 0;
                        } else {
                            if (first.getStoreName() == null) {
                                return -1;
                            } else if (second.getStoreName() == null) {
                                return 1;
                            }
                        }
                        return first.getStoreName().compareTo(second.getStoreName());

                    }
                });
                break;
        }

        return storeData;

    }

    /**
     * Check if a fragment is visible or not
     * 
     * @param fragment Weak reference of the fragment
     * @return True/False
     */
    public static boolean isFragmentVisible(WeakReference<?> fragment) {
        if (fragment.get() != null && ((Fragment) fragment.get()).isVisible()
                && !((Fragment) fragment.get()).isRemoving()) {
            return true;
        }

        return false;
    }

    public static void openAccountActivity(Activity activity,
            boolean showCreateAccount) {
        Intent intent = new Intent();
        // Account activity is being opened from left menu
        intent.putExtra(ConstantValues.SHOW_CREATE_ACCOUNT_KEY, showCreateAccount);
        intent.setClass(activity, AccountActivity.class);
        activity.startActivityForResult(intent, ConstantValues.REQUEST_CODE_MANAGE_ACCOUNT);
    }

    // Will be called when Order Tracking and Status hamburger menu item
    // clicked.
    // clicked.
    public static void openAccountActivityForGuest(Activity activity) {
        Intent intent = new Intent();
        // Account activity is being opened from left menu
        intent.putExtra(ConstantValues.SHOW_GUSET_SIGNIN_KEY, true);
        intent.setClass(activity, AccountActivity.class);
        activity.startActivityForResult(intent, ConstantValues.REQUEST_CODE_MANAGE_ACCOUNT);
    }

    /**
     * Start Rating and review Activity
     * 
     * @param activity Present activity
     * @param webID WebID for product
     */
    public static void openRatingReviewActivity(Activity activity,
            String webID) {
        Intent intent = new Intent();
        intent.putExtra(ConstantValues.EXTRA_KEY_WEB_ID, webID);
        intent.setClass(activity, RatingAndReviewActivity.class);
        activity.startActivity(intent);
    }

    public static int getActionBarHeight(Context context) {
        int actionBarHeight = 0;
        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    /**
     * Converts a MD5 digest byte array into HEX String
     * 
     * @param String targetString
     * @return String MD5HexString
     */
    public static String getMD5HexString(String targetString) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(targetString.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte byteData[] = md.digest();

        // convert the byte to hex format
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();

    }

    // Creates user token non encrypted simple string to set in the header.
    public static String getUserTokenString(String firstName,
            String lastName,
            String email,
            String timeStamp) {
        StringBuffer userTokenString = new StringBuffer();
        userTokenString.append(ConstantValues.SALT_VALUE);
        userTokenString.append(firstName);
        userTokenString.append(lastName);
        userTokenString.append(email);
        userTokenString.append(timeStamp);

        return userTokenString.toString();
    }

    // Opens a link in external browser
    public static final void openUrlInExternalBrowser(String url,
            Context context) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(i);
    }

    public static void openStoreDetailsActivity(Activity activity,
            Store store) {
        Intent intent = new Intent(activity, StoreDetailActivity.class);
        intent.putExtra(ConstantValues.BUNDLE_STORE_DETAIL, store);
        activity.startActivity(intent);
    }

    /**
     * Get Cart will be called when User has signed in/created profile and the
     * local database is empty. Update Cart will be called when User has signed
     * in/created profile and there are skus in local database
     */
    public static void startGetCart() {
        if (KohlsPhoneApplication.getInstance().getAuthenticationUtils() != null) {
            DBShoppingBagHelper shoppingBagHelper = new DBShoppingBagHelper();
            if (shoppingBagHelper.count(DBTablesDef.T_SHOPPING_BAG) == 0) {
                ControllerFactory.getPersistentCartController().getCart();
            } else {
                ControllerFactory.getPersistentCartController().updateCart(true);
            }
        }
    }

    /**
     * This method will be called when items are added/ updated/ deleted from
     * Shopping Bag/ PDP
     * 
     * @param skuCode
     * @param qty
     * @param registryObject
     */
    public static void startUpdateCart(String action,
            String skuCode,
            String qty,
            RegistryObject registryObject) {
        if (KohlsPhoneApplication.getInstance().getAuthenticationUtils() != null) {
            ControllerFactory.getPersistentCartController().updateCart(action,
                    skuCode,
                    qty,
                    registryObject);
        }
    }

    public static void startDeleteCart(WeakHashMap<String, String> deleteItems) {
        if (KohlsPhoneApplication.getInstance().getAuthenticationUtils() != null) {

            ControllerFactory.getPersistentCartController().deleteCartItems(deleteItems);
        }

    }

    /**
     * Open Scanning activity to scan Barcode or QRcode
     * 
     * @param scanReason - Scan Reasons can be of type {@link ScanActivity}
     *            .REQUEST_SCAN_SEARCHRESULTS, {@link ScanActivity}
     *            .REQUEST_SCAN_REQUEST_SCAN_LOYALTYNUMBER, {@link ScanActivity}
     *            .REQUEST_SCAN_DISCOUNTS
     */
    public static void openScanActivity(Activity activity,
            int scanReason) {
        Intent scanIntent;
        /*
         * If Scan is starting for the first time, we need to show Scan Help
         * Activity, which will in turn start Scan for us
         */
        if (KohlsPhoneApplication.getInstance().getKohlsPref().isFirstScan()) {
            scanIntent = new Intent(activity, ScanHelpActivity.class);
            scanIntent.putExtra(ScanActivity.SCAN_REQUEST_TYPE, scanReason);

        } else {
            scanIntent = new Intent(activity.getApplicationContext(), ScanActivity.class);

            // These are the bundle extras needed by the scan library to work
            scanIntent.setAction(ConstantValues.SCAN_ACTION);
            scanIntent.putExtra(ConstantValues.SCAN_FORMAT_TITLE, activity.getResources()
                    .getStringArray(R.array.scan_types));
            scanIntent.putExtra(ConstantValues.SCAN_HISTORY_TITLE, false);

            // Scan Request Reason
            scanIntent.putExtra(ScanActivity.SCAN_REQUEST_TYPE, scanReason);

            // This the height and width of the scan area required, this is
            // needed by scan library.
            DisplayMetrics displaymetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;
            scanIntent.putExtra(ConstantValues.SCAN_WIDTH_TITLE, height * .60);
            scanIntent.putExtra(ConstantValues.SCAN_HEIGHT_TITLE, width * .60);

        }
        activity.startActivityForResult(scanIntent, ConstantValues.REQUEST_CODE_SCAN_ACTIVITY);
    }

    public static String identifyURLForSearchAndPMP(String url) {
        String result = null;
        if (url.contains(ConstantValues.CMS_SEARCH_KEYWORD)) {
            result = url.substring(url.indexOf(ConstantValues.CMS_SEARCH_KEYWORD), url.length());
        } else {
            result = url;
        }

        return result;
    }

    /**
     * Get recommendations from adapter.
     * 
     * @param adapterListener
     */

    public static void getRecommendations(String webId,
            String storeNum,
            String type,
            String limit,
            String postalCode,
            IAdapterListener adapterListener) {
        RecommendationsPO recommendationsPO = new RecommendationsPO();
        if (!TextUtils.isEmpty(webId)) {
            recommendationsPO.setWebID(webId);
        }
        if (!TextUtils.isEmpty(storeNum)) {
            recommendationsPO.setStoreNum(storeNum);
        }
        if (!TextUtils.isEmpty(limit)) {
            recommendationsPO.setLimit(limit);
        }
        if (!TextUtils.isEmpty(type)) {
            recommendationsPO.setType(type);
        }
        if (!TextUtils.isEmpty(postalCode)) {
            recommendationsPO.setPostalCode(postalCode);
        }

        AdapterHelper adapterHelper = new AdapterHelper(AdapterProcedure.RECOMMENDATION,
                recommendationsPO,
                adapterListener);
        adapterHelper.performTask();
    }

    public static void openProductDetailsOnRecommendationClick(Activity activity,
            String webId) {
        Intent intent = new Intent();
        intent.setClass(activity, ProductDetailsActivity.class);
        intent.putExtra(ConstantValues.EXTRA_KEY_WEB_ID, webId);
        intent.putExtra(ConstantValues.FROM, ConstantValues.FROM_RECOMMENDATIONS);
        activity.finish();
        activity.startActivity(intent);
        activity.overridePendingTransition(R.animator.right_in, R.animator.left_out);
    }

    public static String parseFeatureBrandResponse(String response) {
        String resp = null;
        try {
            JSONObject obj = new JSONObject(response);
            String payload = obj.getString("payload");

            JSONArray jsonArray = new JSONObject(payload).getJSONArray("entries");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objProp = jsonArray.getJSONObject(i);
                resp = objProp.getString("properties");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

        }
        return resp;
    }

    public static boolean checkAndLaunchPDPOrPMP(String urlString,
            Context context) {
        boolean pdpPMPLaunched = false;

        String interceptListURL = null;
        boolean isFromRegistry = false;
        RegistryObject registryObject = null;

        if (urlString != null) {
            interceptListURL = urlString;
        }

        if (interceptListURL != null) {
            URL mKohlsURL;
            try {
                mKohlsURL = new URL(interceptListURL);
                String paramStrings = mKohlsURL.getQuery();
                String webID = null;
                String skuCode = null;
                boolean isSkuCode = false;

                if (paramStrings != null) {
                    String[] params = paramStrings.split("&");
                    for (String param : params) {
                        if (param.startsWith("skuCode") || param.startsWith("pCode")
                                || param.startsWith("prd")) {
                            if (!isSkuCode)
                                webID = param.substring(param.indexOf("=") + 1);
                        }
                        if (param.startsWith("regItemId")) {
                            isSkuCode = true;
                            skuCode = param.substring(param.indexOf("=") + 1);
                        }
                        if (param.startsWith("regId")) {
                            isFromRegistry = true;
                            if (registryObject == null) {
                                registryObject = new RegistryObject();
                            }
                            registryObject.setRegistryId(param.substring(param.indexOf("=") + 1));
                        }

                        if (param.startsWith("shipId")) {
                            isFromRegistry = true;
                            if (registryObject == null) {
                                registryObject = new RegistryObject();
                            }
                            registryObject.setShipToId(param.substring(param.indexOf("=") + 1));
                            registryObject.setRegistryQty(1);
                        }
                        if (param.startsWith("regType")) {
                            if (registryObject == null) {
                                registryObject = new RegistryObject();
                            }
                            registryObject.setRegistryType(param.substring(param.indexOf("=") + 1));
                            registryObject.setRegistryQty(1);
                        }

                        if (param.startsWith("qty")) {
                            if (registryObject == null) {
                                registryObject = new RegistryObject();
                            }
                            registryObject.setWantedQty(param.substring(param.indexOf("=") + 1));
                            registryObject.setRegistryQty(1);
                        }

                        if (param.startsWith("listName") && isFromRegistry) {
                            if (registryObject == null) {
                                registryObject = new RegistryObject();
                            }
                            try {
                                registryObject.setRegistryName(URLDecoder.decode(param.substring(param.indexOf("=") + 1),
                                        "UTF-8"));
                            } catch (UnsupportedEncodingException ex) {
                            }
                            registryObject.setRegistryQty(1);
                        }

                    }
                    if (webID != null) {
                        pdpPMPLaunched = true;
                        Intent intent = new Intent(context, ProductDetailsActivity.class);
                        intent.putExtra(ConstantValues.EXTRA_KEY_WEB_ID, webID);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        if (registryObject != null) {
                            intent.putExtra(ConstantValues.EXTRA_KEY_REGISTRY_ID, registryObject);
                        }
                        ((Activity) context).startActivityForResult(intent,
                                ConstantValues.REQUEST_CODE_PDP);
                        intent.putExtra(ConstantValues.FROM, ConstantValues.FROM_BROWS_FRAGMENT);

                    } else if (skuCode != null) {
                        pdpPMPLaunched = true;
                        Intent intent = new Intent(context, ProductDetailsActivity.class);
                        intent.putExtra(ConstantValues.EXTRA_KEY_SKU_CODE, skuCode);
                        intent.putExtra(ConstantValues.FROM, ConstantValues.FROM_FEATURE_BRAND);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        if (registryObject != null) {
                            intent.putExtra(ConstantValues.EXTRA_KEY_REGISTRY_ID, registryObject);
                        }
                        ((Activity) context).startActivityForResult(intent,
                                ConstantValues.REQUEST_CODE_PDP);
                    } else {
                        String catalogID = null;
                        String pmpTitle = null;
                        for (String param : params) {
                            if (param.startsWith("id")) {
                                catalogID = param.substring(param.indexOf("=") + 1);
                            }
                            if (param.startsWith("backTitle")) {
                                pmpTitle = param.substring(param.indexOf("=") + 1);
                                try {
                                    pmpTitle = URLDecoder.decode(pmpTitle, "UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                try {
                                    pmpTitle = URLDecoder.decode(pmpTitle, "UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        }

                        if (catalogID != null) {
                            Bundle args = new Bundle();
                            if (pmpTitle != null) {
                                args.putString(ConstantValues.EXTRA_KEY_PAGE_TITLE, pmpTitle);
                            }
                            pdpPMPLaunched = true;
                            args.putString(ConstantValues.EXTRA_KEY_CATEGORY_ID, catalogID);
                            ((HomeActivity) context).attachProductMatrixFragment(((HomeActivity) context).getFragmentOnScreen(),
                                    args);
                        }
                    }
                }
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return pdpPMPLaunched;
        // http://kstage1.skavaone.com/kohlsopenapi/subcategory.html?listType=registry&regId=2515445&regItemId=91092396&regItemType=SKU&qty=1&pCode=1702229&prd_cd=249174&isFromRegistry=true&listName=Aravinth%20and%20Testing%27s%20Wedding%20Registry&regType=bridal&isFromSearch=true
    }

    public static void getStores(Location location,
            IAdapterListener adapterListener) {
        StoreLocatorPO storeLocatorPO = new StoreLocatorPO();
        if (location != null) {
            storeLocatorPO.setLatitude(String.valueOf(location.getLatitude()));
            storeLocatorPO.setLongitude(String.valueOf(location.getLongitude()));
            storeLocatorPO.setRadius(KohlsPhoneApplication.getInstance()
                    .getConfigurationUtils()
                    .getConfig()
                    .getStoreRadius());

            AdapterHelper adapterTask = new AdapterHelper(AdapterProcedure.STORE_BY_OPENSEARCH,
                    storeLocatorPO,
                    adapterListener);
            adapterTask.performTask();
        }

    }

    public static long getTimeinLong(String expires) {
        SimpleDateFormat parserSDF = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz");
        Date dateStr = null;
        try {
            dateStr = parserSDF.parse(expires);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (dateStr != null) {
            return dateStr.getTime();
        }
        return 0;
    }

    public static void getFlushTimes() {
        CacheFlushHelper mCacheFlushHelper = new CacheFlushHelper();
        mCacheFlushHelper.startFlushCalls();
    }

    public static void sendKohlsAnalytics(AnalyticsObject analyticsObject) {

        ScreenTracker.addScreenBreadCrum(analyticsObject.getPageName());

        if (!analyticsObject.getPageName().equalsIgnoreCase(PageNames.HAMBURGER_MENU.toString())) {
            analyticsObject.seteVar9(ScreenTracker.getSeconLastScreenBreadCrum());
        } else {
            analyticsObject.seteVar9(AnalyticsConstants.NA);
        }

        // Set User details for Analytics call
        analyticsObject.seteVar72(KohlsPhoneApplication.getInstance()
                .getAuthenticationUtils()
                .getPreviousUserMailIdFromPref());
        analyticsObject.seteVar73(KohlsPhoneApplication.getInstance()
                .getAuthenticationUtils()
                .getUserLoyatyID());

        if (KohlsPhoneApplication.getInstance().getAuthenticationUtils().isUserSignedIn()) {
            analyticsObject.setProp17(AnalyticsConstants.LOGGED_IN);
            analyticsObject.seteVar39(KohlsPhoneApplication.getInstance()
                    .getAuthenticationUtils()
                    .getSignedInUserID());
        } else {
            analyticsObject.setProp17(AnalyticsConstants.NOT_LOGGED_IN);
            analyticsObject.seteVar39(AnalyticsConstants.NOT_LOGGED_IN);
        }

        analyticsObject.setAnalyticsPrefObject(KohlsPhoneApplication.getInstance()
                .getKohlsPref()
                .getAnalyticsPrefObject());
        KohlsPhoneApplication.getInstance().getAnalytics().sendAnalytics(analyticsObject);

    }

}
