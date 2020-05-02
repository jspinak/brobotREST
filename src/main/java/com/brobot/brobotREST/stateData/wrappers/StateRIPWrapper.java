package com.brobot.brobotREST.stateData.wrappers;

import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateData.methods.StateObjectDataMethods;
import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickStateRIP;
import com.brobot.brobotREST.stateObjects.objectMethods.exists.ExistsStateRIP;
import lombok.Data;

@Data
public class StateRIPWrapper implements StateObjectWrapper {
    private final ClickStateRIP clickStateRIP;
    private final ExistsStateRIP existsStateRIP;
    private StateObjectDataMethods stateObjectDataMethods;

    private StateRIPData stateObject;

    public StateRIPWrapper(ClickStateRIP clickStateRIP, ExistsStateRIP existsStateRIP,
                           StateObjectDataMethods stateObjectDataMethods) {
        this.clickStateRIP = clickStateRIP;
        this.existsStateRIP = existsStateRIP;
        this.stateObjectDataMethods = stateObjectDataMethods;
    }

    public boolean doActionLeadingToStateChange() {
        return clickStateRIP.clickFirstMatch(stateObject) != null;
    }

    public boolean exists() {
        return existsStateRIP.exists(stateObject);
    }

    public void print(String... strings) {
        stateObjectDataMethods.print(stateObject, strings);
    }

}
