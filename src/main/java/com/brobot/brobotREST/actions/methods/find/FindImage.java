package com.brobot.brobotREST.actions.methods.find;

import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import com.brobot.brobotREST.actions.methods.wrappers.FindPattern;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindImage implements FindImageObject {

    private FindPattern findPattern;

    public FindImage(FindPattern findPattern) {
        this.findPattern = findPattern;
    }

    public Optional<Match> first(ActionOptions actionOptions, StateImageObject stateImage) {
        Optional<Match> matchOptional = Optional.empty();
        for (Region region : getRegions(actionOptions, stateImage)) {
            matchOptional = findPattern.firstPatternMatch(region, stateImage, actionOptions.getSimilarity());
            if (matchOptional.isPresent()) return matchOptional;
        }
        return matchOptional;
    }

    public Matches all(ActionOptions actionOptions, StateImageObject stateImage) {
        Matches matches = new Matches();
        for (Region region : getRegions(actionOptions, stateImage)) {
            matches.addAll(findPattern.allPatternMatches(
                    region, stateImage.getImage(), actionOptions.getSimilarity(), stateImage));
        }
        return matches;
    }

    private List<Region> getRegions(ActionOptions actionOptions, StateImageObject stateImage) {
        if (actionOptions.getSearchRegions().defined())
            return actionOptions.getSearchRegions().getAllRegions();
        return stateImage.getAllSearchRegions();
    }

}
