package com.brobot.brobotREST.stateData.methods;

import com.brobot.brobotREST.database.state.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StateDataMethods {

    private StateObjectCollectionMethods stateObjectCollectionMethods;

    public StateDataMethods(StateObjectCollectionMethods stateObjectCollectionMethods) {
        this.stateObjectCollectionMethods = stateObjectCollectionMethods;
    }

    public void addStateRIPs(StateData state, StateRIPData... stateRIPData) {
        stateObjectCollectionMethods.addStateRIPs(state.getStateObjects(), stateRIPData);
    }

    public void addStateImages(StateData state, StateImageData... stateImageData) {
        stateObjectCollectionMethods.addStateImages(state.getStateObjects(), stateImageData);
    }

    public void addStateRegions(StateData state, StateRegionData... stateRegionData) {
        stateObjectCollectionMethods.addStateRegions(state.getStateObjects(), stateRegionData);
    }

    public void addStateStrings(StateData state, StateStringData... stateStringData) {
        stateObjectCollectionMethods.addStateStrings(state.getStateObjects(), stateStringData);
    }

    public void addStateText(StateData data, String... strings) {
        data.getStateText().addAll(Arrays.asList(strings));
    }

    public void activateMock(StateData data) {
        stateObjectCollectionMethods.activateMock(data.getStateObjects());
    }

    public void deactivateMock(StateData state) {
        stateObjectCollectionMethods.deactivateMock(state.getStateObjects());
    }

    public void addCoexistingState(StateData data, String coexistingStateName,
                                   boolean coexistingStateAlwaysPresent) {
        data.getCoexistingStates().add(
                new CoexistingState(coexistingStateName, coexistingStateAlwaysPresent));
    }

    public List<StateObjectData> getAllStateObjects(StateData state) {
        return stateObjectCollectionMethods.getAllStateObjects(state.getStateObjects());
    }

    public List<StateObjectData> getAllActivatingObjects(StateData state) {
        return stateObjectCollectionMethods.getAllStateObjects(state.getActivatingObjects());
    }

    public void addActivatingRIP(StateData state, StateRIPData stateRIP) {
        stateObjectCollectionMethods.addStateRIPs(state.getActivatingObjects(), stateRIP);
    }

    public void addActivatingImage(StateData state, StateImageData stateImage) {
        stateObjectCollectionMethods.addStateImages(state.getActivatingObjects(), stateImage);
    }

    public void addActivatingStrings(StateData state, StateStringData stateString) {
        stateObjectCollectionMethods.addStateStrings(state.getActivatingObjects(), stateString);
    }

    public void addActivatingRegion(StateData state, StateRegionData stateRegion) {
        stateObjectCollectionMethods.addStateRegions(state.getActivatingObjects(), stateRegion);
    }

    public void print(StateData data) {
        print(data, getAllStateObjects(data));
    }

    public void print(StateData stateData, List<StateObjectData> stateObjects) {
        System.out.println("__"+stateData.getName()+"__");
        for (StateObjectData stateObjectData : stateObjects) {
            System.out.print(stateObjectData.getName());
            if (!stateObjectData.getStatesActivatedOnClick().isEmpty()) {
                System.out.print(" -> ");
                stateObjectData.getStatesActivatedOnClick().forEach(gS -> System.out.print(gS + " "));
            }
            System.out.println();
        }
    }

}
