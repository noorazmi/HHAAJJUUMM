package islamic.buzz.activities;

import islamic.buzz.fragment.utility.FragmentFactory;
import islamic.buzz.fragment.utility.FragmentHelper;
import islamic.buzz.fragment.utility.FragmentOnScreen;
import islamic.buzz.fragments.BaseFragment;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Home Activity
 */
public class HomeActivity extends BaseActivityWithSlider {

	// private IHomeController homeController;

	// Fragment that is currently attached to content layout of the home
	// activity.
	private FragmentOnScreen mFragmentOnScreen;

	private FragmentOnScreen mPrevFragOnScreenForSearch;

	private Context mActivityContext;


	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		// BuzzApplication.getInstance().setAuthenticationUtils(this);

		mActivityContext = this;

	}

	@Override
	public void initContent() {
		attachHomeFragment(null);
		//attachAboutFragment(null);
		attachActionItemListener();
	}

	@Override
	protected void onStart() {
		super.onStart();
		updateActionBarViews();
	}

	@Override
	protected void updateActionBarViews() {
	}

	/**
	 * Attach action item click listener
	 */
	private void attachActionItemListener() {
		// getActionBarHelper().getItemView().setOnClickListener(new
		// View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// UtilityMethods.openHybridScreen((Activity) mActivityContext,
		// HybridScreen.NAMES_OF_ALLAH);
		// }
		// });
	}

	/**
	 * Attach the home fragment on the content layout of the activity.
	 * 
	 * @param bundle
	 *            bundle data that will be passed to the fragment.
	 */
	public void attachHomeFragment(Bundle bundle) {
		if (mFragmentOnScreen != FragmentOnScreen.HOME) {
			FragmentFactory.attachHomeFragment(this, bundle);
			mFragmentOnScreen = FragmentOnScreen.HOME;
		}

	}

	public void attachHajjFragment(Bundle bundle) {
		FragmentFactory.attachHajjFragment(this, bundle);
		mFragmentOnScreen = FragmentOnScreen.HAJJ;
	}

	public void attachUmrahFragment(Bundle bundle) {
		FragmentFactory.attachUmrahFragment(this, null);
		mFragmentOnScreen = FragmentOnScreen.UMRAH;
	}

	public void attachAboutFragment(Bundle bundle) {
		FragmentFactory.attachAboutFragment(this, null);
		mFragmentOnScreen = FragmentOnScreen.ABOUT;
	}

	public void attachNamesOfAllahFragment(Bundle bundle) {
		FragmentFactory.attachNamesOfAllahFragment(this, null);
		mFragmentOnScreen = FragmentOnScreen.NAMES_OF_ALLAH;
	}

	public FragmentOnScreen getFragmentOnScreen() {
		return mFragmentOnScreen;
	}

	public FragmentOnScreen getPreviousFragmentOnScreenForSearch() {
		return mPrevFragOnScreenForSearch;
	}

	/*
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	}

}
