package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.actions.ActionInterface;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.actions.methods.wrappers.MouseWheel;
import com.brobot.brobotREST.database.primitives.match.Matches;
import org.springframework.stereotype.Component;

@Component
public class ScrollMouseWheel implements ActionInterface {

    private MouseWheel mouseWheel;

    public ScrollMouseWheel(MouseWheel mouseWheel) {
        this.mouseWheel = mouseWheel;
    }

    public Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        Matches matches = new Matches();
        matches.setSuccess(mouseWheel.scroll(actionOptions));
        return matches;
    }
}
