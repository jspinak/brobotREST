package com.brobot.brobotREST.database.primitives.region;

import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateRegion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.brobot.brobotREST.actions.NullState.Enum.NULL;

@Data
@EqualsAndHashCode(callSuper=false)
public class Region extends org.sikuli.script.Region implements Comparable<Region> {

    public org.sikuli.script.Region region;
    public int x2 = -1; // x + w
    public int y2 = -1; // y + h

    public Region() {
        Screen screen = new Screen();
        setXYWH(screen.x, screen.y, screen.w, screen.h);
    }

    public Region(int x, int y, int w, int h) {
        setXYWH(x,y,w,h);
    }

    public Region(Match match) {
        setTo(match);
    }

    public Region(Region region) {
        setTo(region);
    }

    public Region(org.sikuli.script.Region region) { setTo(region); }

    public Region(Match match, int xAdjust, int yAdjust, int wAdjust, int hAdjust) {
        x = match.x - xAdjust;
        y = match.y - yAdjust;
        w = wAdjust;
        h = hAdjust;
    }

    private void setXY2() {
        x2 = x + w;
        y2 = y + h;
    }

    public void setXYWH(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setTo(Match match) {
        if (match == null) return;
        setXYWH(match.x, match.y, match.w, match.h);
    }

    public void setTo(org.sikuli.script.Region region) {
        if (region == null) return;
        setXYWH(region.x, region.y, region.w, region.h);
    }

    @Override
    public int compareTo(Region comparesTo) {
        /*for ascending order*/
        int yDiff = this.y - comparesTo.y;
        int xDiff = this.x - comparesTo.x;
        if (yDiff != 0) return yDiff;
        return xDiff;
    }

    @Override
    public String toString() {
        return "x = "+x+" y = "+y;
    }

    public boolean defined() {
        return x!=0 || y!=0;
    }

    public boolean overlaps(Region r) {
        if (containsPoint(r.getTopLeft())) return true;
        if (containsPoint(r.getTopRight())) return true;
        if (containsPoint(r.getBottomLeft())) return true;
        return containsPoint(r.getBottomRight());
    }

    public boolean containsPoint(Location point) {
        return (point.x > this.x && point.x < this.getTopRight().x &&
                point.y > this.y && point.y < this.getBottomRight().y);
    }

    public void realignWH() {
        if (x2 >= 0) w = x2 - x;
        if (y2 >= 0) h = y2 - h;
    }

    public StateRegion inNullState() {
        return new StateRegion.Builder()
                .inState(NULL)
                .withSearchRegion(this)
                .build();
    }

    public Match toMatch() {
        return new Match(this, 1);
    }

    public List<Region> getGridRegions(int rows, int columns) {
        setRaster(rows, columns);
        List<Region> regions = new ArrayList<>();
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                regions.add(new Region(getCell(i,j)));
            }
        }
        return regions;
    }

    public com.brobot.brobotREST.database.primitives.location.Location getRandomLocation() {
        Random randW = new Random();
        Random randH = new Random();
        return new com.brobot.brobotREST.database.primitives.location.Location(
                x + randW.nextInt(w), y + randH.nextInt(h));
    }

    public void print() {
        System.out.println("gameRegion: XYWH="+x+"."+y+"."+w+"."+h+"| ");
    }

    // sikuli is unable to process this class even though it is a child of the sikuli Region
    // casting '(org.sikuli.script.Region) this' does not work
    // returning a new sikuli Region works but eliminates any memory of previously found images
    // this should be ok, images with fixed regions should be defined as RegionImagePairs
    public org.sikuli.script.Region sikuli() {
        return new org.sikuli.script.Region(x,y,w,h);
    }
}
