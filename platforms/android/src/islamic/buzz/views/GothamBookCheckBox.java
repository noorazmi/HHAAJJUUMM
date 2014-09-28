
package islamic.buzz.views;

import islamic.buzz.util.FontUtils;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class GothamBookCheckBox extends CheckBox {
    Typeface mGothamBookTypeface;

    public GothamBookCheckBox(Context context,
            AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public GothamBookCheckBox(Context context,
            AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GothamBookCheckBox(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            mGothamBookTypeface = FontUtils.loadTypeFace(getContext(), FontUtils.Gotham_Book);
            setTypeface(mGothamBookTypeface);
        }
    }
}
