
package islamic.buzz.http.helper;

import islamic.buzz.interfaces.listeners.IGeoLocation;
import islamic.buzz.util.ConstantValues;
import islamic.buzz.util.UtilityMethods;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;

public class GeoLocationHelper implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener,
        LocationListener {

    public static double mLatitude;

    public static double mLongitude;

    private LocationClient mLocationClient;

    // Global variable to hold the current location
    public static Location mCurrentLocation;

    private WeakReference<Activity> mActivityRefrence;

    private IGeoLocation mGeoLocationListner;

    public GeoLocationHelper(WeakReference<Activity> contextRefrence,
            IGeoLocation geoLocationListener) {
        mActivityRefrence = contextRefrence;
        mGeoLocationListner = geoLocationListener;
    }

    public void initializeComponent() {
        startFetchingCurrentLocation();
    }

    private void startFetchingCurrentLocation() {
        initialiseGoogleMap();
    }

    private void initialiseGoogleMap() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mActivityRefrence.get());
        if (resultCode != ConnectionResult.SUCCESS) {
            UtilityMethods.showGoogleServiceErrorDialog(resultCode,
                    ConstantValues.REQUEST_RESOLVE_ERROR,
                    mActivityRefrence.get());
        } else {
            mLocationClient = new LocationClient(mActivityRefrence.get(), this, this);
            mLocationClient.connect();
        }

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        if (!isLocationServiceEnabled()) {
            dismissDialog();
            showLocationSettings();
            if (mGeoLocationListner != null) {
                mGeoLocationListner.onConnectionFailed();
            }
        } else {
            if (mLocationClient.isConnected()) {
                mCurrentLocation = mLocationClient.getLastLocation();
                if (mCurrentLocation != null) {
                    mLatitude = mCurrentLocation.getLatitude();
                    mLongitude = mCurrentLocation.getLongitude();
                }
            }
            if (mGeoLocationListner != null) {
                mGeoLocationListner.onConnected();
            }
        }
        
        

    }

    @Override
    public void onDisconnected() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Show dialog using GooglePlayServicesUtil.getErrorDialog()
        // dismissDialog();
        if (mGeoLocationListner != null) {
            mGeoLocationListner.onConnectionFailed();
        }
        UtilityMethods.showGoogleServiceErrorDialog(result.getErrorCode(),
                ConstantValues.REQUEST_RESOLVE_ERROR,
                mActivityRefrence.get());

    }

    @Override
    public void onLocationChanged(Location location) {
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public String populateZipCode() throws IOException {
        Geocoder coder = new Geocoder(mActivityRefrence.get());
        List<Address> addresses = coder.getFromLocation(mLatitude, mLongitude, 1);
        if (addresses != null && addresses.size() > 0) {
            return addresses.get(0).getPostalCode();
        } else {
            return "";
        }

    }
    
    public Address getAddress() throws IOException {
        Geocoder coder = new Geocoder(mActivityRefrence.get());
        List<Address> addresses = coder.getFromLocation(mLatitude, mLongitude, 1);
        if (addresses != null && addresses.size() > 0) {
            return addresses.get(0);
        } else {
            return null;
        }

    }
    

    public Location getCurrentLocation() {
        return mCurrentLocation;
    }

    public boolean isCurrentLocationAvailable() {
        if (mCurrentLocation != null) {
            return true;
        }
        return false;
    }
    
    /**
     * Show location settings dialog to ask user to enable location service
     * 
     * @param isNetworkEnabled TODO
     */
    private void showLocationSettings() {
        dismissDialog();
        String mErrorMessage = mActivityRefrence.get().getString(R.string.no_gps_enabled);

        Builder dialog = UtilityMethods.getAlertDialog(mActivityRefrence.get(),
                mErrorMessage,
                mActivityRefrence.get().getString(R.string.OPEN_LOCATION_SETTINGS),
                mActivityRefrence.get().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                            int which) {
                        if (which == AlertDialog.BUTTON_POSITIVE) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            mActivityRefrence.get().startActivity(myIntent);
                        } else {
                            dismissDialog();
                        }
                    }
                });
        if (checkFragmentisActive()) {
            dialog.show();
        }
    }
    
    /**
     * Method to dismiss all dialogs on pressing BACK
     */
    private void dismissDialog() {
        UtilityMethods.dismissDialog(ConstantValues.ALERT_DIALOG_DEFAULT);
        UtilityMethods.dismissDialog(ConstantValues.PROGRESS_DIALOG_DEFAULT);
    }
    
    private boolean checkFragmentisActive() {
        return mActivityRefrence.get() != null;

    }
    
    /**
     * Returns true if location service is enabled, else returns false. The
     * getting location will first try to get location based on the available
     * provider , even if it is only GPS provider, if the getting location times
     * out - we check whether the NETWORK PROVIDER is available or not - if not
     * ask User to turn on Network Provider. If user declines then we do show
     * the Location error.
     */
    private boolean isLocationServiceEnabled() {
        LocationManager locationManager = (LocationManager) mActivityRefrence.get().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null || !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return false;
        }
        return true;
    }

}
