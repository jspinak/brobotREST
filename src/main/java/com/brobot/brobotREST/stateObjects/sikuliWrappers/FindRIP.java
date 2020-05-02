package com.brobot.brobotREST.stateObjects.sikuliWrappers;

import com.brobot.brobotREST.database.primatives.RegionImagePair;
import com.brobot.brobotREST.stateObjects.objectMethods.defineRegion.DefineRegionByImage;
import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.primatives.Region;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class FindRIP {

    private Find find;
    private DefineRegionByImage defineRegionByImage;

    public FindRIP(Find find, DefineRegionByImage defineRegionByImage) {
        this.find = find;
        this.defineRegionByImage = defineRegionByImage;
    }

    public Match findFirstMatch(Region searchRegion, Set<RegionImagePair> RIPs) {
        Match firstMatch;
        for (RegionImagePair regionImagePair : RIPs) {
            firstMatch = findFirstMatch(searchRegion, regionImagePair);
            if (firstMatch != null) return firstMatch;
        }
        return null;
    }

    public Match findFirstMatch(Region searchRegion, RegionImagePair regionImagePair) {
        Region Region = regionImagePair.getRegion().defined()? regionImagePair.getRegion() : searchRegion;
        return find.findBestMatch(Region, regionImagePair.getImage());
    }

    private Match findFirstMatchInDefinedRegion(Map<Region, Image> regionImageMap) {
        Region reg;
        Image img;
        Match match;
        for (Map.Entry<Region, Image> regImg : regionImageMap.entrySet()) {
            reg = regImg.getKey();
            img = regImg.getValue();
            if (reg.defined()) {
                match = find.findBestMatch(reg, img);
                if (match != null) return match;
            }
        }
        return null;
    }

    private Match findFirstMatchInUndefinedRegion(Region searchRegion, Map<Region, Image> regionImageMap) {
        Region reg;
        Image img;
        Match match;
        for (Map.Entry<Region, Image> regImg : regionImageMap.entrySet()) {
            reg = regImg.getKey();
            img = regImg.getValue();
            if (!reg.defined()) {
                match = find.findBestMatch(searchRegion, img);
                if (match != null) {
                    reg.setTo(match);
                    return match;
                }
            }
        }
        return null;
    }

}
