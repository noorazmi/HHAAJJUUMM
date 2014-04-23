package com.haj.umrah.views;

import com.haj.umrah.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class UpdatesView extends LinearLayout
{

    public UpdatesView(Context context, AttributeSet attrs)
    {
	super(context, attrs);
	LayoutInflater.from(getContext()).inflate(R.layout.updates, this);
    }

}
