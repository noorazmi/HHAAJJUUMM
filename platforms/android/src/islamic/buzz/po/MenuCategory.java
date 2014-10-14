
package islamic.buzz.po;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is a VO for all the Categories seen in the Menu
 */
public class MenuCategory implements Serializable {
    private static final long serialVersionUID = 831682390492138365L;

    /**
     * Resource Path for the Root Level Category Data.This data is in sync with
     * "Category.json" , SO If this is changed, please change the corresponding
     * data in category.json in assets
     */

    public static final String CATEGORY_ROOT_LEVEL = "CATEGORY_ROOT";
    public static final String CATEGORY_BUZZ_LEVEL = "CATEGORY_BUZZ";
    public static final String CATEGORY_HAJJ_LEVEL = "CATEGORY_HAJJ";
    public static final String CATEGORY_UMRAH_LEVEL = "CATEGORY_UMRAH";
    public static final String CATEGORY_99_NAMES_OF_ALLAH_LEVEL = "CATEGORY_99_NAMES_OF_ALLAH";
    public static final String CATEGORY_ABOUT_LEVEL = "CATEGORY_ABOUT";

    /****
     * Code for the Categories.This data is in sync with "Category.json" , SO If
     * this is changed, please change the corresponding data in category.json in
     * assets
     */

    // all Subcategories will be assigned the same Catcode.
    public static final int CODE_ROOT_LEVEL = 1000;
    public static final int CODE_BUZZ_LEVEL = 1001;
    public static final int CODE_HAJJ_LEVEL = 1002;
    public static final int CODE_UMRAH_LEVEL = 1003;
    public static final int CODE_ABOUT_LEVEL = 1004;


    /************
     * Type of Display of the Category , whether the list needs to show them as
     * Bold/Highligted
     */

    public static final int TYPE_BOLD = 2001;
    public static final int TYPE_HIGHLIGHT = TYPE_BOLD + 1;
    public static final int TYPE_GRAY_BACKGROUND = TYPE_HIGHLIGHT + 1;
    // This is the list of categories which are read from the "category.json"
    @Expose
    ArrayList<MenuCategory> categories;

    // Category name String to be displayed in the menu option - To be set
    // through Constructor
    private String name;

    // Does the category has a child? In case of root category , it will always
    // set this to true, in cases where data is received from server - this will
    // be determined based on resourcePath , if resourcePath has /category - it
    // will be set to true
    private boolean hasChild;

    // Does the category has a parent? All Categories will have this set to true
    // except for ROOT Category
    private boolean hasParent;

    // Resource Path of all the Categories, the Categories hardcoded in the
    // category.json will have the Resource Path hardcoded to the values set
    // above in the class, otherwise if the data is downloaded from server then
    // the resourcePath is taken directly from there.
    private String resourcePath;

    // If the hasParent is true, this is the category which on Clicked takes us
    // to the child.
    private MenuCategory parentCat;

    // Catcode is an Integer code for all the categories to help in switch
    // statements on itemClick. All Categories will have code from the above set
    // of integers
    private int catCode;

    // View type - whether the textview that hold this data needs to be Bold or
    // Highlighted
    private int type;

    // If the category display in the menu is an image
    private String image;

    /**
     * @param name - String of the name to be displayed in the menu
     * @param hasChild - boolean whether the category is expected to have any
     *            sub categories
     * @param hasParent - boolean whether the category has any parent category
     * @param resourcePath - Identifier as to where the category data will lead
     *            to.
     * @param parentCat - If hasParent then what is the parent Category.
     * @param catCode TODO
     */
    public MenuCategory(String name,
            boolean hasChild,
            boolean hasParent,
            String resourcePath,
            MenuCategory parentCat,
            int catCode) {
        this.name = name;
        this.hasChild = hasChild;
        this.hasParent = hasParent;
        this.resourcePath = resourcePath;
        this.parentCat = parentCat;
        this.catCode = catCode;
    }

    public String getName() {
        return name;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public boolean isHasParent() {
        return hasParent;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public MenuCategory getParentCat() {
        return parentCat;
    }

    public int getCatCode() {
        return catCode;
    }

    public ArrayList<MenuCategory> getCategories() {
        return categories;
    }

    public void setParentCat(MenuCategory parentCat) {
        this.parentCat = parentCat;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

}
