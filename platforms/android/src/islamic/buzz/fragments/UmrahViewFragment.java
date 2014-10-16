package islamic.buzz.fragments;

import islamic.buzz.util.LogType;
import islamic.buzz.util.Util;
import islamic.buzz.views.UmrahView;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eybsolution.islamic.buzz.Home;
import com.eybsolution.islamic.buzz.R;

public class UmrahViewFragment extends Fragment
{
    private ViewPager umrahViewPager;
    private PagerAdapter umrahPageAdapter;
    private Home homeActivity;
    private Resources res;
    private static final String TAG = UmrahView.class.getName();
    private View umrahView;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	homeActivity = (Home) getActivity();
	res = getResources();
        //return super.onCreateView(inflater, container, savedInstanceState);
	umrahView = inflater.inflate(R.layout.umrah, container, false);
	umrahViewPager = (ViewPager) umrahView.findViewById(R.id.umrah_viewpager);
//	umrahPageAdapter = new UmrahPagerAdapter(homeActivity.getSupportFragmentManager(), getFragments());
//	umrahViewPager.setAdapter(umrahPageAdapter);
//	umrahViewPager.setOffscreenPageLimit(6);
	
        return umrahView;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
        umrahPageAdapter = new UmrahPagerAdapter(homeActivity.getFragmentManager(), getFragments());
	umrahViewPager.setAdapter(umrahPageAdapter);
	umrahViewPager.setOffscreenPageLimit(6);
    }
    private List<Fragment> getFragments()
    {
	List<Fragment> fList = new ArrayList<Fragment>();

	fList.add(new UmrahIntroFragment());
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
