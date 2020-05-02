package com.brobot.brobotREST.stateObjects.composites;

import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickStateImage;
import com.brobot.brobotREST.stateObjects.objectMethods.region.RegionScroll;
import com.brobot.brobotREST.database.state.StateRegionData;
import org.springframework.stereotype.Component;

@Component
public class ScrollableBoxBuilder {
    private ScrollableBox scrollableBox;

    public ScrollableBoxBuilder(ClickStateImage clickStateImage, RegionScroll regionScroll) {
        scrollableBox = new ScrollableBox(clickStateImage, regionScroll);
    }

    public ScrollableBoxBuilder setBoxRegion(StateRegionData boxRegion) {
        scrollableBox.setBoxRegion(boxRegion);
        return this;
    }

    public ScrollableBoxBuilder addTab(BoxTabEnum boxTabEnum, StateRIPData stateRIP) {
        scrollableBox.getTabs().put(boxTabEnum, stateRIP);
        return this;
    }

    public ScrollableBox build() {
        return scrollableBox;
    }



}
