
package com.jeremyfeinstein.slidingmenu.lib.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SlidingMenuView extends SlidingMenuViewBase {

    private static final String TAG = "CustomViewBehind";

    public SlidingMenuView(Context context) {
        this(context, null);
    }

    public SlidingMenuView(Context context,
            AttributeSet attrs) {
        super(context, attrs, false);
    }

    @Override
    public int getDestScrollX() {
        if (isMenuOpen()) {
            return getBehindWidth();
        } else {
            return 0;
        }
    }

    @Override
    public int getChildLeft(int i) {
        return 0;
    }

    @Override
    public int getChildRight(int i) {
        return getChildLeft(i) + getChildWidth(i);
    }

    @Override
    public boolean isMenuOpen() {
        return getScrollX() == 0;
    }

    @Override
    public int getCustomWidth() {
        int i = isMenuOpen() ? 0 : 1;
        return getChildWidth(i);
    }

    @Override
    public int getChildWidth(int i) {
        if (i <= 0) {
            return getBehindWidth();
        } else {
            return getChildAt(i).getMeasuredWidth();
        }
    }

    @Override
    public int getBehindWidth() {
        ViewGroup.LayoutParams params = getLayoutParams();
        return params.width;
    }

    @Override
    public void setContent(View v) {
        super.setMenu(v);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }

}
