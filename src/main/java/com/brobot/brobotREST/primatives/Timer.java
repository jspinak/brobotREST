package com.brobot.brobotREST.primatives;

import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

@Component
public class Timer {

    private LocalTime startTime;

    public void setStartTime() {
        startTime = LocalTime.now();
    }

    public boolean isBefore(int dayOfWeek, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        int d = calendar.get(Calendar.DAY_OF_WEEK);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        return (dayOfWeek > d || (dayOfWeek == d && (hour > h || (hour == h && minute > m))));
    }

    public boolean isAfterHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        return (h > hour);
    }

    public boolean isAfter(int dayOfWeek, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        int d = calendar.get(Calendar.DAY_OF_WEEK);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        return (dayOfWeek < d || (dayOfWeek == d && (hour < h || (hour == h && minute < m))));
    }

    public boolean isLessThanXMinutesAgo(LocalTime timeToCheck, int minutes) {
        return timeToCheck.plusMinutes(minutes).isAfter(LocalTime.now());
    }

    public long getTimeUntilInSeconds(LocalTime time1, LocalTime time2) {
        return time1.until(time2, ChronoUnit.SECONDS);
    }

    public boolean isDay(int dayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        return dayOfWeek == calendar.get(Calendar.DAY_OF_WEEK);
    }

    public boolean isXminutesAfterStartTime(LocalTime startTime, int minutes) {
        LocalTime now = LocalTime.now();
        return now.isAfter(startTime.plusMinutes(minutes));
    }
}
