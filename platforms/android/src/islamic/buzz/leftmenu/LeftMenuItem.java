package islamic.buzz.leftmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eybsolution.islamic.buzz.R;

public class LeftMenuItem extends LinearLayout {
	public LeftMenuItem(Context context, int iconResId, String title) {
		super(context);
		LayoutInflater.from(getContext()).inflate(R.layout.left_side_menu_item, this);
		((ImageView) findViewById(R.id.menu_icon)).setImageResource(iconResId);
		((TextView) findViewById(R.id.menu_title)).setText(title);
		setTag(title);
	}

}

// public class LeftMenuItem extends View
// {
// private final static int WIDTH_PADDING = 8;
// private final static int HEIGHT_PADDING = 10;
// private final String label;
// private final int resImage;
// private final Bitmap image;
// private final InternalListener listenerAdapter = new InternalListener();
// private static final String TAG = LeftMenuItem.class.getName();
//
// public LeftMenuItem(Context context, int iconResId, String label)
// {
// super(context);
// this.label = label;
// this.resImage = iconResId;
// this.image = BitmapFactory.decodeResource(context.getResources(), resImage);
//
// setFocusable(true);
// setBackgroundColor(Color.WHITE);
//
// setOnClickListener(listenerAdapter);
// setClickable(true);
// }
//
// /**
// * The method that is called when the focus is changed to or from this view.
// */
// protected void onFocusChanged(boolean gainFocus, int direction, Rect
// previouslyFocusedRect)
// {
// if (gainFocus == true)
// {
// this.setBackgroundColor(Color.rgb(255, 165, 0));
// }
// else
// {
// this.setBackgroundColor(Color.WHITE);
// }
// }
//
// /**
// * Method called on to render the view.
// */
// protected void onDraw(Canvas canvas)
// {
// Paint textPaint = new Paint();
// textPaint.setColor(Color.BLACK);
// canvas.drawBitmap(image, WIDTH_PADDING / 2, HEIGHT_PADDING / 2, null);
// canvas.drawText(label, WIDTH_PADDING / 2, (HEIGHT_PADDING / 2) +
// image.getHeight() + 8, textPaint);
// }
//
// @Override
// protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
// {
// setMeasuredDimension(measureWidth(widthMeasureSpec),
// measureHeight(heightMeasureSpec));
// }
//
// private int measureWidth(int measureSpec)
// {
// int preferred = image.getWidth() * 2;
// return getMeasurement(measureSpec, preferred);
// }
//
// private int measureHeight(int measureSpec)
// {
//
// int preferred = image.getHeight() * 2;
// return getMeasurement(measureSpec, preferred);
// }
//
// private int getMeasurement(int measureSpec, int preferred)
// {
// int specSize = MeasureSpec.getSize(measureSpec);
// int measurement = 0;
//
// switch (MeasureSpec.getMode(measureSpec))
// {
// case MeasureSpec.EXACTLY:
// // This means the width of this view has been given.
// measurement = specSize;
// break;
// case MeasureSpec.AT_MOST:
// // Take the minimum of the preferred size and what
// // we were told to be.
// measurement = Math.min(preferred, specSize);
// break;
// default:
// measurement = preferred;
// break;
// }
//
// return measurement;
// }
//
// /**
// * Sets the listener object that is triggered when the view is clicked.
// *
// * @param newListener
// * The instance of the listener to trigger.
// */
// // public void setOnClickListener(ClickListener newListener)
// // {
// // listenerAdapter.setListener(newListener);
// // }
//
// /**
// * Internal click listener class. Translates a view�s click listener to one
// * that is more appropriate for the custom image button class.
// *
// * @author Kah
// */
// private class InternalListener implements View.OnClickListener
// {
// //private ClickListener listener = null;
//
// /**
// * Changes the listener to the given listener.
// *
// * @param newListener
// * The listener to change to.
// */
// // public void setListener(ClickListener newListener)
// // {
// // listener = newListener;
// // }
//
// @Override
// public void onClick(View v)
// {
// Toast.makeText(getContext(), TAG, Toast.LENGTH_SHORT).show();
// // if (listener != null)
//
// // {
// // listener.onClick(CustomImageButton.this);
// // }
// }
// }
//
// }
