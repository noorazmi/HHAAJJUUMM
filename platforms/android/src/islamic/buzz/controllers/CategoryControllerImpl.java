package islamic.buzz.controllers;

import islamic.buzz.fragments.LeftMenuFragment;
import islamic.buzz.interfaces.type.ICategoryController;
import islamic.buzz.po.MenuCategory;
import islamic.buzz.util.UtilityMethods;
import islamic.buzz.vo.IValueObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.content.Context;

/**
 * CategoryController interacts with view via Handler. All the business logic of
 * getting and handling Category items is to be handled here. For a type
 * MenuCategory - it will give us the parent Category, the child Categories
 * after fetching the results from server. - It will also handle item click,
 * based on the category code it will determine what data needs to be delivered
 */
public class CategoryControllerImpl extends BaseControllerImpl implements ICategoryController {

	// This data is needed since CategoryController is called for a particular
	// Clicked Category. We are maintaining this so that when we return back the
	// result categories to View, View can compare the results with the category
	// it has requested from Controller and determine whether the results has
	// been delivered for the correct Category
	private MenuCategory clickedCat;

	// Context is maintained as it is needed to get the JSON data from assets
	private Context mContext;

	private WeakReference<LeftMenuFragment> mFragment;

	public CategoryControllerImpl(Context context, WeakReference<LeftMenuFragment> fragment) {
		mContext = context;
		mFragment = fragment;
	}

	/**
	 * Utility method to get the order of menu items According to wireframes -
	 * menuitem comprises of : 1) All Parent categories 2) Self Category 3) All
	 * Child Categories
	 * 
	 * @param mainCat
	 * @param parentCat
	 *            - If passed, then return the same in the order of items in
	 *            menu
	 * @param childCat
	 *            - If passed, then return the same in the order of items in
	 *            menu
	 * @return
	 */
	@Override
	public ArrayList<MenuCategory> getCategoriesForMenu(MenuCategory mainCat, ArrayList<MenuCategory> parentCat, ArrayList<MenuCategory> childCat) {
		return null;
	}

	/**
	 * Get the category for which the data is being fetched
	 * 
	 * @return
	 */
	@Override
	public MenuCategory getSelectedCategory() {
		return clickedCat;
	}

	/**
	 * For the home page, the application starts off with the Root category and
	 * sub categorues
	 * 
	 * @return
	 */
	@Override
	public ArrayList<MenuCategory> getRootCategory() {
		return getCategoryData();
	}

	/**
	 * Request to get data based on the Resource Path and Code
	 * 
	 * @param clickedCat
	 * @return
	 */
	private ArrayList<MenuCategory> getCategoryData() {
		final ArrayList<MenuCategory> mainCategories = new ArrayList<MenuCategory>();
		String response = UtilityMethods.loadJSONFromAsset(mContext, "category");
		MenuCategory mCategory = (MenuCategory) UtilityMethods.getModelFromJsonString(response, MenuCategory.class);
		mainCategories.addAll(mCategory.getCategories());
		return mainCategories;

	}

	@Override
	public void onSuccess(IValueObject valueObject) {
	}

	@Override
	public void onFailure(Error ex) {
	}

	@Override
	public Error getPayloadError(IValueObject payload) {
		return null;
	}

	@Override
	public long getCacheHeader(IValueObject payload) {
		return 0;
	}

}
