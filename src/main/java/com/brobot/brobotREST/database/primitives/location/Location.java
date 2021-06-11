package com.brobot.brobotREST.database.primitives.location;

import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateLocation;
import lombok.Data;
import org.sikuli.script.Match;

import java.util.Optional;

import static com.brobot.brobotREST.actions.NullState.Enum.NULL;

@Data
public class Location {

    // rewrite to hold Position and not 2 vars (percOf W/H)
    private boolean definedByXY = false;
    private int x = -1;
    private int y = -1;
    private Region region;
    private int percentOfW;
    private int percentOfH;

    // for defining regions (for example, this location defines the BOTTOMRIGHT of the region)
    private Position.Name anchor;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location(org.sikuli.script.Location sikuliLocation) {
        this.x = sikuliLocation.x;
        this.y = sikuliLocation.y;
    }

    public Location(Location loc) {
        if (loc.getRegion().isPresent()) {
            this.region = loc.getRegion().get();
            if (loc.getPercentOfW().isPresent()) percentOfW = loc.getPercentOfW().get();
            else percentOfW = 50;
            if (loc.getPercentOfH().isPresent()) percentOfH = loc.getPercentOfH().get();
            else percentOfH = 50;
        } else {
            x = loc.x;
            y = loc.y;
            definedByXY = true;
        }
        anchor = loc.anchor;
    }

    public Location(Region region, org.sikuli.script.Location sikuliLocation) {
        this.region = region;
        this.percentOfW = (sikuliLocation.x - region.x) * 100 / region.w;
        this.percentOfH = (sikuliLocation.y - region.y) * 100 / region.h;
    }

    public Location(Region region) {
        this.region = region;
        this.percentOfW = (region.getTarget().x - region.x) / region.w;
        this.percentOfH = (region.getTarget().y - region.y) / region.y;
    }

    public Location(Region region, Position position) {
        this.region = region;
        this.percentOfW = position.getPercentW();
        this.percentOfH = position.getPercentH();
    }

    public Location(Region region, int percentOfW, int percentOfH) {
        this.region = region;
        this.percentOfW = percentOfW;
        this.percentOfH = percentOfH;
    }

    public Location(Match match) {
        this.region = new Region(match);
        this.percentOfW = (match.getTarget().x - match.x) / match.w;
        this.percentOfH = (match.getTarget().y - match.y) / match.y;
    }

    public Location(Match match, Position position) {
        this.region = new Region(match);
        this.percentOfW = position.getPercentW();
        this.percentOfH = position.getPercentH();
    }

    public Location(Match match, int percentOfW, int percentOfH) {
        this.region = new Region(match);
        this.percentOfW = percentOfW;
        this.percentOfH = percentOfH;
    }

    public Location(Location location, int addX, int addY) {
        this.x = location.x + addX;
        this.y = location.y + addY;
    }

    public Location(org.sikuli.script.Location location, int addX, int addY) {
        this.x = location.x + addX;
        this.y = location.y + addY;
    }

    public Optional<Integer> getPercentOfW() {
        if (isDefinedByXY()) return Optional.empty();
        return Optional.of(percentOfW);
    }

    public Optional<Integer> getPercentOfH() {
        if (isDefinedByXY()) return Optional.empty();
        return Optional.of(percentOfH);
    }

    public Optional<Region> getRegion() {
        if (isDefinedByXY()) return Optional.empty();
        return Optional.of(region);
    }

    public void addPercentOfW(int addPercent) {
        if (!definedByXY) percentOfW += addPercent;
    }

    public void addPercentOfH(int addPercent) {
        if (!definedByXY) percentOfH += addPercent;
    }

    public void multiplyPercentOfW(double multiplyBy) {
        if (!definedByXY) percentOfW += percentOfW * multiplyBy;
    }

    public void multiplyPercentOfH(double multiplyBy) {
        if (!definedByXY) percentOfH += percentOfH * multiplyBy;
    }

    private org.sikuli.script.Location getSikuliLocationFromXY() {
        return new org.sikuli.script.Location(x, y);
    }

    private org.sikuli.script.Location getSikuliLocationFromRegion() {
        int locX = region.x + Math.max(0, region.w * percentOfW / 100 - 1);
        int locY = region.y + Math.max(0, region.h * percentOfH / 100 - 1);
        return new org.sikuli.script.Location(locX, locY);
    }

    public org.sikuli.script.Location getSikuliLocation() {
        if (isDefinedByXY()) return getSikuliLocationFromXY();
        return getSikuliLocationFromRegion();
    }

    public int getX() {
        return getSikuliLocation().x;
    }

    public int getY() {
        return getSikuliLocation().y;
    }

    public Match toMatch() {
        return new Match(new Region(getX(), getY(), 0, 0), 100);
    }

    public StateLocation inNullState() {
        return new StateLocation.Builder()
                .inState(NULL)
                .withLocation(this)
                .build();
    }

    public Location getOpposite() {
        if (region == null) return this;
        return new Location(this.region, 100 - percentOfW, 100 - percentOfH);
    }

    public void adjustToRegion() {
        percentOfW = Math.min(100, percentOfW);
        percentOfW = Math.max(percentOfW, 0);
        percentOfH = Math.min(100, percentOfH);
        percentOfH = Math.max(percentOfH, 0);
    }

    private boolean isDefinedByXY() {
        return definedByXY || region == null;
    }
}
