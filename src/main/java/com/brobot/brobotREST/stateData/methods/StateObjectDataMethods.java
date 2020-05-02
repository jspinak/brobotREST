package com.brobot.brobotREST.stateData.methods;

import com.brobot.brobotREST.database.state.StateObjectData;
import org.springframework.stereotype.Component;

@Component
public class StateObjectDataMethods {

    public void print(StateObjectData stateObject, String... strings) {
        String name = stateObject.getName();
        if (name != null)
            System.out.print(stateObject.getOwnerStateName()+"."+name);
        for (String s : strings) System.out.print(s);
    }

    public void setName(StateObjectData stateObject, String imageName) {
        stateObject.setName(imageName);
    }

    public boolean setNameIfNull(StateObjectData stateObject, String name) {
        if (stateObject.getName() != null) return false;
        stateObject.setName(name);
        return true;
    }

    public String getName(StateObjectData stateObject) {
        return stateObject.getName();
    }

    public void setOwnerName(StateObjectData stateObject, String ownerName) {
        stateObject.setOwnerStateName(ownerName);
    }

    public void setStaysVisibleAfterClicked(StateObjectData stateObject, boolean staysVisible) {
        stateObject.setStaysVisibleAfterClicked(staysVisible);
    }

    public void addStateToActivate(StateObjectData stateObject, String stateToActivate) {
        stateObject.getStatesActivatedOnClick().add(stateToActivate);
    }

    public void setTimeToWaitAfterAction(StateObjectData stateObject, double timeToWait) {
        stateObject.setTimeToWaitAfterAction(timeToWait);
    }

}
