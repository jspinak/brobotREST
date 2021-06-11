package com.brobot.brobotREST.actions.methods.find;

import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import com.brobot.brobotREST.mock.MockStatus;
import com.brobot.brobotREST.mock.Report;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.brobot.brobotREST.actions.ActionOptions.DoOnEach.BEST;
import static com.brobot.brobotREST.actions.ActionOptions.DoOnEach.FIRST;

@Component
// for both StateImage and StateRIP
public class FindImageOrRIP {

    private final Map<Boolean, FindImageObject> findMethod = new HashMap<>();

    public FindImageOrRIP(FindImage findImage, FindRIP findRIP) {
        findMethod.put(false, findImage);
        findMethod.put(true, findRIP);
    }

    public Matches first(ActionOptions actionOptions, List<StateImageObject> images) {
        Matches matches = new Matches();
        if (images.size() == 0) return matches;
        if (Report.minReportingLevel(Report.OutputLevel.LOW)) {
            System.out.print("|Find.First ");
        }
        for (StateImageObject image : images) {
            if (Report.minReportingLevel(Report.OutputLevel.LOW)) {
                System.out.print(image.getOwnerStateName()+"."+image.getName()+" ");
            }
            Optional<Match> match = findMethod.get(image.isFixed()).first(actionOptions, image);
            if (match.isPresent()) {
                try {
                    matches.add(new MatchObject(match.get(), image));
                    return matches;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return matches;
    }

    public Matches best(ActionOptions actionOptions, List<StateImageObject> images) {
        if (Report.minReportingLevel(Report.OutputLevel.LOW)) {
            System.out.print("Find.Best |");
        }
        Matches matches = new Matches();
        Optional<MatchObject> bestMatch = all(actionOptions, images).getBestMatch();
        bestMatch.ifPresent(matches::add);
        return matches;
    }

    public Matches all(ActionOptions actionOptions, List<StateImageObject> images) {
        if (Report.minReportingLevel(Report.OutputLevel.LOW)) {
            System.out.print("Find.All |");
        }
        Matches matches = new Matches();
        for (StateImageObject image : images) {
            matches.addAll(findMethod.get(image.isFixed()).all(actionOptions, image));
        }
        return matches;
    }

    public Matches each(ActionOptions actionOptions, List<StateImageObject> images) {
        if (Report.minReportingLevel(Report.OutputLevel.LOW)) {
            System.out.print("Find.Each |");
        }
        Matches matches = new Matches();
        for (StateImageObject image : images) {
            Optional<MatchObject> match;
            if (actionOptions.getDoOnEach() == BEST)
                match = all(actionOptions, Collections.singletonList(image)).getBestMatch();
            else match = first(actionOptions, Collections.singletonList(image)).getBestMatch(); // FIRST is default
            match.ifPresent(matches::add);
        }
        return matches;
    }
}
