
package islamic.buzz.helpers;

import islamic.buzz.interfaces.listeners.BottomBarDrawerListener;
import islamic.buzz.util.UtilityMethods;
import islamic.buzz.views.BottomBarDrawer;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eybsolution.islamic.buzz.R;

/**
 * Helper for initializing sliding up layout & attaching listener to it.Provides
 * common functionalities to all the activities which are using sliding up
 * layout.
 * 
 * @author ankit
 */
public class SlidingUpViewHelper implements BottomBarDrawerListener, IAdapterListener {

    private BottomBarDrawer mBottomBarDrawer;

    private Activity mActivity;

    private int mActionBarHeight;

    private int mActionBarAnimOffset;

    private boolean mIsActionBarHidden;

    private View mSearchView;


    private ProgressBar mRecommendationProgressBar;

    private GridView mRecommendedGridView;

    private LinearLayout mRecommendationsLayout;


    private LinearLayout mProgressBar;

    private LinearLayout mFindNearByLayout;

    private TextView mWelcomeMessage;

    private TextView mWelcomeHeader;

    public SlidingUpViewHelper(BottomBarDrawer bottomBarDrawer,
            Activity activity,
            int actionBarHeight) {
        mBottomBarDrawer = bottomBarDrawer;
        mActivity = activity;
        mActionBarHeight = actionBarHeight;
        mActionBarAnimOffset = mActionBarHeight + (int) UtilityMethods.convertDpToPixel(50,
                mActivity);
    }

    /**
     * Initialize Sliding up view's layout
     */
    public void initSlidingUpLayout() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int padding = (int) UtilityMethods.convertDpToPixel(20, mActivity);
        int height = displaymetrics.heightPixels - padding;
        mBottomBarDrawer.setExpandedHeightOfDrawer(height);
        }

    private View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.id_inStoreTool_myKohlsStoreLocatorLayout:
                    showStoreLocator();
                    break;
                case R.id.id_inStoreTool_myKohlsStoreLayout:
                    showStoreDetails();
                    break;
                case R.id.id_inStoreTool_myPriceCheckerImage:
                    UtilityMethods.openScanActivity(mActivity,
                            ScanActivity.REQUEST_SCAN_SEARCHRESULTS);
                    disableSlidingLayout();
                    break;
                case R.id.id_inStoreTool_productReviewsImage:
                    UtilityMethods.openScanActivity(mActivity,
                            ScanActivity.REQUEST_SCAN_PDP_REVIEWS);
                    disableSlidingLayout();
                    break;
                case R.id.id_inStoreTool_myListsImage:
                    showMyLists();
                    break;
            }
        }
    };

    /**
     * Utility method to show and hide Action bar with animation(Sliding
     * transition effect)
     * 
     * @param y
     */
    public void setActionBarTranslation(float y) {
        // Figure out the actionbar height
        int actionBarHeight = mActionBarHeight;
        // A hack to add the translation to the action bar
        ViewGroup content = ((ViewGroup) mActivity.findViewById(android.R.id.content).getParent());
        Logger.debug("Slider", content.toString());
        int children = content.getChildCount();
        for (int i = 0; i < children; i++) {
            View child = content.getChildAt(i);
            if (child.getId() != android.R.id.content) {
                if (y <= -actionBarHeight) {
                    child.setVisibility(View.GONE);
                } else {
                    Logger.debug("Slider", "parallax : " + y);
                    child.setVisibility(View.VISIBLE);
                    child.setTranslationY(y);
                }
            }
        }
    }

    /**
     * Enable Sliding layout & make it visible.
     */
    public void enableSlidingLayout() {
        mFindNearByLayout = (LinearLayout) mActivity.findViewById(R.id.id_inStoreTool_myKohlsStoreLocatorLayout);
        mActivity.findViewById(R.id.id_inStoreTool_myKohlsStoreLocatorLayout)
                .setOnClickListener(mClickListener);
        mActivity.findViewById(R.id.id_inStoreTool_myKohlsStoreLayout)
                .setOnClickListener(mClickListener);
        mActivity.findViewById(R.id.id_inStoreTool_myPriceCheckerImage)
                .setOnClickListener(mClickListener);
        mActivity.findViewById(R.id.id_inStoreTool_productReviewsImage)
                .setOnClickListener(mClickListener);
        mRecommendationProgressBar = (ProgressBar) mActivity.findViewById(R.id.id_inStoreTool_recommendationProgressBar);
        mRecommendedGridView = (GridView) mActivity.findViewById(R.id.id_inStoreTool_recommendedGridView);
        mActivity.findViewById(R.id.id_inStoreTool_myListsImage).setOnClickListener(mClickListener);
        mRecommendationsLayout = (LinearLayout) mActivity.findViewById(R.id.id_inStoreTool_recommendationsLayout);
        mBottomBarDrawer.setDrawerListener(this);
        mProgressBar = (LinearLayout) mActivity.findViewById(R.id.id_inStoreTool_store_layout_progress);
        mWelcomeHeader = (TextView) mActivity.findViewById(R.id.id_inStoreTool_inStore_welcome);
        mWelcomeMessage = (TextView) mActivity.findViewById(R.id.id_inStoreTool_inStore_welcome_message);
        setUserStore();
        /*
         * if (mRecommendationsVO == null) { getRecommendations(); }
         */
        mGeoLocationHelper = new GeoLocationHelper(new WeakReference<Activity>(mActivity),
                geoLocationListner);
        mGeoLocationHelper.initializeComponent();
        mActionBarAnimOffset = mActionBarHeight + (int) UtilityMethods.convertDpToPixel(50,
                mActivity);
        mActionBarAnimOffset = mBottomBarDrawer.getExpandedHeightofDrawer() - mActionBarAnimOffset;
        mBottomBarDrawer.show();
        mBottomBarDrawer.expand();
    }

    /**
     * Disable Sliding layout.
     */
    public void disableSlidingLayout() {
        mBottomBarDrawer.hide();
    }

    /**
     * Set user store on Slider
     */
    private void setUserStore() {
        GothamBoldTextView addressTextView = ((GothamBoldTextView) mActivity.findViewById(R.id.id_inStoreTool_myStoreAddress));
        PreferenceHelper preferenceHelper = new PreferenceHelper(mActivity);
        Store userStore = preferenceHelper.getUserStore();
        StringBuilder addressStringBuilder = new StringBuilder();
        if (userStore != null && userStore.getAddress() != null) {
            mActivity.findViewById(R.id.id_inStoreTool_myKohlsStoreLayout)
                    .setVisibility(View.VISIBLE);
            hideProgressBar();
            String address = userStore.getAddress().getAddr1();
            if (TextUtils.isEmpty(address)) {
                address = userStore.getAddress().getAddr2();
            }
            addressStringBuilder.append(address);
            addressStringBuilder.append("\n");
            addressStringBuilder.append(userStore.getAddress().getCity());
            addressStringBuilder.append(", ");
            addressStringBuilder.append(userStore.getAddress().getState());
            addressStringBuilder.append(", ");
            addressStringBuilder.append(userStore.getAddress().getPostalCode());
            addressTextView.setText(addressStringBuilder.toString());
            mWelcomeMessage.setText(userStore.getAddress().getCity() + ", "
                    + userStore.getAddress().getState());
            visisbleWelcomeMessage();
        }
    }

    private void showStoreLocator() {
        Intent intent = new Intent(mActivity, HomeActivity.class);
        intent.putExtra(ConstantValues.OPEN_SCREEN_KEY, ConstantValues.OPEN_STORE_LOCATOR);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mActivity.startActivity(intent);
        disableSlidingLayout();
    }

    private void showStoreDetails() {
        PreferenceHelper preferenceHelper = new PreferenceHelper(mActivity);
        Store userStore = preferenceHelper.getUserStore();
        if (userStore != null && userStore.getStoreNum() != null) {
            UtilityMethods.openWebViewActivity(mActivity,
                    "http://kohls.shoplocal.com/Kohls/Entry/LandingContent?storeid=" + userStore.getStoreNum()
                            + "&sneakpeek=N&listingid=0",
                    "");
            disableSlidingLayout();
        }

    }

    private void showMyLists() {
        Intent intent = new Intent(mActivity, HomeActivity.class);
        intent.putExtra(ConstantValues.OPEN_SCREEN_KEY, ConstantValues.OPEN_My_LISTS);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mActivity.startActivity(intent);
        disableSlidingLayout();
    }

    @Override
    public void onMoving(int drawerHeight) {
        // Change offset as drawer is moving from bottom to top(its height will
        // increase when it will move from bottom to top).

        if (drawerHeight >= mActionBarAnimOffset) {
            mIsActionBarHidden = true;
            mSearchView.setVisibility(View.GONE);
            mActivity.getActionBar().hide();

        } else if (mIsActionBarHidden) {
            mIsActionBarHidden = false;
            mSearchView.setVisibility(View.VISIBLE);
            mActivity.getActionBar().show();
        }
    }

    @Override
    public void onCollapsing() {
        if (mIsActionBarHidden) {
            mSearchView.setVisibility(View.VISIBLE);
            mActivity.getActionBar().show();
        }
    }

    @Override
    public void onExpanding() {
        mSearchView.setVisibility(View.GONE);
        mIsActionBarHidden = true;
        mActivity.getActionBar().hide();
    }

    public void setSearchView(View searchView) {
        mSearchView = searchView;
    }

    @Override
    public void onSuccess(IValueObject valueObject) {
        if (mActivity != null && !mActivity.isFinishing()) {
        if (valueObject instanceof RecommendationsVO) {
            mRecommendationsVO = (RecommendationsVO) valueObject;
            if (mRecommendationsVO.getPayload() != null && mRecommendationsVO.getPayload()
                    .getRecommendations() != null
                    && !mRecommendationsVO.getPayload().getRecommendations().isEmpty()
                        && mRecommendationsVO.getPayload()
                                .getRecommendations()
                                .get(0)
                                .getProducts() != null
                    && !mRecommendationsVO.getPayload()
                            .getRecommendations()
                            .get(0)
                            .getProducts()
                            .isEmpty()) {
                mRecommendationsLayout.setVisibility(View.VISIBLE);
                mRecommendationProgressBar.setVisibility(View.GONE);

                    RecommendationsAdapter recommendationsAdapter = new RecommendationsAdapter(new WeakReference<Activity>(mActivity),

                       
                        mRecommendationsVO.getPayload().getRecommendations().get(0).getProducts(), ConstantValues.FROM_RECOMMENDATIONS);
                mRecommendedGridView.setAdapter(recommendationsAdapter);
                mRecommendedGridView.setVisibility(View.VISIBLE);
                mRecommendedGridView.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {
                        disableSlidingLayout();
                        UtilityMethods.openProductDetailsOnRecommendationClick(mActivity,
                                mRecommendationsVO.getPayload()
                                        .getRecommendations()
                                        .get(0)
                                        .getProducts()
                                        .get(position)
                                        .getId());
                    }
                });
            } else {
                mRecommendationsLayout.setVisibility(View.GONE);
                mRecommendationProgressBar.setVisibility(View.GONE);
            }
            } else if (valueObject instanceof StoreLocatorVO) {
                StoreLocatorVO storeLocatorVO = (StoreLocatorVO) valueObject;
                if (storeLocatorVO != null && storeLocatorVO.getPayload() != null
                        && storeLocatorVO.getPayload().getStores() != null
                        && !storeLocatorVO.getPayload().getStores().isEmpty()) {
                    storeLocatorVO.getPayload()
                            .setStores(UtilityMethods.sortArrayStore(storeLocatorVO.getPayload()
                                    .getStores(), UtilityMethods.ID_SORT_DISTANCE));
                    setStoreAddress(storeLocatorVO.getPayload().getStores().get(0).getAddress());
                    PreferenceHelper preferenceHelper = new PreferenceHelper(mActivity);
                    preferenceHelper.saveUserStore(storeLocatorVO.getPayload().getStores().get(0));

        } else {
                    hideProgressBar();
                    visibleFindNearByStore();
                    mActivity.findViewById(R.id.id_inStoreTool_myKohlsStoreLayout)
                            .setVisibility(View.GONE);
                }
            } else {
            mRecommendationsLayout.setVisibility(View.GONE);
            mRecommendationProgressBar.setVisibility(View.GONE);
        }
    }
    }

    @Override
    public void onFailure(final Error ex) {
        mActivity.runOnUiThread(new Runnable() {
            
            @Override
            public void run() {
                mRecommendationsLayout.setVisibility(View.GONE);
                mRecommendationProgressBar.setVisibility(View.GONE);
                if (!ErrorHelper.isApplicationManagable(ex)) {
                    UtilityMethods.showToast(mActivity, ex.getMessage(), Toast.LENGTH_SHORT);
                }
                
            }
        });
       
    }

    @Override
    public Error getPayloadError(IValueObject payload) {
        if (mRecommendationsVO != null && mRecommendationsVO.getErrors() != null
                && mRecommendationsVO.getErrors().size() > 0) {
            return new Error(mRecommendationsVO.getErrors().get(0).getCode(),
                    mRecommendationsVO.getErrors().get(0).getMessage(),
                    ErrorType.ADAPTER_PAYLOAD);
        }
        return null;
    }

    /**
     * Call recommendations adapter to get the recommendations of the products.
     */
    private void getRecommendations() {
        mRecommendationsLayout.setVisibility(View.VISIBLE);
        mRecommendationProgressBar.setVisibility(View.VISIBLE);
        mRecommendedGridView.setVisibility(View.GONE);
        String zipCode = null;
        Store userStore = new PreferenceHelper(mActivity).getUserStore();
        String storeNum = null;
        if (userStore != null && userStore.getStoreNum() != null) {
            storeNum = userStore.getStoreNum();
        }
        try {
            zipCode = mGeoLocationHelper.populateZipCode();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        UtilityMethods.getRecommendations(null,
                null,
                ConstantValues.RECOMMENDED_TYPE_TOP_TRENDING,
                "4",
                zipCode,
                this);
    }

    private void getStoreLocation() {
        visibleProgressBar();
        UtilityMethods.getStores(mGeoLocationHelper.getCurrentLocation(), this);

}

    private void setStoreAddress(final StoreLocatorVO.Payload.Store.Address address) {
        final GothamBoldTextView addressTextView = ((GothamBoldTextView) mActivity.findViewById(R.id.id_inStoreTool_myStoreAddress));
        mActivity.findViewById(R.id.id_inStoreTool_myKohlsStoreLayout).setVisibility(View.VISIBLE);
        hideProgressBar();
        hideFindNearByStore();
        mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                StringBuilder addressStringBuilder = new StringBuilder();
                addressStringBuilder.append(address.getAddr1());
                addressStringBuilder.append("\n");
                addressStringBuilder.append(address.getCity());
                addressStringBuilder.append(", ");
                addressStringBuilder.append(address.getState());
                addressStringBuilder.append(", ");
                addressStringBuilder.append(address.getPostalCode());
                addressTextView.setText(addressStringBuilder.toString());
                mWelcomeMessage.setText(address.getCity() + ", " + address.getState());
                visisbleWelcomeMessage();
            }
        });

    }

    private IGeoLocation geoLocationListner = new IGeoLocation() {

        @Override
        public void onConnectionFailed() {
            if (mActivity != null && !mActivity.isFinishing()) {
                mActivity.findViewById(R.id.id_inStoreTool_myKohlsStoreLayout)
                        .setVisibility(View.GONE);
                mRecommendationProgressBar.setVisibility(View.GONE);
            }
        }

        @Override
        public void onConnected() {
            getStoreLocation();
            if (mRecommendationsVO == null) {
                getRecommendations();
            }
        }
    };

    private void hideProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private void visibleProgressBar() {
        if (mProgressBar != null) {

            mProgressBar.setVisibility(View.VISIBLE);
        }

    }

    private void hideFindNearByStore() {
        if (mFindNearByLayout != null) {
            mFindNearByLayout.setVisibility(View.GONE);
        }
    }

    private void visibleFindNearByStore() {
        if (mFindNearByLayout != null) {
            mFindNearByLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onHidden() {
        mIsActionBarHidden = true;
        mSearchView.setVisibility(View.VISIBLE);
        mActivity.getActionBar().show();
    }

    private void hideWelcomeMessage() {
        mWelcomeHeader.setVisibility(View.GONE);
        mWelcomeMessage.setVisibility(View.GONE);

    }

    private void visisbleWelcomeMessage() {
        mWelcomeHeader.setVisibility(View.VISIBLE);
        mWelcomeMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public long getCacheHeader(IValueObject payload) {
        // TODO Auto-generated method stub
        return 0;
    }

}
