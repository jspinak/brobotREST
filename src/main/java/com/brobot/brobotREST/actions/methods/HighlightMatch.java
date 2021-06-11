package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.database.state.stateObject.StateObject;
import com.brobot.brobotREST.mock.Mock;
import com.brobot.brobotREST.mock.Report;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

@Component
public class HighlightMatch {

    private Mock mock;

    public HighlightMatch(Mock mock) {
        this.mock = mock;
    }

    public void turnOn(Match match, StateObject stateObject, ActionOptions actionOptions) {
        if (mock.isActive()) Report.print(match, stateObject, actionOptions);
        else match.highlightOn(actionOptions.getHighlightColor());
    }

    public void turnOff(Match match, StateObject stateObject, ActionOptions actionOptions) {
        if (!mock.isActive()) match.highlightOff();
    }

    public boolean highlight(Match match, StateObject stateObject, ActionOptions actionOptions) {
        if (mock.isActive()) return Report.print(match, stateObject, actionOptions);
        match.highlight(actionOptions.getHighlightSeconds(), actionOptions.getHighlightColor());
        return true;
    }

}
