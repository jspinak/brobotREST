package com.brobot.brobotREST.statesSteuern;

import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.stateData.wrappers.StateObjectWrapper;
import com.brobot.brobotREST.stateObjects.errorManagement.StateRecognitionError;
import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickUntilStateImage;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class StateManagement {

    private StateService stateService;
    private StateFinder stateFinder;
    private PathFinder pathFinder;
    private ClickUntilStateImage clickUntilStateImage;

    public StateManagement(StateService stateService,
                           StateFinder stateFinder, PathFinder pathFinder,
                           ClickUntilStateImage clickUntilStateImage) {
        this.stateService = stateService;
        this.stateFinder = stateFinder;
        this.pathFinder = pathFinder;
        this.clickUntilStateImage = clickUntilStateImage;
    }

    public boolean openState(StateEnum stateToOpen, StateRecognitionError stateRecognitionError) {
        List<StateData> currentStates = stateFinder.getCurrentGameStates();
        if (currentStates.isEmpty()) {
            stateRecognitionError.fehlerBeheben(); //game specific potential errors
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

    private boolean gotoState(List<StateData> fromStates, StateEnum toState) {
        if (fromStates.contains(toState)) return true;
        Set<StateObjectWrapper> stateObjectDataChain = pathFinder.getPathToState(fromStates, toState);
        if (stateObjectDataChain == null) return false;
        for (StateObjectWrapper stateObjectWrapper : stateObjectDataChain) {
            //System.out.println("gotoState, "+stateObject.getName());
            if (!clickUntilStateImage.doActionUntilStateAppears(stateObjectWrapper, 3)) {
                System.out.println("state object not found.");
                return false;
            }
        }
        System.out.print("State found.");
        return true;
    }
}
