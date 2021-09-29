package com.brobot.brobotREST.actions.methods.wrappers;

import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.mock.Mock;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MouseWheel {

    private Mock mock;

    private Map<ActionOptions.ScrollDirection, Integer> scrollInt = new HashMap<>();
    {
        scrollInt.put(ActionOptions.ScrollDirection.DOWN, 1);
        scrollInt.put(ActionOptions.ScrollDirection.UP, -1);
    }

    public MouseWheel(Mock mock) {
        this.mock = mock;
    }

    public boolean scroll(ActionOptions actionOptions) {
        if (mock.isActive()) {
            System.out.format("%s %d %s", "scroll", actionOptions.getTimesToRepeatActionUntil(), "times. ");
            return true;
        }
        new Region().wheel(
                scrollInt.get(actionOptions.getScrollDirection()),
                actionOptions.getTimesToRepeatActionUntil()
        );
        return true;
    }
}
