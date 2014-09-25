
package islamic.buzz.activities;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingMenu.OnOpenListener;
import com.jeremyfeinstein.slidingmenu.lib.app.WindowUtils;
import com.kohls.analytics.CaptureAnalytics;
import com.kohls.analytics.objects.datatypes.PageNames;
import com.kohls.analytics.objects.datatypes.PageType;
import com.kohls.analytics.objects.models.AnalyticsObject;
import com.kohls.analytics.utils.AnalyticsConstants;
import com.kohlsphone.R;
import com.kohlsphone.common.app.KohlsPhoneApplication;
import com.kohlsphone.common.ui.components.SlidingUpViewHelper;
import com.kohlsphone.common.util.UtilityMethods;
import com.kohlsphone.framework.view.component.views.BottomBarDrawer;
import com.kohlsphone.framework.view.fragment.HamburgerListFragment;
import com.kohlsphone.helper.actionbar.ActionBarHelper;
import com.kohlsphone.helper.error.UnCaughtException;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * This is the base class for all activities
 */
public abstract class BaseActivityWithSlider extends SlidingActivity {

    // Action bar height as it is dependent on activity, it is being kept as
    // static so that it can be accessed by any fragment or adapter.
    private int mActionBarHeight;

    private ActionBarHelper mActionBarHelper;

    private SlidingUpViewHelper mSlidingViewHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(BaseActivityWithSlider.this));

        setContentView(R.layout.content_frame);
        initActionBar();
        initLeftSlidMenu();
        initContent();
        mActionBarHeight = UtilityMethods.getActionBarHeight(this);
        mSlidingViewHelper = new SlidingUpViewHelper(((BottomBarDrawer) findViewById(R.id.id_bottomBarDrawer)),
                this,
                getActionBarHeight());

        mSlidingViewHelper.initSlidingUpLayout();

        UtilityMethods.clearEditTextViewFocusOnOutsideTouch(getWindow().getDecorView(), this);
        // Enable logging in crash reporting
        KohlsPhoneApplication.getInstance().getCrashReporting().enableLogging();

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateActionBarViews();
    }

    public abstract void initContent();

    protected abstract void updateActionBarViews();

    protected void initActionBar() {
        mActionBarHelper = new ActionBarHelper(this, getActionBar());
        mActionBarHelper.initActionBarWithCustomView();
    }

    public ActionBarHelper getActionBarHelper() {
        return mActionBarHelper;
    }

    private void initLeftSlidMenu() {
        FrameLayout left = new FrameLayout(this);
        left.setId("LEFT".hashCode());
        setBehindContentView(left);
        setSlidingActionBarEnabled(true);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        HamburgerListFragment menuFragment = new HamburgerListFragment();
        fragmentTransaction.replace("LEFT".hashCode(), menuFragment, HamburgerListFragment.TAG);
        fragmentTransaction.commit();
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setShadowWidth(30);
        setSlidingMenuOffset(true);
        slidingMenu.setFadeDegree(0.9f);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // Hide keyboard on showing Side pannel
        slidingMenu.setOnOpenListener(new OnOpenListener() {
            @Override
            public void onOpen() {
                sendAnalyticsOnLoad();
                UtilityMethods.hideKeyboard(BaseActivityWithSlider.this,
                        mActionBarHelper.getItemView());
            }
        });
    }

    private void setSlidingMenuOffset(boolean left) {
        int orientation = this.getResources().getConfiguration().orientation;
        int offset = orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 3;
        getSlidingMenu().setBehindOffset(WindowUtils.getDisplayMetrics(this).widthPixels / offset
                - 10);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        UtilityMethods.getFlushTimes();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getSlidingMenu().isSlidingEnabled()) {
                    UtilityMethods.hideKeyboard(this, getWindow().getDecorView());
                    toggle();
                } else {
                    onBackPressed();
                }
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideSlider() {
        toggle();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * @return action bar height.
     */
    public int getActionBarHeight() {
        return mActionBarHeight;
    }

    /**
     * @return Sliding view helper
     */
    public SlidingUpViewHelper getSlidingViewHelper() {
        return mSlidingViewHelper;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        KohlsPhoneApplication.getInstance().getAuthenticationUtils().resetIdleTimer();
        return super.dispatchTouchEvent(ev);
    }

}
