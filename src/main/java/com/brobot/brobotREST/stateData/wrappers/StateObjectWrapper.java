package com.brobot.brobotREST.stateData.wrappers;

import com.brobot.brobotREST.database.state.StateObjectData;

public interface StateObjectWrapper {

    boolean doActionLeadingToStateChange();
    boolean exists();
    void print(String... strings);
    StateObjectData getStateObject();

}
