package com.balki.util;

/**
 * @author Balkı
 * @since 23/03/2019
 */
public class TimeOperations {
    private static Long startTime = null;

    private static long getCurrentTime(){
        //return Calendar.getInstance().getTimeInMillis();
        return System.nanoTime();
    }

    public static void startTimer(){
        startTime = getCurrentTime();
    }

    public static long stopTimer(){
        if(startTime == null){
            return -1;
        }

        long diff = getCurrentTime() - startTime;
        startTime = null;
        return diff;
    }

    public static String formatDuration(long durationInMillis){
        long millis = durationInMillis % 1000;
        long second = (durationInMillis / 1000) % 60;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%03d", hour, minute, second, millis);
    }
}
