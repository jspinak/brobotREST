package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.actions.ActionInterface;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.actions.methods.find.Find;
import com.brobot.brobotREST.actions.methods.wrappers.Wait;
import com.brobot.brobotREST.database.primitives.match.Matches;
import org.springframework.stereotype.Component;

@Component
public class ClickUntil implements ActionInterface {

    private Find find;
    private Click click;
    private Wait wait;

    public ClickUntil(Find find, Click click, Wait wait) {
        this.find = find;
        this.click = click;
        this.wait = wait;
    }

    public Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        Matches matches1 = new Matches();
        if (objectCollections.length == 0) return matches1;
        Matches matches2;
        printClickType(actionOptions);
        for (int i = 0; i < actionOptions.getTimesToRepeatActionUntil(); i++) {
            System.out.print("(#" + (i + 1));
            setSuccessCriteriaBeforeClick(actionOptions, i);
            matches1 = click.perform(actionOptions, objectCollections[0]);
            if (searchObjects2(actionOptions.getClickUntil(), objectCollections)) {
                setSuccessCriteriaBeforeFind(actionOptions);
                matches2 = find.perform(actionOptions, objectCollections[1]);
                System.out.print(") ");
                return matches2;
            } else if (matches1.isSuccess()) {
                System.out.print(") ");
                return matches1;
            }
            if (i < actionOptions.getTimesToRepeatActionUntil() - 1)
                wait.wait(actionOptions.getPauseBetweenActionRepetitions());
        }
        System.out.print(") ");
        return matches1;
    }

    private void printClickType(ActionOptions actionOptions) {
        System.out.print("click.");
        if (actionOptions.getClickUntil() != ActionOptions.ClickUntil.NO_CONDITION) {
            System.out.print("until." + actionOptions.getClickUntil());
        }
    }

    private boolean searchObjects2(ActionOptions.ClickUntil clickUntil,
                                   ObjectCollection... objectCollections) {
        return objectCollections.length > 1 &&
                (clickUntil == ActionOptions.ClickUntil.OBJECTS2_APPEAR ||
                        clickUntil == ActionOptions.ClickUntil.OBJECTS2_VANISH);
    }

    // click until objects1 vanish: reverse search (search until not found)
    // click until objects2 vanish: full search for every click on obj1, reverse search for obj2
    // click until objects2 appear: full search for both always (default)
    private void setSuccessCriteriaBeforeClick(ActionOptions actionOptions, int iteration) {
        if (actionOptions.getClickUntil() == ActionOptions.ClickUntil.OBJECTS1_VANISH)
            actionOptions.setSuccessEvaluation(Matches::isEmpty);
        else actionOptions.setSuccessEvaluation(matches -> !matches.isEmpty());
    }

    private void setSuccessCriteriaBeforeFind(ActionOptions actionOptions) {
        if (actionOptions.getClickUntil() == ActionOptions.ClickUntil.OBJECTS2_VANISH)
            actionOptions.setSuccessEvaluation(Matches::isEmpty);
    }
}
