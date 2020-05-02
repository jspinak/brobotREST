package com.brobot.brobotREST.stateObjects.objectMethods.region;

import com.brobot.brobotREST.database.state.StateRegionData;
import com.brobot.brobotREST.primatives.enums.LocationEnum;
import org.springframework.stereotype.Component;

@Component
public class RegionScroll {

    private RegionDrag regionDrag;

    public RegionScroll(RegionDrag regionDrag) {
        this.regionDrag = regionDrag;
    }

    public boolean scrollDownToSeeBelow(StateRegionData stateRegionData) {
        return regionDrag.dragStopInRegion(stateRegionData, LocationEnum.BOTTOMMIDDLE, LocationEnum.TOPMIDDLE);
    }

    public boolean scrollUpToSeeAbove(StateRegionData stateRegionData) {
        return regionDrag.dragStopInRegion(stateRegionData, LocationEnum.TOPMIDDLE, LocationEnum.BOTTOMMIDDLE);
    }
}
