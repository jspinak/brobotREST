package com.brobot.brobotREST.database.state.stateObject.otherStateObjects;

import com.brobot.brobotREST.database.primitives.location.Position;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.stateObject.StateObject;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.brobot.brobotREST.actions.NullState.Enum.NULL;

@Data
public class StateRegion implements StateObject {

    private String name = "";
    private Region searchRegion = new Region();
    private StateEnum ownerStateName = NULL;
    private int staysVisibleAfterClicked = 100;
    private int timesClickedWithAction = 0;
    private Position position = new Position(50, 50);
    private Map<Position.Name, Position> anchors = new HashMap<>();
    private String mockText = "mock text";

    //think about deleting this field, it may be confusing and can easily be replaced by a separate region
    //sometimes we need to select a region within the searchRegion and act on it. this Region may change frequently.
    private Region actionableRegion;

    private StateRegion() {
    }

    public int x() {
        return searchRegion.x;
    }

    public int y() {
        return searchRegion.y;
    }

    public int w() {
        return searchRegion.w;
    }

    public int h() {
        return searchRegion.h;
    }

    public boolean defined() {
        return getSearchRegion().defined();
    }

    public static class Builder {
        private String name = "";
        private Region searchRegion = new Region();
        private StateEnum ownerStateName = NULL;
        private Position position = new Position(50, 50);

        // Positions.Name: the border of the region to define
        // Position: the location in the region to use as a defining point
        private Map<Position.Name, Position> anchors = new HashMap<>();
        private String mockText = "mock text";

        private Region actionableRegion = new Region();

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

        public Builder setPointLocation(Position position) {
            this.position = position;
            return this;
        }

        public Builder addAnchor(Position.Name definedRegionBorder, Position location) {
            this.anchors.put(definedRegionBorder, location);
            return this;
        }

        public Builder setMockText(String text) {
            this.mockText = text;
            return this;
        }

        public StateRegion build() {
            StateRegion stateRegion = new StateRegion();
            stateRegion.name = name;
            stateRegion.searchRegion = searchRegion;
            stateRegion.ownerStateName = ownerStateName;
            stateRegion.position = position;
            stateRegion.anchors = anchors;
            stateRegion.mockText = mockText;
            stateRegion.actionableRegion = actionableRegion;
            return stateRegion;
        }

    }
}
