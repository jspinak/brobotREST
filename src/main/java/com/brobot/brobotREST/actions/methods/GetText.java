package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.actions.ActionInterface;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.actions.methods.find.Find;
import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.mock.Mock;
import com.brobot.brobotREST.actions.methods.wrappers.Wait;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GetText implements ActionInterface {

    private Mock mock;
    private Find find;
    private Wait wait;

    public GetText(Mock mock, Find find, Wait wait) {
        this.mock = mock;
        this.find = find;
        this.wait = wait;
    }

    public Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        Matches matches = new Matches();
        for (int i = 0; i < actionOptions.getTimesToRepeatActionUntil(); i++) {
            matches = getText(actionOptions, objectCollections);
            if (actionOptions.getGetTextUntil() == ActionOptions.GetTextUntil.TEXT_APPEARS
                && !matches.getText().isEmpty())
                break;
            if (actionOptions.getGetTextUntil() == ActionOptions.GetTextUntil.TEXT_VANISHES
                    && matches.getText().isEmpty())
                break;
            if (i < actionOptions.getTimesToRepeatActionUntil() - 1)
                wait.wait(actionOptions.getPauseBetweenActionRepetitions());
        }
        return matches;
    }

    private Matches getText(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        Matches matches = find.perform(actionOptions, objectCollections);
        LocalDateTime startTime = LocalDateTime.now();
        long nanoTimeout = 0;
        while (LocalDateTime.now().isBefore(startTime.plusNanos(nanoTimeout)) && matches.getText().equals("")) {
            nanoTimeout = (long) (actionOptions.getMaxWait() * Math.pow(10, 9));
            for (MatchObject matchObject : matches.getMatchObjects()) {
                if (mock.isActive()) matchObject.setText("Sample Mock Text");
                else matchObject.setText(matchObject.getMatch().text());
            }
        }
        matches.setDuration(matches.getDuration().plusNanos(nanoTimeout));
        return matches;
    }
    
}
