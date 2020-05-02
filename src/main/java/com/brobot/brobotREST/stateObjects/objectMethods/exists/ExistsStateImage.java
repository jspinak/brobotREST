package com.brobot.brobotREST.stateObjects.objectMethods.exists;

import com.brobot.brobotREST.stateObjects.sikuliWrappers.*;
import com.brobot.brobotREST.database.state.StateImageData;
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

    public boolean exists(StateImageData stateImageData) {
        return exists((float)Settings.MinSimilarity, stateImageData);
    }

    public boolean exists(float similarity, StateImageData stateImageData) {
        if (mockRegion.isUseMock()) return mockRegion.stateObjectsExist(stateImageData);
        return exists.exists(stateImageData.getSearchRegion(), similarity, stateImageData.getImage());
    }

}
