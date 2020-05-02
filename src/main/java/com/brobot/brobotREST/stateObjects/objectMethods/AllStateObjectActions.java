package com.brobot.brobotREST.stateObjects.objectMethods;

import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickAll;
import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickStateImage;
import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickUntilStateImage;
import com.brobot.brobotREST.stateObjects.objectMethods.string.TypeStateString;
import com.brobot.brobotREST.stateObjects.objectMethods.wait.WaitStateImage;
import com.brobot.brobotREST.stateObjects.objectMethods.wait.WaitStateRIP;
import org.springframework.stereotype.Component;

@Component
public class AllStateObjectActions {

    private final ClickStateImage clickStateImage;
    private final ClickAll clickAll;
    private ClickUntilStateImage clickUntilStateImage;
    private final WaitStateImage waitStateImage;
    private WaitStateRIP waitStateRIP;
    private TypeStateString typeStateString;

    public AllStateObjectActions(ClickStateImage clickStateImage, ClickAll clickAll,
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
