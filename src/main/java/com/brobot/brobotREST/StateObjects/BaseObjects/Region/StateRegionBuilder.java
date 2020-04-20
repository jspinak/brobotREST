package com.brobot.brobotREST.StateObjects.BaseObjects.Region;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickStateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObjectBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObjectsManager;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import com.brobot.brobotREST.Primatives.Region;
import org.springframework.stereotype.Component;

@Component
public class StateRegionBuilder implements StateObjectBuilder {

    private final ClickStateImage clickStateImage;
    private StateObjectsManager stateObjectsManager;

    private StateRegion stateRegion;

    public StateRegionBuilder(ClickStateImage clickStateImage,
                              StateObjectsManager stateObjectsManager) {
        this.clickStateImage = clickStateImage;
        this.stateObjectsManager = stateObjectsManager;
    }

    public StateRegionBuilder init(GameStateEnum gameStateEnum) {
        stateRegion = new StateRegion(clickStateImage);
        stateRegion.setCurrentState(gameStateEnum);
        stateObjectsManager.addStateRegion(gameStateEnum, stateRegion);
        return this;
    }

    public StateRegionBuilder setName(String name) {
        stateRegion.setName(name);
        return this;
    }

    public StateRegionBuilder setSearchRegion(Region defineRegion) {
        stateRegion.setStateRegion(defineRegion);
        return this;
    }

    public StateRegionBuilder addStatesToActivate(GameStateEnum... gameStatesToActivate) {
        for (GameStateEnum gameState : gameStatesToActivate) {
            stateRegion.addStatesToActivateOnClick(gameState);
            stateObjectsManager.addActivatingObject(gameState, stateRegion);
        }
        return this;
    }

    public StateRegionBuilder setTimeToWaitAfterAction(double secondsToWaitAfterClick) {
        stateRegion.getAssociatedStates().setTimeToWaitAfterAction(secondsToWaitAfterClick);
        return this;
    }

    public StateRegion build() {
        return stateRegion;
    }
}
