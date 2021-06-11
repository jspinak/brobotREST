package com.brobot.brobotREST.mock;

import com.brobot.brobotREST.database.primitives.image.Image;
import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateRegion;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import com.brobot.brobotREST.web.services.StateService;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class Mock {

    private MockStatus mockStatus;
    private MockMatch mockMatch;
    private StateService stateService;

    public Mock(MockStatus mockStatus, MockMatch mockMatch, StateService stateService) {
        this.mockStatus = mockStatus;
        this.mockMatch = mockMatch;
        this.stateService = stateService;
    }

    // replace this in occurrences with mockStatus
    public boolean isActive() {
        return mockStatus.isUseMock();
    }

    // the getMatch methods are called by the FindPattern class.
    // they are executed at a low level, before methods for findEach or findBest
    // the regular methods for findEach and findBest will therefore also return values for mock matches
    public Optional<Match> getMatch(StateImageObject stateImageObject, Region searchRegion) {
        return getMatch(stateImageObject, searchRegion, stateImageObject.getImage());
    }

    public Optional<Match> getMatch(StateImageObject stateImageObject, Region searchRegion, Image image) {
        int randomProbability = new Random().nextInt(100);
        Optional<State> state = stateService.findByName(stateImageObject.getOwnerStateName());
        if (!state.isPresent()) return Optional.empty();
        int probExists = stateImageObject.getProbabilityExists() *
                state.get().getProbabilityExists();
        if (stateImageObject.getTimesClickedWithAction() > 0 &&
                randomProbability > stateImageObject.getStaysVisibleAfterClicked())
            probExists = 0;
        boolean found = probExists > randomProbability;
        if (Report.minReportingLevel(Report.OutputLevel.HIGH)) {
            System.out.format("found=%s ", found);
            if (!found) System.out.format("%d>=%d ", randomProbability, probExists);
            //System.out.println();
        }
        if (found) return Optional.of(mockMatch.makeMockMatch(searchRegion, image));
        return Optional.empty();
    }

    public Matches getAllMatches(StateImageObject stateImageObject, Region searchRegion) {
        return getAllMatches(stateImageObject, searchRegion, stateImageObject.getImage());
    }

    public Matches getAllMatches(StateImageObject stateImageObject, Region searchRegion, Image image) {
        int possibleMatches = new Random().nextInt(10);
        Matches matches = new Matches();
        for (int i = 0; i < possibleMatches; i++) {
            Optional<Match> match = getMatch(stateImageObject, searchRegion);
            match.ifPresent(value -> {
                try {
                    matches.add(new MatchObject(value, stateImageObject));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        if (Report.minReportingLevel(Report.OutputLevel.HIGH))
            System.out.format("%s: %s: #matches=%s| \n", "getAllMatches",
                    image.getImageNames().toString(), matches.getMatchObjects().size());
        return matches;
    }

    public Matches getAllMatches(StateRegion searchRegion, int probability) { // for machine learning methods
        int possibleMatches = new Random().nextInt(10);
        Matches matches = new Matches();
        for (int i = 0; i < possibleMatches; i++) {
            // if match probability ? probability
            int row = new Random().nextInt(10);
            int col = new Random().nextInt(10);
            searchRegion.getSearchRegion().setRaster(10, 10);
            Match match = new Region(searchRegion.getSearchRegion().getCell(row, col)).toMatch();
            try {
                matches.add(new MatchObject(match, searchRegion));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (Report.minReportingLevel(Report.OutputLevel.HIGH))
            System.out.format("%s: %s: #matches=%s| \n", "getAllMatches",
                    searchRegion.getName(), matches.getMatchObjects().size());
        return matches;

    }
}
