package com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects;

import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data
class AssociatedStates {
    private GameStateEnum currentState;
    private List<GameStateEnum> gameStatesToActivateOnClick = new ArrayList<>();
    private double timeToWaitAfterAction = .5;

    public void addStatesToActivateOnClick(StateObject stateObject, GameStateEnum... gameStates) {
        List<GameStateEnum> states = new ArrayList<>();
        for (GameStateEnum gameState : gameStates) states.add(gameState);
        addStatesToActivateOnClick(stateObject, states);
    }

    public void addStatesToActivateOnClick(StateObject stateObject, List<GameStateEnum> gameStates) {
        for (GameStateEnum gameState : gameStates) {
            gameStatesToActivateOnClick.add(gameState);
        }
    }

}
