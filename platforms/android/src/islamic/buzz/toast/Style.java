
package islamic.buzz.toast;

import android.graphics.Color;
import android.graphics.Typeface;

import com.eybsolution.islamic.buzz.R;

public class Style {

    public static final int BLACK = 0;

    public static final int GREEN = 1;

    public CustomToast.Animations animations = CustomToast.Animations.POPUP;

    public int background = getBackground(BLACK);

    public int typefaceStyle = Typeface.NORMAL;

    public int textColor = Color.WHITE;

    public int dividerColor = Color.WHITE;

    public int buttonTextColor = Color.LTGRAY;

    /**
     * Returns a preset style.
     * 
     * @param styleType
     * @return
     */
    public static Style getStyle(int styleType) {

        final Style style = new Style();

        switch (styleType) {

            case BLACK:

                style.textColor = Color.WHITE;
                style.background = getBackground(BLACK);
                style.dividerColor = Color.WHITE;
                return style;

            case GREEN:

                style.textColor = Color.WHITE;
                style.background = getBackground(GREEN);
                style.dividerColor = Color.WHITE;
                return style;

            default:

                style.textColor = Color.WHITE;
                style.background = getBackground(BLACK);
                style.dividerColor = Color.WHITE;
                return style;

        }

    }

    /**
     * Returns a preset style with specified animations.
     * 
     * @param styleType
     * @param animations
     * @return
     */
    public static Style getStyle(int styleType,
    		CustomToast.Animations animations) {

        final Style style = new Style();
        style.animations = animations;

        switch (styleType) {

            case BLACK:

                style.textColor = Color.WHITE;
                style.background = getBackground(BLACK);
                style.dividerColor = Color.WHITE;
                return style;

            case GREEN:

                style.textColor = Color.WHITE;
                style.background = getBackground(GREEN);
                style.dividerColor = Color.WHITE;
                return style;

            default:

                style.textColor = Color.WHITE;
                style.background = getBackground(BLACK);
                style.dividerColor = Color.WHITE;
                return style;

        }

    }

    public static int getBackground(int style) {

        switch (style) {

            case BLACK:

                return (R.drawable.custom_toast_background_black);

            case GREEN:

                return (R.drawable.custom_toast_background_green);

            default:

                return (R.drawable.custom_toast_background_black);

        }

    }

}
