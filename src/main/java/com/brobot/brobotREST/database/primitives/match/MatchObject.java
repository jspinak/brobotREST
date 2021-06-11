package com.brobot.brobotREST.database.primitives.match;

import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.state.stateObject.StateObject;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import lombok.Data;
import org.sikuli.script.Match;

import java.time.LocalDateTime;

@Data
public class MatchObject {

    private Match match;
    private String text = "";
    private StateObject stateObject;
    private LocalDateTime timeStamp;

    // we want to prevent the creation of a MatchObject with match = null
    public MatchObject(Match match, StateObject stateObject) throws Exception {
        if (match == null || stateObject == null)
            throw new Exception("null parameters in MatchObject are not allowed");
        this.match = match;
        this.stateObject = stateObject;
        this.timeStamp = LocalDateTime.now();
    }

    public StateEnum getState() {
        return stateObject.getOwnerStateName();
    }

    public Location getLocation() {
        return new Location(match, stateObject.getPosition());
    }
}
