
package com.report.crash;

import com.bugsense.trace.BugSenseHandler;
import com.bugsense.trace.ExceptionCallback;

import android.content.Context;

import java.util.HashMap;

/**
 * Library to send any crash or exception messages using third party library
 */
public class CrashReport implements ExceptionCallback {

    // Context of Application
    private final Context mContext;

    // Boolean value for weather to enable crash
    private static final boolean mCrash = true;

    // Boolean value for whether Bugsense is enabled
    private boolean isEnabled = false;

    public CrashReport(Context context,
            boolean isEnabled,
            String key) {
        this.mContext = context;
        this.isEnabled = isEnabled;
        if (isEnabled) {
            initAndStart(key);
            setExceptionCallback();
        }
    }

    /**
     * Initialize and start crash reporting
     * 
     * @param mContext
     */
    private void initAndStart(String key) {
        // TODO needs to be discussed weather to send data over Wifi Only or not
        BugSenseHandler.sendDataOverWiFiOnly();
        BugSenseHandler.initAndStartSession(mContext, key);
    }

    /**
     * Set exception callback to present activity
     */
    public void setExceptionCallback() {
        if (isEnabled) {
            BugSenseHandler.setExceptionCallback(this);
        }
    }

    /**
     * Start crash report session
     * 
     * @param mContext
     */
    public void startReporting() {
        if (isEnabled) {
            BugSenseHandler.startSession(mContext);
        }
    }

    /**
     * Close crash report session
     * 
     * @param mContext
     */
    public void stopReporting() {
        if (isEnabled) {
            BugSenseHandler.closeSession(mContext);
        }
    }

    /**
     * Delete all crash report for present activity
     * 
     * @param mContext
     */
    public void destroyReport() {
        if (isEnabled) {
            BugSenseHandler.flush(mContext);
        }
    }

    /**
     * Handle request to add extra data of certain type.
     * 
     * @param extraDataType Enum type for the etra data to be added
     * @param message Message to be send
     */
    public void logExtraData(ExtraDataType extraDataType,
            String message) {
        switch (extraDataType.toInteger()) {
            case CrashConstants.VALUE_BREADCRUM:
                addBreadCrum(message);
                break;
            case CrashConstants.VALUE_TYPE:
                logCrashType(message);
                break;
            case CrashConstants.VALUE_LEVEL:
                logCrashLevel(message);
                break;
            case CrashConstants.VALUE_EVENT:
                logCustomEvent(message);
                break;
            case CrashConstants.VALUE_USER_ID:
                setUserID(message);
                break;
            default:
                break;
        }
    }

    /**
     * Add extra data for crash
     * 
     * @param key Kye value for message
     * @param message String message Value
     */
    public void logExtraData(String key,
            String message) {
        if (isEnabled) {
            BugSenseHandler.addCrashExtraData(key, message);
        }
    }

    /**
     * Add extra data from hash map, in pair of key, value
     * 
     * @param data
     */
    public void logExtraData(HashMap<String, String> map) {
        if (isEnabled && (!map.isEmpty() || map != null)) {
            BugSenseHandler.addCrashExtraMap(map);
        }

    }

    /**
     * Send crash level
     * 
     * @param message
     */
    private void logCrashLevel(String level) {
        if (isEnabled) {
            BugSenseHandler.addCrashExtraData(CrashConstants.TAG_LEVEL, level);
        }
    }

    /**
     * Remove crash level information
     */
    public void removeCrashLevel() {
        if (isEnabled) {
            BugSenseHandler.removeCrashExtraData(CrashConstants.TAG_LEVEL);
        }
    }

    /**
     * Send crash type
     * 
     * @param message
     */
    private void logCrashType(String message) {
        if (isEnabled) {
            BugSenseHandler.addCrashExtraData(CrashConstants.TAG_TYPE, message);
        }
    }

    /**
     * Remove crash type information
     */
    public void removeCrashType() {
        if (isEnabled) {
            BugSenseHandler.removeCrashExtraData(CrashConstants.TAG_TYPE);
        }
    }

    /**
     * Add a message as a point of interest to track user actions
     * 
     * @param message
     */
    private void addBreadCrum(String message) {
        if (isEnabled) {
            BugSenseHandler.leaveBreadcrumb(message);
        }
    }

    /**
     * Log a event
     */
    private void logCustomEvent(String event) {
        if (isEnabled) {
            BugSenseHandler.sendEvent(event);
        }
    }

    /**
     * Remove all crash related extra data
     */
    public void clearAllExtraData() {
        if (isEnabled) {
            BugSenseHandler.clearCrashExtraData();
        }
    }

    /**
     * Setting user identification string message
     * 
     * @param userIdentifier this can be a Email ID, a unique Key or any thing
     *            to define a distinctive User
     */
    private void setUserID(String userId) {
        if (isEnabled) {
            BugSenseHandler.setUserIdentifier(userId);
        }
    }

    /**
     * Send exception
     * 
     * @param e
     */
    public void logException(Exception e) {
        if (isEnabled) {
            BugSenseHandler.sendException(e);
        }
    }

    /**
     * Log exception with a message and key
     * 
     * @param key
     * @param message
     * @param e
     */
    public void logException(String key,
            String message,
            Exception e) {
        if (isEnabled) {
            BugSenseHandler.sendExceptionMessage(key, message, e);
        }
    }

    /**
     * Log exception with a hash map
     * 
     * @param map
     * @param e
     */
    public void logException(HashMap<String, String> map,
            Exception e) {
        if (isEnabled && (!map.isEmpty() || map != null)) {
            BugSenseHandler.sendExceptionMap(map, e);
        }
    }

    /**
     * Enable logging data to be send. In bugSense by default it sends the last
     * 5000 lines with no filter
     * 
     * @param lines
     */
    public void enableLogging() {
        if (isEnabled) {
            BugSenseHandler.setLogging(true);
        }
    }

    /**
     * Set Number of lines of log to send in report
     * 
     * @param lines
     */
    public void setLogging(int lines) {
        if (isEnabled) {
            BugSenseHandler.setLogging(lines);
        }
    }

    /**
     * Set type of log to send in report
     * 
     * @param lines
     */
    public void setLogging(LogType logType) {
        if (isEnabled) {
            BugSenseHandler.setLogging(logType.toString());
        }
    }

    /**
     * Set type of log and number of lines to send in report
     * 
     * @param lines
     */
    public void setLogging(int lines,
            LogType logType) {
        if (isEnabled) {
            BugSenseHandler.setLogging(lines, logType.toString());
        }
    }

    /**
     * Cause a forced crash.
     */
    public void forceCrash(String message) {
        if (mCrash && isEnabled) {
            throw new RuntimeException(message);
        }
    }

    /**
     * Last action on a crash
     */
    @Override
    public void lastBreath(Exception arg0) {
        logException(arg0);
    }

}
