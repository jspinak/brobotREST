package com.brobot.brobotREST.StateObjects.ObjectMethods.Region;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.Drag;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import com.brobot.brobotREST.Primatives.Enums.LocationEnum;
import org.sikuli.script.Location;
import org.springframework.stereotype.Component;

@Component
public class RegionDrag {

    private MockRegion mockRegion;
    private RegionLocations regionLocations;
    private Drag drag;

    public RegionDrag(MockRegion mockRegion, RegionLocations regionLocations, Drag drag) {
        this.mockRegion = mockRegion;
        this.regionLocations = regionLocations;
        this.drag = drag;
    }

    public boolean dragInRegion(StateRegion stateRegion, LocationEnum from, LocationEnum to) {
        if (mockRegion.isUseMock()) return true;
        Location dragFrom = regionLocations.getLocationInsideRegion(from, stateRegion.getStateRegion());
        Location dragTo = regionLocations.getLocationInsideRegion(to, stateRegion.getStateRegion());
        return drag.drag(stateRegion.getStateRegion(), dragFrom, dragTo);
    }

    public boolean dragStopInRegion(StateRegion stateRegion, LocationEnum from, LocationEnum to) {
        if (mockRegion.isUseMock()) return true;
        Location dragTo = regionLocations.getLocationInsideRegion(to, stateRegion.getStateRegion());
        Location dragToStop = new Location(dragTo.x+1,dragTo.y+1);
        dragInRegion(stateRegion, from, to);
        return drag.drag(dragTo, dragToStop);
    }

}
