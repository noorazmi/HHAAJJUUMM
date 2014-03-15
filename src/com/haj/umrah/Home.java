package com.haj.umrah;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.haj.umrah.sidemenu.ContentWindow;

public class Home extends Activity
{
    private FrameLayout container;
    private ContentWindow contentWindow;
    private GestureDetectorCompat gestureDetectorCompat;
    private static final String TAG = Home.class.getSimpleName();

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
	//Toast.makeText(getBaseContext(), "clidke", Toast.LENGTH_SHORT).show();
//	AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.left_menu_slide_right);
//	set.setTarget(contentWindow);
//	set.start();
	
	
	
//	ObjectAnimator anim = ObjectAnimator.ofFloat(contentWindow, "x", 0f, 400f);
//	anim.setDuration(500);
//	anim.start();
	
	
//	ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(contentWindow, "translationX", 0, 400);
//	objectAnimator.setDuration(4000);
//	objectAnimator.start();
	if(contentWindow.getTranslationX() == 0)
	{
	    contentWindow.animate().x(400f).setDuration(300);  
	}
	else{
	    contentWindow.animate().x(0f).setDuration(300);
	}
	
    }
    
    class MenuGestureListener extends GestureDetector.SimpleOnGestureListener{
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
	    
	    return super.onFling(e1, e2, velocityX, velocityY);
	}
	
    } 
}
