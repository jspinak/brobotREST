package com.brobot.brobotREST.StateObjects.Composites;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickStateImage;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Region.RegionScroll;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public @Data class ScrollableBox {
    private ClickStateImage clickStateImage;
    private RegionScroll regionScroll;

    private StateRegion boxRegion = new StateRegion(clickStateImage);
    private Map<BoxTabEnum, StateRIP> tabs = new HashMap<>();

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
