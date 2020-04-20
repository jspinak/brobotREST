package com.brobot.brobotREST.StateObjects.ObjectMethods.DefineRegion;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import org.springframework.stereotype.Component;

@Component
public class DefineStateRegion {

    private MockRegion mockRegion;
    private DefineRegionByImage defineRegionByImage;

    public DefineStateRegion(MockRegion mockRegion, DefineRegionByImage defineRegionByImage) {
        this.mockRegion = mockRegion;
        this.defineRegionByImage = defineRegionByImage;
    }

    public boolean defineByImage(StateImage stateImage, StateRegion stateRegion) {
        if (mockRegion.isUseMock()) return mockRegion.stateObjectsExist(stateImage);
        return defineRegionByImage.define(stateImage, stateRegion);
    }

    public boolean defineByImage(StateImage stateImage, StateRegion stateRegion, float similarity) {
        if (mockRegion.isUseMock()) return mockRegion.stateObjectsExist(stateImage);
        return defineRegionByImage.define(stateImage, stateRegion, similarity);

    }
}
