package com.brobot.brobotREST.StateObjects.BaseObjects.RIP;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickStateImage;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Exists.ExistsStateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.Region;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public @Data class StateRIP extends StateObject {
    private ExistsStateRIP existsStateRIP;
    private ClickStateImage clickStateImage;

    private boolean staysVisibleAfterClicked = false;
    private Region searchRegion;
    private Map<Region, Image> pairs = new HashMap<>();

    public StateRIP(ClickStateImage clickStateImage, ExistsStateRIP existsStateRIP, Region gameReg) {
        this.existsStateRIP = existsStateRIP;
        this.clickStateImage = clickStateImage;
        searchRegion = new Region(gameReg);
        setProbabilityExists(0); // must be triggered by another StateRIP or StateString or StateImage
    }

    public boolean isEmpty() {
        return pairs.isEmpty();
    }

    @Override
    public boolean doActionLeadingToStateChange() {
        return clickStateImage.clickFirstMatch(this) != null;
    }

    @Override
    public boolean exists() {
        return existsStateRIP.exists(this);
    }

}
