package com.brobot.brobotREST.database.primitives.location;

import com.brobot.brobotREST.database.primitives.region.Region;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefineLocation {

    /*

    public void set(Location location, int x, int y) {
        location.setDefinedByXY(true);
        location.setX(x);
        location.setY(y);
    }

    public void set(Location location, Region region, int percentOfW, int percentOfH) {
        location.setDefinedByXY(false);
        location.setRegion(region);
        location.setPercentOfW(percentOfW);
        location.setPercentOfH(percentOfH);
    }

    public void addXStayInRegion(Location location, int addX) {
        if (location.isDefinedByXY()) {
            location.setX(location.getX() + addX);
            return;
        }
        Optional<Region> reg = location.getRegion();
        if (reg.isEmpty()) return;
        int newX = location.getX() + addX;
        int newPercentW = newX / reg.get().w;
        location.setPercentOfW(Math.min(100, newPercentW));
    }

    public void addYStayInRegion(Location location, int addY) {
        if (location.isDefinedByXY()) {
            location.setY(location.getY() + addY);
            return;
        }
        Optional<Region> reg = location.getRegion();
        if (reg.isEmpty()) return;
        int newY = location.getY() + addY;
        int newPercentH = newY / reg.get().h;
        location.setPercentOfH(Math.min(100, newPercentH));
    }

    public void addXExtendRegion(Location location, int addX) {
        if (!location.isDefinedByXY()) {
            Optional<Region> reg = location.getRegion();
            if (reg.isEmpty()) return;
            reg.get().w = Math.max(reg.get().w, location.getX() + addX);
        }
        addXStayInRegion(location, addX);
    }

    public void addYExtendRegion(Location location, int addY) {
        if (!location.isDefinedByXY()) {
            Optional<Region> reg = location.getRegion();
            if (reg.isEmpty()) return;
            reg.get().h = Math.max(reg.get().h, location.getY() + addY);
        }
        addYStayInRegion(location, addY);
    }

    public void addXYStayInRegion(Location location, int addX, int addY) {
        addXStayInRegion(location, addX);
        addYStayInRegion(location, addY);
    }

    public void addXYExtendRegion(Location location, int addX, int addY) {
        addXExtendRegion(location, addX);
        addYExtendRegion(location, addY);
    }

    public void convertToXY(Location location) {
        org.sikuli.script.Location loc = location.getSikuliLocation();
        location.setX(loc.x);
        location.setY(loc.y);
        location.setDefinedByXY(true);
    }

    public boolean convertToPercentOfRegion(Location location, Region region) {
        if (!location.isDefinedByXY() || !region.contains(location.getSikuliLocation())) return false;
        location.setRegion(region);
        location.setPercentOfW((location.getX() - region.x) / region.w);
        location.setPercentOfH((location.getY() - region.y) / region.h);
        location.setDefinedByXY(false);
        return true;
    }

     */

}
