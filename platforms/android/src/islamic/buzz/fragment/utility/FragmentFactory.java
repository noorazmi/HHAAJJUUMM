
package islamic.buzz.fragment.utility;

import islamic.buzz.activities.HomeActivity;
import islamic.buzz.fragments.HomeFragment;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;

/**
 * It helps in generating fragment & replace it at content layout.
 */
public class FragmentFactory {

    // A bad approach will look for a better solution to this.
    private static Bundle mLastInvokedPMPBundle;

    public static void attachHomeFragment(Activity activity,
            Bundle bundle,
            boolean replaceOnBack) {
        HomeFragment homeFragment = new HomeFragment();
        if (replaceOnBack) {
            FragmentHelper.replaceFragmentOnBackPress(new WeakReference<Activity>(activity),
                    homeFragment,
                    null,
                    null);

        } else {
            FragmentHelper.replaceFragmentWithDefaultAnimation(new WeakReference<Activity>(activity),
                    homeFragment,
                    null,
                    null);
        }
    }

	public static void attachHajjFragment(HomeActivity homeActivity, Bundle bundle) {
		
	}

	public static void attachUmrahFragment(HomeActivity homeActivity, Object object) {
		
	}

	public static void attachAboutFragment(HomeActivity homeActivity, Object object) {
		
	}

	public static void attachNamesOfAllahFragment(HomeActivity homeActivity, Object object) {
		
	}
    
    
    

}
