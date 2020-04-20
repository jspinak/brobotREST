package com.brobot.brobotREST.StatesSteuern;

import com.brobot.brobotREST.StateObjects.ErrorManagement.GameError;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickUntilStateImage;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateManagement {

    private StateFinder stateFinder;
    private PathFinder pathFinder;
    private GameStateRepository gameStateRepository;
    private ClickUntilStateImage clickUntilStateImage;

    public StateManagement(StateFinder stateFinder, PathFinder pathFinder,
                           GameStateRepository gameStateRepository,
                           ClickUntilStateImage clickUntilStateImage) {
        this.stateFinder = stateFinder;
        this.pathFinder = pathFinder;
        this.gameStateRepository = gameStateRepository;
        this.clickUntilStateImage = clickUntilStateImage;
    }

    public boolean openState(GameStateEnum stateToOpen, GameError gameError) {
        List<State> currentStates = stateFinder.getCurrentGameStates();
        if (currentStates.isEmpty()) {
            gameError.fehlerBeheben(); //game specific potential errors
            currentStates = stateFinder.getCurrentGameStates();
            if (currentStates.isEmpty()) { // do some other error detection (connection, computer, etc)
                // if the current state isn't found, it could be with another game, or have an error. go to the
                // start state and try to start the game again. in essense, you have an error state that leads to the start state.
                currentStates = stateFinder.getCurrentGameStates();
            }
        }
        if (!currentStates.isEmpty()) {
            return gotoState(currentStates, stateToOpen);
        }
        System.out.println(" couldn't find the game state. ");
        return false;
    }

    private boolean gotoState(List<State> fromStates, GameStateEnum toState) {
        if (fromStates.contains(toState)) return true;
        List<StateObject> stateObjectChain = pathFinder.getPathToState(fromStates, toState);
        if (stateObjectChain == null) return false;
        for (StateObject stateObject : stateObjectChain) {
            //System.out.println("gotoState, "+stateObject.getName());
            if (!clickUntilStateImage.doActionUntilStateAppears(stateObject, 3)) {
                System.out.println("state object not found.");
                return false;
            }
        }
        System.out.print("State found.");
        return true;
    }
}
