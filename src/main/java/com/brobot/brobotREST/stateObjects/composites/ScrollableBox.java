package com.brobot.brobotREST.stateObjects.composites;

import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickStateImage;
import com.brobot.brobotREST.stateObjects.objectMethods.region.RegionScroll;
import com.brobot.brobotREST.database.state.StateRegionData;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public @Data class ScrollableBox {
    private ClickStateImage clickStateImage;
    private RegionScroll regionScroll;

    private StateRegionData boxRegion = new StateRegionData();
    private Map<BoxTabEnum, StateRIPData> tabs = new HashMap<>();

    public ScrollableBox(ClickStateImage clickStateImage, RegionScroll regionScroll) {
        this.clickStateImage = clickStateImage;
        this.regionScroll = regionScroll;
    }

    public boolean clickTab(BoxTabEnum boxTabEnum) {
        return clickStateImage.clickFirstMatch(tabs.get(boxTabEnum)) != null;
    }

    public boolean scrollDown() {
        return regionScroll.scrollDownToSeeBelow(boxRegion);
    }

    public boolean scrollUp() {
        return regionScroll.scrollUpToSeeAbove(boxRegion);
    }

}
