
package islamic.buzz.helpers;

import com.eybsolution.islamic.buzz.R;

import android.app.ActionBar;
import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Action bar helper for displaying action bar in the activity & it also handles
 * all the events on the action bar items.
 */
public class ActionBarHelper {
	
	public static final int ACTION_ITEM_WEBVIEW_ACTIVITY = 7;
	public static final int ACTION_ITEM_ERROR_ACTIVITY = 8;

    // Action bar of the activity.
    private ActionBar mActionBar;

    // Activity on which action bar to be attached.
    private Activity mActivity;

    // List icon of the action bar.
    private ImageView mListImageView;

    // Action bar title text view.
    private TextView mTitleTextView;

    // Action bar action item image view
    private ImageView mItemView;

    // Action bar extra action
    private ImageView mExtraItemView;

    // Action bar app image.
    private ImageView mAppImageView;

    private LinearLayout mListIconLayout;

    private FrameLayout mImageandText;

    public static final int ACTION_ITEM_SHOPPING_BAG = 1;


    private TextView mItemViewCount;

    public ActionBarHelper(Activity activity,
            ActionBar actionBar) {
        mActivity = activity;
        mActionBar = actionBar;
    }

    public void initActionBarWithCustomView() {
        mActionBar.setIcon(R.drawable.list_icon);
//        mActionBar.setCustomView(R.layout.main_activity_actionbar);
//        View customView = mActionBar.getCustomView();
//        mListImageView = (ImageView) customView.findViewById(R.id.id_actionBar_list_icon);
//        mTitleTextView = (TextView) customView.findViewById(R.id.id_actionBar_title_text);
//        mTitleTextView.setTypeface(FontUtils.loadTypeFace(mActivity, FontUtils.Gotham_Book));
//        mItemView = (ImageView) customView.findViewById(R.id.id_actionBar_item_image);
//        mExtraItemView = (ImageView) customView.findViewById(R.id.id_actionBar_extra_image);
//        mItemViewCount = (TextView) customView.findViewById(R.id.id_actionBar_item_image_count);
//        mAppImageView = (ImageView) customView.findViewById(R.id.id_actionBar_app_image);
//        mListIconLayout = (LinearLayout) customView.findViewById(R.id.id_actionBar_list_layout);
//        mImageandText = (FrameLayout) customView.findViewById(R.id.id_actionBar_mainAction);
    }

    public LinearLayout getBackButtonView() {
        return mListIconLayout;
    }

    public ImageView getAppImageView() {
        return mAppImageView;
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public TextView getItemViewCountView() {
        return mItemViewCount;
    }
    
    public void showActionBarForErrorActivity() {
        showActionBarForSlider("", ACTION_ITEM_ERROR_ACTIVITY, false, true, true, 0);
        mImageandText.setVisibility(View.GONE);
    }

    /**
     * Shows action bar for slider.
     * 
     * @param actionBarTitle title of the action bar.
     * @param actionItem action item of the action bar.
     */
    private void showActionBarForSlider(String actionBarTitle,
            final int actionItem,
            boolean isShowTitle,
            boolean isShowAppIcon,
            final boolean isHomeUpEnabled,
            int listIconDrawableId) {
    	
    }

    private void updateRightIcon() {
        if (mItemView.getVisibility() == View.VISIBLE) {
        	
        }
    }

   
    public void showActionBarForWebViewActivity() {
        showActionBarForSlider("",
                ACTION_ITEM_WEBVIEW_ACTIVITY,
                false,
                true,
                true,
                R.drawable.back_arrow);
        // Hide shopping bag icon
        mImageandText.setVisibility(View.GONE);
    }
    
    /**
     * Makes the shopping bag icon on the right hand side on the action bar
     * visible.
     */
    public void showShoppinBagIcon() {
       // mItemView.setVisibility(View.VISIBLE);
    }

    public void hideShoppingBagCount() {
        mItemViewCount.setVisibility(View.GONE);
    }

    public void showShoppingBagCount() {
        //mItemViewCount.setVisibility(View.VISIBLE);
    }


    public View getItemView() {
        return mItemView;
    }

    public View getExtraItemView() {
        return mExtraItemView;
    }

   

}
