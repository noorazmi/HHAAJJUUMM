package islamic.buzz.fragment.utility;

import islamic.buzz.fragments.BaseFragment;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.eybsolution.islamic.buzz.R;

public class FragmentHelper {

	private static String fragmentTag;

	public static void replaceFragment(WeakReference<?> activity, Fragment fragment) {
		replaceFragment(activity, fragment, null);
	}

	public static void replaceFragment(WeakReference<?> activity, Fragment fragment, Bundle datas) {
		replaceFragmentWithDefaultAnimation(activity, fragment, datas, null);
	}

	public static void replaceFragmentWithDefaultAnimation(WeakReference<?> act, Fragment fragment, Bundle datas, String fragTag) {
		if (act.get() != null && !((Activity) act.get()).isFinishing()) {

			fragmentTag = fragTag;
			FragmentTransaction transaction = ((Activity) act.get()).getFragmentManager().beginTransaction();
			if (datas != null) {
				fragment.setArguments(datas);
			}
			transaction.setCustomAnimations(R.animator.fragment_slide_right_enter, R.animator.fragment_slide_left_exit, 0, 0);
			Log.d("test", "goto : Total Fragment count : " + ((Activity) act.get()).getFragmentManager().getBackStackEntryCount());
			transaction.replace(((Activity) act.get()).findViewById(R.id.content_detail).getId(), fragment, fragTag);
			transaction.show(fragment);
			transaction.commit();
		}
	}

	public static void replaceFragmentOnBackPress(WeakReference<?> act, Fragment fragment, Bundle datas, String fragTag) {

		if (act.get() != null && !((Activity) act.get()).isFinishing()) {

			FragmentTransaction transaction = ((Activity) act.get()).getFragmentManager().beginTransaction();
			if (datas != null) {
				fragment.setArguments(datas);
			}
			transaction.setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fragment_slide_right_exit, 0, 0);
			Log.d("test", "goto : Total Fragment count : " + ((Activity) act.get()).getFragmentManager().getBackStackEntryCount());
			transaction.replace(((Activity) act.get()).findViewById(R.id.content_detail).getId(), fragment, fragTag);
			transaction.show(fragment);
			transaction.commit();
		}
	}

	public static void replaceFragmentWithFadeOutAnimation(WeakReference<?> act, Fragment fragment, Bundle datas, String fragTag) {
		if (act.get() != null && !((Activity) act.get()).isFinishing()) {

			FragmentTransaction transaction = ((Activity) act.get()).getFragmentManager().beginTransaction();
			if (datas != null) {
				fragment.setArguments(datas);
			}
			transaction.setCustomAnimations(0, R.animator.fragment_fade_out, 0, 0);
			transaction.replace(((Activity) act.get()).findViewById(R.id.content_detail).getId(), fragment, fragTag);
			transaction.show(fragment);
			transaction.commit();
		}
	}

	public static void replaceFragmentWithNoAnimation(WeakReference<?> act, Fragment fragment, Bundle datas) {
		if (act.get() != null && !((Activity) act.get()).isFinishing()) {
			FragmentTransaction transaction = ((Activity) act.get()).getFragmentManager().beginTransaction();
			if (datas != null) {
				fragment.setArguments(datas);
			}
			transaction.replace(((Activity) act.get()).findViewById(R.id.content_detail).getId(), fragment, null);
			transaction.show(fragment);
			transaction.commit();
		}
	}

	public static void replaceFragmentWithStateLoss(WeakReference<?> act, Fragment fragment, Bundle datas) {
		if (act.get() != null && !((Activity) act.get()).isFinishing()) {
			FragmentTransaction transaction = ((Activity) act.get()).getFragmentManager().beginTransaction();
			if (datas != null) {
				fragment.setArguments(datas);
			}
			transaction.replace(((Activity) act.get()).findViewById(R.id.content_detail).getId(), fragment, null);
			transaction.show(fragment);
			transaction.commitAllowingStateLoss();
		}
	}

	/**
	 * Replace content frame with
	 * 
	 * @param act
	 *            Week ref for the activity
	 * @param fragment
	 *            Fragment to put in
	 * @param keepInStack
	 *            Weather to keep in backstack or not
	 * @param datas
	 *            Bundled data
	 * @param enter
	 *            New fragment enter animation.
	 * @param exit
	 *            Previous fragment's exit animation.
	 * @param popEnter
	 *            On Back button fragment's enter animation.
	 * @param popExit
	 *            On Back button fragment's exit animation.
	 * @param fragTag
	 *            tag of the fragment transaction while adding transaction in
	 *            the stack.
	 */
	public static void replaceFragmentWithCustomAnimation(WeakReference<?> activity, Fragment fragment, Bundle datas, int enter, int exit, int popEnter, int popExit, String fragTag) {

		if (activity.get() != null && !((Activity) activity.get()).isFinishing()) {

			FragmentTransaction transaction = ((Activity) activity.get()).getFragmentManager().beginTransaction();
			if (datas != null) {
				fragment.setArguments(datas);
			}
			transaction.setCustomAnimations(enter, popExit, exit, popEnter);
			Log.d("test", "goto : Total Fragment count : " + ((Activity) activity.get()).getFragmentManager().getBackStackEntryCount());
			transaction.replace(((Activity) activity.get()).findViewById(R.id.content_detail).getId(), fragment, fragTag);
			transaction.show(fragment);
			transaction.commit();
		}
	}

	public static void addFragmentWithNoAnimation(WeakReference<?> act, Fragment fragment, Bundle datas) {

		if (act.get() != null && !((Activity) act.get()).isFinishing()) {

			FragmentTransaction transaction = ((Activity) act.get()).getFragmentManager().beginTransaction();
			if (datas != null) {
				fragment.setArguments(datas);
			}

			transaction.add(((Activity) act.get()).findViewById(R.id.content_detail).getId(), fragment, null);
			transaction.addToBackStack(null);
			transaction.show(fragment);
			transaction.commit();
		}
	}

	public static BaseFragment getFragmentonTop(WeakReference<?> act) {
		if (act.get() != null && !((Activity) act.get()).isFinishing()) {

			BaseFragment fragment = null;
			try {
				if (fragmentTag != null)
					fragment = (BaseFragment) ((Activity) act.get()).getFragmentManager().findFragmentByTag(fragmentTag);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return fragment;
		}
		return null;
	}
}
