package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.actions.ActionInterface;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.actions.methods.find.Find;
import com.brobot.brobotREST.actions.methods.wrappers.ClickLocationOnce;
import com.brobot.brobotREST.actions.methods.wrappers.MouseMove;
import com.brobot.brobotREST.actions.methods.wrappers.Wait;
import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.state.stateObject.StateObject;
import com.brobot.brobotREST.web.services.StateService;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.brobot.brobotREST.actions.methods.Click.Type.*;

@Component
public class Click implements ActionInterface {

    public enum Type {
        LEFT, RIGHT, MIDDLE, DOUBLE_LEFT, DOUBLE_RIGHT, DOUBLE_MIDDLE
    }

    private Map<Type, String> typeToMouseAction = new HashMap<>();

    {
        typeToMouseAction.put(LEFT, "L");
        typeToMouseAction.put(RIGHT, "R");
        typeToMouseAction.put(MIDDLE, "M");
        typeToMouseAction.put(DOUBLE_LEFT, "LD");
        typeToMouseAction.put(DOUBLE_RIGHT, "RD");
        typeToMouseAction.put(DOUBLE_MIDDLE, "MD");
    }

    private Find find;
    private ClickLocationOnce clickLocationOnce;
    private Wait wait;
    private MouseMove mouseMove;
    private StateService stateService;

    public Click(Find find, ClickLocationOnce clickLocationOnce,
                 Wait wait, MouseMove mouseMove,
                 StateService stateService) {
        this.find = find;
        this.clickLocationOnce = clickLocationOnce;
        this.wait = wait;
        this.mouseMove = mouseMove;
        this.stateService = stateService;
    }

    public Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        Matches matches = find.perform(actionOptions, objectCollections); // find performs only on 1st collection
        int i = 0;
        setStateProbabilitiesAfterClick(matches, actionOptions);
        for (MatchObject matchObject : matches.getMatchObjects()) {
            click(matchObject.getMatch(), matchObject.getStateObject(), actionOptions);
            i++;
            if (actionOptions.isMoveMouseAfterClick()) moveMouseAfterClick(actionOptions);
            if (actionOptions.isCheckConditionAfterEachAction() &&
                    objectCollections.length > 1 &&
                    find.perform(actionOptions, objectCollections[1]).isSuccess()) break;
            if (i == actionOptions.getMaxMatchesToActOn()) break;
            wait.wait(actionOptions.getPauseBetweenActions());
        }
        return matches;
    }

    private boolean setStateProbabilitiesAfterClick(Matches matches, ActionOptions actionOptions) {
        if (matches.isEmpty() || actionOptions.getStateProbabilitiesAfterClick().isEmpty()) return false;
        actionOptions.getStateProbabilitiesAfterClick().forEach(
                (key, value) -> stateService.findByName(key).ifPresent(state -> state.setProbabilityExists(value)));
        return true;
    }

    private void moveMouseAfterClick(ActionOptions actionOptions) {
        int x = actionOptions.getOffsetLocationBy().getX();
        int y = actionOptions.getOffsetLocationBy().getY();
        if (x > 0 && y > 0) mouseMove.mouseMove(x, y);
        else mouseMove.mouseMove(actionOptions.getLocationAfterClick());
    }

    private void click(Match match, StateObject imageObject, ActionOptions actionOptions) {
        Location location = new Location(match, imageObject.getPosition());
        location.setX(location.getX() + actionOptions.getAddX());
        location.setY(location.getY() + actionOptions.getAddY());
        for (int i = 0; i < actionOptions.getNumberOfActions(); i++) {
            clickLocationOnce.click(actionOptions.getDelayBeforeMouseDown(), actionOptions.getPauseAfterMouseUp(),
                    actionOptions.getClickDelay(), typeToMouseAction.get(actionOptions.getClickType()), location);
            if (i < actionOptions.getNumberOfActions() - 1) wait.wait(actionOptions.getPauseBetweenActions());
        }
    }
}
