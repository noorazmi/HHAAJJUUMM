package islamic.buzz.fragment.utility;

import islamic.buzz.activities.HomeActivity;
import islamic.buzz.fragments.HajjFragment;
import islamic.buzz.fragments.HomeFragment;
import islamic.buzz.fragments.UmrahFragment;
import islamic.buzz.views.AboutFragment;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;

/**
 * It helps in generating fragment & replace it at content layout.
 */
public class FragmentFactory {


	public static void attachHomeFragment(HomeActivity homeActivity, Bundle bundle) {
		HomeFragment homeFragment = new HomeFragment();
		FragmentHelper.replaceFragmentWithDefaultAnimation(new WeakReference<Activity>(homeActivity), homeFragment, null, null);
	}

	public static void attachHajjFragment(HomeActivity homeActivity, Bundle bundle) {
		HajjFragment hajjFragment = new HajjFragment();
		FragmentHelper.replaceFragmentWithDefaultAnimation(new WeakReference<Activity>(homeActivity), hajjFragment, null, null);
	}

	public static void attachUmrahFragment(HomeActivity homeActivity, Object object) {
		UmrahFragment umrahFragment = new UmrahFragment();
		FragmentHelper.replaceFragmentWithDefaultAnimation(new WeakReference<Activity>(homeActivity), umrahFragment, null, null);
	}

	public static void attachAboutFragment(HomeActivity homeActivity, Object object) {
		AboutFragment aboutFragment = new AboutFragment();
		FragmentHelper.replaceFragmentWithDefaultAnimation(new WeakReference<Activity>(homeActivity), aboutFragment, null, null);
	}

	public static void attachNamesOfAllahFragment(HomeActivity homeActivity, Object object) {

	}

}
