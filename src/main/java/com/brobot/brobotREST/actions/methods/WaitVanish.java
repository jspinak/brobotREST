package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.actions.ActionInterface;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.actions.methods.find.Find;
import com.brobot.brobotREST.database.primitives.match.Matches;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class WaitVanish implements ActionInterface {

    private Find find;

    public WaitVanish(Find find) {
        this.find = find;
    }

    // search for matches. if none exist, return true.
    // the DoOn options have no effect here.
    public Matches perform(ActionOptions actionOptions, ObjectCollection[] objectCollections) {
        actionOptions.setFind(ActionOptions.Find.FIRST);
        Matches matches = new Matches();
        LocalDateTime startTime = LocalDateTime.now();
        long nanoTimeout = 0;
        while (LocalDateTime.now().isBefore(startTime.plusNanos(nanoTimeout)) && !matches.isEmpty()) {
            nanoTimeout = (long) (actionOptions.getMaxWait() * Math.pow(10,9));
            matches = find.perform(actionOptions, objectCollections[0]);
            matches.setDuration(Duration.between(startTime, LocalDateTime.now()));
        }
        matches.setSuccess(matches.isEmpty());
        return matches;
    }

}