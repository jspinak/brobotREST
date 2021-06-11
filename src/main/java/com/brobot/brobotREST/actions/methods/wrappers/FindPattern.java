package com.brobot.brobotREST.actions.methods.wrappers;

import com.brobot.brobotREST.database.primitives.image.Image;
import com.brobot.brobotREST.database.primitives.image.ImagePatterns;
import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import com.brobot.brobotREST.mock.Mock;
import com.brobot.brobotREST.mock.Report;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class FindPattern {

    private ImagePatterns imagePatterns;
    private Mock mock;

    public FindPattern(ImagePatterns imagePatterns, Mock mock) {
        this.imagePatterns = imagePatterns;
        this.mock = mock;
    }

    private Optional<Match> findBest(Region region, Pattern pattern) {
        try {
            Match match = region.sikuli().find(pattern);
            return Optional.ofNullable(match);
        } catch (FindFailed ignored) {}
        return Optional.empty();
    }

    private Matches findAll(Region region, Pattern pattern, StateImageObject stateImageObject) {
        Matches matches = new Matches();
        Iterator<Match> newMatches;
        try {
            newMatches = region.sikuli().findAll(pattern);
            while (newMatches.hasNext()) {
                try {
                    matches.add(new MatchObject(newMatches.next(), stateImageObject));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (FindFailed ignored) {}
        return matches;
    }

    public Optional<Match> firstPatternMatch(Region region, StateImageObject image, double similarity) {
        return firstPatternMatch(region, image, image.getImage(), similarity);
    }

    public Optional<Match> firstPatternMatch(Region region, StateImageObject stateImageObject,
                                             Image image, double similarity) {
        if (mock.isActive()) {
            //System.out.print("mock first pattern ");
            //if (stateImageObject.getImage().getAllPatterns().size() > 0 && Report.minReportingLevel(Report.OutputLevel.LOW))
            //    System.out.format("%s.%s| ", "findPattern", image.getAllPatterns().get(0));
            return mock.getMatch(stateImageObject, region);
        }
        List<Pattern> patterns = imagePatterns.getPatterns(image, similarity);
        Optional<Match> match = Optional.empty();
        for (Pattern pattern : patterns) {
            match = findBest(region, pattern);
            if (match.isPresent()) return match;
        }
        return match;
    }

    public Matches allPatternMatches(Region region, Image image, double similarity,
                                     StateImageObject stateImageObject) {
        if (mock.isActive()) return mock.getAllMatches(stateImageObject, region);
        List<Pattern> patterns = imagePatterns.getPatterns(image, similarity);
        Matches matches = new Matches();
        for (Pattern pattern : patterns) {
            matches.addAll(findAll(region, pattern, stateImageObject));
        }
        return matches;
    }
}
