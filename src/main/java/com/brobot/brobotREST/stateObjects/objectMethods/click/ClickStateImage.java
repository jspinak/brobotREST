package com.brobot.brobotREST.stateObjects.objectMethods.click;

import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.*;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.state.StateRegionData;
import com.brobot.brobotREST.database.primatives.Region;
import org.sikuli.basics.Settings;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

@Component
public class ClickStateImage {

    private MockRegion mockRegion;
    private Click click;
    private ImageCollection imageCollection;
    private ClickStateRIP clickStateRIP;

    public ClickStateImage(MockRegion mockRegion, Click click, ImageCollection imageCollection,
                           ClickStateRIP clickStateRIP) {
        this.mockRegion = mockRegion;
        this.click = click;
        this.imageCollection = imageCollection;
        this.clickStateRIP = clickStateRIP;
    }

    public boolean region(StateRegionData stateRegionData) {
        boolean mockSucceeds = mockRegion.doActionToChangeState(stateRegionData);
        if (mockRegion.isUseMock()) return mockSucceeds;
        return click.click(stateRegionData.getStateRegion());
    }

    public boolean RIP(StateRIPData stateRIPData) {
        return clickStateRIP.click(stateRIPData);
    }

    public Match clickFirstMatch(StateRIPData stateRIPData) {
        return clickStateRIP.clickFirstMatch(stateRIPData);
    }

    public boolean click(StateImageData stateImageData) {
        return clickBestMatch(stateImageData) != null;
    }

    public boolean click(Region region, StateImageData stateImageData) {
        return clickBestMatch(region, stateImageData) != null;
    }

    public Match clickBestMatch(StateImageData stateImageData) {
        return clickBestMatch((float)Settings.MinSimilarity, stateImageData);
    }

    public Match clickBestMatch(float similarity, StateImageData stateImageData) {
        return clickBestMatch(similarity, Settings.ClickDelay, stateImageData);
    }

    public Match clickFirstMatch(StateImageData... stateImageData) {
        return clickFirstMatch((float)Settings.MinSimilarity, stateImageData);
    }

    public Match clickFirstMatch(float similarity, StateImageData... stateImageData) {
        return clickFirstMatch(similarity, Settings.ClickDelay, stateImageData);
    }

    public Match clickFirstMatch(float similarity, double clickDelay, StateImageData... stateImages) {
        Match match;
        for (StateImageData stateImageData : stateImages) {
            match = clickBestMatch(similarity, clickDelay, stateImageData);
            if (match != null) return match;
        }
        return null;
    }

    public Match clickBestMatch(float similarity, double clickDelay, StateImageData stateImageData) {
        Match mockMatch = mockRegion.doActionToChangeState(stateImageData) ? mockRegion.getMockMatch() : null;
        if (mockRegion.isUseMock()) return mockMatch;
        double previousClickDelay = Settings.ClickDelay;
        Settings.ClickDelay = clickDelay;
        Match match = click.clickBestMatch(
                stateImageData.getSearchRegion(), similarity, stateImageData.getImage());
        Settings.ClickDelay = previousClickDelay;
        return match;
    }

    public Match clickBestMatch(Region region, StateImageData stateImageData) {
        Match mockMatch = mockRegion.doActionToChangeState(stateImageData) ? mockRegion.getMockMatch() : null;
        if (mockRegion.isUseMock()) return mockMatch;
        Match match = click.clickBestMatch(region, stateImageData.getImage());
        return match;
    }

}
