package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.actions.ActionInterface;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.actions.methods.wrappers.Wait;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.mock.Mock;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateString;
import com.brobot.brobotREST.database.primitives.region.Region;
import org.sikuli.basics.Settings;
import org.springframework.stereotype.Component;

@Component
public class Type implements ActionInterface {

    private Mock mock;
    private Click click;
    private Wait wait;

    public Type(Mock mock, Click click, Wait wait) {
        this.mock = mock;
        this.click = click;
        this.wait = wait;
    }

    public Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        Matches matches = new Matches();
        if (actionOptions.isClickMatchBeforeTyping()) {
            matches = click.perform(actionOptions, objectCollections[0]);
            wait.wait(actionOptions.getPauseBetweenActions());
        }
        // for DoOn FIRST and BEST
        double defaultTypeDelay = Settings.TypeDelay;
        Settings.TypeDelay = actionOptions.getTypeDelay();
        int i = 0;
        if (matches.isSuccess() && !objectCollections[0].getStateStrings().isEmpty())
            for (StateString stateString : objectCollections[0].getStateStrings()) {
                type(stateString);
                i++;
                if (i<objectCollections[0].getStateStrings().size())
                    wait.wait(actionOptions.getPauseBetweenActions());
            }
        Settings.TypeDelay = defaultTypeDelay;
        return matches;
    }

    public boolean type(StateString stateString) {
        if (mock.isActive()) return true;
        return new Region().type(stateString.getString()) != 0;
    }

}
