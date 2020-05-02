package com.brobot.brobotREST.stateData.wrappers;

import com.brobot.brobotREST.database.state.StateStringData;
import com.brobot.brobotREST.stateData.methods.StateObjectDataMethods;
import com.brobot.brobotREST.stateObjects.objectMethods.string.TypeStateString;
import lombok.Data;

@Data
public class StateStringWrapper implements StateObjectWrapper {

    private TypeStateString typeStateString;
    private final StateObjectDataMethods stateObjectDataMethods;
    private StateStringData stateObject;

    public StateStringWrapper(TypeStateString typeStateString,
                              StateObjectDataMethods stateObjectDataMethods) {
        this.typeStateString = typeStateString;
        this.stateObjectDataMethods = stateObjectDataMethods;
    }

    public boolean doActionLeadingToStateChange() {
        return typeStateString.string(stateObject);
    }

    public boolean exists() {
        return true;
    }

    public void print(String... strings) {
        stateObjectDataMethods.print(stateObject, strings);
    }

}
