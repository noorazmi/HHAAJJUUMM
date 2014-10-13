package islamic.buzz.controllers;

import islamic.buzz.fragments.LeftMenuFragment;
import islamic.buzz.interfaces.type.ICategoryController;
import islamic.buzz.po.MenuCategory;
import islamic.buzz.util.UtilityMethods;
import islamic.buzz.vo.IValueObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.content.Context;

import com.eybsolution.islamic.buzz.R;

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

	/******
	 * This is a utility method in order to get the parent categories in the
	 * opposite order.
	 * 
	 * @param childCat
	 *            - The category should have hasParent 'true' in order to
	 *            deliver parent Categories
	 * @return
	 */

	private ArrayList<MenuCategory> getParentCategories(MenuCategory childCat) {
		final ArrayList<MenuCategory> parentCategories = new ArrayList<MenuCategory>();

		// loop till the childCat does not have any parent Category.
		while (childCat != null && childCat.isHasParent()) {
			// The type is set to BOLD, because the UI for the list needs to be
			// BOLD for the parent categories
			MenuCategory parentCat = childCat.getParentCat();
			if (parentCat.getCatCode() == MenuCategory.CODE_ROOT_LEVEL) {
				parentCat = new MenuCategory(mContext.getString(R.string.home), true, false, MenuCategory.CATEGORY_ROOT_LEVEL, null, MenuCategory.CODE_ROOT_LEVEL);
				// Menu Text item should have gray background
				parentCat.setType(MenuCategory.TYPE_GRAY_BACKGROUND);
			} else {
				parentCat.setType(MenuCategory.TYPE_BOLD);
			}
			parentCategories.add(parentCat);
			childCat = childCat.getParentCat();
		}

		if (parentCategories != null) {
			java.util.Collections.reverse(parentCategories);
		}

		return parentCategories;
	}

	/**
	 * Utility method to get the child elements
	 * 
	 * @param parentCat
	 * @return
	 */
	// private ArrayList<MenuCategory> getChildCategories(MenuCategory
	// parentCat) {
	// ArrayList<MenuCategory> childCategories = new ArrayList<MenuCategory>();
	// if (parentCat.isHasChild() && parentCat.getResourcePath() != null) {
	// childCategories = getCategoryData(parentCat);
	// }
	//
	// return childCategories;
	// }

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

		// Save the category into clicked Category
		this.clickedCat = mainCat;

		ArrayList<MenuCategory> menuCats = new ArrayList<MenuCategory>();

		// If parent Category has been provided return the same back otherwise
		// try to get the parent categories
		if (parentCat != null) {
			menuCats.addAll(parentCat);

		} else {
			ArrayList<MenuCategory> parentCats = getParentCategories(mainCat);
			if (parentCats != null) {
				menuCats.addAll(parentCats);
			}
		}

		if (mainCat.getCatCode() == MenuCategory.CODE_ROOT_LEVEL) {
			mainCat = new MenuCategory(mContext.getString(R.string.home), true, false, MenuCategory.CATEGORY_ROOT_LEVEL, null, MenuCategory.CODE_ROOT_LEVEL);
		}
		// Home needs Black highlight
		mainCat.setType(MenuCategory.TYPE_HIGHLIGHT);

		// Add self in the list
		menuCats.add(mainCat);

		// If child category has been provided, then add the same otherwise get
		// child categories and add.
		if (childCat != null) {
			menuCats.addAll(childCat);

		} else {
			// ArrayList<MenuCategory> childCats = getChildCategories(mainCat);
			// if (childCats != null) {
			// menuCats.addAll(childCats);
			// }
		}

		return menuCats;
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
		return getCategoriesForMenu(new MenuCategory(mContext.getString(R.string.home), true, false, MenuCategory.CATEGORY_ROOT_LEVEL, null, MenuCategory.CODE_ROOT_LEVEL), null, null);
	}

	/**
	 * Request to get data based on the Resource Path and Code
	 * 
	 * @param clickedCat
	 * @return
	 */
	private ArrayList<MenuCategory> getCategoryData(MenuCategory clickedCat) {

		final ArrayList<MenuCategory> mainCategories = new ArrayList<MenuCategory>();

		String response = UtilityMethods.loadJSONFromAsset(mContext, "category");
		MenuCategory mCategory = (MenuCategory) UtilityMethods.getModelFromJsonString(response, MenuCategory.class);
		// Loop through the categories and set the Parent category
		// to Root Level
		for (MenuCategory category : mCategory.getCategories()) {
			category.setParentCat(clickedCat);
		}

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
