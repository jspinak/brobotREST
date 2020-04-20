package com.brobot.brobotREST.StateObjects.BaseObjects.String;

import com.brobot.brobotREST.StateObjects.ObjectMethods.String.TypeStateString;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import lombok.Data;

public @Data
class StateString extends StateObject {

    private String string;
    private TypeStateString typeStateString;

    public StateString(TypeStateString typeStateString) {
        this.typeStateString = typeStateString;
        setProbabilityExists(100); // you can always type to the screen (prob string exists)
    }

    public void addString(String string) {
        this.string = string;
    }

    @Override
    public boolean doActionLeadingToStateChange() {
        return typeStateString.string(this);
    }

    @Override
    public boolean exists() {
        return true;
    }




}
