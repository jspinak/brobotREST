package com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject;

import com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects.AssociatedStates;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
//import javafx.scene.control.TreeItem;
import lombok.Data;

import java.util.List;

@Data
public abstract class StateObject {

    private String name;
    private int probabilityExists = 0;
    private AssociatedStates associatedStates = new AssociatedStates();
    //private TreeItem treeItem;

    public abstract boolean doActionLeadingToStateChange();
    public abstract boolean exists();

    public void print(String... strings) {
        if (name != null) System.out.print(getCurrentState()+"."+name);
        for (String s : strings) System.out.print(s);
    }

    public void setCurrentState(GameStateEnum gameState) {
        associatedStates.setCurrentState(gameState);
    }

    public void addStatesToActivateOnClick(GameStateEnum... gameStates) {
        for (GameStateEnum gameState : gameStates)
            associatedStates.addStatesToActivateOnClick(this, gameState);
    }

    public List<GameStateEnum> getStatesToActivateOnClick() {
        return associatedStates.getGameStatesToActivateOnClick();
    }

    public GameStateEnum getCurrentState() {
        return associatedStates.getCurrentState();
    }

    public double getTimeToWaitAfterAction() {
        return associatedStates.getTimeToWaitAfterAction();
    }
}
