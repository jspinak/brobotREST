package com.brobot.brobotREST.StateObjects.BaseObjects.String;

import com.brobot.brobotREST.StateObjects.ObjectMethods.String.TypeStateString;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObjectBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObjectsManager;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public class StateStringBuilder implements StateObjectBuilder {
    private TypeStateString typeStateString;
    private StateObjectsManager stateObjectsManager;

    private StateString stateString;

    public StateStringBuilder(TypeStateString typeStateString, StateObjectsManager stateObjectsManager) {
        this.typeStateString = typeStateString;
        this.stateObjectsManager = stateObjectsManager;
    }

    public StateStringBuilder init(GameStateEnum gameStateEnum) {
        stateString = new StateString(typeStateString);
        stateString.setCurrentState(gameStateEnum);
        stateObjectsManager.addStateString(gameStateEnum, stateString);
        return this;
    }

    public StateStringBuilder setName(String string) {
        stateString.setName(string);
        return this;
    }

    public StateStringBuilder setString(String string) {
        stateString.addString(string);
        if (stateString.getName() == null) stateString.setName(string);
        if (string.equals(Key.ESC)) stateString.setName("esc");
        return this;
    }

    public StateStringBuilder addStatesToActivate(GameStateEnum... gameStatesToActivate) {
        for (GameStateEnum gameState : gameStatesToActivate) {
            stateString.addStatesToActivateOnClick(gameState);
            stateObjectsManager.addActivatingObject(gameState, stateString);
        }
        return this;
    }

    public StateStringBuilder setTimeToWaitAfterAction(double secondsToWaitAfterClick) {
        stateString.getAssociatedStates().setTimeToWaitAfterAction(secondsToWaitAfterClick);
        return this;
    }

    public StateString build() {
        return stateString;
    }

    public StateString buildESC(GameStateEnum stateName, GameStateEnum... activatedStates) {
        return init(stateName)
                .setString(Key.ESC)
                .addStatesToActivate(activatedStates)
                .build();
    }

}
