package com.brobot.brobotREST.StatesSteuern;

import com.brobot.brobotREST.StateObjects.State.State;
import org.springframework.stereotype.Component;

@Component
public class Memory {

    // individual images and RIPs still need to have an expected state on region.
    // the reason expected state needs to be so granular is that, when mocked, the base methods for region, exists, etc.
    // return true or false based on the expected state. expected state can change with one region.
    // if this were determined only by open and close methods, a lot of these methods would return false
    // they would return false because they wouldn't record the change in state between, for example,
    // opening a dialog box (that lives in a different state) and clicking the confirm button on this dialog box.
    // the dialog box does not exist in the original state.

    private State expectedState;

    public void setExpectedState(State expectedState) {
        this.expectedState = expectedState;
    }

    public State getExpectedState() {
        return expectedState;
    }
}
