package com.brobot.brobotREST.database.primitives.location;

import com.brobot.brobotREST.database.primitives.region.Region;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class RegionLocations {
/*
    private Positions positions;

    public RegionLocations(Positions positions) {
        this.positions = positions;
    }

    public Location getLocationInsideRegion(Positions.Name position, Region region) {
        return getLocationInsideRegion(position, region, 10);
    }

    public Location getLocationInsideRegion(Positions.Name pos, Region region, int percentCloserToCenter) {
        Position position = positions.getPosition(pos);
        Location location = new Location(region, position.getPercentW(), position.getPercentH());
        return getCloserToCenter(location, percentCloserToCenter);
    }

    public Optional<Location> getOpposite(Location location) {
        if (!location.isDefinedByXY() || location.getRegion().isEmpty()) return Optional.empty();
        int percentW = 100 - location.getPercentOfW().get();
        int percentH = 100 - location.getPercentOfH().get();
        return Optional.of(new Location(location.getRegion().get(), percentW, percentH));
    }

    public Location getOpposite(Position.Name pos, Region region) {
        return getOpposite(pos, region, 0);
    }

    public Location getOpposite(Position.Name pos, Region region, int percentCloserToCenter) {
        Position position = positions.getPosition(pos);
        Location location = new Location(region, 100 - position.getPercentW(), 100 - position.getPercentH());
        return getCloserToCenter(location, percentCloserToCenter);
    }

    public Location getCloserToCenter(Location location, int percentCloserToCenter) {
        if (location.getPercentOfW().isEmpty() ||
                location.getPercentOfH().isEmpty() ||
                location.getRegion().isEmpty()) return location;
        int percentW = location.getPercentOfW().get();
        int percentH = location.getPercentOfH().get();
        Region region = location.getRegion().get();
        percentW += (50 - percentW) * percentCloserToCenter / 100;
        percentH += (50 - percentH) * percentCloserToCenter / 100;
        return new Location(region, percentW, percentH);
    }

    public Location getNearestLocationInRegion(Region region, Location location) {
        int x = Math.max(location.getSikuliLocation().x, region.x);
        int y = Math.max(location.getSikuliLocation().y, region.y);
        x = Math.min(x, region.getTopRight().x);
        y = Math.min(y, region.getBottomRight().y);
        return new Location(x, y);
    }

    public Location[] getCenterLocations(Region... regions) {
        return Stream.of(regions).map(reg -> new Location(reg, 50, 50)).toArray(Location[]::new);
    }

 */
}
