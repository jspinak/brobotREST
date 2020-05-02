package com.brobot.brobotREST.stateObjects.objectMethods.string;

import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.database.state.StateStringData;
import com.brobot.brobotREST.database.primatives.Region;
import org.springframework.stereotype.Component;

@Component
public class TypeStateString {

    private final MockRegion mockRegion;

    public TypeStateString(MockRegion mockRegion) {
        this.mockRegion = mockRegion;
    }

    public boolean string(StateStringData stateStringData) {
        boolean typedInCorrectState = mockRegion.doActionToChangeState(stateStringData);
        if (mockRegion.isUseMock()) return typedInCorrectState;
        return new Region().type(stateStringData.getString()) != 0;
    }

}
