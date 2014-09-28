
package islamic.buzz.error;

import com.kohlsphone.BuildConfig;
import com.kohlsphone.common.util.Logger;
import com.kohlsphone.framework.view.activity.ErrorScreenActivity;

import android.app.Activity;
import android.content.Intent;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Capture all uncaught exceptions
 */
public class UnCaughtException implements UncaughtExceptionHandler {
    private final Activity mActivity;

    public UnCaughtException(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void uncaughtException(Thread thread,
            Throwable ex) {
        Logger.error("EXCEPTION", "Application Crashed");
        if (BuildConfig.DEBUG) {
            ex.printStackTrace();
        }
        Intent intent = new Intent(mActivity.getBaseContext(), ErrorScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mActivity.startActivity(intent);
        mActivity.finish();
        // TODO (Sanchit/Ankit) need to look into this
        System.exit(0);
    }
}
