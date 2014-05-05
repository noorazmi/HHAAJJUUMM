package com.haj.umrah.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.haj.umrah.Home;
import com.haj.umrah.R;
import com.haj.umrah.fragments.HajjIntroFragment;
import com.haj.umrah.fragments.UmrahHalaq;
import com.haj.umrah.fragments.UmrahIhramFragment;
import com.haj.umrah.fragments.UmrahPriorToDepartureFragment;
import com.haj.umrah.fragments.UmrahSai;
import com.haj.umrah.fragments.UmrahTawafFragment;
import com.haj.umrah.util.LogType;
import com.haj.umrah.util.Util;

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
	hajjViewPager.setOffscreenPageLimit(0);
	//hajjViewPager.setCurrentItem(1);
    }

    public HajjView(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	//LayoutInflater.from(getContext()).inflate(R.layout.umrah, this);
    }

    private List<Fragment> getFragments()
    {
	List<Fragment> fList = new ArrayList<Fragment>();

	fList.add(new HajjIntroFragment());
	fList.add(new UmrahPriorToDepartureFragment());
	fList.add(new UmrahIhramFragment());
	fList.add(new UmrahTawafFragment());
	fList.add(new UmrahSai());
	fList.add(new UmrahHalaq());

	return fList;
    }

    class UmrahPagerAdapter extends FragmentPagerAdapter
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
	    //return getFragments().get(position);
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
	    Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + "() called. positions::"+position, LogType.LOG_TYPE_DEBUG);
		 
	    String title = null;
	    
	    switch (position)
	    {
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

}
