package com.brobot.brobotREST.stateObjects.objectMethods.region;

import com.brobot.brobotREST.stateObjects.sikuliWrappers.Drag;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.database.state.StateRegionData;
import com.brobot.brobotREST.primatives.enums.LocationEnum;
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

    public boolean dragInRegion(StateRegionData stateRegionData, LocationEnum from, LocationEnum to) {
        if (mockRegion.isUseMock()) return true;
        Location dragFrom = regionLocations.getLocationInsideRegion(from, stateRegionData.getStateRegion());
        Location dragTo = regionLocations.getLocationInsideRegion(to, stateRegionData.getStateRegion());
        return drag.drag(stateRegionData.getStateRegion(), dragFrom, dragTo);
    }

    public boolean dragStopInRegion(StateRegionData stateRegionData, LocationEnum from, LocationEnum to) {
        if (mockRegion.isUseMock()) return true;
        Location dragTo = regionLocations.getLocationInsideRegion(to, stateRegionData.getStateRegion());
        Location dragToStop = new Location(dragTo.x+1,dragTo.y+1);
        dragInRegion(stateRegionData, from, to);
        return drag.drag(dragTo, dragToStop);
    }

}
