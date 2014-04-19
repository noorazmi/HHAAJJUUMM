package com.haj.umrah.views;

import com.haj.umrah.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class InfoUpdatesView extends LinearLayout
{

    public InfoUpdatesView(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	LayoutInflater.from(getContext()).inflate(R.layout.umrah, this);
    }

}
