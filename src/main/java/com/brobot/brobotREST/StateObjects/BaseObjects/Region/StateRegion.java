package com.brobot.brobotREST.StateObjects.BaseObjects.Region;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickStateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.Primatives.Region;
import lombok.Data;

public @Data
class StateRegion extends StateObject {

    private Region stateRegion;
    private ClickStateImage clickStateImage;

    public StateRegion(ClickStateImage clickStateImage) {
        this.clickStateImage = clickStateImage;
        setProbabilityExists(100); // a region can always exist (unless it's outside the screen boundaries)
    }

    public StateRegion(Region stateRegion) {
        this.stateRegion = stateRegion;
    }

    @Override
    public boolean doActionLeadingToStateChange() {
        return clickStateImage.region(this);
    }

    @Override
    public boolean exists() {
        return true;
    }
}
