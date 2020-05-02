package com.brobot.brobotREST.stateData.wrappers;

import com.brobot.brobotREST.database.state.*;
import com.brobot.brobotREST.stateData.methods.StateObjectDataMethods;
import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickStateImage;
import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickStateRIP;
import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickStateRegion;
import com.brobot.brobotREST.stateObjects.objectMethods.exists.ExistsStateImage;
import com.brobot.brobotREST.stateObjects.objectMethods.exists.ExistsStateRIP;
import com.brobot.brobotREST.stateObjects.objectMethods.string.TypeStateString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StateObjectWrapperFactory {
    private final ClickStateImage clickStateImage;
    private final ExistsStateImage existsStateImage;
    private ClickStateRIP clickStateRIP;
    private final ExistsStateRIP existsStateRIP;
    private final ClickStateRegion clickStateRegion;
    private final TypeStateString typeStateString;
    private final StateObjectDataMethods stateObjectDataMethods;

    public StateObjectWrapperFactory(ClickStateImage clickStateImage, ExistsStateImage existsStateImage,
                                     ClickStateRIP clickStateRIP, ExistsStateRIP existsStateRIP,
                                     ClickStateRegion clickStateRegion, TypeStateString typeStateString,
                                     StateObjectDataMethods stateObjectDataMethods) {
        this.clickStateImage = clickStateImage;
        this.existsStateImage = existsStateImage;
        this.clickStateRIP = clickStateRIP;
        this.existsStateRIP = existsStateRIP;
        this.clickStateRegion = clickStateRegion;
        this.typeStateString = typeStateString;
        this.stateObjectDataMethods = stateObjectDataMethods;
    }

    public StateRIPWrapper buildRIPWrapper(StateRIPData stateRIPData) {
        StateRIPWrapper newRIPWrapper = new StateRIPWrapper(
                clickStateRIP, existsStateRIP, stateObjectDataMethods);
        newRIPWrapper.setStateObject(stateRIPData);
        return newRIPWrapper;
    }

    public StateImageWrapper buildImageWrapper(StateImageData imageData) {
        StateImageWrapper newImageWrapper = new StateImageWrapper(
                clickStateImage, existsStateImage, stateObjectDataMethods);
        newImageWrapper.setStateObject(imageData);
        return newImageWrapper;
    }

    public StateStringWrapper buildStringWrapper(StateStringData stringData) {
        StateStringWrapper newStringWrapper = new StateStringWrapper(
                typeStateString, stateObjectDataMethods);
        newStringWrapper.setStateObject(stringData);
        return newStringWrapper;
    }

    public StateRegionWrapper buildRegionWrapper(StateRegionData regionData) {
        StateRegionWrapper newRegionWrapper = new StateRegionWrapper(
                clickStateRegion, stateObjectDataMethods);
        newRegionWrapper.setStateObject(regionData);
        return newRegionWrapper;
    }

    public List<StateObjectWrapper> getAllStateObjectWrappers(StateData state) {
        return getObjectWrappers(state.getStateObjects());
    }

    public List<StateObjectWrapper> getAllActivatingObjectWrappers(StateData state) {
        return getObjectWrappers(state.getActivatingObjects());
    }

    private List<StateObjectWrapper> getObjectWrappers(StateObjectCollection stateObjectCollection) {
        List<StateObjectWrapper> wrappers = new ArrayList<>();
        stateObjectCollection.getStateRIPs().forEach(
                rip -> wrappers.add(buildRIPWrapper(rip)));
        stateObjectCollection.getStateImages().forEach(
                image -> wrappers.add(buildImageWrapper(image)));
        stateObjectCollection.getStateStrings().forEach(
                string -> wrappers.add(buildStringWrapper(string)));
        stateObjectCollection.getStateRegions().forEach(
                region -> wrappers.add(buildRegionWrapper(region)));
        return wrappers;
    }
}
