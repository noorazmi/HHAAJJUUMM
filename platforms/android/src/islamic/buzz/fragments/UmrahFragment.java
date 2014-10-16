package islamic.buzz.fragments;

import islamic.buzz.util.LogType;
import islamic.buzz.util.Util;
import islamic.buzz.views.UmrahView;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.eybsolution.islamic.buzz.R;

public class UmrahFragment extends BaseFragment {

	private ViewPager umrahViewPager;
	private PagerAdapter umrahPageAdapter;
	private Resources res;
	private static final String TAG = UmrahView.class.getName();

	@Override
	protected void initController() {}

	@Override
	protected void initFields(Bundle bundle) {

		res = getActivity().getResources();
		umrahViewPager = (ViewPager) getFragmentView().findViewById(R.id.umrah_viewpager);
		umrahPageAdapter = new UmrahPagerAdapter(getActivity().getFragmentManager(), getFragments());
		umrahViewPager.setAdapter(umrahPageAdapter);
		umrahViewPager.setOffscreenPageLimit(5);

	}

	private List<Fragment> getFragments() {
		List<Fragment> fList = new ArrayList<Fragment>();

		fList.add(new UmrahIntroFragment());
		fList.add(new UmrahPriorToDepartureFragment());
		fList.add(new UmrahIhramFragment());
		fList.add(new UmrahTawafFragment());
		fList.add(new UmrahSai());
		fList.add(new UmrahHalaq());

		return fList;
	}

	class UmrahPagerAdapter extends FragmentStatePagerAdapter {

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
		public CharSequence getPageTitle(int position) {
			Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + "() called. positions::" + position, LogType.LOG_TYPE_DEBUG);

			String title = null;

			switch (position) {
			case 0:
				title = res.getString(R.string.introduction).toUpperCase();
				break;
			case 1:
				title = res.getString(R.string.prior_to_departure).toUpperCase();
				break;
			case 2:
				title = res.getString(R.string.ihraam).toUpperCase();
				break;
			case 3:
				title = res.getString(R.string.tawaaf).toUpperCase();
				break;
			case 4:
				title = res.getString(R.string.sai).toUpperCase();
				break;
			case 5:
				title = res.getString(R.string.halaq).toUpperCase();
				break;
			}
			return title;
		}

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

}
