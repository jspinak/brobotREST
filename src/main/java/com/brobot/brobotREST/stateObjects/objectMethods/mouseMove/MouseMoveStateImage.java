package com.brobot.brobotREST.stateObjects.objectMethods.mouseMove;

import com.brobot.brobotREST.stateObjects.sikuliWrappers.ImageCollection;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MouseMove;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.state.StateRegionData;
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

    public boolean mouseMove(StateRegionData stateRegionData, StateImageData... stateImageData) {
        if (mockRegion.isUseMock()) return mockRegion.stateObjectsExist(stateImageData);
        return mouseMove.mouseMove(stateRegionData.getStateRegion(), imageCollection.getImageArray(stateImageData));
    }

}
