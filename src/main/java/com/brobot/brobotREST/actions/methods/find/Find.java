package com.brobot.brobotREST.actions.methods.find;

import com.brobot.brobotREST.actions.ActionInterface;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.actions.methods.wrappers.Time;
import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateLocation;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateRegion;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import com.brobot.brobotREST.manageStates.StateMemory;
import com.brobot.brobotREST.mock.MockStatus;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Component
public class Find implements ActionInterface {

    private StateMemory stateMemory;
    private StateService stateService;
    private Time time;
    private final MockStatus mockStatus;

    private Map<ActionOptions.Find, BiFunction<ActionOptions, List<StateImageObject>, Matches>> findFunction = new HashMap<>();

    public Find(FindImageOrRIP findImageOrRIP, StateMemory stateMemory, StateService stateService,
                Time time, MockStatus mockStatus) {
        this.stateMemory = stateMemory;
        this.stateService = stateService;
        this.time = time;
        this.mockStatus = mockStatus;
        findFunction.put(ActionOptions.Find.FIRST, findImageOrRIP::first);
        findFunction.put(ActionOptions.Find.BEST, findImageOrRIP::best);
        findFunction.put(ActionOptions.Find.EACH, findImageOrRIP::each);
        findFunction.put(ActionOptions.Find.ALL, findImageOrRIP::all);
    }

    public void addCustomFind(BiFunction<ActionOptions, List<StateImageObject>, Matches> customFind) {
        findFunction.put(ActionOptions.Find.CUSTOM, customFind);
    }

    public Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        Matches matches = new Matches();
        if (objectCollections.length == 0) return matches;
        ObjectCollection objectCollection = objectCollections[0];
        time.setStartTime();
        int timesSearched = 0;
        while (continueSearching(actionOptions, matches, timesSearched)) {
            mockStatus.addMockPerformed();
            if (actionOptions.getTempFind() != null)
                matches = actionOptions.getTempFind().apply(actionOptions, objectCollection.getStateImages());
            else matches = findFunction.get(actionOptions.getFind()).apply(actionOptions, objectCollection.getStateImages());
            matches.setSuccess(actionOptions.getSuccessEvaluation().test(matches));
            matches.setDuration(time.getDuration());
            timesSearched++;
        }
        stateMemory.adjustActiveStatesWithMatches(matches);
        addOtherObjectsDirectlyAsMatchObjects(matches, objectCollection);
        return matches;
    }

    private void addOtherObjectsDirectlyAsMatchObjects(Matches matches, ObjectCollection objectCollection) {
        addMatches(matches, objectCollection);
        addRegions(matches, objectCollection);
        addLocations(matches, objectCollection);
    }

    private void addMatches(Matches matches, ObjectCollection objectCollection) {
        for (Matches m : objectCollection.getMatches()) matches.addAll(m);
    }

    private void addRegions(Matches matches, ObjectCollection objectCollection) {
        for (StateRegion r : objectCollection.getStateRegions()) {
            try {
                MatchObject newMO = new MatchObject(r.getSearchRegion().toMatch(), r);
                if (mockStatus.isUseMock()) newMO.setText(r.getMockText());
                matches.add(newMO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addLocations(Matches matches, ObjectCollection objectCollection) {
        for (StateLocation l : objectCollection.getStateLocations()) {
            try {
                matches.add(new MatchObject(l.getLocation().toMatch(), l));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean continueSearching(ActionOptions actionOptions, Matches matches, int timesSearched) {
        if (timesSearched == 0) return true;
        if (time.expired(actionOptions.getMaxWait())) return false;
        return !matches.isSuccess();
    }

}