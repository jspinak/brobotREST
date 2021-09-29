package com.brobot.brobotREST.actions.methods.wrappers;

import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.mock.Mock;
import com.brobot.brobotREST.mock.Report;
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
            if (Report.minReportingLevel(Report.OutputLevel.HIGH))
                System.out.format("drag %d.%d to %d.%d| ",
                        from.getX(), from.getY(), to.getX(), to.getY());
            new Region().dragDrop(from.getSikuliLocation(), to.getSikuliLocation());
        } catch (FindFailed findFailed) {
            System.out.print("|drag failed| ");
            return false;
        }
        return true;
    }

    public boolean drag(Location from, Location to, ActionOptions actionOptions) {
        //System.out.println("to.getY, drag offset y:"+to.getY()+" "+actionOptions.getDragToOffsetY());
        Location adjustedDragTo = new Location(
                to.getX() + actionOptions.getDragToOffsetX(),
                to.getY() + actionOptions.getDragToOffsetY());
        if (mock.isActive()) return mock.drag(from, adjustedDragTo);
        Settings.DelayBeforeMouseDown = actionOptions.getDelayBeforeMouseDown();
        Settings.DelayBeforeDrag = actionOptions.getDelayBeforeDrag();
        Settings.DelayBeforeDrop = actionOptions.getDelayBeforeDrop();
        Settings.MoveMouseDelay = actionOptions.getMoveMouseDelay();
        return drag(from, adjustedDragTo);
    }
}
