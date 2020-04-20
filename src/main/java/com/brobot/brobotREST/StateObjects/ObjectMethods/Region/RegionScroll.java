package com.brobot.brobotREST.StateObjects.ObjectMethods.Region;

import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import com.brobot.brobotREST.Primatives.Enums.LocationEnum;
import org.springframework.stereotype.Component;

@Component
public class RegionScroll {

    private RegionDrag regionDrag;

    public RegionScroll(RegionDrag regionDrag) {
        this.regionDrag = regionDrag;
    }

    public boolean scrollDownToSeeBelow(StateRegion stateRegion) {
        return regionDrag.dragStopInRegion(stateRegion, LocationEnum.BOTTOMMIDDLE, LocationEnum.TOPMIDDLE);
    }

    public boolean scrollUpToSeeAbove(StateRegion stateRegion) {
        return regionDrag.dragStopInRegion(stateRegion, LocationEnum.TOPMIDDLE, LocationEnum.BOTTOMMIDDLE);
    }
}
