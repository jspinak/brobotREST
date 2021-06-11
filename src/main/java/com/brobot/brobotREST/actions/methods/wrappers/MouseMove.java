package com.brobot.brobotREST.actions.methods.wrappers;

import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.mock.Mock;
import com.brobot.brobotREST.mock.Report;
import org.sikuli.script.FindFailed;
import org.springframework.stereotype.Component;

@Component
public class MouseMove {

    private Mock mock;

    public MouseMove(Mock mock) {
        this.mock = mock;
    }

    public boolean mouseMove(Location location) {
        if (mock.isActive()) {
            if (Report.minReportingLevel(Report.OutputLevel.HIGH))
                System.out.format("%s: %d.%d| ", "mouseMove to", location.getX(), location.getY());
            return true;
        }
        try {
            return new Region().mouseMove(location.getSikuliLocation()) != 0;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            return false;
        }
    }

    public boolean mouseMove(int xOff, int yOff) {
        if (mock.isActive()) return true;
        return new Region().mouseMove(xOff, yOff) != 0;
    }

}
