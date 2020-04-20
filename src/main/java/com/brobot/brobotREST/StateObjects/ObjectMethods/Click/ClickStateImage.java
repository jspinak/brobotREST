package com.brobot.brobotREST.StateObjects.ObjectMethods.Click;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.*;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import com.brobot.brobotREST.Primatives.Region;
import org.sikuli.basics.Settings;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

@Component
public class ClickStateImage {

    private MockRegion mockRegion;
    private Click click;
    private ImageCollection imageCollection;

    public ClickStateImage(MockRegion mockRegion, Click click, ImageCollection imageCollection) {
        this.mockRegion = mockRegion;
        this.click = click;
        this.imageCollection = imageCollection;
    }

    public boolean region(StateRegion stateRegion) {
        boolean mockSucceeds = mockRegion.doActionToChangeState(stateRegion);
        if (mockRegion.isUseMock()) return mockSucceeds;
        return click.click(stateRegion.getStateRegion());
    }

    public boolean RIP(StateRIP stateRIP) {
        return clickFirstMatch(stateRIP) != null;
    }

    public Match clickFirstMatch(StateRIP stateRIP) {
        if (mockRegion.isUseMock()) System.out.print(stateRIP.getName()+"::clickFirstMatch | ");
        Match mockMatch = mockRegion.doActionToChangeState(stateRIP) ? mockRegion.getMockMatch() : null;
        if (mockRegion.isUseMock()) return mockMatch;
        return click.clickFirstMatch(stateRIP.getSearchRegion(), stateRIP.getPairs());
    }

    public boolean image(StateImage stateImage) {
        return clickBestMatch(stateImage) != null;
    }

    public boolean image(Region region, StateImage stateImage) {
        return clickBestMatch(region, stateImage) != null;
    }

    public Match clickBestMatch(StateImage stateImage) {
        return clickBestMatch((float)Settings.MinSimilarity, stateImage);
    }

    public Match clickBestMatch(float similarity, StateImage stateImage) {
        return clickBestMatch(similarity, Settings.ClickDelay, stateImage);
    }

    public Match clickFirstMatch(StateImage... stateImages) {
        return clickFirstMatch((float)Settings.MinSimilarity, stateImages);
    }

    public Match clickFirstMatch(float similarity, StateImage... stateImages) {
        return clickFirstMatch(similarity, Settings.ClickDelay, stateImages);
    }

    public Match clickFirstMatch(float similarity, double clickDelay, StateImage... stateImages) {
        Match match;
        for (StateImage stateImage : stateImages) {
            match = clickBestMatch(similarity, clickDelay, stateImage);
            if (match != null) return match;
        }
        return null;
    }

    public Match clickBestMatch(float similarity, double clickDelay, StateImage stateImage) {
        Match mockMatch = mockRegion.doActionToChangeState(stateImage) ? mockRegion.getMockMatch() : null;
        if (mockRegion.isUseMock()) return mockMatch;
        double previousClickDelay = Settings.ClickDelay;
        Settings.ClickDelay = clickDelay;
        Match match = click.clickBestMatch(stateImage.getSearchRegion(), similarity, stateImage.getStateImage());
        Settings.ClickDelay = previousClickDelay;
        return match;
    }

    public Match clickBestMatch(Region region, StateImage stateImage) {
        Match mockMatch = mockRegion.doActionToChangeState(stateImage) ? mockRegion.getMockMatch() : null;
        if (mockRegion.isUseMock()) return mockMatch;
        Match match = click.clickBestMatch(region, stateImage.getStateImage());
        return match;
    }



}
