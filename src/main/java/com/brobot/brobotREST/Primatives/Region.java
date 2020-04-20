package com.brobot.brobotREST.Primatives;

import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class Region extends org.sikuli.script.Region implements Comparable<Region> {

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

    public Region(Match match, int leftBuffer, int rightBuffer, int topBuffer, int bottomBuffer,
                                boolean includeImageRegion) {
        if (includeImageRegion) {
            x = match.x - leftBuffer;
            y = match.y - topBuffer;
            w = match.w + leftBuffer + rightBuffer;
            h = match.h + topBuffer + bottomBuffer;
        } else {
            x = match.getBottomRight().x - leftBuffer;
            y = match.getBottomLeft().y - topBuffer;
            w = leftBuffer + rightBuffer;
            h = topBuffer + bottomBuffer;
        }
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

    public void setTo(Region region) {
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
}
