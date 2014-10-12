
package islamic.buzz.interfaces.type;

import islamic.buzz.controllers.CategoryControllerImpl;
import islamic.buzz.controllers.HomeController;
import islamic.buzz.fragments.HomeFragment;
import islamic.buzz.fragments.LeftMenuFragment;

import java.lang.ref.WeakReference;

import android.content.Context;

/**
 * Factory to provide Controller instances. All dependencies will be injected by
 * this class.
 */
public class ControllerFactory {
    public static HomeController getHomeController(WeakReference<HomeFragment> fragment) {
        return new HomeController(fragment);
    }
//
    public static ICategoryController getCategoryController(Context context,
            WeakReference<LeftMenuFragment> fragment) {
        return new CategoryControllerImpl(context, fragment);
    }
//
//    public static IProductMatrixController
//            getProductMatrixController(WeakReference<ProductMatrixFragment> fragment) {
//        return new ProductMatrixControllerImpl(fragment);
//    }

    
}
