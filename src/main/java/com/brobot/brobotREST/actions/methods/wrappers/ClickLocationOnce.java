package com.brobot.brobotREST.actions.methods.wrappers;

import com.brobot.brobotREST.actions.methods.Click;
import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.mock.Mock;
import org.sikuli.script.Mouse;
import org.springframework.stereotype.Component;

@Component
public class ClickLocationOnce {

    private Mock mock;

    public ClickLocationOnce(Mock mock) {
        this.mock = mock;
    }

    public boolean click(double pauseBeforeMouseDown, double pauseAfterMouseUp, double clickDelay,
                         String clickType, Location location) {
        if (mock.isActive()) {
            System.out.print("<click>"); // this could be expanded if object clicks are given mock actions
            if (!clickType.equals("L")) System.out.print(clickType);
            System.out.print(" ");
            return true;
        }
        Integer md = (int) pauseBeforeMouseDown * 1000;
        Integer mu = (int) pauseAfterMouseUp * 1000;
        Integer cd = (int) clickDelay * 1000;
        return click(md, mu, cd, clickType, location);
    }

    private boolean click(Integer pauseBeforeMouseDown, Integer pauseAfterMouseUp, Integer clickDelay,
                          String clickType, Location location) {
        return Mouse.click(location.getSikuliLocation(), clickType,
                pauseBeforeMouseDown, pauseAfterMouseUp, clickDelay) != null;
    }

}
