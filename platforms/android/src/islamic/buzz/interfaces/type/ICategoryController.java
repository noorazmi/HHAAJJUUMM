
package islamic.buzz.interfaces.type;

import islamic.buzz.po.MenuCategory;

import java.util.ArrayList;

/**
 * Interface to get Categories - Categories are distributed into parent , child
 * and self category.
 */
public interface ICategoryController {

    /**
     * The Menu items currently work in the fashion that First items are Parent,
     * next is the Category itself and then we see child elements
     * 
     * @param mainCat
     * @param parentCat
     * @param childCat
     * @return
     */
    public ArrayList<MenuCategory> getCategoriesForMenu(MenuCategory mainCat,
            ArrayList<MenuCategory> parentCat,
            ArrayList<MenuCategory> childCat);

    /**
     * Category Controller will return the category which it is trying to get
     * child for.
     * 
     * @return
     */
    public MenuCategory getSelectedCategory();

    /**
     * Get the child elements of the root menu, ie the base menu of the
     * application
     * 
     * @return
     */
    public ArrayList<MenuCategory> getRootCategory();

}
