package com.brobot.brobotREST.StateObjects.ObjectMethods.Wait;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.Wait;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WaitStateRIP {

    private final MockRegion mockRegion;
    private Wait wait;

    public WaitStateRIP(MockRegion mockRegion, Wait wait) {
        this.mockRegion = mockRegion;
        this.wait = wait;
    }

    public Match wait(double timeout, StateRIP stateRIP) {
        if (mockRegion.isUseMock())
            return mockRegion.stateObjectsExist(stateRIP) ? mockRegion.getMockMatch() : null;
        return wait.wait(timeout, stateRIP);
    }

    public Match wait(double timeout, List<StateRIP> stateRIPs) {
        if (mockRegion.isUseMock()) {
            for (StateRIP stateRIP : stateRIPs) {
                if (mockRegion.stateObjectsExist(stateRIP)) return mockRegion.getMockMatch();
            }
            return null;
        }
        return wait.wait(timeout, stateRIPs);
    }

    public boolean waitVanish(double timeout, StateRIP stateRIP) {
        if (mockRegion.isUseMock()) return true; //assume that it goes away. different probability than that of appearing.
        return wait.waitVanish(timeout, stateRIP);
    }
}