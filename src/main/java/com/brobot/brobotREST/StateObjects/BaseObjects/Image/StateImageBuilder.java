package com.brobot.brobotREST.StateObjects.BaseObjects.Image;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickStateImage;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Exists.ExistsStateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObjectBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObjectsManager;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.Region;
import org.springframework.stereotype.Component;

@Component
public class StateImageBuilder implements StateObjectBuilder {

    private final ClickStateImage clickStateImage;
    private final ExistsStateImage existsStateImage;
    private StateObjectsManager stateObjectsManager;

    private StateImage stateImage;

    public StateImageBuilder(ClickStateImage clickStateImage, ExistsStateImage existsStateImage,
                             StateObjectsManager stateObjectsManager) {
        this.clickStateImage = clickStateImage;
        this.existsStateImage = existsStateImage;
        this.stateObjectsManager = stateObjectsManager;
    }

    public StateImageBuilder init(GameStateEnum gameStateEnum) {
        stateImage = new StateImage(clickStateImage, existsStateImage);
        stateImage.setCurrentState(gameStateEnum);
        stateObjectsManager.addStateImage(gameStateEnum, stateImage);
        return this;
    }

    public StateImageBuilder setName(String string) {
        stateImage.setName(string);
        return this;
    }

    public StateImageBuilder setSearchRegion(Region defineRegion) {
        stateImage.setSearchRegion(defineRegion);
        return this;
    }

    public StateImageBuilder addImage(String... imageNames) {
        stateImage.setStateImage(new Image(imageNames));
        if (stateImage.getName() == null) stateImage.setName(imageNames[0]);
        return this;
    }

    public StateImageBuilder setStaysVisibleAfterClicked() {
        stateImage.setStaysVisibleAfterClicked(true);
        return this;
    }

    public StateImageBuilder addStatesToActivate(GameStateEnum... gameStatesToActivate) {
        for (GameStateEnum gameState : gameStatesToActivate) {
            stateImage.addStatesToActivateOnClick(gameState);
            stateObjectsManager.addActivatingObject(gameState, stateImage);
        }
        return this;
    }

    public StateImageBuilder setTimeToWaitAfterAction(double secondsToWaitAfterClick) {
        stateImage.getAssociatedStates().setTimeToWaitAfterAction(secondsToWaitAfterClick);
        return this;
    }

    public StateImage build() {
        return stateImage;
    }

}
