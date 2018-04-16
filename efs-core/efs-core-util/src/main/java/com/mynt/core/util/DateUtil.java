package com.mynt.core.util;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;

public final class DateUtil {

    public static final String DATE_FORMAT = "YYYY-MMM-dd";
    public static final String DATE_TIME_FORMAT = DATE_FORMAT + " hh:mm z";
    public static final String ORDER_CODE_DATE_FORMAT = "MMYYYYdd";
    public static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_TIME_FORMAT);
    public static final String TIMEZONE_ID = "Asia/Singapore";

    public static String toDateString(DateTime dateTime) {
        return null == dateTime ? "Not set" : dateTime.toString(DATE_FORMAT);
    }

    public static String orderCodeDateString(DateTime dateTime) {
        return null == dateTime ? "Not set" : dateTime.toString(ORDER_CODE_DATE_FORMAT);
    }

    public static String toDateTimeString() {
        return new DateTime().toString(DATE_TIME_FORMAT);
    }

    public static String toDateTimeString(DateTime dateTime) {
        return dateTime.toString(DATE_TIME_FORMAT);
    }

    public static String toDateString(LocalDate start) {
        return start.toString(DATE_FORMAT);
    }

}
