
package islamic.buzz.toast;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* Manages the life of a CustomToast. Initially copied from the Crouton library */
public class CustomToastManager extends Handler {

    @SuppressWarnings("UnusedDeclaration")
    private static final String TAG = "CustomToastManager";

    /* Potential messages for the handler to send * */
    private static final class Messages {

        /* Hexadecimal numbers that represent acronyms for the operation * */
        private static final int DISPLAY_CUSTOM_TOAST = 0x445354;

        private static final int ADD_CUSTOM_TOAST = 0x415354;

        private static final int REMOVE_CUSTOM_TOAST = 0x525354;

    }

    private static CustomToastManager mManagerCustomToast;

    private final Queue<CustomToast> mQueue;

    /* Private method to create a new list if the manager is being initialized */
    private CustomToastManager() {

        mQueue = new LinkedBlockingQueue<CustomToast>();

    }

    /*
     * Singleton method to ensure all CustomToasts are passed through the same
     * manager
     */
    protected static synchronized CustomToastManager getInstance() {

        if (mManagerCustomToast != null) {

            return mManagerCustomToast;

        } else {

            mManagerCustomToast = new CustomToastManager();

            return mManagerCustomToast;

        }

    }

    /* Add CustomToast to queue and try to show it */
    protected void add(CustomToast CustomToast) {

        /* Add CustomToast to queue and try to show it */
        mQueue.add(CustomToast);
        this.showNextCustomToast();

    }

    /* Shows the next CustomToast in the list */
    private void showNextCustomToast() {

        if (mQueue.isEmpty()) {

            /* There is no CustomToast to display next */

            return;

        }

        /* Get next CustomToast in the queue */
        final CustomToast CustomToast = mQueue.peek();

        /*
         * Show CustomToast if none are showing (not sure why this works but it
         * does)
         */
        if (!CustomToast.isShowing()) {

            final Message message = obtainMessage(Messages.ADD_CUSTOM_TOAST);
            message.obj = CustomToast;
            sendMessage(message);

        } else {

            sendMessageDelayed(CustomToast, Messages.DISPLAY_CUSTOM_TOAST, getDuration(CustomToast));

        }

    }

    /* Show/dismiss a CustomToast after a specific duration */
    private void sendMessageDelayed(CustomToast CustomToast,
            final int messageId,
            final long delay) {

        Message message = obtainMessage(messageId);
        message.obj = CustomToast;
        sendMessageDelayed(message, delay);

    }

    /* Get duration and add one second to compensate for show/hide animations */
    private long getDuration(CustomToast CustomToast) {

        long duration = CustomToast.getDuration();
        duration += 1000;

        return duration;

    }

    @Override
    public void handleMessage(Message message) {

        final CustomToast CustomToast = (CustomToast) message.obj;

        switch (message.what) {

            case Messages.DISPLAY_CUSTOM_TOAST:

                showNextCustomToast();

                break;

            case Messages.ADD_CUSTOM_TOAST:

                displayCustomToast(CustomToast);

                break;

            case Messages.REMOVE_CUSTOM_TOAST:

                removeCustomToast(CustomToast);

                break;

            default: {

                super.handleMessage(message);

                break;

            }

        }

    }

    /* Displays a CustomToast */
    private void displayCustomToast(CustomToast CustomToast) {

        if (CustomToast.isShowing()) {

            /* If the CustomToast is already showing do not show again */

            return;

        }

        final WindowManager windowManager = CustomToast.getWindowManager();

        final View toastView = CustomToast.getView();

        final WindowManager.LayoutParams params = CustomToast.getWindowManagerParams();

        if (windowManager != null) {

            windowManager.addView(toastView, params);

        }

        sendMessageDelayed(CustomToast,
                Messages.REMOVE_CUSTOM_TOAST,
                CustomToast.getDuration() + 500);

    }

    /* Hide and remove the CustomToast */
    protected void removeCustomToast(CustomToast CustomToast) {

        final WindowManager windowManager = CustomToast.getWindowManager();

        final View toastView = CustomToast.getView();

        if (windowManager != null) {

            mQueue.poll();

            windowManager.removeView(toastView);

            sendMessageDelayed(CustomToast, Messages.DISPLAY_CUSTOM_TOAST, 500);

        }

    }

    /* Cancels/removes all showing pending CustomToasts */
    protected void cancelAllCustomToasts() {

        removeMessages(Messages.ADD_CUSTOM_TOAST);
        removeMessages(Messages.DISPLAY_CUSTOM_TOAST);
        removeMessages(Messages.REMOVE_CUSTOM_TOAST);

        for (CustomToast CustomToast : mQueue) {

            if (CustomToast.isShowing()) {

                CustomToast.getWindowManager().removeView(CustomToast.getView());

            }

        }

        mQueue.clear();

    }

}
