package com.brobot.brobotREST.mock;

import org.springframework.stereotype.Component;

@Component
public class MockStatus {

    private boolean useMock = false;
    private int mocksPerformed = 0;

    public boolean isUseMock() {
        return useMock;
    }

    public void setUseMock(boolean useMock) {
        this.useMock = useMock;
    }

    public void addMockPerformed() {
        mocksPerformed++;
    }

    public int getMocksPerformed() {
        return mocksPerformed;
    }

}
