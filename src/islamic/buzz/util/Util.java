package islamic.buzz.util;


import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.util.TypedValue;

public class Util
{
    // Make this class non instantiable
    private Util()
    {}

    // Global log printing method with global application name APP_TAG
    public static void printLog(String tag, String message, LogType logType)
    {
	StringBuffer mMessage = new StringBuffer("[");
	mMessage.append(tag).append("] ").append(message);
	switch (logType)
	{
	case LOG_TYPE_DEBUG:
	    Log.d(Const.APP_TAG, mMessage.toString());
	    break;

	case LOG_TYPE_ERROR:
	    Log.e(Const.APP_TAG, mMessage.toString());
	    break;
	}

    };

    /** Convert from dp to pixels **/
    public static final float getPixels(int dp, Resources res)
    {
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

    @TargetApi(11)
    public static void enableStrictMode()
    {
	if (Util.hasGingerbread())
	{
	    StrictMode.ThreadPolicy.Builder threadPolicyBuilder = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog();
	    StrictMode.VmPolicy.Builder vmPolicyBuilder = new StrictMode.VmPolicy.Builder().detectAll().penaltyLog();

	    if (Util.hasHoneycomb())
	    {
		threadPolicyBuilder.penaltyFlashScreen();
		// TODO:Uncomment the following line and resolve the error.
		// vmPolicyBuilder.setClassInstanceLimit(ImageGridActivity.class,
		// 1).setClassInstanceLimit(ImageDetailActivity.class, 1);
	    }
	    StrictMode.setThreadPolicy(threadPolicyBuilder.build());
	    StrictMode.setVmPolicy(vmPolicyBuilder.build());
	}
    }

    public static boolean hasFroyo()
    {
	// Can use static final constants like FROYO, declared in later versions
	// of the OS since they are inlined at compile time. This is guaranteed
	// behavior.
	return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    public static boolean hasGingerbread()
    {
	return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb()
    {
	return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1()
    {
	return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBean()
    {
	return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

}
