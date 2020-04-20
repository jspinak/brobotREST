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
public class DefineRegionByFrame {

    public enum frameType {
        TOP_AND_BOTTOM, TOP_AND_RIGHT
    }

    private MockRegion mockRegion;
    private Find find;

    public DefineRegionByFrame(MockRegion mockRegion, Find find) {
        this.mockRegion = mockRegion;
        this.find = find;
    }

    public boolean define(Region searchRegion1, Image definingImage1,
                          Region searchRegion2, Image definingImage2,
                          StateRegion regionToDefine, float similarity, frameType type) {
        if (mockRegion.isUseMock()) return true;
        Match match1 = find.findBestMatch(searchRegion1, similarity, definingImage1);
        Match match2 = find.findBestMatch(searchRegion2, similarity, definingImage2);
        if (match1 == null || match2 == null) return false;
        if (type == frameType.TOP_AND_BOTTOM) regionToDefine.setStateRegion(
                makeRegionFromTopBottomFrame(match1, match2));
        if (type == frameType.TOP_AND_RIGHT) regionToDefine.setStateRegion(
                makeRegionFromTopRightFrame(match1, match2));
        return true;
    }

    private Region makeRegionFromTopBottomFrame(Match top, Match bottom) {
        return new Region(
                top.x, top.getBottomLeft().y,
                Math.max(top.getBottomRight().x, bottom.getBottomRight().x) -
                        Math.min(top.getBottomLeft().x, bottom.getBottomLeft().x),
                bottom.getTopRight().y - top.getBottomLeft().y
        );
    }

    private Region makeRegionFromTopRightFrame(Match top, Match right) {
        return new Region(
                top.x, top.getBottomLeft().y,
                right.x - top.x,
                right.getBottomLeft().y - top.getBottomLeft().y
        );
    }

    public boolean define(StateImage stateImage1, StateImage stateImage2,
                          StateRegion regionToDefine, frameType type) {
        return define(stateImage1.getSearchRegion(), stateImage1.getStateImage(),
                stateImage2.getSearchRegion(), stateImage2.getStateImage(),
                regionToDefine, (float) Settings.MinSimilarity, type);
    }
}