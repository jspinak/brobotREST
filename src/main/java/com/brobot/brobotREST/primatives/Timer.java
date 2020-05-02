package com.brobot.brobotREST.primatives;

import org.springframework.stereotype.Component;

import java.time.LocalTime;
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
}
