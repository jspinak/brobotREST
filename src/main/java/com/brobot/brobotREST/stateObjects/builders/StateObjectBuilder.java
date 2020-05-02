package com.brobot.brobotREST.stateObjects.builders;

import com.brobot.brobotREST.primatives.enums.StateEnum;

public interface StateObjectBuilder {

    StateObjectBuilder setName(String name);
    StateObjectBuilder addStatesToActivate(StateEnum... gameStatesToActivate);
    StateObjectBuilder setTimeToWaitAfterAction(double secondsToWaitAfterClick);
    //StateObjectData build();
}
