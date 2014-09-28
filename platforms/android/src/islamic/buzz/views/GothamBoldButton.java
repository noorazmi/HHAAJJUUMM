
package islamic.buzz.views;

import com.kohlsphone.common.util.FontUtils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class GothamBoldButton extends Button {
    Typeface mGothamBoldTypeface;

    public GothamBoldButton(Context context,
            AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public GothamBoldButton(Context context,
            AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GothamBoldButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            mGothamBoldTypeface = FontUtils.loadTypeFace(getContext(), FontUtils.Gotham_Bold);
            setTypeface(mGothamBoldTypeface);
        }
    }
}
