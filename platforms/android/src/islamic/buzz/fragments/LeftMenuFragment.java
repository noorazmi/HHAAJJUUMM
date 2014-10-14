package islamic.buzz.fragments;

import islamic.buzz.activities.HomeActivity;
import islamic.buzz.interfaces.type.ControllerFactory;
import islamic.buzz.interfaces.type.ICategoryController;
import islamic.buzz.po.MenuCategory;
import islamic.buzz.view.adapter.LeftMenuListAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.eybsolution.islamic.buzz.R;

public class LeftMenuFragment extends BaseFragment implements OnItemClickListener, Handler.Callback {

	public static final String TAG = LeftMenuFragment.class.getName();

	private LeftMenuListAdapter mDrawerAdapter;

	private MenuCategory mSelectedCategory;

	private ICategoryController mCategoryController;

	private ListView mDrawerList;

	@Override
	protected void initializeController() {
		mCategoryController = ControllerFactory.getCategoryController(getActivity(), new WeakReference<LeftMenuFragment>(this));
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

		mDrawerAdapter = new LeftMenuListAdapter(getActivity(), R.id.id_navigationDrawer_menuItemTxt, mCategoryController.getRootCategory());
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
			handleDataSetChanged(mCategoryController.getCategoriesForMenu(mSelectedCategory, null, (ArrayList<MenuCategory>) object));
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
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		
		mSelectedCategory = mDrawerAdapter.getListofCategories().get(position);
		
		if (mSelectedCategory.getCatCode() == MenuCategory.CODE_BUZZ_LEVEL) {
			//((HomeActivity) getActivity()).getSlidingViewHelper().disableSlidingLayout();
			((HomeActivity) getActivity()).attachHomeFragment(null, true);
		} else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_HAJJ_LEVEL) {
			((HomeActivity) getActivity()).attachHajjFragment(null);
			// ((HomeActivity) getActivity()).hideSlider();
		} else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_UMRAH_LEVEL) {
			((HomeActivity) getActivity()).attachUmrahFragment(null);
		} else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_ABOUT_LEVEL) {
			((HomeActivity) getActivity()).attachAboutFragment(null);
			((HomeActivity) getActivity()).hideSlider();
		}

		
	}

	@Override
	public boolean onBackPress() {
		return false;
	}

}
