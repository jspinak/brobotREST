package com.brobot.brobotREST.StateObjects.SikuliWrappers;

import org.sikuli.script.Match;

public class MatchProxy {
    private MockRegion mockRegion;

    Match match;

    public MatchProxy(MockRegion mockRegion) {
        this.mockRegion = mockRegion;
    }


}
