package com.brobot.brobotREST.StateObjects.State;

import com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects.ActivatingObjects;
import com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects.CoexistingGameStates;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
//import javafx.scene.control.TreeItem;

import java.util.List;

public interface State {

    void addStateText(String... strings);
    void addStateImages(StateImage... stateImages);
    void addStateRIPs(StateRIP... stateRIPS);
    void addStateRegions(StateRegion... stateRegions);
    void addStateStrings(StateString... stateStrings);
    void activateMock();
    void deactivateMock();
    List<StateRIP> getStateRIPs();
    List<StateObject> getAllStateObjects();
    List<String> getStateText();
    //List<StateImage> getStateImages();
    ActivatingObjects getActivatingObjects();
    boolean exists();
    boolean isOnTopOfActivatingState();
    void setOnTopOfActivatingState(boolean isOnTop);
    CoexistingGameStates getCoexistingGameStates();
    void addCoexistingGameState(GameStateEnum gameState, boolean alwaysPresent);
    void addActivatingGameObject(StateObject stateObject);
    GameStateEnum getName();
    StateRIP getStateRIP(String name);
    StateImage getStateImage(String name);
    StateString getStateString(String name);
    StateRegion getStateRegion(String name);
    void print();
    //TreeItem getTreeItem();
    //void setTreeItem(TreeItem treeItem);
    //TreeItem newTreeItem();

    /*
    GameState getExpectedStateAfterClose();
    boolean exists();
    boolean textExists(String textOnScreen);
    boolean doAll();
    boolean hasStateRegion(StateRegion stateRegion);
    boolean hasStateRIP(StateRIP stateRIP);
    List<StateRIP> getStateRIPs();
    void activateMock();
    */
}
