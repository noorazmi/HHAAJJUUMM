package islamic.buzz.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.view.View;

public class Divider extends View
{
    private final int requestedWidth;
    private final int requestedHeight;
    private final int paddingLeft;
    private final int paddingRight;

    private final int backGroundColor;
    private final Paint paint;

    public Divider(Context context, int backGroundColor, int requestedWidth, int requestedHeight, int paddingLeft, int paddingRight)
    {
	super(context);
	//setBackgroundResource(backgroundResId);TODO Check how to use drawables in on draw method.
	setPadding(paddingLeft, 0, paddingRight, 0);
	this.requestedWidth = requestedWidth;
	this.requestedHeight = requestedHeight;
	this.paddingLeft = paddingLeft;
	this.paddingRight = paddingRight;
	this.backGroundColor = backGroundColor;
	this.paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
	int left = getPaddingLeft();
	int top = getPaddingTop();
	//int right = getWidth() - getPaddingRight();
	int bottom = getHeight() - getPaddingBottom();
	
	paint.setStyle(Style.STROKE);
	paint.setStrokeCap(Cap.ROUND);
	paint.setStrokeWidth(4);

	paint.setColor(getResources().getColor(backGroundColor));
	canvas.drawLine(left, top, requestedWidth-paddingLeft-paddingRight, bottom, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
	setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /** Helper method **/
    private int measureWidth(int widthMeasureSpec)
    {
	return getMeasurement(widthMeasureSpec, requestedWidth);
    }

    /** Helper method **/
    private int measureHeight(int heightMeasureSpec)
    {
	return getMeasurement(heightMeasureSpec, requestedHeight);
    }

    /** Helper method **/
    private int getMeasurement(int measureSpec, int requested)
    {

	int specSize = MeasureSpec.getSize(measureSpec);
	int measurement = 0;

	switch (MeasureSpec.getMode(measureSpec))
	{
	case MeasureSpec.EXACTLY:
	    // This means the width of this view has been given.
	    measurement = specSize;
	    break;
	case MeasureSpec.AT_MOST:
	    // Take the minimum of the preferred size and what
	    // we were told to be.
	    measurement = Math.min(requested, specSize);
	    break;
	default:// MeasureSpec.UNSPECIFIED The parent has not imposed any
		// constraint on the child. It can be whatever size it wants.

	    measurement = requested;
	    break;
	}

	return measurement;

    }

}
