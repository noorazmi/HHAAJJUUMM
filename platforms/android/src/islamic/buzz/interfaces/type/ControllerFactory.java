
package islamic.buzz.interfaces.type;

import islamic.buzz.controllers.HomeController;
import islamic.buzz.fragments.HomeFragment;

import java.lang.ref.WeakReference;

/**
 * Factory to provide Controller instances. All dependencies will be injected by
 * this class.
 */
public class ControllerFactory {
    public static HomeController getHomeController(WeakReference<HomeFragment> fragment) {
        return new HomeController(fragment);
    }
//
//    public static ICategoryController getCategoryController(Context context,
//            WeakReference<HamburgerListFragment> fragment) {
//        return new CategoryControllerImpl(context, fragment);
//    }
//
//    public static IProductMatrixController
//            getProductMatrixController(WeakReference<ProductMatrixFragment> fragment) {
//        return new ProductMatrixControllerImpl(fragment);
//    }

    
}
