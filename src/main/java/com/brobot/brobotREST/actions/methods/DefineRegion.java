package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.actions.ActionInterface;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.actions.methods.find.Find;
import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.location.Position;
import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateLocation;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DefineRegion implements ActionInterface {

    private Find find;

    public DefineRegion(Find find) {
        this.find = find;
    }

    public Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        Matches matches = find.perform(actionOptions, objectCollections);
        if (actionOptions.getDefineAs() == ActionOptions.DefineAs.INSIDE_ANCHORS) {
            matches.getDefinedRegions().add(getSmallestRegionFromAnchors(matches, objectCollections[0]));
        } else {
            for (Match match : matches.getMatches()) {
                Region region = new Region(match);
                if (actionOptions.getDefineAs() == ActionOptions.DefineAs.BELOW_MATCH) region.y += region.h;
                if (actionOptions.getDefineAs() == ActionOptions.DefineAs.ABOVE_MATCH) region.y -= region.h;
                if (actionOptions.getDefineAs() == ActionOptions.DefineAs.LEFT_OF_MATCH) region.x -= region.w;
                if (actionOptions.getDefineAs() == ActionOptions.DefineAs.RIGHT_OF_MATCH) region.x += region.w;
                matches.getDefinedRegions().add(region);
            }
        }
        matches.setSuccess(matches.getDefinedRegion().defined());
        return matches;
    }

    // here, MIDDLE... means somewhere in the middle, not exactly in the middle. This is necessary to 
    // differentiate between points that define only x or y and those that define x and y.
    public Region getSmallestRegionFromAnchors(Matches matches, ObjectCollection objectCollection) {
        Region region = new Region();
        BorderValues borderValues = new BorderValues();
        for (MatchObject matchObject : matches.getMatchObjects()) {
            Match match = matchObject.getMatch();
            for (Map.Entry<Position.Name, Position> anchor : matchObject.getStateObject().getAnchors().entrySet()) {
                borderValues.setAsDefined(anchor.getKey());
                adjustRegion(region, new Location(match, anchor.getValue()), anchor.getKey());
            }
        }
        for (StateLocation location : objectCollection.getStateLocations()) {
            Location l = location.getLocation();
            borderValues.setAsDefined(l.getAnchor());
            adjustRegion(region, l, l.getAnchor());
        }
        if (borderValues.allBordersDefined()) return region;
        return new Region(); // return an undefined region instead of a partially defined region
    }

    private void adjustRegion(Region region, Location location, Position.Name anchor) {
        if (anchor == Position.Name.TOPLEFT) {
            region.x = Math.max(region.x, location.getX());
            region.y = Math.max(region.y, location.getY());
        }
        if (anchor == Position.Name.MIDDLELEFT) {
            region.x = Math.max(region.x, location.getX());
        }
        if (anchor == Position.Name.BOTTOMLEFT) {
            region.x = Math.max(region.x, location.getX());
            region.y2 = Math.min(region.y2, location.getY());
        }
        if (anchor == Position.Name.TOPMIDDLE) {
            region.y = Math.max(region.y, location.getY());
        }
        if (anchor == Position.Name.TOPRIGHT) {
            region.x2 = Math.min(region.x2, location.getX());
            region.y = Math.max(region.y, location.getY());
        }
        if (anchor == Position.Name.MIDDLERIGHT) {
            region.x2 = Math.min(region.x2, location.getX());
        }
        if (anchor == Position.Name.BOTTOMRIGHT) {
            region.x2 = Math.min(region.x2, location.getX());
            region.y2 = Math.min(region.y2, location.getY());
        }
        if (anchor == Position.Name.BOTTOMMIDDLE) {
            region.y2 = Math.min(region.y2, location.getY());
        }
    }

}
