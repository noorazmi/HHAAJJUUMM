
package islamic.buzz.activities;

import com.kohlsphone.R;
import com.kohlsphone.common.app.KohlsPhoneApplication;
import com.kohlsphone.common.ui.components.SlidingUpViewHelper;
import com.kohlsphone.common.util.UtilityMethods;
import com.kohlsphone.framework.view.component.views.BottomBarDrawer;
import com.kohlsphone.helper.actionbar.ActionBarHelper;
import com.kohlsphone.helper.error.UnCaughtException;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public abstract class BaseActivityWithoutSlider extends Activity {

    private ActionBarHelper mActionBarHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(BaseActivityWithoutSlider.this));

        setContentView(R.layout.content_frame);
        FrameLayout contentLayout = (FrameLayout) findViewById(R.id.content_detail);
        View contentView = getLayoutInflater().inflate(getLayoutId(), null);
        contentLayout.addView(contentView);
        initActionBar();
        SlidingUpViewHelper slidingHelper = new SlidingUpViewHelper(((BottomBarDrawer) findViewById(R.id.id_bottomBarDrawer)),
                this,
                UtilityMethods.getActionBarHeight(this));

        slidingHelper.initSlidingUpLayout();
        initializeViews(getIntent().getExtras());
        // Enable logging in crash reporting
        KohlsPhoneApplication.getInstance().getCrashReporting().enableLogging();
    }

    protected abstract void initializeViews(Bundle bundle);

    protected abstract int getLayoutId();

    protected void initActionBar() {
        mActionBarHelper = new ActionBarHelper(this, getActionBar());
        mActionBarHelper.initActionBarWithCustomView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateActionBarViews();
    }

    /**
     * It is called from onStart of the {@link Activity} to update action bar
     * views.
     */
    protected abstract void updateActionBarViews();

    public void updateActivityOnSuccess(final Object object) {
        if (!isFinishing() || !isDestroyed()) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    updateViewsOnSuccess(object);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        UtilityMethods.getFlushTimes();
        super.onResume();

    }

    public abstract void updateViewsOnSuccess(Object object);

    public abstract void updateViewsOnFailure(Object object);

    public void updateActivityOnFailure(final Object object) {
        if (!isFinishing()) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    updateViewsOnFailure(object);
                }
            });
        }
    }

    public ActionBarHelper getActionBarHelper() {
        return mActionBarHelper;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        KohlsPhoneApplication.getInstance().getAuthenticationUtils().resetIdleTimer();
        return super.dispatchTouchEvent(ev);
    }
}
