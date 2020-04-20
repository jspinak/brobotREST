package com.brobot.brobotREST.StateObjects.ObjectMethods.Exists;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.*;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import org.sikuli.basics.Settings;
import org.springframework.stereotype.Component;

@Component
public class ExistsStateImage {

    private final MockRegion mockRegion;
    private final Exists exists;
    private ImageCollection imageCollection;

    public ExistsStateImage(MockRegion mockRegion, Exists exists, ImageCollection imageCollection) {
        this.mockRegion = mockRegion;
        this.exists = exists;
        this.imageCollection = imageCollection;
    }

    public boolean exists(StateImage stateImage) {
        return exists((float)Settings.MinSimilarity, stateImage);
    }

    public boolean exists(float similarity, StateImage stateImage) {
        if (mockRegion.isUseMock()) return mockRegion.stateObjectsExist(stateImage);
        return exists.exists(stateImage.getSearchRegion(), similarity, stateImage.getStateImage());
    }

}
