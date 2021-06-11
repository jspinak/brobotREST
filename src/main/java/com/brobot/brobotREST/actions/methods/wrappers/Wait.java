package com.brobot.brobotREST.actions.methods.wrappers;

import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.mock.Mock;
import com.brobot.brobotREST.mock.Report;
import org.springframework.stereotype.Component;

@Component
public class Wait {

    private final Mock mock;

    public Wait(Mock mock) {
        this.mock = mock;
    }

    public void wait(double timeout) {
        if (mock.isActive()) {
            if (Report.minReportingLevel(Report.OutputLevel.HIGH) && timeout > 0)
                System.out.format("%s: %.1f| ", "wait ", timeout);
            return;
        }
        new Region().wait(timeout);
    }

}