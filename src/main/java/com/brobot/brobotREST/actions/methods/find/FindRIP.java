package com.brobot.brobotREST.actions.methods.find;

import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.methods.wrappers.FindPattern;
import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.primitives.regionImagePairs.RegionImagePair;
import com.brobot.brobotREST.database.primitives.regionImagePairs.RegionImagePairs;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FindRIP implements FindImageObject {

    /*
    RegionImagePairs contains pairs of <region,image>.
    When an image is found, it's associated region is defined there.

    We want to look first for images that have already been found, in their associated regions.
    If it is not found, we search for the remaining images.
     */

    private FindPattern findPattern;

    public FindRIP(FindPattern findPattern) {
        this.findPattern = findPattern;
    }

    // When the image has never been found, the search region is used.
    public Region useRegion(RegionImagePair pair, Region searchRegion) {
        return pair.getRegion().defined() ? pair.getRegion() : searchRegion;
    }

    private Set<RegionImagePair> getDefinedPairs(RegionImagePairs regionImagePairs) {
        return regionImagePairs.getPairs().stream()
                .filter(pair -> pair.getRegion().defined())
                .collect(Collectors.toSet());
    }

    private Set<RegionImagePair> getUndefinedPairs(RegionImagePairs regionImagePairs) {
        return regionImagePairs.getPairs().stream()
                .filter(pair -> !pair.getRegion().defined())
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Match> first(ActionOptions actionOptions, StateImageObject stateImage) {
        //System.out.print("RIP first| ");
        Optional<Match> match = findMatch(stateImage, getDefinedPairs(stateImage.getRegionImagePairs()),
                actionOptions.getSimilarity(), stateImage.getSearchRegion());
        if (match.isPresent()) return match;
        return findMatch(stateImage, getUndefinedPairs(stateImage.getRegionImagePairs()),
                actionOptions.getSimilarity(), stateImage.getSearchRegion());
    }

    // returns the best matches for each pair (max one match per pair)
    // defines each pair's region when found
    @Override
    public Matches all(ActionOptions actionOptions, StateImageObject stateImageObject) {
        Matches matches = new Matches();
        for (RegionImagePair pair : stateImageObject.getRegionImagePairs().getPairs()) {
            Optional<Match> match = findMatch(stateImageObject, Collections.singleton(pair),
                    actionOptions.getSimilarity(), stateImageObject.getSearchRegion());
            if (match.isPresent()) {
                try {
                    matches.add(new MatchObject(match.get(), stateImageObject));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return matches;
    }

    private Optional<Match> findMatch(StateImageObject stateImageObject, Set<RegionImagePair> pairs,
                                      double similarity, Region searchRegion) {
        Optional<Match> match;
        for (RegionImagePair pair : pairs) {
            match = findPattern.firstPatternMatch(
                    useRegion(pair, searchRegion), stateImageObject, pair.getImage(), similarity);
            if (match.isPresent()) {
                if (!pair.getRegion().defined()) pair.setRegion(new Region(match.get()));
                return match;
            }
        }
        return Optional.empty();
    }


}
