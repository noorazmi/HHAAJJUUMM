package islamic.buzz.views;

import islamic.buzz.fragments.HajjDayFifthFragment;
import islamic.buzz.fragments.HajjDayFourFragment;
import islamic.buzz.fragments.HajjDayOneFragment;
import islamic.buzz.fragments.HajjDaySixthFragment;
import islamic.buzz.fragments.HajjDayThreeFragment;
import islamic.buzz.fragments.HajjDayTwoFragment;
import islamic.buzz.fragments.HajjIntroFragment;
import islamic.buzz.fragments.HajjTawafulwidaFragment;
import islamic.buzz.fragments.UmrahHalaq;
import islamic.buzz.fragments.UmrahIhramFragment;
import islamic.buzz.fragments.UmrahPriorToDepartureFragment;
import islamic.buzz.fragments.UmrahSai;
import islamic.buzz.fragments.UmrahTawafFragment;
import islamic.buzz.util.LogType;
import islamic.buzz.util.Util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bitstacksolutions.R;
import com.eybsolution.islamic.buzz.Home;

public class HajjView extends LinearLayout
{
    private ViewPager hajjViewPager;
    private PagerAdapter hajjPageAdapter;
    private Home homeActivity;
    private Resources res;
    private static final String TAG = HajjView.class.getName();

    public HajjView(Context context)
    {
	super(context, null);
	homeActivity = (Home) context;
	res = homeActivity.getResources();
	LayoutInflater.from(getContext()).inflate(R.layout.umrah, this);
	hajjViewPager = (ViewPager) findViewById(R.id.umrah_viewpager);
	hajjPageAdapter = new UmrahPagerAdapter(homeActivity.getSupportFragmentManager(), getFragments());
	hajjViewPager.setAdapter(hajjPageAdapter);
	hajjViewPager.setOffscreenPageLimit(6);
	// hajjViewPager.setCurrentItem(1);
    }

    public HajjView(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	// LayoutInflater.from(getContext()).inflate(R.layout.umrah, this);
    }

    private List<Fragment> getFragments()
    {
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

    class UmrahPagerAdapter extends FragmentStatePagerAdapter
    {

	private List<Fragment> fragments;

	public UmrahPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments)
	{
	    super(fragmentManager);
	    this.fragments = fragments;
	}

	@Override
	public int getCount()
	{
	    return this.fragments.size();
	}

	@Override
	public Fragment getItem(int position)
	{
	    return this.fragments.get(position);
	    // return getFragments().get(position);
	}

	@Override
	public int getItemPosition(Object object)
	{
	    return POSITION_NONE;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
	    return super.instantiateItem(container, position);
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
	    Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + "() called. positions::" + position, LogType.LOG_TYPE_DEBUG);

	    String title = null;

	    switch (position)
	    {
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
