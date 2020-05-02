package com.brobot.brobotREST.stateObjects.state.associatedStatesAndObjects;

import com.brobot.brobotREST.database.state.*;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActivatingObjectsManager {

    private Map<String, StateObjectCollection> activatingObjectsMap = new HashMap<>();
    private StateService stateService;

    public ActivatingObjectsManager(StateService stateService) {
        this.stateService = stateService;
    }

    public StateObjectCollection getActivatingObjects(String name) {
        StateObjectCollection activatingObjects = activatingObjectsMap.get(name);
        if (activatingObjects == null) return new StateObjectCollection();
        return activatingObjects;
    }

    public void addActivatingImage(String name, StateImageData stateImageData) {
        StateData state = stateService.findByName(name);
        if (state != null) {
            state.getActivatingObjects().getStateImages().add(stateImageData);
            return;
        }
        if (!activatingObjectsMap.containsKey(name))
            activatingObjectsMap.put(name, new StateObjectCollection());
        activatingObjectsMap.get(name).getStateImages().add(stateImageData);
    }

    public void addActivatingRIP(String name, StateRIPData stateRIPData) {
        StateData state = stateService.findByName(name);
        if (state != null) {
            state.getActivatingObjects().getStateRIPs().add(stateRIPData);
            return;
        }
        if (!activatingObjectsMap.containsKey(name))
            activatingObjectsMap.put(name, new StateObjectCollection());
        activatingObjectsMap.get(name).getStateRIPs().add(stateRIPData);
    }

    public void addActivatingString(String name, StateStringData stateStringData) {
        StateData state = stateService.findByName(name);
        if (state != null) {
            state.getActivatingObjects().getStateStrings().add(stateStringData);
            return;
        }
        if (!activatingObjectsMap.containsKey(name))
            activatingObjectsMap.put(name, new StateObjectCollection());
        activatingObjectsMap.get(name).getStateStrings().add(stateStringData);
    }

    public void addActivatingRegion(String name, StateRegionData stateRegionData) {
        StateData state = stateService.findByName(name);
        if (state != null) {
            state.getActivatingObjects().getStateRegions().add(stateRegionData);
            return;
        }
        if (!activatingObjectsMap.containsKey(name))
            activatingObjectsMap.put(name, new StateObjectCollection());
        activatingObjectsMap.get(name).getStateRegions().add(stateRegionData);
    }
}
