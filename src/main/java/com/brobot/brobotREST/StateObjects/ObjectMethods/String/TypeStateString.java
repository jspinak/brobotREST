package com.brobot.brobotREST.StateObjects.ObjectMethods.String;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import com.brobot.brobotREST.Primatives.Region;
import org.springframework.stereotype.Component;

@Component
public class TypeStateString {

    private final MockRegion mockRegion;

    public TypeStateString(MockRegion mockRegion) {
        this.mockRegion = mockRegion;
    }

    public boolean string(StateString stateString) {
        boolean typedInCorrectState = mockRegion.doActionToChangeState(stateString);
        if (mockRegion.isUseMock()) return typedInCorrectState;
        return new Region().type(stateString.getString()) != 0;
    }

}
