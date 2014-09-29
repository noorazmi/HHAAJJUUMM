
package com.jeremyfeinstein.slidingmenu.lib.view;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ShareActionProvider;

import java.lang.reflect.Field;

public class CustomShareActionProvider extends ShareActionProvider {

    public CustomShareActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView(MenuItem forItem) {

        View view = super.onCreateActionView(forItem);
        try {
            Field imageField =
                    view.getClass().getDeclaredField("mExpandActivityOverflowButtonImage");
            imageField.setAccessible(true);
            Field dbField = view.getClass().getDeclaredField("mDefaultActivityButton");
            dbField.setAccessible(true);
            final View mDefaultActivityButton = (View) dbField.get(view);
            mDefaultActivityButton.getViewTreeObserver()
                    .addOnPreDrawListener(new OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            mDefaultActivityButton.setVisibility(View.GONE);
                            return true;
                        }
                    });

            Field mActivityChooserContentField =
                    view.getClass().getDeclaredField("mActivityChooserContent");
            mActivityChooserContentField.setAccessible(true);
            final View mActivityChooserContent = (View) mActivityChooserContentField.get(view);
            mActivityChooserContent.getViewTreeObserver()
                    .addOnPreDrawListener(new OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            mActivityChooserContent.setBackgroundDrawable(null);
                            return true;
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

}
