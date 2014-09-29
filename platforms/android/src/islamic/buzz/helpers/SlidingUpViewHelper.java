
package islamic.buzz.helpers;

import islamic.buzz.http.helper.GeoLocationHelper;
import islamic.buzz.interfaces.listeners.BottomBarDrawerListener;
import islamic.buzz.interfaces.listeners.IGeoLocation;
import islamic.buzz.util.Logger;
import islamic.buzz.util.UtilityMethods;
import islamic.buzz.views.BottomBarDrawer;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Helper for initializing sliding up layout & attaching listener to it.Provides
 * common functionalities to all the activities which are using sliding up
 * layout.
 * 
 * @author ankit
 */
public class SlidingUpViewHelper implements BottomBarDrawerListener {

    private BottomBarDrawer mBottomBarDrawer;

    private Activity mActivity;

    private int mActionBarHeight;

    private int mActionBarAnimOffset;

    private boolean mIsActionBarHidden;

    private View mSearchView;
    private boolean mIsExpanded;

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
//                case R.id.id_inStoreTool_myKohlsStoreLocatorLayout:
//                    showStoreLocator();
//                    break;
                
            }
        }
    };

	private GeoLocationHelper mGeoLocationHelper;

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
//        mFindNearByLayout = (LinearLayout) mActivity.findViewById(R.id.id_inStoreTool_myKohlsStoreLocatorLayout);
//        mActivity.findViewById(R.id.id_inStoreTool_myKohlsStoreLocatorLayout)
//                .setOnClickListener(mClickListener);
//        mActivity.findViewById(R.id.id_inStoreTool_myKohlsStoreLayout)
//                .setOnClickListener(mClickListener);
//        mActivity.findViewById(R.id.id_inStoreTool_myPriceCheckerImage)
//                .setOnClickListener(mClickListener);
//        mActivity.findViewById(R.id.id_inStoreTool_productReviewsImage)
//                .setOnClickListener(mClickListener);
//        mRecommendationProgressBar = (ProgressBar) mActivity.findViewById(R.id.id_inStoreTool_recommendationProgressBar);
//        mRecommendedGridView = (GridView) mActivity.findViewById(R.id.id_inStoreTool_recommendedGridView);
//        mActivity.findViewById(R.id.id_inStoreTool_myListsImage).setOnClickListener(mClickListener);
//        mRecommendationsLayout = (LinearLayout) mActivity.findViewById(R.id.id_inStoreTool_recommendationsLayout);
//        mBottomBarDrawer.setDrawerListener(this);
//        mProgressBar = (LinearLayout) mActivity.findViewById(R.id.id_inStoreTool_store_layout_progress);
//        mWelcomeHeader = (TextView) mActivity.findViewById(R.id.id_inStoreTool_inStore_welcome);
//        mWelcomeMessage = (TextView) mActivity.findViewById(R.id.id_inStoreTool_inStore_welcome_message);
//        setUserStore();
//        /*
//         * if (mRecommendationsVO == null) { getRecommendations(); }
//         */
        mGeoLocationHelper = new GeoLocationHelper(new WeakReference<Activity>(mActivity), geoLocationListner);
//        mGeoLocationHelper.initializeComponent();
        mActionBarAnimOffset = mActionBarHeight + (int) UtilityMethods.convertDpToPixel(50,
                mActivity);
        mActionBarAnimOffset = mBottomBarDrawer.getExpandedHeightofDrawer() - mActionBarAnimOffset;
        mBottomBarDrawer.show();
        mBottomBarDrawer.expand();
        mIsExpanded = true;
    }

    
    /**
     * Disable Sliding layout.
     */
    public void disableSlidingLayout() {
        mBottomBarDrawer.hide();
        mIsExpanded = false;
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

    


   
    

    private IGeoLocation geoLocationListner = new IGeoLocation() {

        @Override
        public void onConnectionFailed() {
            if (mActivity != null && !mActivity.isFinishing()) {
               
                mRecommendationProgressBar.setVisibility(View.GONE);
            }
        }

        @Override
        public void onConnected() {
            
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

    public boolean isSliderExpanded() {
        return mIsExpanded;
    }
    

}
