package com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects;

import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data
class ActivatingObjects {

    private List<StateObject> activatingObjects = new ArrayList<>();

    public void addActivatingObject(StateObject stateObject) {
        activatingObjects.add(stateObject);
    }

    public boolean contains(StateObject stateObject) {
        return activatingObjects.contains(stateObject);
    }
}
