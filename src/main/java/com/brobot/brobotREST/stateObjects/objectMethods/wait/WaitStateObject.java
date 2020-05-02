package com.brobot.brobotREST.stateObjects.objectMethods.wait;

import com.brobot.brobotREST.database.state.StateObjectData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateData.wrappers.StateObjectWrapper;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockStateManagement;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

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

    public boolean waitForActivatedState(StateObjectData stateObjectData) {
        //System.out.println("waitforActivatedState___________");
        Set<StateRIPData> stateRIPs = mockStateManagement.getAllActivatedRIPs(stateObjectData);
        return waitStateRIP.wait(stateObjectData.getTimeToWaitAfterAction(), stateRIPs) != null;
    }

    public boolean waitVanish(StateObjectWrapper stateObjectWrapper, double timeout) {
        if (mockRegion.isUseMock()) return true; //assume that it goes away. different probability than that of appearing.
        LocalDateTime startTime = LocalDateTime.now();
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            if (!stateObjectWrapper.exists()) return true;
        }
        return false;
    }
}
