package com.brobot.brobotREST.StateObjects.Composites;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickStateImage;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Region.RegionScroll;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import org.springframework.stereotype.Component;

@Component
public class ScrollableBoxBuilder {
    private ScrollableBox scrollableBox;

    public ScrollableBoxBuilder(ClickStateImage clickStateImage, RegionScroll regionScroll) {
        scrollableBox = new ScrollableBox(clickStateImage, regionScroll);
    }

    public ScrollableBoxBuilder setBoxRegion(StateRegion boxRegion) {
        scrollableBox.setBoxRegion(boxRegion);
        return this;
    }

    public ScrollableBoxBuilder addTab(BoxTabEnum boxTabEnum, StateRIP stateRIP) {
        scrollableBox.getTabs().put(boxTabEnum, stateRIP);
        return this;
    }

    public ScrollableBox build() {
        return scrollableBox;
    }



}
