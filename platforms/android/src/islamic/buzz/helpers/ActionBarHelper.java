
package islamic.buzz.helpers;

import com.kohlsphone.R;
import com.kohlsphone.common.app.KohlsPhoneApplication;
import com.kohlsphone.common.util.FontUtils;
import com.kohlsphone.common.util.UtilityMethods;
import com.kohlsphone.common.value.ConstantValues;
import com.kohlsphone.framework.view.activity.AccountActivity;
import com.kohlsphone.framework.view.activity.BaseActivityWithSlider;
import com.kohlsphone.framework.view.activity.UpdateAccountActivity;
import com.kohlsphone.framework.view.fragment.FragmentFactory;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Action bar helper for displaying action bar in the activity & it also handles
 * all the events on the action bar items.
 */
public class ActionBarHelper {

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

    public static final int ACTION_ITEM_LIST = 2;

    public static final int ACTION_ITEM_MAP = 3;

    public static final int ACTION_ITEM_DETAIL = 4;

    public static final int ACTION_ITEM_PRODUCT_DETAIL = 5;

    public static final int ACTION_ITEM_SIGNIN_NEWPROFILE = 6;

    public static final int ACTION_ITEM_WEBVIEW_ACTIVITY = 7;

    public static final int ACTION_ITEM_ERROR_ACTIVITY = 8;

    public static final int ACTION_ITEM_EDIT_ITEM = 9;

    public static final int ACTION_ITEM_RATING_REVIEW = 10;

    public static final int ACTION_ITEM_UPDATE_ACCOUNT = 11;

    public static final int ACTION_ITEM_EULA = 12;

    // Shopping bag item text count on the top of the shopping bag

    private TextView mItemViewCount;

    public ActionBarHelper(Activity activity,
            ActionBar actionBar) {
        mActivity = activity;
        mActionBar = actionBar;
    }

    public void initActionBarWithCustomView() {
        mActionBar.setIcon(R.drawable.list_icon);
        mActionBar.setCustomView(R.layout.main_activity_actionbar);
        View customView = mActionBar.getCustomView();
        mListImageView = (ImageView) customView.findViewById(R.id.id_actionBar_list_icon);
        mTitleTextView = (TextView) customView.findViewById(R.id.id_actionBar_title_text);
        mTitleTextView.setTypeface(FontUtils.loadTypeFace(mActivity, FontUtils.Gotham_Book));
        mItemView = (ImageView) customView.findViewById(R.id.id_actionBar_item_image);
        mExtraItemView = (ImageView) customView.findViewById(R.id.id_actionBar_extra_image);
        mItemViewCount = (TextView) customView.findViewById(R.id.id_actionBar_item_image_count);
        mAppImageView = (ImageView) customView.findViewById(R.id.id_actionBar_app_image);
        mListIconLayout = (LinearLayout) customView.findViewById(R.id.id_actionBar_list_layout);
        mImageandText = (FrameLayout) customView.findViewById(R.id.id_actionBar_mainAction);
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
        if (isShowTitle && !TextUtils.isEmpty(actionBarTitle)) {
            mTitleTextView.setVisibility(View.VISIBLE);
            mTitleTextView.setText(actionBarTitle);
            // Handles list icon click & it toggles slider of the home screen.
            mTitleTextView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (actionItem == ACTION_ITEM_DETAIL) {
                        if (mActivity instanceof AccountActivity) {
                            finishAccountActitiy();
                            return;
                        }
                        mActivity.finish();

                    } else if (actionItem == ACTION_ITEM_UPDATE_ACCOUNT) {
                        if (mActivity instanceof UpdateAccountActivity) {
                            Fragment fragment = mActivity.getFragmentManager()
                                    .findFragmentByTag(ConstantValues.TAG_RESET_PASSWORD);
                            if (fragment != null) {
                                FragmentFactory.attachSignInfoFragment((UpdateAccountActivity) mActivity,
                                        null);
                            }
                        }
                    } else if (mActivity instanceof BaseActivityWithSlider) {
                        ((BaseActivityWithSlider) mActivity).toggle();
                    }
                }
            });
        } else {
            mTitleTextView.setVisibility(View.GONE);
        }
        if (isShowAppIcon) {
            mAppImageView.setVisibility(View.VISIBLE);
        } else {
            mAppImageView.setVisibility(View.GONE);
        }
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        if (listIconDrawableId != 0) {
            mListImageView.setImageDrawable(mActivity.getResources()
                    .getDrawable(listIconDrawableId));
            if (listIconDrawableId == R.drawable.list_icon) {
                mTitleTextView.setPadding(20, 0, 0, 0);
            } else {
                mTitleTextView.setPadding(0, 0, 0, 0);
            }
            // Handles list icon click & it toggles slider of the home screen.
            mListIconLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isHomeUpEnabled) {
                        if (mActivity instanceof AccountActivity) {
                            finishAccountActitiy();
                            return;
                        }
                        mActivity.finish();
                    } else if (mActivity instanceof BaseActivityWithSlider) {
                        UtilityMethods.hideKeyboard(mActivity, mListIconLayout);
                        ((BaseActivityWithSlider) mActivity).toggle();
                    }

                }
            });
        } else {
            mListImageView.setVisibility(View.GONE);
        }
        RelativeLayout.LayoutParams params;
        mImageandText.setVisibility(View.VISIBLE);
        mItemView.setVisibility(View.VISIBLE);
        switch (actionItem) {

            case ACTION_ITEM_EULA:
                mListIconLayout.setVisibility(View.GONE);
                mTitleTextView.setClickable(false);
                params = (RelativeLayout.LayoutParams) mTitleTextView.getLayoutParams();
                params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                mTitleTextView.setLayoutParams(params); // causes layout update
                break;

            case ACTION_ITEM_SHOPPING_BAG:
                mItemView.setImageDrawable(mActivity.getResources()
                        .getDrawable(R.drawable.shopping_bag));

                // When there is only 1 item in the action bar, the itemView is
                // Right aligned
                params = (RelativeLayout.LayoutParams) mImageandText.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                mImageandText.setLayoutParams(params); // causes layout update

                // The extra item from Action bar is removed
                mExtraItemView.setVisibility(View.GONE);
                break;
            case ACTION_ITEM_LIST:
                mItemView.setImageDrawable(mActivity.getResources()
                        .getDrawable(R.drawable.shopping_bag));
                params = (RelativeLayout.LayoutParams) mImageandText.getLayoutParams();
                mItemView.setVisibility(View.GONE);
                mImageandText.setVisibility(View.GONE);
                // When there are two items in the action bar, the right Item is
                // set to right aligned, while the left item is set to Left
                // property and Right align property is removed.
                mExtraItemView.setVisibility(View.VISIBLE);
                mExtraItemView.setImageDrawable(mActivity.getResources()
                        .getDrawable(R.drawable.list_view));
                mExtraItemView.setLayoutParams(params);
                break;
            case ACTION_ITEM_MAP:
                mItemView.setImageDrawable(mActivity.getResources()
                        .getDrawable(R.drawable.shopping_bag));

                params = (RelativeLayout.LayoutParams) mImageandText.getLayoutParams();
                mImageandText.setVisibility(View.GONE);
                mItemView.setVisibility(View.GONE);
                // When there are two items in the action bar, the right Item is
                // set to right aligned, while the left item is set to Left
                // property and Right align property is removed.
                mExtraItemView.setVisibility(View.VISIBLE);
                mExtraItemView.setImageDrawable(mActivity.getResources()
                        .getDrawable(R.drawable.map_view));
                mExtraItemView.setLayoutParams(params);
                break;
            case ACTION_ITEM_DETAIL:
                mImageandText.setVisibility(View.GONE);
                mExtraItemView.setVisibility(View.GONE);
                break;
            case ACTION_ITEM_PRODUCT_DETAIL:
                mItemView.setImageDrawable(mActivity.getResources()
                        .getDrawable(R.drawable.shopping_bag));
                params = (RelativeLayout.LayoutParams) mImageandText.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                mImageandText.setLayoutParams(params); // causes layout update
                mExtraItemView.setVisibility(View.GONE);
                break;
            case ACTION_ITEM_EDIT_ITEM:
                mItemView.setImageDrawable(mActivity.getResources()
                        .getDrawable(R.drawable.shopping_bag));

                // When there is only 1 item in the action bar, the itemView is
                // Right aligned
                params = (RelativeLayout.LayoutParams) mImageandText.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                mImageandText.setLayoutParams(params); // causes layout update

                // The extra item from Action bar is removed
                mExtraItemView.setVisibility(View.GONE);
                break;
        }
        updateRightIcon();

    }

    private void updateRightIcon() {
        if (mItemView.getVisibility() == View.VISIBLE) {
            int count = UtilityMethods.getShopingBagItemsCount();
            if (count > 0) {
                mItemView.setImageDrawable(mActivity.getResources()
                        .getDrawable(R.drawable.green_bag_icon));
                updateShopingBagCount();
            }
        }
    }

    /**
     * Hides the shopping bag icon on the right hand side on the action bar
     * along with the numbers on it.
     */
    public void hideShoppinBagIcon() {
        mItemView.setVisibility(View.GONE);
        hideShopingBagCount();
    }

    /**
     * Makes the shopping bag icon on the right hand side on the action bar
     * visible.
     */
    public void showShoppinBagIcon() {
        mItemView.setVisibility(View.VISIBLE);
    }

    public void hideShoppingBagCount() {
        mItemViewCount.setVisibility(View.GONE);
    }

    public void showShoppingBagCount() {
        mItemViewCount.setVisibility(View.VISIBLE);
    }

    public void showActionBarWithShoppingBag(String actionBarTitle) {
        showActionBarForSlider(actionBarTitle,
                ACTION_ITEM_SHOPPING_BAG,
                false,
                true,
                false,
                R.drawable.list_icon);
    }

    public void showActionBarWithList(String actionBarTitle) {
        showActionBarForSlider(actionBarTitle,
                ACTION_ITEM_LIST,
                true,
                false,
                false,
                R.drawable.list_icon);
    }

    public void showActionBarWithMap(String actionBarTitle) {
        showActionBarForSlider(actionBarTitle,
                ACTION_ITEM_MAP,
                true,
                false,
                false,
                R.drawable.list_icon);
    }

    public void showActionBarWithDetail(String actionBarTitle) {
        showActionBarForSlider(actionBarTitle,
                ACTION_ITEM_DETAIL,
                true,
                false,
                true,
                R.drawable.back_arrow);
    }

    public void showActionBarWithProductDetail() {
        showActionBarForSlider("",
                ACTION_ITEM_PRODUCT_DETAIL,
                false,
                true,
                true,
                R.drawable.back_arrow);
    }

    public void showActionBarWithEditItem() {
        showActionBarForSlider(mActivity.getResources().getString(R.string.edit_bag_item),
                ACTION_ITEM_EDIT_ITEM,
                true,
                true,
                true,
                R.drawable.back_arrow);
        mItemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });
    }

    public void showActionBarForUpdateAccount(String title) {
        showActionBarForSlider(title,
                ACTION_ITEM_UPDATE_ACCOUNT,
                true,
                false,
                true,
                R.drawable.back_arrow);
    }

    public void showActionBarForHybridScreens(String title) {
        showActionBarForSlider(title,
                ACTION_ITEM_SHOPPING_BAG,
                true,
                true,
                true,
                R.drawable.back_arrow);
    }

    public void showActionBarForRatingReviewScreens() {
        showActionBarForSlider("",
                ACTION_ITEM_RATING_REVIEW,
                true,
                false,
                true,
                R.drawable.back_arrow);
        hideShoppinBagIcon();
    }

    public void showActionBarForEULA(String title) {
        showActionBarForSlider(title, ACTION_ITEM_EULA, true, false, false, 0);
    }

    public View getItemView() {
        return mItemView;
    }

    public View getExtraItemView() {
        return mExtraItemView;
    }

    public void ShowActionBarWithBackArrowForClosingAccountActivity(String actionBarTitle) {
        showActionBarForSlider(actionBarTitle,
                ACTION_ITEM_SIGNIN_NEWPROFILE,
                true,
                false,
                true,
                R.drawable.back_arrow);
    }

    public void updateShopingBagCount() {
        updateShopingBagCountAndVisibility(true);
    }

    public void updateShopingBagCountAndVisibility(boolean isBagCountVisible) {
        int count = UtilityMethods.getShopingBagItemsCount();
        if (count > 0) {
            mItemView.setImageDrawable(mActivity.getResources()
                    .getDrawable(R.drawable.green_bag_icon));
            mItemViewCount.setText(String.valueOf(count));
            if (isBagCountVisible) {
                mItemViewCount.setVisibility(View.VISIBLE);
            }
        } else {
            mItemView.setImageDrawable(mActivity.getResources()
                    .getDrawable(R.drawable.shopping_bag));
            if (isBagCountVisible) {
                mItemViewCount.setVisibility(View.GONE);
            }
        }
    }

    public void hideShopingBagCount() {
        mItemViewCount.setVisibility(View.GONE);
    }

    public void showShopingBagCount() {
        mItemViewCount.setVisibility(View.VISIBLE);
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

    public void showActionBarForErrorActivity() {
        showActionBarForSlider("", ACTION_ITEM_ERROR_ACTIVITY, false, true, true, 0);
        // Hide shopping bag icon
        hideShopingBagCount();
        mImageandText.setVisibility(View.GONE);
    }

    private void finishAccountActitiy() {
        // Intent intent = new Intent();
        // intent.putExtra(ConstantValues.ATTACH_MANAGE_ACCOUNT_FRAGMENT,
        // ConstantValues.ATTACH_MANAGE_ACCOUNT_FRAGMENT);
        // mActivity.setResult(Activity.RESULT_OK, intent);
        // mActivity.finish();

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (KohlsPhoneApplication.getInstance().getAuthenticationUtils().isUserSignedIn()) {
            intent.putExtra(ConstantValues.ATTACH_MANAGE_ACCOUNT_FRAGMENT,
                    ConstantValues.ATTACH_MANAGE_ACCOUNT_FRAGMENT);
        } else {
            intent.putExtra(ConstantValues.CLOSE_ACTIVITY_ONLY, ConstantValues.CLOSE_ACTIVITY_ONLY);
        }
        mActivity.setResult(Activity.RESULT_OK, intent);
        mActivity.finish();
    }
}
