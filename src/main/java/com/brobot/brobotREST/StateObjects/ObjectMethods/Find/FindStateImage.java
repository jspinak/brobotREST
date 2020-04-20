package com.brobot.brobotREST.StateObjects.ObjectMethods.Find;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.Find;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.FindRIP;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.ImageCollection;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.Primatives.Region;
import org.sikuli.basics.Settings;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindStateImage {

    private final MockRegion mockRegion;
    private final Find find;
    private FindRIP findRIP;
    private final ImageCollection imageCollection;

    public FindStateImage(MockRegion mockRegion, Find find, FindRIP findRIP, ImageCollection imageCollection) {
        this.mockRegion = mockRegion;
        this.find = find;
        this.findRIP = findRIP;
        this.imageCollection = imageCollection;
    }

    public Match findBestMatch(Region region, StateImage... stateImages) {
        return findBestMatch(region, (float)Settings.MinSimilarity, stateImages);
    }

    public Match findBestMatch(Region region, float similarity, StateImage... stateImages) {
        if (mockRegion.isUseMock())
            return mockRegion.stateObjectsExist(stateImages) ? mockRegion.getMockMatch() : null;
        return find.findBestMatch(region, similarity, imageCollection.getImageArray(stateImages));
    }

    public Match findFirstMatch(StateRIP stateRIP) {
        if (mockRegion.isUseMock())
            return mockRegion.stateObjectExists(stateRIP) ? mockRegion.getMockMatch() : null;
        return findRIP.findFirstMatch(stateRIP.getSearchRegion(), stateRIP.getPairs());
    }

    public Match findFirstMatch(StateImage... stateImages) {
        return find.findFirstMatch((float)Settings.MinSimilarity, stateImages);
    }

    public List<Match> findAll(Region searchRegion, StateImage stateImage) {
        return findAll(searchRegion, (float)Settings.MinSimilarity, stateImage);
    }

    public List<Match> findAll(Region searchRegion, float similarity, StateImage stateImage) {
        if (mockRegion.isUseMock() && mockRegion.stateObjectExists(stateImage))
            return mockRegion.getMockMatchList();
        return find.findAll(searchRegion, similarity, stateImage.getStateImage());
    }

}