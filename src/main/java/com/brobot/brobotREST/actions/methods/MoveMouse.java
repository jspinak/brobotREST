package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.actions.ActionInterface;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.actions.methods.find.Find;
import com.brobot.brobotREST.actions.methods.wrappers.MouseMove;
import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.location.Position;
import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.match.Matches;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

@Component
public class MoveMouse implements ActionInterface {

    private final Find find;
    private final MouseMove mouseMove;

    public MoveMouse(Find find, MouseMove mouseMove) {
        this.find = find;
        this.mouseMove = mouseMove;
    }

    // multiple moves can be done with multiple ObjectCollections
    public Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        Location moveTo;
        Matches matches = new Matches();
        if (objectCollections.length > 0 && !objectCollections[0].empty()) {
            matches = find.perform(actionOptions, objectCollections[0]);
            // for DoOn FIRST and BEST
            if (!matches.getBestMatch().isPresent()) return matches;
            MatchObject matchObject = matches.getBestMatch().get();
            Match match = matchObject.getMatch();
            moveTo = getMoveToLocation(match, matchObject.getStateObject().getPosition(), actionOptions);
            mouseMove.mouseMove(moveTo);
            return matches;
        } else {
            matches.setSuccess(
                    mouseMove.mouseMove(actionOptions.getAddX(), actionOptions.getAddY()));
            return matches;
        }
    }

    private Location getMoveToLocation(Match match, Position position, ActionOptions actionOptions) {
        Location moveTo1 = new Location(match, position);
        return new Location(
                moveTo1.getX() + actionOptions.getAddX(),
                moveTo1.getY() + actionOptions.getAddY());
    }

}
