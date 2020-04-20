package com.brobot.brobotREST.StateObjects.ObjectMethods.DefineRegion;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.Find;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.Region;
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
                          StateRegion regionToDefine, float similarity,
                          int leftBuffer, int rightBuffer, int topBuffer, int bottomBuffer,
                          boolean includeImageRegion) {
        if (mockRegion.isUseMock()) return true;
        Match match = find.findBestMatch(searchRegion, similarity, definingImage);
        if (match == null) return false;
        regionToDefine.setStateRegion(new Region(match, leftBuffer, rightBuffer, topBuffer, bottomBuffer,
                includeImageRegion));
        return true;
    }

    public boolean define(StateImage stateImage, StateRegion stateRegion,
                          int leftBuffer, int rightBuffer, int topBuffer, int bottomBuffer,
                          boolean includeImageRegion) {
        return define(stateImage.getSearchRegion(), stateImage.getStateImage(),
                stateRegion, (float)Settings.MinSimilarity,
                leftBuffer, rightBuffer, topBuffer, bottomBuffer, includeImageRegion);
    }

}
