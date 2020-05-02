package com.brobot.brobotREST.stateObjects.objectMethods.exists;

import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.Exists;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ExistsStateRIP {

    private final MockRegion mockRegion;
    private final Exists exists;

    public ExistsStateRIP(MockRegion mockRegion, Exists exists) {
        this.mockRegion = mockRegion;
        this.exists = exists;
    }

    public boolean exists(StateRIPData stateRIPData) {
        if (mockRegion.isUseMock()) {
            return mockRegion.stateObjectExists(stateRIPData);
        }
        return exists.exists(stateRIPData.getSearchRegion(), stateRIPData.getRegionImagePairs().getPairs());
    }

    public boolean exists(Set<StateRIPData> stateRIPs) {
        for (StateRIPData stateRIPData : stateRIPs) {
            if (exists(stateRIPData)) return true;
        }
        return false;
    }

}
