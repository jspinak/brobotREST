package com.brobot.brobotREST.StateObjects.SikuliWrappers;

import com.brobot.brobotREST.StateObjects.ObjectMethods.DefineRegion.DefineRegionByImage;
import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.Region;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FindRIP {

    private Find find;
    private DefineRegionByImage defineRegionByImage;

    public FindRIP(Find find, DefineRegionByImage defineRegionByImage) {
        this.find = find;
        this.defineRegionByImage = defineRegionByImage;
    }

    public Match findFirstMatch(Region searchRegion, Map<Region, Image> regionImageMap) {
        Match firstMatch;
        for (Map.Entry<Region, Image> entry : regionImageMap.entrySet()) {
            firstMatch = findFirstMatch(searchRegion, entry);
            if (firstMatch != null) return firstMatch;
        }
        return null;
    }

    public Match findFirstMatch(Region searchRegion, Map.Entry<Region, Image> regionImageEntry) {
        Region Region = regionImageEntry.getKey().defined()? regionImageEntry.getKey() : searchRegion;
        return find.findBestMatch(Region, regionImageEntry.getValue());
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
