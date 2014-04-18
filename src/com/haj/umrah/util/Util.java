package com.haj.umrah.util;

import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;

public class Util
{
    //Make this class non instantiable
    private Util()
    {}

    //Global log printing method with global application name APP_TAG
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
    public static final float getPixels(int dp, Resources res){
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

}
