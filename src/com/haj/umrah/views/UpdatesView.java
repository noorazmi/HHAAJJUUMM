package com.haj.umrah.views;


import com.haj.umrah.Home;
import com.haj.umrah.R;
import com.haj.umrah.fragments.ImageGridFragment;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class UpdatesView extends LinearLayout
{
    private Home home;
    private String TAG = UpdatesView.class.getSimpleName();
    public UpdatesView(Context context)
    {
	super(context);
	home = (Home) context;
	//LayoutInflater.from(getContext()).inflate(R.layout.updates_item, this);
	LayoutInflater.from(getContext()).inflate(R.layout.updates, this);
	LinearLayout layout = (LinearLayout) findViewById(R.id.upates_holder);
	
	if (home.getSupportFragmentManager().findFragmentByTag(TAG) == null)
	{
	    final FragmentTransaction ft = home.getSupportFragmentManager().beginTransaction();
	    //ft.add(android.R.id.content, new ImageGridFragment(), TAG);
	    ft.add(R.id.upates_holder, new ImageGridFragment(), TAG);
	    
	    ft.commit();
	}
    }

    /** Will be called only when the layout is inflated in xml layout file. **/
    public UpdatesView(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	//LayoutInflater.from(getContext()).inflate(R.layout.updates_item, this);
    }

}
