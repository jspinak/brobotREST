package com.brobot.brobotREST.stateObjects.objectMethods.wait;

import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.Wait;
import com.brobot.brobotREST.database.state.StateImageData;
import org.sikuli.basics.Settings;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

@Component
public class WaitStateImage {

    private final MockRegion mockRegion;
    private Wait wait;

    public WaitStateImage(MockRegion mockRegion, Wait wait) {
        this.mockRegion = mockRegion;
        this.wait = wait;
    }

    public void pause(double timeout) {
        if (mockRegion.isUseMock()) return;
        wait.wait(timeout);
    }

    public Match images(double timeout, StateImageData... stateImageData) {
        return images(timeout, (float) Settings.MinSimilarity, stateImageData);
    }

    public Match images(double timeout, float similarity, StateImageData... stateImageData) {
        if (mockRegion.isUseMock())
            return mockRegion.stateObjectsExist(stateImageData) ? mockRegion.getMockMatch() : null;
        return wait.wait(timeout, similarity, stateImageData);
    }

    public boolean imagesToVanish(double timeout, StateImageData... stateImageData) {
        return imagesToVanish(timeout, (float) Settings.MinSimilarity, stateImageData);
    }

    public boolean imagesToVanish(double timeout, float similarity, StateImageData... stateImageData) {
        if (mockRegion.isUseMock()) return true; //assume that it goes away. different probability than that of appearing.
        return wait.waitVanish(timeout, similarity, stateImageData);
    }

}