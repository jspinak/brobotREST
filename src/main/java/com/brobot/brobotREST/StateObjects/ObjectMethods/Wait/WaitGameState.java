package com.brobot.brobotREST.StateObjects.ObjectMethods.Wait;

import com.brobot.brobotREST.StateObjects.State.State;
import org.springframework.stereotype.Component;

@Component
public class WaitGameState {

    private WaitStateRIP waitStateRIP;

    public WaitGameState(WaitStateRIP waitStateRIP) {
        this.waitStateRIP = waitStateRIP;
    }

    public boolean wait(double timeout, State state) {
        return waitStateRIP.wait(timeout, state.getStateRIPs()) != null;
    }
}
