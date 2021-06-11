package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.actions.ActionInterface;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.actions.methods.find.Find;
import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.actions.methods.wrappers.Wait;
import org.springframework.stereotype.Component;

@Component
public class Highlight implements ActionInterface {

    private Find find;
    private HighlightMatch highlightMatch;
    private Wait wait;

    public Highlight(Find find, HighlightMatch highlightMatch, Wait wait) {
        this.find = find;
        this.highlightMatch = highlightMatch;
        this.wait = wait;
    }

    // add maxMatchesToActOn code
    public Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        Matches matches = find.perform(actionOptions, objectCollections);
        if (actionOptions.isHighlightAllAtOnce()) {
            matches.getMatchObjects().forEach(matchObject ->
                    highlightMatch.turnOn(matchObject.getMatch(), matchObject.getStateObject(), actionOptions));
            wait.wait(actionOptions.getHighlightSeconds());
            matches.getMatchObjects().forEach(matchObject ->
                    highlightMatch.turnOff(matchObject.getMatch(), matchObject.getStateObject(), actionOptions));
            return matches;
        }
        for (MatchObject matchObject : matches.getMatchObjects()) {
            highlightMatch.highlight(matchObject.getMatch(), matchObject.getStateObject(), actionOptions);
            if (matchObject != matches.getMatchObjects().get(matches.getMatchObjects().size() - 1))
                wait.wait(actionOptions.getPauseBetweenActions());
        }
        return matches;
    }
}
