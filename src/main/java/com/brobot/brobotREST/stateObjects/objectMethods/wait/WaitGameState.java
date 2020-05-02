package com.brobot.brobotREST.stateObjects.objectMethods.wait;

import com.brobot.brobotREST.database.state.StateData;
import org.springframework.stereotype.Component;

@Component
public class WaitGameState {

    private WaitStateRIP waitStateRIP;

    public WaitGameState(WaitStateRIP waitStateRIP) {
        this.waitStateRIP = waitStateRIP;
    }

    public boolean wait(double timeout, StateData state) {
        return waitStateRIP.wait(timeout, state.getStateObjects().getStateRIPs()) != null;
    }
}
