package com.haj.umrah.sidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.haj.umrah.R;

public class LeftSideMenu extends LinearLayout
{

    public LeftSideMenu(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	LayoutInflater.from(context).inflate(R.layout.left_side_menu, this);
    }
    
    

}
