package com.brobot.brobotREST.StateObjects.ObjectMethods.Wait;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockStateManagement;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class WaitStateObject {

    private WaitStateRIP waitStateRIP;
    private MockRegion mockRegion;
    private MockStateManagement mockStateManagement;

    public WaitStateObject(WaitStateRIP waitStateRIP, MockRegion mockRegion,
                           MockStateManagement mockStateManagement) {
        this.waitStateRIP = waitStateRIP;
        this.mockRegion = mockRegion;
        this.mockStateManagement = mockStateManagement;
    }

    public boolean waitForActivatedState(StateObject stateObject) {
        //System.out.println("waitforActivatedState___________");
        List<StateRIP> stateRIPs = mockStateManagement.getAllActivatedRIPs(stateObject);
        return waitStateRIP.wait(stateObject.getTimeToWaitAfterAction(), stateRIPs) != null;
    }

    public boolean waitVanish(StateObject stateObject) {
        Double timeout = stateObject.getTimeToWaitAfterAction();
        return waitVanish(stateObject, timeout);
    }

    public boolean waitVanish(StateObject stateObject, double timeout) {
        if (mockRegion.isUseMock()) return true; //assume that it goes away. different probability than that of appearing.
        LocalDateTime startTime = LocalDateTime.now();
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            if (!stateObject.exists()) return true;
        }
        return false;
    }
}
