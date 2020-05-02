package com.brobot.brobotREST.stateData.methods;

import com.brobot.brobotREST.database.state.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class StateObjectCollectionMethods {

    public Set<StateRIPData> getRIPs(StateObjectCollection collection) {
        return collection.getStateRIPs();
    }

    public Set<StateImageData> getImages(StateObjectCollection collection) {
        return collection.getStateImages();
    }

    public Set<StateRegionData> getRegions(StateObjectCollection collection) {
        return collection.getStateRegions();
    }

    public Set<StateStringData> getStrings(StateObjectCollection collection) {
        return collection.getStateStrings();
    }

    public void activateMock(StateObjectCollection collection) {
        for (StateRIPData stateRIP : getRIPs(collection)) stateRIP.setProbabilityExists(100);
    }

    public void deactivateMock(StateObjectCollection collection) {
        for (StateRIPData stateRIP : getRIPs(collection)) stateRIP.setProbabilityExists(0);
    }

    public void addStateRIPs(StateObjectCollection collection, StateRIPData... stateRIPData) {
        getRIPs(collection).addAll(Arrays.asList(stateRIPData));
    }

    public void addStateImages(StateObjectCollection collection, StateImageData... stateImageData) {
        getImages(collection).addAll(Arrays.asList(stateImageData));
    }

    public void addStateStrings(StateObjectCollection collection, StateStringData... stateStringData) {
        getStrings(collection).addAll(Arrays.asList(stateStringData));
    }

    public void addStateRegions(StateObjectCollection collection, StateRegionData... stateRegionData) {
        getRegions(collection).addAll(Arrays.asList(stateRegionData));
    }

    public List<StateObjectData> getAllStateObjects(StateObjectCollection collection) {
        List<StateObjectData> stateObjects = new ArrayList<>();
        stateObjects.addAll(getRIPs(collection));
        stateObjects.addAll(getImages(collection));
        stateObjects.addAll(getRegions(collection));
        stateObjects.addAll(getStrings(collection));
        return stateObjects;
    }

}
