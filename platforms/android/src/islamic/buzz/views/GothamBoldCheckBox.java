
package islamic.buzz.views;

import islamic.buzz.util.FontUtils;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class GothamBoldCheckBox extends CheckBox {
    Typeface mGothamBoldTypeface;

    public GothamBoldCheckBox(Context context,
            AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public GothamBoldCheckBox(Context context,
            AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GothamBoldCheckBox(Context context) {
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
