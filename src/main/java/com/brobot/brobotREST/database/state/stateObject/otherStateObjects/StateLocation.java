package com.brobot.brobotREST.database.state.stateObject.otherStateObjects;

import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.location.Position;
import com.brobot.brobotREST.database.state.stateObject.StateObject;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class StateLocation implements StateObject {

    private String name;
    private Location location;
    private StateEnum ownerStateName;
    private int staysVisibleAfterClicked = 100;
    private int timesClickedWithAction = 0;
    private Position position;
    private Map<Position.Name, Position> anchors; // just one, but defined with Map b/c it's a StateObject

    private StateLocation() {
    }

    public boolean defined() {
        return location != null;
    }

    public static class Builder {
        private String name;
        private Location location;
        private StateEnum ownerStateName;
        private Position position = new Position(0, 0);
        private Map<Position.Name, Position> anchors = new HashMap<>();

        public Builder called(String name) {
            this.name = name;
            return this;
        }

        public Builder withLocation(Location location) {
            this.location = location;
            return this;
        }

        public Builder inState(StateEnum stateName) {
            this.ownerStateName = stateName;
            return this;
        }

        public Builder setAnchor(Position.Name cornerOfRegionToDefine) {
            this.anchors.put(cornerOfRegionToDefine, position);
            return this;
        }

        public StateLocation build() {
            StateLocation stateLocation = new StateLocation();
            stateLocation.name = name;
            stateLocation.location = location;
            stateLocation.ownerStateName = ownerStateName;
            stateLocation.position = position;
            stateLocation.anchors = anchors;
            return stateLocation;
        }

    }
}
