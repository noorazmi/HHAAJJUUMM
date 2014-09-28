
package islamic.buzz.toast;



import com.eybsolution.islamic.buzz.R;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomToast {

    private static final String TAG = "AppToast";

    /**
     * Backgrounds for all types of SuperToasts.
     */
    public static class Background {

        public static final int BLACK = Style.getBackground(Style.BLACK);

        public static final int GREEN = Style.getBackground(Style.GREEN);

    }

    /**
     * Animations for all types of SuperToasts.
     */
    public enum Animations {

        FADE,
        FLYIN,
        SCALE,
        POPUP

    }

    /**
     * Durations for all types of SuperToasts.
     */
    public static class Duration {

        public static final int ONE_AND_HALF = (1500);

        public static final int TWO = (2000);

        public static final int THREE_AND_HALF = (3500);

        public static final int TEN = (10000);

    }

    /**
     * Text sizes for all types of SuperToasts.
     */
    public static class TextSize {

        public static final int EXTRA_SMALL = (12);

        public static final int SMALL = (14);

        public static final int MEDIUM = (16);

        public static final int LARGE = (18);

    }

    /**
     * Positions for icons used in all types of SuperToasts.
     */
    public enum IconPosition {

        /**
         * Set the icon to the left of the text.
         */
        LEFT,

        /**
         * Set the icon to the right of the text.
         */
        RIGHT

    }

    private Animations mAnimations = Animations.FADE;

    private Context mContext;

    private int mGravity = Gravity.BOTTOM;

    private int mDuration = Duration.ONE_AND_HALF;

    private int mTypefaceStyle;

    private int mBackground;

    private int mXOffset = 0;

    private int mYOffset = 0;

    private LinearLayout mRootLayout;

    private TextView mMessageTextView;

    private View mToastView;

    private WindowManager mWindowManager;

    private WindowManager.LayoutParams mWindowManagerParams;

    /**
     * Instantiates a new {@value #TAG}.
     *
     * @param context {@link android.content.Context}
     */
    public CustomToast(Context context) {

        if (context == null) {

            throw new IllegalArgumentException(TAG + "Context is null");

        }

        this.mContext = context;

        mYOffset = context.getResources().getDimensionPixelSize(R.dimen.toast_hover);

        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mToastView = layoutInflater.inflate(R.layout.custom_toast_layout, null);

        mWindowManager = (WindowManager) mToastView.getContext()
                .getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);

        mRootLayout = (LinearLayout) mToastView.findViewById(R.id.root_layout);

        mMessageTextView = (TextView) mToastView.findViewById(R.id.message_textview);

    }

    /**
     * Instantiates a new {@value #TAG} with a specified style.
     *
     * @param context {@link android.content.Context}
     * @param style {@link com.github.johnpersano.supertoasts.Style}
     */
    public CustomToast(Context context,
            Style style) {

        if (context == null) {

            throw new IllegalArgumentException(TAG + "Context is null");

        }

        this.mContext = context;

        mYOffset = context.getResources().getDimensionPixelSize(R.dimen.toast_hover);

        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mToastView = layoutInflater.inflate(R.layout.custom_toast_layout, null);

        mWindowManager = (WindowManager) mToastView.getContext()
                .getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);

        mRootLayout = (LinearLayout) mToastView.findViewById(R.id.root_layout);

        mMessageTextView = (TextView) mToastView.findViewById(R.id.message_textview);

        this.setStyle(style);

    }

    /**
     * Shows the {@value #TAG}. If another {@value #TAG} is showing than this
     * one will be added to a queue and shown when the previous {@value #TAG} is
     * dismissed.
     */
    public void show() {

        mWindowManagerParams = new WindowManager.LayoutParams();

        mWindowManagerParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowManagerParams.width = WindowManager.LayoutParams.FILL_PARENT;
        mWindowManagerParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        mWindowManagerParams.format = PixelFormat.TRANSLUCENT;
        mWindowManagerParams.windowAnimations = getAnimation();
        mWindowManagerParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mWindowManagerParams.gravity = mGravity;
        mWindowManagerParams.x = mXOffset;
        mWindowManagerParams.y = mYOffset;

        CustomToastManager.getInstance().add(this);

    }

    /**
     * Sets the message text of the {@value #TAG}.
     *
     * @param text {@link CharSequence}
     */
    public void setText(CharSequence text) {

        mMessageTextView.setText(text);

    }

    /**
     * Returns the message text of the {@value #TAG}.
     *
     * @return {@link CharSequence}
     */
    public CharSequence getText() {

        return mMessageTextView.getText();

    }

    /**
     * Sets the message typeface style of the {@value #TAG}.
     *
     * @param typeface {@link android.graphics.Typeface} int
     */
    public void setTypefaceStyle(int typeface) {

        mTypefaceStyle = typeface;

        mMessageTextView.setTypeface(mMessageTextView.getTypeface(), typeface);

    }

    /**
     * Returns the message typeface style of the {@value #TAG}.
     *
     * @return {@link android.graphics.Typeface} int
     */
    public int getTypefaceStyle() {

        return mTypefaceStyle;

    }

    /**
     * Sets the message text color of the {@value #TAG}.
     *
     * @param textColor {@link android.graphics.Color}
     */
    public void setTextColor(int textColor) {

        mMessageTextView.setTextColor(textColor);

    }

    /**
     * Returns the message text color of the {@value #TAG}.
     *
     * @return int
     */
    public int getTextColor() {

        return mMessageTextView.getCurrentTextColor();

    }

    /**
     * Sets the text size of the {@value #TAG} message.
     *
     * @param textSize int
     */
    public void setTextSize(int textSize) {

        mMessageTextView.setTextSize(textSize);

    }

    /**
     * Sets the text size of the {@value #TAG} message.
     *
     * @param textSize int
     */
    public void setTextGravity(int gravity) {

        mMessageTextView.setGravity(gravity);

    }

    /**
     * Returns the text size of the {@value #TAG} message in pixels.
     *
     * @return float
     */
    public float getTextSize() {

        return mMessageTextView.getTextSize();

    }

    /**
     * Sets the duration that the {@value #TAG} will show.
     *
     * @param duration {@link com.CustomToast.johnpersano.supertoasts.KohlsToast.Duration}
     */
    public void setDuration(int duration) {

        Log.e(TAG, "Duration ::: " + duration);

        this.mDuration = duration;

    }

    /**
     * Returns the duration of the {@value #TAG}.
     *
     * @return int
     */
    public int getDuration() {

        return this.mDuration;

    }

    /**
     * Sets an icon resource to the {@value #TAG} with a specified position.
     *
     * @param iconResource
     *            {@link com.CustomToast.johnpersano.supertoasts.KohlsToast.Icon}
     * @param iconPosition {@link com.CustomToast.johnpersano.supertoasts.KohlsToast.IconPosition}
     */
    public void setIcon(int iconResource,
            IconPosition iconPosition) {

        if (iconPosition == IconPosition.LEFT) {

            mMessageTextView.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources()
                    .getDrawable(R.drawable.no_internet), null, null, null);

        } else if (iconPosition == IconPosition.RIGHT) {

            mMessageTextView.setCompoundDrawablesWithIntrinsicBounds(null,
                    null,
                    mContext.getResources().getDrawable(iconResource),
                    null);

        }

    }

    /**
     * Sets the background resource of the {@value #TAG}.
     *
     * @param background {@link com.CustomToast.johnpersano.supertoasts.KohlsToast.Background}
     */
    public void setBackground(int background) {

        this.mBackground = background;

        mRootLayout.setBackgroundResource(background);

    }

    /**
     * Returns the background resource of the {@value #TAG}.
     *
     * @return int
     */
    public int getBackground() {

        return this.mBackground;

    }

    /**
     * Sets the gravity of the {@value #TAG} along with x and y offsets.
     *
     * @param gravity {@link android.view.Gravity} int
     * @param xOffset int
     * @param yOffset int
     */
    public void setGravity(int gravity,
            int xOffset,
            int yOffset) {

        this.mGravity = gravity;
        this.mXOffset = xOffset;
        this.mYOffset = yOffset;

    }

    /**
     * Sets the show/hide animations of the {@value #TAG}.
     *
     * @param animations {@link com.CustomToast.johnpersano.supertoasts.KohlsToast.Animations}
     */
    public void setAnimations(Animations animations) {

        this.mAnimations = animations;

    }

    /**
     * Returns the show/hide animations of the {@value #TAG}.
     *
     * @return {@link com.CustomToast.johnpersano.supertoasts.KohlsToast.Animations}
     */
    public Animations getAnimations() {

        return this.mAnimations;

    }

    /**
     * Dismisses the {@value #TAG}.
     */
    public void dismiss() {

        CustomToastManager.getInstance().removeCustomToast(this);

    }

    /**
     * Returns the {@value #TAG} message textview.
     *
     * @return {@link android.widget.TextView}
     */
    public TextView getTextView() {

        return mMessageTextView;

    }

    /**
     * Returns the {@value #TAG} view.
     *
     * @return {@link android.view.View}
     */
    public View getView() {

        return mToastView;

    }

    /**
     * Returns true if the {@value #TAG} is showing.
     *
     * @return boolean
     */
    public boolean isShowing() {

        return mToastView != null && mToastView.isShown();

    }

    /**
     * Returns the window manager that the {@value #TAG} is attached to.
     *
     * @return {@link android.view.WindowManager}
     */
    public WindowManager getWindowManager() {

        return mWindowManager;

    }

    /**
     * Returns the window manager layout params of the {@value #TAG}.
     *
     * @return {@link android.view.WindowManager.LayoutParams}
     */
    public WindowManager.LayoutParams getWindowManagerParams() {

        return mWindowManagerParams;

    }

    /**
     * Private method used to return a specific animation for a animations enum
     */
    private int getAnimation() {

        if (mAnimations == Animations.FLYIN) {

            return android.R.style.Animation_Translucent;

        } else if (mAnimations == Animations.SCALE) {

            return android.R.style.Animation_Dialog;

        } else if (mAnimations == Animations.POPUP) {

            return android.R.style.Animation_InputMethod;

        } else {

            return android.R.style.Animation_Toast;

        }

    }

    /**
     * Private method used to set a default style
     */
    private void setStyle(Style style) {

        this.setAnimations(style.animations);
        this.setTypefaceStyle(style.typefaceStyle);
        this.setTextColor(style.textColor);
        this.setBackground(style.background);

    }

    /**
     * @param context
     * @param textCharSequence
     * @param durationInteger
     * @return
     */
    public static CustomToast create(Context context,
            CharSequence textCharSequence,
            int durationInteger) {

        CustomToast superToast = new CustomToast(context);
        superToast.setText(textCharSequence);
        superToast.setDuration(durationInteger);

        return superToast;

    }

    /**
     * @param context
     * @param textCharSequence
     * @param durationInteger
     * @param animations
     * @return
     */
    public static CustomToast create(Context context,
            CharSequence textCharSequence,
            int durationInteger,
            Animations animations) {

        final CustomToast superToast = new CustomToast(context);
        superToast.setText(textCharSequence);
        superToast.setDuration(durationInteger);
        superToast.setAnimations(animations);

        return superToast;

    }

    /**
     * @param context
     * @param textCharSequence
     * @param durationInteger
     * @param style
     * @return
     */
    public static CustomToast create(Context context,
            CharSequence textCharSequence,
            int durationInteger,
            Style style) {

        final CustomToast superToast = new CustomToast(context);
        superToast.setText(textCharSequence);
        superToast.setDuration(durationInteger);
        superToast.setStyle(style);

        return superToast;

    }

    /**
     * Dismisses and removes all showing/pending
     */
    public static void cancelAllSuperToasts() {

        CustomToastManager.getInstance().cancelAllCustomToasts();

    }

}
