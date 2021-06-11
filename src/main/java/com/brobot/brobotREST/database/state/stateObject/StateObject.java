package com.brobot.brobotREST.database.state.stateObject;

import com.brobot.brobotREST.database.primitives.location.Position;
import com.brobot.brobotREST.primatives.enums.StateEnum;

import java.util.Map;

public interface StateObject {

    String getName();
    StateEnum getOwnerStateName();
    Position getPosition();
    Map<Position.Name, Position> getAnchors();
    int getTimesClickedWithAction(); // times per action. resets to 0 when action is complete.
    void setTimesClickedWithAction(int times);
}
