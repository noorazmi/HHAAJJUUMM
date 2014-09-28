
package com.report.crash;

/**
 * Enum Class to define types of log to send
 */
public enum LogType {
    VERBOSE(CrashConstants.VALUE_VERBOSE),
    DEBUG(CrashConstants.VALUE_DEBUG),
    INFO(CrashConstants.VALUE_INFO),
    WARING(CrashConstants.VALUE_WARNING),
    ERROR(CrashConstants.VALUE_ERROR),
    FATAL(CrashConstants.VALUE_FATAL),
    SILENT(CrashConstants.VALUE_SILENT),
    VERBOSE_ACTIVITYMANAGER(CrashConstants.VALUE_VERBOSE_WITH_ACTIVITY_MANAGER),
    DEBUG_ACTIVITYMANAGER(CrashConstants.VALUE_DEBUG_WITH_ACTIVITY_MANAGER),
    INFO_ACTIVITYMANAGER(CrashConstants.VALUE_INFO_WITH_ACTIVITY_MANAGER),
    WARING_ACTIVITYMANAGER(CrashConstants.VALUE_WARNING_WITH_ACTIVITY_MANAGER),
    ERROR_ACTIVITYMANAGER(CrashConstants.VALUE_ERROR_WITH_ACTIVITY_MANAGER),
    FATAL_ACTIVITYMANAGER(CrashConstants.VALUE_FATAL_WITH_ACTIVITY_MANAGER),
    SILENT_ACTIVITYMANAGER(CrashConstants.VALUE_SILENT_WITH_ACTIVITY_MANAGER);

    private String stringValue;

    private LogType(String value) {
        stringValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }

}
