package com.brobot.brobotREST.StateObjects.BaseObjects.String;

import com.brobot.brobotREST.StateObjects.ObjectMethods.String.TypeStateString;
import org.springframework.stereotype.Component;

@Component
public class StateStringFactoryGeneric {


    private TypeStateString typeStateString;

    StateStringFactoryGeneric(TypeStateString typeStateString) {
        this.typeStateString = typeStateString;
    }

    public StateString makeNewStateString(String string) {
        StateString stateString = new StateString(typeStateString);
        stateString.addString(string);
        return stateString;
    }

}
