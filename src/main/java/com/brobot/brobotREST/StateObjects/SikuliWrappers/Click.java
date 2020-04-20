package com.brobot.brobotREST.StateObjects.SikuliWrappers;

import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.Region;
import org.sikuli.basics.Settings;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Click {

    private Find find;
    private FindRIP findRIP;

    public Click(Find find, FindRIP findRIP) {
        this.find = find;
        this.findRIP = findRIP;
    }

    public boolean click(Region region) {
        return region.click() != 0;
    }

    public Match clickBestMatch(Region region, Image... images) {
        return clickBestMatch(region, (float)Settings.MinSimilarity, images);
    }

    public Match clickBestMatch(Region region, float similarity, Image... images) {
        Match match = find.findBestMatch(region, similarity, images);
        if (match != null) match.click();
        return match;
    }

    public Match clickBestMatch(Region region, float similarity, double clickDelay, Image... images) {
        double previousClickDelay = Settings.ClickDelay;
        Settings.ClickDelay = clickDelay;
        Match match = clickBestMatch(region, similarity, images);
        Settings.ClickDelay = previousClickDelay;
        return match;
    }

    public Match clickFirstMatch(Region searchRegion, Map<Region, Image> regionImageMap) {
        Match match = findRIP.findFirstMatch(searchRegion, regionImageMap);
        if (match != null) match.click();
        return match;
    }

    public Match clickFirstMatch(Region searchRegion, Map.Entry<Region, Image> regionImageMap) {
        Match match = findRIP.findFirstMatch(searchRegion, regionImageMap);
        if (match != null) match.click();
        return match;
    }

}
