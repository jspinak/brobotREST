package com.brobot.brobotREST.actions.methods.wrappers;

import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.mock.Mock;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.springframework.stereotype.Component;

@Component
public class DragLocation {
    private Mock mock;

    /*
    Standard Sikuli settings:
    Settings.DelayBeforeMouseDown = 0.3
    Settings.DelayBeforeDrag = 0.3
    Settings.DelayBeforeDrop = 0.3
    Settings.MoveMouseDelay = 0.5
     */

    public DragLocation(Mock mock) {
        this.mock = mock;
    }

    public boolean drag(Location from, Location to) {
        try {
            new Region().dragDrop(from.getSikuliLocation(), to.getSikuliLocation());
        } catch (FindFailed findFailed) {
            System.out.print("|drag failed| ");
            return false;
        }
        return true;
    }

    public boolean drag(Location from, Location to, ActionOptions actionOptions) {
        if (mock.isActive()) return true; // you can make this more sophisticated later
        Settings.DelayBeforeMouseDown = actionOptions.getDelayBeforeMouseDown();
        Settings.DelayBeforeDrag = actionOptions.getDelayBeforeDrag();
        Settings.DelayBeforeDrop = actionOptions.getDelayBeforeDrop();
        Settings.MoveMouseDelay = actionOptions.getMoveMouseDelay();
        return drag(from, to);
    }
}
