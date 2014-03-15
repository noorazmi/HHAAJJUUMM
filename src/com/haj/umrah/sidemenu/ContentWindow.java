package com.haj.umrah.sidemenu;

import com.haj.umrah.R;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ContentWindow extends LinearLayout {

	public ContentWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(getContext())
				.inflate(R.layout.content_window, this);
	}
	
//	public void setX(int x)
//	{
//	  //super.setX(x);
//	    Toast.makeText(getContext(), "---------------", Toast.LENGTH_SHORT).show();
//	    //(0.01f);
//	    AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.left_menu_slide_right);
//		set.setTarget(this);
//		set.start();
//	}

}
