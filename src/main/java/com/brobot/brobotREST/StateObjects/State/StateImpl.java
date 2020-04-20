package com.brobot.brobotREST.StateObjects.State;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Exists.ExistsGameState;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Exists.ExistsStateRIP;
import com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects.ActivatingObjects;
import com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects.CoexistingGameStates;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
//import javafx.scene.control.TreeItem;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public @Data
class StateImpl implements State {
    private ExistsStateRIP existsStateRIP;

    private GameStateEnum name;
    private List<String> stateText = new ArrayList<>();
    private StateObjects stateObjects = new StateObjects();
    private ActivatingObjects activatingObjects = new ActivatingObjects();
    private boolean onTopOfActivatingState = false;
    private CoexistingGameStates coexistingGameStates;
    //private TreeItem treeItem;
    private LocalTime lastTimeAccessed = LocalTime.of(0,0,0);

    public StateImpl(ExistsStateRIP existsStateRIP, ExistsGameState existsGameState) {
        this.existsStateRIP = existsStateRIP;
        coexistingGameStates = new CoexistingGameStates(existsGameState);
    }

    public void addStateRIPs(StateRIP... stateRIPs) {
        //System.out.print(name+": "); /////////////// testing
        stateObjects.addStateRIPs(stateRIPs);
    }

    public void addStateImages(StateImage... stateImages) {
        stateObjects.addStateImages(stateImages);
    }

    public void addStateRegions(StateRegion... stateRegions) {
        stateObjects.addStateRegions(stateRegions);
    }

    public void addStateStrings(StateString... stateStrings) {
        stateObjects.addStateStrings(stateStrings);
    }

    public void addStateText(String... strings) {
        for (String string : strings) this.stateText.add(string);
    }

    public void activateMock() {
        stateObjects.activateMock();
    }

    public void deactivateMock() {
        stateObjects.deactivateMock();
    }

    public List<StateRIP> getStateRIPs() {
        return stateObjects.getStateRIPs();
    }

    public boolean exists() {
        return existsStateRIP.exists(getStateRIPs());
    }

    public void addCoexistingGameState(GameStateEnum gameStateEnum, boolean coexistingStateAlwaysPresent) {
        coexistingGameStates.addGameState(gameStateEnum, coexistingStateAlwaysPresent);
    }

    public void addActivatingGameObject(StateObject stateObject) {
        activatingObjects.addActivatingObject(stateObject);
    }

    public List<StateObject> getAllStateObjects() {
        return stateObjects.getAllStateObjects();
    }

    public StateRIP getStateRIP(String name) {
        return stateObjects.getStateRIP(name);
    }

    public StateImage getStateImage(String name) {
        return stateObjects.getStateImage(name);
    }

    public StateString getStateString(String name) {
        return stateObjects.getStateString(name);
    }

    public StateRegion getStateRegion(String name) {
        return stateObjects.getStateRegion(name);
    }

    public void print() {
        System.out.println("__"+name+"__");
        for (StateObject stateObject : getAllStateObjects()) {
            System.out.print(stateObject.getName());
            if (!stateObject.getStatesToActivateOnClick().isEmpty()) {
                System.out.print(" -> ");
                stateObject.getStatesToActivateOnClick().forEach(gS -> System.out.print(gS + " "));
            }
            System.out.println();
        }
        //System.out.println();
    }
/*
    public TreeItem newTreeItem() {
        treeItem = new TreeItem(name);
        return treeItem;
    }

 */
}
