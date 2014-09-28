
package islamic.buzz.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Extent Grid View - to Fix Scroll Issue
 * 
 * @author sanchit.gupta
 */
public class WrappedListView extends ListView {
    public WrappedListView(Context context) {
        super(context);
    }

    public WrappedListView(Context context,
            AttributeSet attrs) {
        super(context, attrs);
    }

    public WrappedListView(Context context,
            AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec,
            int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}
