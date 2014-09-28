
package islamic.buzz.fragments;

import islamic.buzz.activities.BaseActivityWithSlider;
import islamic.buzz.helpers.PreferenceHelper;
import islamic.buzz.interfaces.type.ControllerFactory;
import islamic.buzz.interfaces.type.ICategoryController;
import islamic.buzz.po.MenuCategory;
import islamic.buzz.util.ConstantValues;
import islamic.buzz.util.UtilityMethods;
import islamic.buzz.view.adapter.LeftMenuListAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.eybsolution.islamic.buzz.R;

public class LeftMenuFragment extends BaseFragment implements
        OnItemClickListener,
        Handler.Callback {

    public static final String TAG = LeftMenuFragment.class.getName();

    private LeftMenuListAdapter mDrawerAdapter;

    private MenuCategory mSelectedCategory;

    private ICategoryController mCategoryController;

    private ListView mDrawerList;

    private boolean mIsPMPInitialized;

    private String mParrentCategory = "";

    @Override
    protected void initializeController() {
//        mCategoryController = ControllerFactory.getCategoryController(getActivity(),
//                new WeakReference<LeftMenuFragment>(this));
    }

    @Override
    protected void initializeAttributes() {
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {

        mDrawerList = (ListView) getActivity().findViewById(R.id.id_homeActivity_nav_menu_list_view);

    }

    @Override
    protected void loadContent() {

        mDrawerAdapter = new LeftMenuListAdapter(getActivity(),
                R.id.id_navigationDrawer_menuItemTxt,
                mCategoryController.getRootCategory());
        // Set the adapter for the list view
        mDrawerList.setAdapter(mDrawerAdapter);
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(this);

    }

    @Override
    protected int intializaLayoutId() {
        return R.layout.menu_list;
    }

    private void handleDataSetChanged(final ArrayList<MenuCategory> categoryList) {

        mDrawerAdapter.setListofCategories(categoryList);
        mDrawerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void updateViewsOnSuccess(Object object) {
        if (mCategoryController.getSelectedCategory() == mSelectedCategory) {
            handleDataSetChanged(mCategoryController.getCategoriesForMenu(mSelectedCategory,
                    null,
                    (ArrayList<MenuCategory>) object));
        }
    }

    @Override
    public void updateViewsOnFailure(Object object) {
        // ErrorHelper.isApplicationManagable((com.kohlsphone.helper.error.Error)
        // object);

        // if (mCategoryController.getSelectedCategory() == mSelectedCategory) {
        // Toast.makeText(getActivity(), "Faailure Downloading Categories",
        // Toast.LENGTH_LONG)
        // .show();
        // }
    }

    @Override
    public boolean handleMessage(Message arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView,
            View view,
            int position,
            long id) {
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.id_navigationDrawer_progress);

        progressBar.setVisibility(View.VISIBLE);
        ((HomeActivity) getActivity()).getActionBarHelper().showShoppinBagIcon();
        mSelectedCategory = mDrawerAdapter.getListofCategories().get(position);
        ((HomeActivity) getActivity()).getActionBarHelper().showShoppingBagCount();
        // If selected Category is Root Category directly update the list
        if (mSelectedCategory != null && mSelectedCategory.getCatCode() == MenuCategory.CODE_ROOT_LEVEL) {
            ((HomeActivity) getActivity()).getSlidingViewHelper().disableSlidingLayout();
            // On Click to go back to the Root categories, we need to update the
            // action bar
            ((BaseActivityWithSlider) getActivity()).getActionBarHelper()
                    .showActionBarWithShoppingBag(null);
            // On top item click(goto Home screen). Also clear backstack so when
            // user taps on back button, application is closed

            ((HomeActivity) getActivity()).attachHomeFragment(null, true);
            handleDataSetChanged(mCategoryController.getRootCategory());

            loadContent();
            // handleDataSetChanged(mCategoryController.getRootCategory());

        } else {
            // If selected Category is not root level and it has a child item -
            // we make request to get the items
            if (mSelectedCategory != null && mSelectedCategory.isHasChild()) {
                mParrentCategory = mSelectedCategory.getName();
                mCategoryController.getCategoriesForMenu(mSelectedCategory, null, null);
            } else {
                // If selected Category does not have child item - close the
                // slider and update the list to show the selected item
                if (mSelectedCategory != null) {
                    handleDataSetChanged(mCategoryController.getCategoriesForMenu(mSelectedCategory,
                            null,
                            null));
                }
            }
        }

        // Check if clicked item need to open PMP screen or not
        if (mSelectedCategory.getResourcePath()
                .contains(getResources().getString(R.string.prefixToRemove))) {
            // Extract Dimin String from resource path
            String diminString = mSelectedCategory.getResourcePath()
                    .substring(mSelectedCategory.getResourcePath().lastIndexOf("/") + 1);
            Bundle args = new Bundle();
            args.putString(ConstantValues.EXTRA_KEY_PARRENT_CATEGORY, mParrentCategory);
            args.putString(ConstantValues.EXTRA_KEY_PAGE_TITLE, mSelectedCategory.getName());
            args.putString(ConstantValues.EXTRA_KEY_CATEGORY_ID, diminString);
            ((HomeActivity) getActivity()).attachProductMatrixFragment(((HomeActivity) getActivity()).getFragmentOnScreen(),
                    args);
        }

        // Specific for feature brands.
        if (mSelectedCategory.getCatCode() == MenuCategory.CODE_FEATUREBRAND_CHILD_LEVEL) {

            String resoursepath = mSelectedCategory.getResourcePath();
            String name = mSelectedCategory.getName();
            Bundle bundle = new Bundle();
            bundle.putString(ConstantValues.EXTRA_KEY_PAGE_TITLE, name);
            if (resoursepath.contains(ConstantValues.KOHLS_URL)) {
                bundle.putString(ConstantValues.EXTRA_KEY_URL, resoursepath);
                ((HomeActivity) getActivity()).attachFeatureBrandWebFragment(((HomeActivity) getActivity()).getFragmentOnScreen(),
                        bundle);
            } else {
                bundle.putString(ConstantValues.EXTRA_KEY_CATEGORY_ID, resoursepath);
                bundle.putBoolean(ConstantValues.EXTRA_KEY_IS_FOR_FEATURED_BRAND, true);
                ((HomeActivity) getActivity()).attachProductMatrixFragment(((HomeActivity) getActivity()).getFragmentOnScreen(),
                        bundle);
            }
        }

        if (mSelectedCategory != null && !mSelectedCategory.isHasChild()) {
            ((HomeActivity) getActivity()).getSlidingViewHelper().disableSlidingLayout();
            if (mSelectedCategory.getCatCode() == MenuCategory.CODE_STORE_LEVEL) {
                ((HomeActivity) getActivity()).attachStoreLocatorFragment(null);

            } else if (mSelectedCategory != null && mSelectedCategory.getCatCode() == MenuCategory.CODE_ACCOUNT_LEVEL) {
                PreferenceHelper kohlsPreference = KohlsPhoneApplication.getInstance()
                        .getKohlsPref();
                // if(AuthUtil.getInstance(getActivity()).isUserSignedIn()){
                if (!TextUtils.isEmpty(kohlsPreference.getUserMailId())) {
                    // ((HomeActivity)
                    // getActivity()).getActionBarHelper().hideShoppinBagIcon();
                    // ((HomeActivity)
                    // getActivity()).getActionBarHelper().hideShoppingBagCount();
                    attachAccountFragment();
                } else {
                    UtilityMethods.openAccountActivity((getActivity()), true);
                }
            } else if (mSelectedCategory != null && mSelectedCategory.getCatCode() == MenuCategory.CODE_ORDERSTATUS_LEVEL) {
                UtilityMethods.openAccountActivityForGuest((getActivity()));

            } else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_HELP_LEVEL) {
                ((HomeActivity) getActivity()).attachHelpAndContactUsFragment(null);
                // ((HomeActivity) getActivity()).hideSlider();
            } else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_LIST_LEVEL) {
                ((HomeActivity) getActivity()).attachListFragment(null);
            } else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_REGISTRY_LEVEL) {
                ((HomeActivity) getActivity()).attachRegistryFragment(null);
            } else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_INSTORE_LEVEL) {
                ((HomeActivity) getActivity()).getSlidingViewHelper().enableSlidingLayout();
            }
            ((HomeActivity) getActivity()).hideSlider();
        }
    }

   

    @Override
    public boolean onBackPress() {
        // TODO Auto-generated method stub
        return false;
    }

}
