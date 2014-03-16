package com.haj.umrah;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;

import com.haj.umrah.sidemenu.ContentWindow;

public class Home extends Activity
{
    private FrameLayout container;
    private ContentWindow contentWindow;
    

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);
	container = (FrameLayout) findViewById(R.id.container);
	contentWindow = (ContentWindow) findViewById(R.id.contnet_window);
	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	//Inflate the menu this adds items to the action bar if it is present.
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
}
