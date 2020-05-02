package com.brobot.brobotREST.stateObjects.objectMethods.defineRegion;

import com.brobot.brobotREST.stateObjects.sikuliWrappers.Find;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.state.StateRegionData;
import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.primatives.Region;
import org.sikuli.basics.Settings;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

@Component
public class DefineRegionAroundImage {

    private MockRegion mockRegion;
    private Find find;

    public DefineRegionAroundImage(MockRegion mockRegion, Find find) {
        this.mockRegion = mockRegion;
        this.find = find;
    }

    public boolean define(Region searchRegion, Image definingImage,
                          StateRegionData regionToDefine, float similarity,
                          int leftBuffer, int rightBuffer, int topBuffer, int bottomBuffer,
                          boolean includeImageRegion) {
        if (mockRegion.isUseMock()) return true;
        Match match = find.findBestMatch(searchRegion, similarity, definingImage);
        if (match == null) return false;
        regionToDefine.setStateRegion(new Region(match, leftBuffer, rightBuffer, topBuffer, bottomBuffer,
                includeImageRegion));
        return true;
    }

    public boolean define(StateImageData stateImageData, StateRegionData stateRegionData,
                          int leftBuffer, int rightBuffer, int topBuffer, int bottomBuffer,
                          boolean includeImageRegion) {
        return define(stateImageData.getSearchRegion(), stateImageData.getImage(),
                stateRegionData, (float)Settings.MinSimilarity,
                leftBuffer, rightBuffer, topBuffer, bottomBuffer, includeImageRegion);
    }

}
