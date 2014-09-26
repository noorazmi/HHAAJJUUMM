
package islamic.buzz.util;


import com.eybsolution.islamic.buzz.BuildConfig;

import android.util.Log;

/**
 * Custom logger to implement debug flag
 */
public class Logger {

    public static void debug(String tag,
            String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void error(String tag,
            String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }
    }
}
