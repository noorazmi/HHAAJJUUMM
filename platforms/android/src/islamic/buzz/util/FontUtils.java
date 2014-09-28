
package islamic.buzz.util;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;

import java.util.Hashtable;

public class FontUtils {

    public static final String Gotham_Bold = "Gotham-Bold.otf";

    public static final String Gotham_Book = "Gotham-Book.otf";

    private static Hashtable<String, Typeface> mFontMap = new Hashtable<String, Typeface>();

    /**
     * Loads the desired font.
     * 
     * @param context This should be application context.
     * @param fontName
     * @return
     */
    public static synchronized Typeface loadTypeFace(final Context context,
            final String fontName) {

        if (TextUtils.isEmpty(fontName)) {
            throw new IllegalArgumentException("Font name can not be NULL!");
        }

        if (mFontMap.containsKey(fontName)) {
            return mFontMap.get(fontName);
        }

        if (context == null) {
            throw new IllegalArgumentException("Context name can not be NULL!");
        }

        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);

        if (typeFace != null) {
            mFontMap.put(fontName, typeFace);
        }

        return typeFace;
    }
}
