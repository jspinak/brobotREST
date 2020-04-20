package com.brobot.brobotREST.StateObjects.ObjectMethods;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickAll;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickStateImage;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickUntilStateImage;
import com.brobot.brobotREST.StateObjects.ObjectMethods.String.TypeStateString;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Wait.WaitStateImage;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Wait.WaitStateRIP;
import org.springframework.stereotype.Component;

@Component
public class AllGameObjectActions {

    private final ClickStateImage clickStateImage;
    private final ClickAll clickAll;
    private ClickUntilStateImage clickUntilStateImage;
    private final WaitStateImage waitStateImage;
    private WaitStateRIP waitStateRIP;
    private TypeStateString typeStateString;

    public AllGameObjectActions(ClickStateImage clickStateImage, ClickAll clickAll,
                                ClickUntilStateImage clickUntilStateImage,
                                WaitStateImage waitStateImage, WaitStateRIP waitStateRIP,
                                TypeStateString typeStateString) {
        this.clickStateImage = clickStateImage;
        this.clickAll = clickAll;
        this.clickUntilStateImage = clickUntilStateImage;
        this.waitStateImage = waitStateImage;
        this.waitStateRIP = waitStateRIP;
        this.typeStateString = typeStateString;
    }

    public ClickStateImage click() {
        return clickStateImage;
    }

    public ClickAll clickAll() {
        return clickAll;
    }

    public void pause(double timeout) {
        waitStateImage.pause(timeout);
    }

    public WaitStateImage waitForImage() {
        return waitStateImage;
    }

    public WaitStateRIP waitForRIP() {
        return waitStateRIP;
    }

    public TypeStateString type() {
        return typeStateString;
    }

    public ClickUntilStateImage clickUntil() {
        return clickUntilStateImage;
    }
}
