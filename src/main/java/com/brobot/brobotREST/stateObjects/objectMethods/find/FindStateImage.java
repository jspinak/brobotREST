package com.brobot.brobotREST.stateObjects.objectMethods.find;

import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.Find;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.FindRIP;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.ImageCollection;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.primatives.Region;
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

    public Match findBestMatch(Region region, StateImageData... stateImageData) {
        return findBestMatch(region, (float)Settings.MinSimilarity, stateImageData);
    }

    public Match findBestMatch(Region region, float similarity, StateImageData... stateImageData) {
        if (mockRegion.isUseMock())
            return mockRegion.stateObjectsExist(stateImageData) ? mockRegion.getMockMatch() : null;
        return find.findBestMatch(region, similarity, imageCollection.getImageArray(stateImageData));
    }

    public Match findFirstMatch(StateRIPData stateRIP) {
        if (mockRegion.isUseMock())
            return mockRegion.stateObjectExists(stateRIP) ? mockRegion.getMockMatch() : null;
        return findRIP.findFirstMatch(
                stateRIP.getSearchRegion(), stateRIP.getRegionImagePairs().getPairs());
    }

    public Match findFirstMatch(StateImageData... stateImageData) {
        return find.findFirstMatch((float)Settings.MinSimilarity, stateImageData);
    }

    public List<Match> findAll(Region searchRegion, StateImageData stateImageData) {
        return findAll(searchRegion, (float)Settings.MinSimilarity, stateImageData);
    }

    public List<Match> findAll(Region searchRegion, float similarity, StateImageData stateImageData) {
        if (mockRegion.isUseMock() && mockRegion.stateObjectExists(stateImageData))
            return mockRegion.getMockMatchList();
        return find.findAll(searchRegion, similarity, stateImageData.getImage());
    }

}