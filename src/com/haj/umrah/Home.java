package com.haj.umrah;

import com.haj.umrah.sidemenu.ContentWindow;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

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
	// LeftSideMenu leftSideMenu = new
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.home, menu);
	return true;
    }

    public void onLeftMenuClick(View v)
    {
	Toast.makeText(getBaseContext(), "clidke", Toast.LENGTH_SHORT).show();
	AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.left_menu_slide_right);
	set.setTarget(contentWindow);
	set.start();
    }
}
