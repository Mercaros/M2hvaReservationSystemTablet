package com.example.lifesopriceless.myapplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtils {

    // format 24hre ex. 12:12 , 17:15
     static String HOUR_FORMAT = "HH:mm";
     static String TIMEZONE_FORMAT = "Europe/Amsterdam";


    public static String getCurrentHour() {
        TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE_FORMAT);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfHour = new SimpleDateFormat(HOUR_FORMAT);
        sdfHour.setTimeZone(timeZone);
        String hour = sdfHour.format(cal.getTime());
        return hour;
    }


    public static boolean isHourInInterval(String target, String start, String end) {
        return ((target.compareTo(start) >= 0)
                && (target.compareTo(end) <= 0));
    }


    public static boolean isNowInInterval(String start, String end) {
        return DateUtils.isHourInInterval
                (DateUtils.getCurrentHour(), start, end);
    }
}
