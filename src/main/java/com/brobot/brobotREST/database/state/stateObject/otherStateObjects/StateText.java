package com.brobot.brobotREST.database.state.stateObject.otherStateObjects;

import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class StateText {

    private String name;
    private Region searchRegion;
    private StateEnum ownerStateName;

    private String text;
    // if a substring is found, set expected state
    private Map<String, StateEnum> expectedState = new HashMap<>();

    private StateText() {}

    public boolean defined() { return text != null && text.length() > 0; }

    public static class Builder {
        private String name;
        private Region searchRegion;
        private StateEnum ownerStateName;
        private Map<String, StateEnum> expectedState;

        public Builder called(String name) {
            this.name = name;
            return this;
        }

        public Builder withSearchRegion(Region searchRegion) {
            this.searchRegion = searchRegion;
            return this;
        }

        public Builder inState(StateEnum stateName) {
            this.ownerStateName = stateName;
            return this;
        }

        public Builder addExpectedState(String string, StateEnum stateEnum) {
            this.expectedState.put(string, stateEnum);
            return this;
        }

        public StateText build() {
            StateText stateText = new StateText();
            stateText.name = name;
            stateText.searchRegion = searchRegion;
            stateText.ownerStateName = ownerStateName;
            stateText.expectedState = expectedState;
            return stateText;
        }

    }

}
