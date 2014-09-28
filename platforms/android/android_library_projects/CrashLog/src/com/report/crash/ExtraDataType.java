
package com.report.crash;

/**
 * Enum Class to define types of extra crash data
 */
public enum ExtraDataType {
    BREADCRUM(CrashConstants.TAG_BREADCRUM, CrashConstants.VALUE_BREADCRUM),
    LEVEL(CrashConstants.TAG_LEVEL, CrashConstants.VALUE_LEVEL),
    TYPE(CrashConstants.TAG_TYPE, CrashConstants.VALUE_TYPE),
    EVENT(CrashConstants.TAG_EVENT, CrashConstants.VALUE_EVENT),
    USERID(CrashConstants.TAG_USER_ID, CrashConstants.VALUE_USER_ID);

    private String stringValue;

    private int intValue;

    private ExtraDataType(String toString,
            int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    public int toInteger() {
        return intValue;
    }
}
