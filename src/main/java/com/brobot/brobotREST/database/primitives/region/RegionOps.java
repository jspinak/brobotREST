package com.brobot.brobotREST.database.primitives.region;

import com.brobot.brobotREST.database.primitives.location.Location;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

@Component
public class RegionOps {

    public boolean isWithinRegion(Location location, Region region) {
        return region.contains(location.getSikuliLocation());
    }

    public boolean matchesOverlap(Match match1, Match match2) {
        return new Region(match1).contains(new Region(match2));
    }

    public boolean objectsOverlap(Region region, Match match) {
        return region.contains(new Region(match));
    }
}
