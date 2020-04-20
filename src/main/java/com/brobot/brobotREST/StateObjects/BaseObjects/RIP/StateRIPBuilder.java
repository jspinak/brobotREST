package com.brobot.brobotREST.StateObjects.BaseObjects.RIP;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickStateImage;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Exists.ExistsStateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObjectBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObjectsManager;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.Region;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateRIPBuilder implements StateObjectBuilder {

    private final ClickStateImage clickStateImage;
    private final ExistsStateRIP existsStateRIP;
    private StateObjectsManager stateObjectsManager;

    private StateRIP stateRIP;

    public StateRIPBuilder(ClickStateImage clickStateImage, ExistsStateRIP existsStateRIP,
                           StateObjectsManager stateObjectsManager) {
        this.clickStateImage = clickStateImage;
        this.existsStateRIP = existsStateRIP;
        this.stateObjectsManager = stateObjectsManager;
    }

    public StateRIPBuilder init(GameStateEnum gameStateEnum) {
        stateRIP = new StateRIP(clickStateImage, existsStateRIP, new Region());
        stateRIP.setCurrentState(gameStateEnum);
        stateObjectsManager.addStateRIP(gameStateEnum, stateRIP);
        return this;
    }

    public StateRIPBuilder setSearchRegion(Region defineRegion) {
        stateRIP.setSearchRegion(defineRegion);
        return this;
    }

    public StateRIPBuilder setName(String name) {
        stateRIP.setName(name);
        return this;
    }

    public StateRIPBuilder addRIPs(List<String> strings) {
        strings.forEach(str -> addRIPs(str));
        return this;
    }

    public StateRIPBuilder addRIPs(String... strings) {
        for (String string : strings) {
            stateRIP.getPairs().put(new Region(), new Image(string));
            if (stateRIP.getName() == null) stateRIP.setName(string);
        }
        return this;
    }

    public StateRIPBuilder setStaysVisibleAfterClicked() {
        stateRIP.setStaysVisibleAfterClicked(true);
        return this;
    }

    public StateRIPBuilder addStatesToActivate(GameStateEnum... gameStatesToActivate) {
        for (GameStateEnum gameState : gameStatesToActivate) {
            stateRIP.addStatesToActivateOnClick(gameState);
            stateObjectsManager.addActivatingObject(gameState, stateRIP);
        }
        return this;
    }

    public StateRIPBuilder setTimeToWaitAfterAction(double secondsToWaitAfterClick) {
        stateRIP.getAssociatedStates().setTimeToWaitAfterAction(secondsToWaitAfterClick);
        return this;
    }

    public StateRIP build() {
        return stateRIP;
    }

}
