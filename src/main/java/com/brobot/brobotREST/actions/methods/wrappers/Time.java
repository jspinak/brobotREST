package com.brobot.brobotREST.actions.methods.wrappers;

import com.brobot.brobotREST.mock.MockStatus;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class Time {

    private final MockStatus mockStatus;

    private LocalDateTime startTime;
    private int startMocks;
    private int mocksPerSecond = 2;

    public Time(MockStatus mockStatus) {
        this.mockStatus = mockStatus;
    }

    public void setStartTime() {
        if (mockStatus.isUseMock()) startMocks = mockStatus.getMocksPerformed();
        else startTime = LocalDateTime.now();
    }

    public boolean expired(double maxWait) {
        if (mockStatus.isUseMock()) {
            //System.out.print("#mocks="+mockStatus.getMocksPerformed()+"| ");
            return (mockStatus.getMocksPerformed() - startMocks) > maxWait * mocksPerSecond;
        }
        long nanoTimeout = (long) (maxWait * Math.pow(10, 9));
        return LocalDateTime.now().isAfter(startTime.plusNanos(nanoTimeout));
    }

    public Duration getDuration() {
        if (mockStatus.isUseMock())
            return Duration.ofSeconds((mockStatus.getMocksPerformed() - startMocks) / mocksPerSecond);
        return Duration.between(startTime, LocalDateTime.now());
    }
}
