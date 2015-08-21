package com.xinyang.calendarview.calendar.widget;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeUtils
 *
 * @author xinyang
 */
public class TimeUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT_NOYEAR = new SimpleDateFormat("MM-dd HH:mm");

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT_ONLYDATE = new SimpleDateFormat("M-d");
    /**
     * timeStamp2Str格式转换
     */
    public static String timeStamp2DefaultOnlyDate(String time) {
        try {
            Timestamp ts = new Timestamp(Long.parseLong(time));
            return DEFAULT_DATE_FORMAT_ONLYDATE.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
    }

    /**
     * timeStamp2Str格式转换
     */
    public static String timeStamp2DefaultDateNoYear(String time) {
        try {
            Timestamp ts = new Timestamp(Long.parseLong(time));
            return DEFAULT_DATE_FORMAT_NOYEAR.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
    }

}
