package com.haj.umrah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
	timer = new SplashTimer(3000, 1000);
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
	    Intent intent = new Intent(getBaseContext(), Home.class);
	    startActivity(intent);
	    finish();
	}

	@Override
	public void onTick(long millisUntilFinished)
	{}

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
