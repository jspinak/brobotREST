package com.brobot.brobotREST.StateObjects.BaseObjects.Image;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickStateImage;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Exists.ExistsStateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.Primatives.*;
import lombok.Data;

import java.util.List;

public @Data
class StateImage extends StateObject {
    private ClickStateImage clickStateImage;
    private ExistsStateImage existsStateImage;

    private boolean staysVisibleAfterClicked = false;
    private Region searchRegion;
    private Image stateImage;

    public StateImage(ClickStateImage clickStateImage, ExistsStateImage existsStateImage) {
        this.clickStateImage = clickStateImage;
        this.existsStateImage = existsStateImage;
        setProbabilityExists(0);
    }

    @Override
    public boolean doActionLeadingToStateChange() {
        return clickStateImage.clickBestMatch(this) != null;
    }

    @Override
    public boolean exists() {
        return existsStateImage.exists(this);
    }

    public List<String> getImageNames() {
        return stateImage.getImageNames();
    }
}
