package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.database.primitives.location.Position;
import com.brobot.brobotREST.database.primitives.region.Region;

import java.util.HashMap;
import java.util.Map;

public class BorderValues {

    private Map<Position.Name, Integer> borders = new HashMap<>();
    {
        borders.put(Position.Name.MIDDLELEFT, new Region().x);
        borders.put(Position.Name.BOTTOMMIDDLE, new Region().y2);
        borders.put(Position.Name.TOPMIDDLE, new Region().y);
        borders.put(Position.Name.MIDDLERIGHT, new Region().x2);
    }

    private Map<Position.Name, Boolean> defined = new HashMap<>();
    {
        defined.put(Position.Name.MIDDLELEFT, false);
        defined.put(Position.Name.BOTTOMMIDDLE, false);
        defined.put(Position.Name.TOPMIDDLE, false);
        defined.put(Position.Name.MIDDLERIGHT, false);
    }

    public void setBorder(Position.Name border, int value) {
        borders.put(border, value);
    }

    public int getBorder(Position.Name border) {
        return borders.get(border);
    }

    public void setAsDefined(Position.Name border) {
        defined.put(border, true);
    }

    public boolean isDefined(Position.Name border) {
        return defined.get(border);
    }

    public boolean allBordersDefined() {
        for (Boolean defined : defined.values()) {
            if (!defined) return false;
        }
        return true;
    }
}
