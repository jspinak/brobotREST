package com.brobot.brobotREST.actions.composites;

import com.brobot.brobotREST.actions.Action;
import com.brobot.brobotREST.actions.ObjectCollection;
import org.springframework.stereotype.Component;

@Component
public class Select {

    private Action action;

    public Select(Action action) {
        this.action = action;
    }

    public boolean select(SelectActionObject sao) {
        sao.resetTotalSwipes();
        for (int i=0; i<sao.getMaxSwipes(); i++) {
            sao.setFoundMatches(action.perform(sao.getFindActionOptions(), sao.getFindObjectCollection()));
            if (sao.getFoundMatches().isSuccess()) {
                action.perform(sao.getClickActionOptions(), new ObjectCollection.Builder()
                        .withMatches(sao.getFoundMatches())
                        .build());
                if (sao.getConfirmationObjectCollection() == null) {
                    sao.setSuccess(true);
                    return true;
                }
                else {
                    sao.setFoundConfirmations(action.perform(
                            sao.getConfirmActionOptions(), sao.getConfirmationObjectCollection()));
                    if (sao.getFoundConfirmations().isSuccess()) {
                        sao.setSuccess(true);
                        return true;
                    }
                }
            }
            action.perform(sao.getSwipeActionOptions(), sao.getSwipeFromObjColl(), sao.getSwipeToObjColl());
            sao.addSwipe();
        }
        return false;
    }
}
