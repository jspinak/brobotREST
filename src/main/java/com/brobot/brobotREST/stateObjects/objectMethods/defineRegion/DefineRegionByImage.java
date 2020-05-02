package com.brobot.brobotREST.stateObjects.objectMethods.defineRegion;

import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.Find;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.state.StateRegionData;
import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.primatives.Region;
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
                          StateRegionData regionToDefine, float similarity) {
        if (mockRegion.isUseMock()) return true;
        Match match = find.findBestMatch(searchRegion, similarity, definingImage);
        if (match == null) return false;
        regionToDefine.setStateRegion(new Region(match));
        return true;
    }

    public boolean define(String imageLocation, StateRegionData stateRegionData) {
        return define(new Region(), new Image(imageLocation), stateRegionData, (float)Settings.MinSimilarity);
    }

    public boolean define(StateImageData stateImageData, StateRegionData stateRegionData) {
        return define(stateImageData.getSearchRegion(), stateImageData.getImage(),
                stateRegionData, (float)Settings.MinSimilarity);
    }

    public boolean define(StateImageData stateImageData, StateRegionData stateRegionData, float similarity) {
        return define(stateImageData.getSearchRegion(), stateImageData.getImage(), stateRegionData, similarity);
    }

}
