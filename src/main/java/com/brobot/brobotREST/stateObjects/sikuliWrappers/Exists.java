package com.brobot.brobotREST.stateObjects.sikuliWrappers;

import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.primatives.Region;
import com.brobot.brobotREST.database.primatives.RegionImagePair;
import org.sikuli.basics.Settings;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

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

    public boolean exists(Region searchRegion, Set<RegionImagePair> RIPs) {
        return findRIP.findFirstMatch(searchRegion, RIPs) != null;
    }

}
