package com.brobot.brobotREST.StateObjects.ObjectMethods.MouseMove;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.ImageCollection;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.MouseMove;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import org.springframework.stereotype.Component;

@Component
public class MouseMoveStateImage {

    private final MockRegion mockRegion;
    private final MouseMove mouseMove;
    private final ImageCollection imageCollection;

    public MouseMoveStateImage(MockRegion mockRegion, MouseMove mouseMove, ImageCollection imageCollection) {
        this.mockRegion = mockRegion;
        this.mouseMove = mouseMove;
        this.imageCollection = imageCollection;
    }

    public boolean mouseMove(StateRegion stateRegion, StateImage... stateImages) {
        if (mockRegion.isUseMock()) return mockRegion.stateObjectsExist(stateImages);
        return mouseMove.mouseMove(stateRegion.getStateRegion(), imageCollection.getImageArray(stateImages));
    }

}
