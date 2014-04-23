package com.haj.umrah;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.haj.umrah.fragments.UmrahViewFragment;
import com.haj.umrah.sidemenu.ContentWindow;
import com.haj.umrah.views.UmrahView;

public class Home extends FragmentActivity
{
    private FrameLayout container;
    private ContentWindow contentWindow;
    private LinearLayout contentHolder;
    private LinearLayout contentView;
    private UmrahView umrahView;
    private View currentView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);
	container = (FrameLayout) findViewById(R.id.container);
	contentWindow = (ContentWindow) findViewById(R.id.contnet_window);
	contentHolder = (LinearLayout) contentWindow.findViewById(R.id.content_holder);
//	FragmentManager fragmentManager = getSupportFragmentManager();
//	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//	fragmentTransaction.add(R.id.content_holder, new UmrahViewFragment());
//	fragmentTransaction.commit();
	umrahView = new UmrahView(this);
	contentHolder.addView(umrahView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	// Inflate the menu this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.home, menu);
	return true;
    }

    @Override
    public void onBackPressed()
    {
	if (!contentWindow.canAnimateToRight())
	{
	    contentWindow.animateLeft();
	    return;
	}
	super.onBackPressed();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
	if (keyCode == KeyEvent.KEYCODE_MENU)
	{
	    if (contentWindow.canAnimateToRight())
	    {
		contentWindow.animateRight();
		
	    }
	    else
	    {
		contentWindow.animateLeft();
	    }
	    return true;
	}
	return super.onKeyUp(keyCode, event);
    }

    // Returns the content holder
    public LinearLayout getContentHolder()
    {
	return contentHolder;
    }

    // Returns the main content currently showing.
    public LinearLayout getContentView()
    {
	return contentView;
    }
}
