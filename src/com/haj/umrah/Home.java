package com.haj.umrah;

import com.haj.umrah.sidemenu.LeftSideMenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.FrameLayout;

public class Home extends Activity
{
    private FrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);
	container = (FrameLayout) findViewById(R.id.container);
	//LeftSideMenu leftSideMenu = new 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.home, menu);
	return true;
    }

}
