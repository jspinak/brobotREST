package com.brobot.brobotREST.StateObjects.ObjectMethods.Wait;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.Wait;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
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

    public Match images(double timeout, StateImage... stateImages) {
        return images(timeout, (float) Settings.MinSimilarity, stateImages);
    }

    public Match images(double timeout, float similarity, StateImage... stateImages) {
        if (mockRegion.isUseMock())
            return mockRegion.stateObjectsExist(stateImages) ? mockRegion.getMockMatch() : null;
        return wait.wait(timeout, similarity, stateImages);
    }

    public boolean imagesToVanish(double timeout, StateImage... stateImages) {
        return imagesToVanish(timeout, (float) Settings.MinSimilarity, stateImages);
    }

    public boolean imagesToVanish(double timeout, float similarity, StateImage... stateImages) {
        if (mockRegion.isUseMock()) return true; //assume that it goes away. different probability than that of appearing.
        return wait.waitVanish(timeout, similarity, stateImages);
    }

}