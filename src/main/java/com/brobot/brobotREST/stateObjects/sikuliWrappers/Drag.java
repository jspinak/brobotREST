package com.brobot.brobotREST.stateObjects.sikuliWrappers;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.springframework.stereotype.Component;

@Component
public class Drag {

    public boolean drag(Location dragFrom, Location dragTo) {
        return drag(new Screen(), dragFrom, dragTo);
    }

    public boolean drag(Region region, Location dragFrom, Location dragTo) {
        try {
            region.dragDrop(dragFrom,dragTo);
        } catch (FindFailed findFailed) {
            System.out.print("drag failed ");
            return false;
        }
        return true;
    }
}
