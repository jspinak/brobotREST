package com.brobot.brobotREST.stateData.wrappers;

import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.stateData.methods.StateObjectDataMethods;
import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickStateImage;
import com.brobot.brobotREST.stateObjects.objectMethods.exists.ExistsStateImage;
import lombok.Data;

@Data
public class StateImageWrapper implements StateObjectWrapper {

    private final ClickStateImage clickStateImage;
    private final ExistsStateImage existsStateImage;
    private final StateObjectDataMethods stateObjectDataMethods;
    private StateImageData stateObject;

    public StateImageWrapper(ClickStateImage clickStateImage, ExistsStateImage existsStateImage,
                             StateObjectDataMethods stateObjectDataMethods) {
        this.clickStateImage = clickStateImage;
        this.existsStateImage = existsStateImage;
        this.stateObjectDataMethods = stateObjectDataMethods;
    }

    public boolean doActionLeadingToStateChange() {
        return clickStateImage.clickFirstMatch(stateObject) != null;
    }

    public boolean exists() {
        return existsStateImage.exists(stateObject);
    }

    public void print(String... strings) {
        stateObjectDataMethods.print(stateObject, strings);
    }

}
