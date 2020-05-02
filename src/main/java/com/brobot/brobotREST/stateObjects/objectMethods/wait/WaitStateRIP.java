package com.brobot.brobotREST.stateObjects.objectMethods.wait;

import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.Wait;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class WaitStateRIP {

    private final MockRegion mockRegion;
    private Wait wait;

    public WaitStateRIP(MockRegion mockRegion, Wait wait) {
        this.mockRegion = mockRegion;
        this.wait = wait;
    }

    public Match wait(double timeout, StateRIPData stateRIP) {
        if (mockRegion.isUseMock())
            return mockRegion.stateObjectsExist(stateRIP) ? mockRegion.getMockMatch() : null;
        return wait.wait(timeout, stateRIP);
    }

    public Match wait(double timeout, Set<StateRIPData> stateRIPs) {
        if (mockRegion.isUseMock()) {
            for (StateRIPData stateRIP : stateRIPs) {
                if (mockRegion.stateObjectsExist(stateRIP)) return mockRegion.getMockMatch();
            }
            return null;
        }
        return wait.wait(timeout, stateRIPs);
    }

    public boolean waitVanish(double timeout, StateRIPData stateRIP) {
        if (mockRegion.isUseMock()) return true; //assume that it goes away. different probability than that of appearing.
        return wait.waitVanish(timeout, stateRIP);
    }
}