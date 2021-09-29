package com.brobot.brobotREST.actions;

import com.brobot.brobotREST.actions.methods.*;
import com.brobot.brobotREST.actions.methods.find.Find;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import com.brobot.brobotREST.actions.methods.wrappers.Wait;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static com.brobot.brobotREST.actions.ActionOptions.Action.*;

@Component
public class Action {

    private Find find;
    private Wait wait;
    private ScrollMouseWheel scrollMouseWheel;

    Map<ActionOptions.Action, ActionInterface> actions = new HashMap<>();

    public Action(Find find, ClickUntil clickUntil, Drag drag, DefineRegion defineRegion, Type type,
                  MoveMouse moveMouse, WaitVanish waitVanish, GetText getText, Highlight highlight,
                  Wait wait, ScrollMouseWheel scrollMouseWheel) {
        this.find = find;
        this.wait = wait;
        this.scrollMouseWheel = scrollMouseWheel;
        actions.put(FIND, find);
        actions.put(CLICK, clickUntil);
        actions.put(DRAG, drag);
        actions.put(DEFINE, defineRegion);
        actions.put(TYPE, type);
        actions.put(MOVE, moveMouse);
        actions.put(VANISH, waitVanish);
        actions.put(GET_TEXT, getText);
        actions.put(HIGHLIGHT, highlight);
        actions.put(SCROLL_MOUSE_WHEEL, scrollMouseWheel);
    }

    // default options
    public Matches find(StateImageObject... stateImageObjects) {
        ObjectCollection[] collections = new ObjectCollection[stateImageObjects.length];
        for (int i = 0; i < collections.length; i++) collections[i] = stateImageObjects[i].asObjectCollection();
        return find(collections);
    }

    // default options
    public Matches find(ObjectCollection... objectCollections) {
        return perform(new ActionOptions.Builder().build(), objectCollections);
    }

    // StateImageObjects as ObjectCollections
    public Matches perform(ActionOptions actionOptions, StateImageObject... stateImageObjects) {
        ObjectCollection[] collections = new ObjectCollection[stateImageObjects.length];
        for (int i = 0; i < collections.length; i++) collections[i] = stateImageObjects[i].asObjectCollection();
        return perform(actionOptions, collections);
    }

    // no objects
    public Matches perform(ActionOptions actionOptions) {
        return perform(actionOptions, new ObjectCollection.Builder().build());
    }

    // determine the action from the action options: for example, Action = FIND, DoOn = FIRST, ...
    public Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        wait.wait(actionOptions.getPauseBeforeBegin());
        if (objectCollections == null) return new Matches();
        Matches matches = actions.get(actionOptions.getAction()).perform(actionOptions, objectCollections);
        wait.wait(actionOptions.getPauseAfterEnd());
        return matches;
    }

    // adds a user defined Find operation under the Find enum CUSTOM
    public void setCustomFind(BiFunction<ActionOptions, List<StateImageObject>, Matches> customFind) {
        find.addCustomFind(customFind);
    }

    // accepts a user defined action
    public Matches perform(BiFunction<ActionOptions, ObjectCollection[], Matches> supplier,
                           ActionOptions actionOptions, ObjectCollection... objectCollections) {
        return supplier.apply(actionOptions, objectCollections);
    }

}
