package com.haj.umrah.util;

public class AppInfo
{
    private static int screenWidth;
    private static int screenHeight;
    private AppInfo()
    {}
    
    public static int getScreenWidth()
    {
        return screenWidth;
    }
    public static void setScreenWidth(int screenWidth)
    {
	AppInfo.screenWidth = screenWidth;
    }
    public static int getScreenHeight()
    {
        return screenHeight;
    }
    public static void setScreenHeight(int screenHeight)
    {
	AppInfo.screenHeight = screenHeight;
    }
}
