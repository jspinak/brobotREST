package com.brobot.brobotREST.stateObjects.objectMethods.defineRegion;

import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.state.StateRegionData;
import org.springframework.stereotype.Component;

@Component
public class DefineStateRegion {

    private MockRegion mockRegion;
    private DefineRegionByImage defineRegionByImage;

    public DefineStateRegion(MockRegion mockRegion, DefineRegionByImage defineRegionByImage) {
        this.mockRegion = mockRegion;
        this.defineRegionByImage = defineRegionByImage;
    }

    public boolean defineByImage(StateImageData stateImageData, StateRegionData stateRegionData) {
        if (mockRegion.isUseMock()) return mockRegion.stateObjectsExist(stateImageData);
        return defineRegionByImage.define(stateImageData, stateRegionData);
    }

    public boolean defineByImage(StateImageData stateImageData, StateRegionData stateRegionData, float similarity) {
        if (mockRegion.isUseMock()) return mockRegion.stateObjectsExist(stateImageData);
        return defineRegionByImage.define(stateImageData, stateRegionData, similarity);

    }
}
