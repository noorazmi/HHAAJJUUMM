package com.haj.umrah.sidemenu;

import com.haj.umrah.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class LeftSideMenu extends LinearLayout {

	public LeftSideMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.left_side_menu, this);
	}

	public void onLeftMenuClick(View v) {
		AlertDialog dialog = new Builder(getContext()).create();
		dialog.setMessage("Click");
		dialog.show();
	}

}
