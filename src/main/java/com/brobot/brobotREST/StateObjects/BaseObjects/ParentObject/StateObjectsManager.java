package com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject;

import com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects.ActivatingObjects;
import com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects.ActivatingObjectsManager;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImagesManager;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIPsManager;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegionsManager;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateStringsManager;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

@Component
public class StateObjectsManager {

    private final ActivatingObjectsManager activatingObjectsManager;
    private final StateRIPsManager stateRIPsManager;
    private final StateImagesManager stateImagesManager;
    private final StateRegionsManager stateRegionsManager;
    private final StateStringsManager stateStringsManager;

    public StateObjectsManager(ActivatingObjectsManager activatingObjectsManager,
                               StateRIPsManager stateRIPsManager,
                               StateImagesManager stateImagesManager,
                               StateRegionsManager stateRegionsManager,
                               StateStringsManager stateStringsManager) {

        this.activatingObjectsManager = activatingObjectsManager;
        this.stateRIPsManager = stateRIPsManager;
        this.stateImagesManager = stateImagesManager;
        this.stateRegionsManager = stateRegionsManager;
        this.stateStringsManager = stateStringsManager;
    }

    public void addActivatingObject(GameStateEnum gameStateEnum, StateObject stateObject) {
        activatingObjectsManager.addActivatingObject(gameStateEnum, stateObject);
    }

    public void addStateRIP(GameStateEnum gameStateEnum, StateRIP stateRIP) {
        stateRIPsManager.addStateRIP(gameStateEnum, stateRIP);
    }

    public void addStateImage(GameStateEnum gameStateEnum, StateImage stateImage) {
        stateImagesManager.addStateImage(gameStateEnum, stateImage);
    }

    public void addStateRegion(GameStateEnum gameStateEnum, StateRegion stateRegion) {
        stateRegionsManager.addStateRegion(gameStateEnum, stateRegion);
    }

    public void addStateString(GameStateEnum gameStateEnum, StateString stateString) {
        stateStringsManager.addStateString(gameStateEnum, stateString);
    }

    public void addAllObjects(State state) {
        GameStateEnum name = state.getName();
        ActivatingObjects activatingObjects = activatingObjectsManager.getActivatingObjects(name);
        for (StateObject activatingObject : activatingObjects.getActivatingObjects()) {
            state.addActivatingGameObject(activatingObject);
        }
        for (StateRIP stateRIP : stateRIPsManager.getStateRIPs(name)) {
            state.addStateRIPs(stateRIP);
        }
        for (StateImage stateImage : stateImagesManager.getStateImages(name)) {
            state.addStateImages(stateImage);
        }
        for (StateRegion stateRegion : stateRegionsManager.getStateRegions(name)) {
            state.addStateRegions(stateRegion);
        }
        for (StateString stateString : stateStringsManager.getStateStrings(name)) {
            state.addStateStrings(stateString);
        }
    }
}
