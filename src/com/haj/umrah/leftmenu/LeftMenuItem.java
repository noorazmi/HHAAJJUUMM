package com.haj.umrah.leftmenu;

import com.haj.umrah.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

public class LeftMenuItem extends LinearLayout
{
    public LeftMenuItem(Context context, int iconResId, String title)
    {
	super(context);
	LayoutInflater.from(getContext()).inflate(R.layout.left_side_menu_item, this);
	((ImageView) findViewById(R.id.menu_icon)).setImageResource(iconResId);
	((TextView)findViewById(R.id.menu_title)).setText(title);
    }

}
