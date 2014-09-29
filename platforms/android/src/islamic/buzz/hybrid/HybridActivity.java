package islamic.buzz.hybrid;

import islamic.buzz.app.BuzzApplication;
import islamic.buzz.helpers.ActionBarHelper;
import islamic.buzz.interfaces.listeners.IUpdateActionBarListener;
import islamic.buzz.util.ConstantValues;
import islamic.buzz.util.Logger;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebView;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;

import com.eybsolution.islamic.buzz.BuildConfig;
import com.eybsolution.islamic.buzz.R;

/**
 * Common Activity for showing all hybrid pages
 */
@SuppressLint("NewApi")
public class HybridActivity extends CordovaActivity implements IUpdateActionBarListener {
	private static final String TAG = HybridActivity.class.getSimpleName();

	// Constants
	// private static final String MESSAGE_ON_PAGE_FINISHED = "onPageFinished";

	private static final String STATE_PAGE_TITLE = "pageTitle";

	private static final String STATE_SHOPPING_BAG_ICON_VISIBLE_STATUS = "bagIcon";

	private static final String BACK_BUTTON_PLUGIN = "BackButtonPlugin";

	private static final String NATIVE_HYBRID_PLUGIN = "NativeHybridPlugin";

	// Action Constants
	// Inward actions to be performed in Native Objects
	private static final String IN_ACTION_SET_ACTIONBAR = "IN_ACTION_SET_ACTIONBAR";

	private static final String IN_ACTION_PULSE = "IN_ACTION_PULSE";

	private static final String IN_ACTION_SET_ACTIONBAR_DATA_URL = "url";

	// Local variables
	private HybridScreen screen = null;

	private ActionBarHelper mActionBarHelper;

	private String mPageTitle = "";

	private int mBagIconVisibilityStatus = View.GONE;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Enabling debugging of JS or Webview in kitkat version.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			if (BuildConfig.DEBUG) {
				WebView.setWebContentsDebuggingEnabled(true);
			}
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		Bundle bundle = getIntent().getExtras();
		if (bundle.containsKey(ConstantValues.SCREEN_TO_LOAD_KEY)) {
			screen = (HybridScreen) bundle.get(ConstantValues.SCREEN_TO_LOAD_KEY);
			if ("".equals(mPageTitle)) {
				mPageTitle = screen.getScreenTitle();
				if (HybridScreen.NAMES_OF_ALLAH == screen) {
					mBagIconVisibilityStatus = View.VISIBLE;
				}
				initializeActionBar();
			}
			Logger.debug(TAG, "Screen is: " + screen);
		}
	}

	@Override
	protected void onResume() {
		if (mActionBarHelper != null) {
			// mActionBarHelper.updateShopingBagCountAndVisibility(false);
		}

		if (isSecurePageShowing()) {
			// KohlsPhoneApplication.getInstance().getAuthenticationUtils().resetIdleTimer();
		}
		super.onResume();
	}

	/**
	 * Save Action Bar State
	 */
	@Override
	protected void onSaveInstanceState(Bundle instanceState) {
		super.onSaveInstanceState(instanceState);
		if (instanceState != null) {
			instanceState.putString(STATE_PAGE_TITLE, mPageTitle);
			instanceState.putInt(STATE_SHOPPING_BAG_ICON_VISIBLE_STATUS, mBagIconVisibilityStatus);
		}
	}

	/**
	 * Restore Action Bar State
	 */
	@Override
	protected void onRestoreInstanceState(Bundle instanceState) {
		super.onRestoreInstanceState(instanceState);
		if (instanceState != null) {
			if (instanceState.containsKey(STATE_PAGE_TITLE)) {
				mPageTitle = instanceState.getString(STATE_PAGE_TITLE);
			}
			if (instanceState.containsKey(STATE_SHOPPING_BAG_ICON_VISIBLE_STATUS)) {
				mBagIconVisibilityStatus = instanceState.getInt(STATE_SHOPPING_BAG_ICON_VISIBLE_STATUS);
			}
			// Re-iniitalize action bar after screen is restored from hidden
			// state
			initializeActionBar();
		}
	}

	/**
	 * The IBM Worklight web framework calls this method after its
	 * initialization is complete and web resources are ready to be used.
	 */
	// @Override
	// public void onInitWebFrameworkComplete(WLInitWebFrameworkResult result) {
	// if (result.getStatusCode() == WLInitWebFrameworkResult.SUCCESS) {
	// loadUrl(WL.getInstance().getMainHtmlFilePath());
	// } else {
	// handleFailure();
	// }
	// }

	private void handleFailure() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setNegativeButton(R.string.close, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				endActivity();
			}
		});

		alertDialogBuilder.setTitle(screen.getScreenTitle());
		alertDialogBuilder.setMessage(R.string.api_call_failed);
		alertDialogBuilder.setCancelable(false).create().show();
	}

	@Override
	public void updateActionBarData() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// mActionBarHelper.updateShopingBagCountAndVisibility(false);
			}

		});
	}

	/**
	 * This will return the current screen Url to be displayed in the screen
	 * 
	 * @return Screen Url or Empty String if screen object is not initialized
	 */
	public String getScreenUrl() {
		String screenUrl = "";
		if (screen != null) {
			screenUrl = screen.getScreenUrl();
		}
		return screenUrl;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public void onActionReceived(String action, JSONObject data) {
		// On Action received from Hybrid, reset idle timer
		if (IN_ACTION_SET_ACTIONBAR.equalsIgnoreCase(action)) {
			try {
				if (data.has(IN_ACTION_SET_ACTIONBAR_DATA_URL)) {
					final HybridScreen screen = HybridScreen.getScreenFromUrlPostfix(data.getString(IN_ACTION_SET_ACTIONBAR_DATA_URL));
					if (screen != null) {
						getActivity().runOnUiThread(new Runnable() {

							@Override
							public void run() {
								mPageTitle = screen.getScreenTitle();
								setActionBarTitle(mPageTitle);
								switch (screen) {
								case SHOPPINGBAG: {
									mBagIconVisibilityStatus = View.VISIBLE;
									break;
								}
								default: {
									mBagIconVisibilityStatus = View.GONE;
								}
								}
								setShoppingBagIconVisibility(mBagIconVisibilityStatus);
							}
						});
					}
				}
			} catch (JSONException je) {
				Logger.error(TAG, je.getMessage());
			}
		} else if (IN_ACTION_PULSE.equalsIgnoreCase(action)) {
			Logger.debug(TAG, "Pulse from Hybrid received");
		}

	}

	// Code not required
	// @Override
	// public Object onMessage(String id,
	// Object data) {
	// if (MESSAGE_ON_PAGE_FINISHED.equalsIgnoreCase(id)) {
	// KohlsPhoneApplication.getInstance().getAuthenticationUtils().startIdleTimer(this);
	// }
	// return super.onMessage(id, data);
	// }

	public boolean isSecurePageShowing() {
		if (appView == null) {
			return false;
		}

		HybridScreen screen = HybridScreen.getScreenFromUrl(appView.getUrl());
		// Since all pages except Shopping Bag contains PII information. All
		// others will be treated as secure
		if (screen != HybridScreen.NAMES_OF_ALLAH) {
			return true;
		}

		return false;
	}

	public void setActionBarTitle(String titleText) {
		if (mActionBarHelper == null) {
			return;
		}
		mActionBarHelper.getTitleTextView().setText(titleText);
		mPageTitle = titleText;
	}

	private void setShoppingBagIconVisibility(int visibility) {
		if (mActionBarHelper == null) {
			return;
		}
		mActionBarHelper.getItemView().setVisibility(visibility);
		mActionBarHelper.getItemViewCountView().setVisibility(visibility);
		mBagIconVisibilityStatus = visibility;
	}

	/**
	 * Initialize Action Bar
	 */
	private void initializeActionBar() {
		Logger.debug(TAG, "Initializing Action Bar");
		if (getActionBar() == null) {
			Logger.error(TAG, "Unable to initialize Action Bar");
			return;
		}

		if (mActionBarHelper != null) {
			setActionBarTitle(mPageTitle);
			setShoppingBagIconVisibility(mBagIconVisibilityStatus);
			return;
		}
		mActionBarHelper = new ActionBarHelper(this, getActionBar());
		mActionBarHelper.initActionBarWithCustomView();
		//mActionBarHelper.showActionBarForHybridScreens(mPageTitle);
		mActionBarHelper.getAppImageView().setVisibility(View.GONE);
		mActionBarHelper.getTitleTextView().setVisibility(View.VISIBLE);
		mActionBarHelper.getBackButtonView().setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				performBackPress();
			}
		});
	}

	/**
	 * Handle Back button functionality of Action Bar to be same as device back
	 * button in Android
	 */
	private void performBackPress() {
		if (appView == null) {
			Logger.error(TAG, "Webview is not available");
			return;
		}

		HybridScreen presentScreen = HybridScreen.getScreenFromUrl(appView.getUrl());

		if (presentScreen != null) {
			if (presentScreen.isMoveToShoppingBag()) {
				BackButtonPlugin bridge = (BackButtonPlugin) appView.pluginManager.getPlugin(BACK_BUTTON_PLUGIN);
				bridge.reportEvent();
			} else if ((presentScreen == HybridScreen.SHOPPINGBAG) || (presentScreen == HybridScreen.EMPTY_SHOPPINGBAG) || (presentScreen == HybridScreen.MY_INFORMATION) || (presentScreen == HybridScreen.ORDER_HISTORY)) {
				endActivity();
			} else if (presentScreen == HybridScreen.CONFIRMATION) {
				NativeHybridCommPlugin nhplugin = new NativeHybridCommPlugin();
				try {
					nhplugin.goToHomeScreen(this);
				} catch (Exception e) {
					e.printStackTrace();
					Logger.error(TAG, "Unable to perform back");
				}
			} else {
				performHistoryBack(appView);
			}
		} else {
			performHistoryBack(appView);
		}
	}

	private void performHistoryBack(CordovaWebView appView) {
		WebBackForwardList mWebBackForwardList = appView.copyBackForwardList();
		WebHistoryItem historyItem = mWebBackForwardList.getItemAtIndex(mWebBackForwardList.getCurrentIndex() - 1);

		if (historyItem != null) {
			backHistory();
		} else {
			endActivity();
		}
	}
}
