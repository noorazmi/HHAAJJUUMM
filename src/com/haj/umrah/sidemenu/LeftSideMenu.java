package com.haj.umrah.sidemenu;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.haj.umrah.R;
import com.haj.umrah.leftmenu.LeftMenuItem;
import com.haj.umrah.views.Divider;

public class LeftSideMenu extends LinearLayout
{
    private LinearLayout scrollViewContainer;

    public LeftSideMenu(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	LayoutInflater.from(context).inflate(R.layout.left_side_menu, this);
	scrollViewContainer = (LinearLayout) findViewById(R.id.scroll_view_container);
	setMenu();
    }

    //Create left side menu list 
    private void setMenu()
    {
	
	String[] menuItemTitles = getResources().getStringArray(R.array.left_menu_titles);
	int[] menuItemIcons = {R.drawable.info_update, R.drawable.kaba,R.drawable.hajj};
	View menuItem;
	View divider;
	for (int i = 0; i < menuItemTitles.length; i++)
	{
	    menuItem = new LeftMenuItem(getContext(), menuItemIcons[i], menuItemTitles[i]);
	    scrollViewContainer.addView(menuItem);
	    divider = new Divider(getContext(), R.drawable.divider);
	}
    }

    private String getXml(String path)
    {
	String xmlString = null;
	AssetManager am = getContext().getAssets();
	try
	{
	    InputStream is = am.open(path);
	    int length = is.available();
	    byte[] data = new byte[length];
	    is.read(data);
	    xmlString = new String(data);
	}
	catch (IOException e1)
	{
	    e1.printStackTrace();
	}

	return xmlString;
    }

}
