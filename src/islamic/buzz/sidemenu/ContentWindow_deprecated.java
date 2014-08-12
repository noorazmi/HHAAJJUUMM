package islamic.buzz.sidemenu;

import islamic.buzz.util.LogType;
import islamic.buzz.util.Util;
import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bitstacksolutions.R;

public class ContentWindow_deprecated extends LinearLayout
{
    private static final String TAG = ContentWindow_deprecated.class.getName();
    private GestureDetectorCompat leftMenuButtonGestureDetectorCompat;
    private GestureDetectorCompat gestureDetectorCompat;
    private static final int ANIMATION_DURATION = 150;
    private ImageButton leftMenuHandle;

    public ContentWindow_deprecated(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	LayoutInflater.from(getContext()).inflate(R.layout.content_window, this);
	gestureDetectorCompat = new GestureDetectorCompat(getContext(), new MenuGestureListener());
	leftMenuButtonGestureDetectorCompat = new GestureDetectorCompat(getContext(), new LeftMenuButtonGestureListener());
	setOnTouchListener(new MyViewTouchListener());
	leftMenuHandle = (ImageButton) findViewById(R.id.left_menu_handle);
	//leftMenuHandle.setOnTouchListener(new MyViewTouchListener());
	leftMenuHandle.setOnTouchListener(new MenuButtonOnTouchListener());
    }
    
    class MenuButtonOnTouchListener implements OnTouchListener{

	

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
	    Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + "() called", LogType.LOG_TYPE_DEBUG);
	    //animateRight();
	    leftMenuButtonGestureDetectorCompat.onTouchEvent(event);
	    return false;
	}

	
	
    }
    
    class MyViewTouchListener implements OnTouchListener
    {

	
	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
	    Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + TAG + "() called", LogType.LOG_TYPE_DEBUG);
	    gestureDetectorCompat.onTouchEvent(event);
	    return true;
	}

    }

    
    class LeftMenuButtonGestureListener extends GestureDetector.SimpleOnGestureListener{
	@Override
	public boolean onDown(MotionEvent e)
	{
	    if (canAnimateToRight())
	    {
		    animateRight();

	    }
	    else
	    {

		animateLeft();
	    }
	    return super.onDown(e);
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
	    return super.onFling(e1, e2, velocityX, velocityY);
	}
    }
    
    class MenuGestureListener extends GestureDetector.SimpleOnGestureListener
    {
	private static final int SWIPE_THRESHOLD = 100;
	private static final int SWIPE_VELOCITY_THRESHOLD = 100;

	@Override
	public boolean onDown(MotionEvent e)
	{
	   
	    Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + "() called", LogType.LOG_TYPE_DEBUG);
	    if (canAnimateToRight())
	    {
		    //animateRight();

	    }
	    else
	    {

		animateLeft();
	    }

	    return super.onDown(e);
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
	    Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + TAG + "() called", LogType.LOG_TYPE_DEBUG);
	    boolean result = false;
	    try
	    {
		float diffY = e2.getY() - e1.getY();
		float diffX = e2.getX() - e1.getX();
		if (Math.abs(diffX) > Math.abs(diffY))
		{
		    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD)
		    {
			if (diffX > 0)
			{
			    onSwipeRight();
			}
			else
			{
			    onSwipeLeft();
			}
		    }
		}
		else
		{
		    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD)
		    {
			if (diffY > 0)
			{
			    onSwipeBottom();
			}
			else
			{
			    onSwipeTop();
			}
		    }
		}
	    }
	    catch (Exception exception)
	    {
		exception.printStackTrace();
	    }
	    return result;
	}

	public void onSwipeRight()
	{
	    Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + "() called", LogType.LOG_TYPE_DEBUG);
	    animateRight();
	}

	public void onSwipeLeft()
	{
	    Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + "() called", LogType.LOG_TYPE_DEBUG);
	    animateLeft();
	}

	public void onSwipeTop()
	{
	    Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + "() called", LogType.LOG_TYPE_DEBUG);
	}

	public void onSwipeBottom()
	{
	    Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + "() called", LogType.LOG_TYPE_DEBUG);
	}

    }

    // Animate the left menu back to original position.
    private void animateLeft()
    {
	animate().x(0f).setDuration(ANIMATION_DURATION);
    }

    // Animate the left menu to right to show the menu list
    private void animateRight()
    {

	animate().x(400f).setDuration(ANIMATION_DURATION);
    }

    private boolean canAnimateToRight()
    {
	boolean b = getTranslationX() == 0;
	Util.printLog(TAG, new Exception().getStackTrace()[0].getMethodName() + "() called:" + b, LogType.LOG_TYPE_DEBUG);
	return b;
    }

//    @Override
//    public void onBackPressed()
//    {
//	if (!canAnimateToRight())
//	{
//	    animateLeft();
//	    return;
//	}
//	super.onBackPressed();
//    }

}
