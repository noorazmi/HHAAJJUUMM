package com.eybsolution.islamic.buzz;

import islamic.buzz.activities.HomeActivity;
import islamic.buzz.util.AppInfo;

import com.eybsolution.islamic.buzz.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.WindowManager;

public class Splash extends Activity
{
    private SplashTimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	//setTheme(R.style.AppTheme);
	super.onCreate(savedInstanceState);

	//requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.splash);
	// if(Utility.isThemed)
	// setTheme(R.style.AppTheme);
	timer = new SplashTimer(1000, 1000);
	timer.start();
    }

    private class SplashTimer extends CountDownTimer
    {

	public SplashTimer(long millisInFuture, long countDownInterval)
	{
	    super(millisInFuture, countDownInterval);
	}

	@Override
	public void onFinish()
	{
	    //Intent intent = new Intent(getBaseContext(), Home.class);
	    Intent intent = new Intent(getBaseContext(), HomeActivity.class);
	    startActivity(intent);
	    finish();
	}

	@Override
	public void onTick(long millisUntilFinished)
	{}

    }
    
    @Override
    protected void onStart()
    {
        super.onStart();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        
        AppInfo.setScreenWidth(size.x);
        AppInfo.setScreenHeight(size.y);
        
    }
    
    @Override
    protected void onDestroy()
    {
	timer.cancel();
        super.onDestroy();
        
    }
    
    @Override
    public void onBackPressed()
    {
	timer.cancel();
	super.onBackPressed();
    }

}
