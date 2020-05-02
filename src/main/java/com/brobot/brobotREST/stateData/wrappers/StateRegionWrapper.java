package com.brobot.brobotREST.stateData.wrappers;

import com.brobot.brobotREST.database.state.StateRegionData;
import com.brobot.brobotREST.stateData.methods.StateObjectDataMethods;
import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickStateRegion;
import lombok.Data;

@Data
public class StateRegionWrapper implements StateObjectWrapper {

    private ClickStateRegion clickStateRegion;
    private final StateObjectDataMethods stateObjectDataMethods;
    private StateRegionData stateObject;

    public StateRegionWrapper(ClickStateRegion clickStateRegion,
                              StateObjectDataMethods stateObjectDataMethods) {
        this.clickStateRegion = clickStateRegion;
        this.stateObjectDataMethods = stateObjectDataMethods;
    }

    public boolean doActionLeadingToStateChange() {
        return clickStateRegion.region(stateObject);
    }

    public boolean exists() {
        return true;
    }

    public void print(String... strings) {
        stateObjectDataMethods.print(stateObject, strings);
    }

}
