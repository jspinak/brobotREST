package com.brobot.brobotREST.StateObjects.SikuliWrappers;

import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.Region;
import org.sikuli.basics.Settings;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Exists {

    private Find find;
    private FindRIP findRIP;

    public Exists(Find find, FindRIP findRIP) {
        this.find = find;
        this.findRIP = findRIP;
    }

    public boolean exists(Region region, float similarity, Image... images) {
        return find.findBestMatch(region, similarity, images) != null;
    }

    public boolean exists(Region region, Image... images) {
        return exists(region, (float) Settings.MinSimilarity, images);
    }

    public boolean exists(Region searchRegion, Map<Region, Image> regionImageMap) {
        return findRIP.findFirstMatch(searchRegion, regionImageMap) != null;
    }

}
