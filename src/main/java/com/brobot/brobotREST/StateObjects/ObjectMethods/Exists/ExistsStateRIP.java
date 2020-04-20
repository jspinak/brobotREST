package com.brobot.brobotREST.StateObjects.ObjectMethods.Exists;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.Exists;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExistsStateRIP {

    private final MockRegion mockRegion;
    private final Exists exists;

    public ExistsStateRIP(MockRegion mockRegion, Exists exists) {
        this.mockRegion = mockRegion;
        this.exists = exists;
    }

    public boolean exists(StateRIP stateRIP) {
        if (mockRegion.isUseMock()) {
            if (mockRegion.stateObjectExists(stateRIP)) return true;
        }
        return exists.exists(stateRIP.getSearchRegion(), stateRIP.getPairs());
    }

    public boolean exists(List<StateRIP> stateRIPs) {
        for (StateRIP stateRIP : stateRIPs) {
            if (exists(stateRIP)) return true;
        }
        return false;
    }

}
