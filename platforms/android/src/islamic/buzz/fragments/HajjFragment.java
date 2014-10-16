package islamic.buzz.fragments;

import islamic.buzz.util.LogType;
import islamic.buzz.util.Util;
import islamic.buzz.views.HajjView;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.eybsolution.islamic.buzz.R;

public class HajjFragment extends BaseFragment {

	private ViewPager hajjViewPager;
	private PagerAdapter hajjPageAdapter;
	private Resources res;
	private static final String TAG = HajjView.class.getName();

	@Override
	protected void initController() {}

	@Override
	protected void initFields(Bundle bundle) {

		res = getActivity().getResources();
		hajjViewPager = (ViewPager) getFragmentView().findViewById(R.id.umrah_viewpager);
		hajjPageAdapter = new UmrahPagerAdapter(getActivity().getFragmentManager(), getFragments());
		hajjViewPager.setAdapter(hajjPageAdapter);
		hajjViewPager.setOffscreenPageLimit(6);

	}

	@Override
	protected int getLayoutId() {
		return R.layout.umrah;
	}

	@Override
	public void onClick(View v) {}

	@Override
	public boolean onBackPress() {
		return false;
	}

	private List<Fragment> getFragments() {
		List<Fragment> fList = new ArrayList<Fragment>();

		fList.add(new HajjIntroFragment());
		fList.add(new HajjDayOneFragment());
		fList.add(new HajjDayTwoFragment());
		fList.add(new HajjDayThreeFragment());
		fList.add(new HajjDayFourFragment());
		fList.add(new HajjDayFifthFragment());
		fList.add(new HajjDaySixthFragment());
		fList.add(new HajjTawafulwidaFragment());

		return fList;
	}

	private class UmrahPagerAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragments;

		public UmrahPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
			super(getChildFragmentManager());
			this.fragments = fragments;
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}

		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
			// return getFragments().get(position);
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			return super.instantiateItem(container, position);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + "() called. positions::" + position, LogType.LOG_TYPE_DEBUG);

			String title = null;

			switch (position) {
			case 0:
				title = res.getString(R.string.introduction).toUpperCase();
				break;
			case 1:
				title = res.getString(R.string.first_day_8th_dhul_hijjah).toUpperCase();
				break;
			case 2:
				title = res.getString(R.string.hajj_second_day).toUpperCase();
				break;
			case 3:
				title = res.getString(R.string.hajj_third_day).toUpperCase();
				break;
			case 4:
				title = res.getString(R.string.hajj_fourth_day).toUpperCase();
				break;
			case 5:
				title = res.getString(R.string.hajj_fifth_day).toUpperCase();
				break;
			case 6:
				title = res.getString(R.string.hajj_sixth_day).toUpperCase();
				break;
			case 7:
				title = res.getString(R.string.tawaful_wida).toUpperCase();
				break;
			}
			return title;
		}

	}

}
