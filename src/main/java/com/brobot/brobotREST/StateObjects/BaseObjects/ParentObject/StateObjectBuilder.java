package com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject;

import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;

public interface StateObjectBuilder {

    StateObjectBuilder setName(String name);
    StateObjectBuilder addStatesToActivate(GameStateEnum... gameStatesToActivate);
    StateObjectBuilder setTimeToWaitAfterAction(double secondsToWaitAfterClick);
    StateObject build();
}
