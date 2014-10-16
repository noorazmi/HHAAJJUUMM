package islamic.buzz.fragments;

import islamic.buzz.activities.HomeActivity;
import islamic.buzz.interfaces.type.ControllerFactory;
import islamic.buzz.interfaces.type.ICategoryController;
import islamic.buzz.po.MenuCategory;
import islamic.buzz.view.adapter.LeftMenuListAdapter;

import java.lang.ref.WeakReference;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.eybsolution.islamic.buzz.R;

public class LeftMenuFragment extends BaseFragment implements OnItemClickListener {

	public static final String TAG = LeftMenuFragment.class.getName();
	private LeftMenuListAdapter mDrawerAdapter;
	private MenuCategory mSelectedCategory;
	private ICategoryController mCategoryController;
	private ListView mDrawerList;

	@Override
	protected void initController() {
		mCategoryController = ControllerFactory.getCategoryController(getActivity(), new WeakReference<LeftMenuFragment>(this));
	}

	@Override
	protected void initFields(Bundle savedInstanceState) {

		mDrawerList = (ListView) getFragmentView().findViewById(R.id.id_homeActivity_nav_menu_list_view);
		mDrawerAdapter = new LeftMenuListAdapter(getActivity(), R.id.id_navigationDrawer_menuItemTxt, mCategoryController.getRootCategory());
		// Set the adapter for the list view
		mDrawerList.setAdapter(mDrawerAdapter);
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(this);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.menu_list;
	}

	@Override
	public void onClick(View v) {}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

		mSelectedCategory = mDrawerAdapter.getListofCategories().get(position);

		if (mSelectedCategory.getCatCode() == MenuCategory.CODE_BUZZ_LEVEL) {
			((HomeActivity) getActivity()).attachHomeFragment(null);
		} else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_HAJJ_LEVEL) {
			((HomeActivity) getActivity()).attachHajjFragment(null);
		} else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_UMRAH_LEVEL) {
			((HomeActivity) getActivity()).attachUmrahFragment(null);
		} else if (mSelectedCategory.getCatCode() == MenuCategory.CODE_ABOUT_LEVEL) {
			((HomeActivity) getActivity()).attachAboutFragment(null);
		}

		((HomeActivity) getActivity()).hideSlider();
	}

	@Override
	public boolean onBackPress() {
		return false;
	}

}
