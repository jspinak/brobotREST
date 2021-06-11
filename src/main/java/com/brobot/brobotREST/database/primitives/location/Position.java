package com.brobot.brobotREST.database.primitives.location;

import com.brobot.brobotREST.primatives.Pair;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private int percentW;
    private int percentH;

    public enum Name {
        TOPLEFT, TOPMIDDLE, TOPRIGHT, MIDDLELEFT, MIDDLEMIDDLE, MIDDLERIGHT, BOTTOMLEFT, BOTTOMMIDDLE, BOTTOMRIGHT
    }

    private Map<Name, Pair<Integer,Integer>> positions = new HashMap<>();
    {
        positions.put(Name.TOPLEFT, Pair.of(0,0));
        positions.put(Name.TOPMIDDLE, Pair.of(50, 0));
        positions.put(Name.TOPRIGHT, Pair.of(100, 0));
        positions.put(Name.MIDDLELEFT, Pair.of(0, 50));
        positions.put(Name.MIDDLEMIDDLE, Pair.of(50, 50));
        positions.put(Name.MIDDLERIGHT, Pair.of(100, 50));
        positions.put(Name.BOTTOMLEFT, Pair.of(0, 100));
        positions.put(Name.BOTTOMMIDDLE, Pair.of(50, 100));
        positions.put(Name.BOTTOMRIGHT, Pair.of(100, 100));
    }

    public Position(int percentW, int percentH) {
        this.percentW = percentW;
        this.percentH = percentH;
    }

    public Position(Name positionName) {
        this.percentW = positions.get(positionName).getKey();
        this.percentH = positions.get(positionName).getValue();
    }

    public Position(Name positionName, int addPercentW, int addPercentH) {
        this.percentW = positions.get(positionName).getKey() + addPercentW;
        this.percentH = positions.get(positionName).getValue() + addPercentH;
    }

    public int getPercentW() {
        return percentW;
    }

    public int getPercentH() {
        return percentH;
    }

}
