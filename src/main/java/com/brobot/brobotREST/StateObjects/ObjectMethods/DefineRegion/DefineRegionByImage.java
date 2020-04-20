package com.brobot.brobotREST.StateObjects.ObjectMethods.DefineRegion;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.Find;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.Region;
import org.sikuli.basics.Settings;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

@Component
public class DefineRegionByImage {

    private MockRegion mockRegion;
    private Find find;

    public DefineRegionByImage(MockRegion mockRegion, Find find) {
        this.mockRegion = mockRegion;
        this.find = find;
    }

    public boolean define(Region searchRegion, Image definingImage,
                          StateRegion regionToDefine, float similarity) {
        if (mockRegion.isUseMock()) return true;
        Match match = find.findBestMatch(searchRegion, similarity, definingImage);
        if (match == null) return false;
        regionToDefine.setStateRegion(new Region(match));
        return true;
    }

    public boolean define(String imageLocation, StateRegion stateRegion) {
        return define(new Region(), new Image(imageLocation), stateRegion, (float)Settings.MinSimilarity);
    }

    public boolean define(StateImage stateImage, StateRegion stateRegion) {
        return define(stateImage.getSearchRegion(), stateImage.getStateImage(),
                stateRegion, (float)Settings.MinSimilarity);
    }

    public boolean define(StateImage stateImage, StateRegion stateRegion, float similarity) {
        return define(stateImage.getSearchRegion(), stateImage.getStateImage(), stateRegion, similarity);
    }

}
