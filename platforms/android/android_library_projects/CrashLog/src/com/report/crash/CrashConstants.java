
package com.report.crash;

public class CrashConstants {

    // String value for MyApp
    private final static String TAG_MYAPP = "MyApp";

    // Tag for breadcrum
    public static final String TAG_BREADCRUM = "BREADCRUM";

    // Value for breadcrum
    public static final int VALUE_BREADCRUM = 0;

    // Tag for level
    public static final String TAG_LEVEL = "LEVEL";

    // Value for level
    public static final int VALUE_LEVEL = 1;

    // Tag for type
    public static final String TAG_TYPE = "TYPE";

    // Value for type
    public static final int VALUE_TYPE = 2;

    // Tag for event
    public static final String TAG_EVENT = "EVENT";

    // Value for event
    public static final int VALUE_EVENT = 3;

    // Tag for userID
    public static final String TAG_USER_ID = "USER ID";

    // Value for userID
    public static final int VALUE_USER_ID = 4;

    // Value for Verbose log type
    public static final String VALUE_VERBOSE = "*:V";

    // Value for Debug log type
    public static final String VALUE_DEBUG = "*:D";

    // Value for Info log type
    public static final String VALUE_INFO = "*:I";

    // Value for Warning log type
    public static final String VALUE_WARNING = "*:W";

    // Value for Error log type
    public static final String VALUE_ERROR = "*:E";

    // Value for Fatal log type
    public static final String VALUE_FATAL = "*:F";

    // Value for Silent log type
    public static final String VALUE_SILENT = "*:S";

    // Value for Verbose log type
    public static final String VALUE_VERBOSE_WITH_ACTIVITY_MANAGER =
            "ActivityManager:I " + TAG_MYAPP + ":V *:S";

    // Value for Debug log type
    public static final String VALUE_DEBUG_WITH_ACTIVITY_MANAGER = "ActivityManager:I " + TAG_MYAPP
            + ":D *:S";

    // Value for Info log type
    public static final String VALUE_INFO_WITH_ACTIVITY_MANAGER = "ActivityManager:I " + TAG_MYAPP
            + ":I *:S";

    // Value for Warning log type
    public static final String VALUE_WARNING_WITH_ACTIVITY_MANAGER =
            "ActivityManager:I " + TAG_MYAPP + ":W *:S";

    // Value for Error log type
    public static final String VALUE_ERROR_WITH_ACTIVITY_MANAGER = "ActivityManager:I " + TAG_MYAPP
            + ":E *:S";

    // Value for Fatal log type
    public static final String VALUE_FATAL_WITH_ACTIVITY_MANAGER = "ActivityManager:I " + TAG_MYAPP
            + ":F *:S";

    // Value for Silent log type
    public static final String VALUE_SILENT_WITH_ACTIVITY_MANAGER =
            "ActivityManager:I " + TAG_MYAPP + ":S *:S";

}
