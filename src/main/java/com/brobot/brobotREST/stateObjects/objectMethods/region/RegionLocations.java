package com.brobot.brobotREST.stateObjects.objectMethods.region;

import com.brobot.brobotREST.primatives.enums.LocationEnum;
import com.brobot.brobotREST.database.primatives.Region;
import org.sikuli.script.Location;
import org.springframework.stereotype.Component;

@Component
public class RegionLocations {

    public Location getExactLocation(LocationEnum locationEnum, Region region) {
        if (locationEnum.equals(LocationEnum.TOPLEFT)) return region.getTopLeft();
        if (locationEnum.equals(LocationEnum.TOPMIDDLE)) return new Location(region.getCenter().x,region.y);
        if (locationEnum.equals(LocationEnum.TOPRIGHT)) return region.getTopRight();
        if (locationEnum.equals(LocationEnum.MIDDLELEFT)) return new Location(region.x,region.getCenter().y);
        if (locationEnum.equals(LocationEnum.MIDDLEMIDDLE)) return new Location(region.getCenter().x,region.getCenter().y);
        if (locationEnum.equals(LocationEnum.MIDDLERIGHT)) return new Location(region.getTopRight().x,region.getCenter().y);
        if (locationEnum.equals(LocationEnum.BOTTOMLEFT)) return region.getBottomLeft();
        if (locationEnum.equals(LocationEnum.BOTTOMMIDDLE)) return new Location(region.getCenter().x,region.getBottomRight().y);
        if (locationEnum.equals(LocationEnum.BOTTOMRIGHT)) return region.getBottomRight();
        return null;
    }

    public Location getLocationInsideRegion(LocationEnum locationEnum, Region region) {
        return getLocationInsideRegion(locationEnum, region, 10);
    }

    public Location getLocationInsideRegion(LocationEnum locationEnum, Region region, int percentFromEdge) {
        Location location = getExactLocation(locationEnum, region);
        if (location.x == region.x) location.x += region.w * percentFromEdge / 100;
        if (location.x == region.getBottomRight().x) location.x -= region.w * percentFromEdge / 100;
        if (location.y == region.y) location.y += region.h * percentFromEdge / 100;
        if (location.y == region.getBottomRight().y) location.y -= region.h * percentFromEdge / 100;
        return location;
    }
}
