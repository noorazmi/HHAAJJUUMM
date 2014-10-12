package islamic.buzz.fragments;

import islamic.buzz.activities.BaseActivityWithSlider;
import islamic.buzz.activities.HomeActivity;
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
		OnItemClickListener, Handler.Callback {

	public static final String TAG = LeftMenuFragment.class.getName();

	private LeftMenuListAdapter mDrawerAdapter;

	private MenuCategory mSelectedCategory;

	private ICategoryController mCategoryController;

	private ListView mDrawerList;

	private boolean mIsPMPInitialized;

	private String mParrentCategory = "";

	@Override
	protected void initializeController() {
		 mCategoryController =
		 ControllerFactory.getCategoryController(getActivity(),
		 new WeakReference<LeftMenuFragment>(this));
	}

	@Override
	protected void initializeAttributes() {
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {

		mDrawerList = (ListView) getActivity().findViewById(
				R.id.id_homeActivity_nav_menu_list_view);

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
			handleDataSetChanged(mCategoryController.getCategoriesForMenu(
					mSelectedCategory, null, (ArrayList<MenuCategory>) object));
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
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		ProgressBar progressBar = (ProgressBar) view
				.findViewById(R.id.id_navigationDrawer_progress);

		progressBar.setVisibility(View.VISIBLE);
		mSelectedCategory = mDrawerAdapter.getListofCategories().get(position);
		// If selected Category is Root Category directly update the list
		if (mSelectedCategory != null
				&& mSelectedCategory.getCatCode() == MenuCategory.CODE_ROOT_LEVEL) {
			((HomeActivity) getActivity()).getSlidingViewHelper()
					.disableSlidingLayout();
			// On Click to go back to the Root categories, we need to update the
			// action bar
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
				mCategoryController.getCategoriesForMenu(mSelectedCategory,
						null, null);
			} else {
				// If selected Category does not have child item - close the
				// slider and update the list to show the selected item
				if (mSelectedCategory != null) {
					handleDataSetChanged(mCategoryController
							.getCategoriesForMenu(mSelectedCategory, null, null));
				}
			}
		}
		
		if (mSelectedCategory != null && !mSelectedCategory.isHasChild()) {
			((HomeActivity) getActivity()).getSlidingViewHelper()
					.disableSlidingLayout();
			if (mSelectedCategory.getCatCode() == MenuCategory.CODE_HAJJ_LEVEL) {
				((HomeActivity) getActivity())
						.attachHajjFragment(null);
				// ((HomeActivity) getActivity()).hideSlider();
			} else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_UMRAH_LEVEL) {
				((HomeActivity) getActivity()).attachUmrahFragment(null);
			} else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_ABOUT_LEVEL) {
				((HomeActivity) getActivity()).attachAboutFragment(null);
				((HomeActivity) getActivity()).hideSlider();
			}
		}
	}

	@Override
	public boolean onBackPress() {
		return false;
	}

}
