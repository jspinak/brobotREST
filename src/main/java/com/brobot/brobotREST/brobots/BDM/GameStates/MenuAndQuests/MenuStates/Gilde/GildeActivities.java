package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.Gilde;

import com.brobot.brobotREST.stateData.wrappers.StateObjectWrapperFactory;
import com.brobot.brobotREST.stateData.wrappers.StateRIPWrapper;
import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;
import org.springframework.stereotype.Component;

@Component
public class GildeActivities {

    private final Gilde gilde;
    private final AllStateObjectActions we;

    private StateRIPWrapper gildenQuest;

    public GildeActivities(Gilde gilde, AllStateObjectActions we,
                           StateObjectWrapperFactory stateObjectWrapperFactory) {
        this.gilde = gilde;
        this.we = we;

        gildenQuest = stateObjectWrapperFactory.buildRIPWrapper(gilde.getGildenQuest());
    }

    public boolean machGildenQuest() {
        we.clickUntil().objectDisappears(gildenQuest,2);
        we.waitForImage().pause(.5);
        getBelohnungUndQuestAnnehmen();
        we.click().RIP(gilde.getArbeitTab());
        return getBelohnungUndQuestAnnehmen();
    }

    private boolean getBelohnungUndQuestAnnehmen() {
        getBelohnung();
        questAnnehmen();
        we.click().RIP(gilde.getSortieren());
        getBelohnung();
        questAnnehmen();
        return true;
    }

    private boolean getBelohnung() {
        if (!we.click().click(gilde.getBelohnungErhalten())) return false;
        we.waitForImage().pause(.5);
        if (!we.click().click(gilde.getBelohnungErhalten())) return false;
        we.waitForImage().pause(.5);
        //bdoGameRegion.clickSicher();
        return true;
    }

    private boolean questAnnehmen() {
        if (!we.click().click(gilde.getGildenQuestAnnehmen())) return false;
        we.waitForImage().pause(.5);
        we.click().RIP(gilde.getGildenQuestAnnehmenBestatigen());
        //return !bdoGameRegion.clickIfExists(bdoButtons.X);
        return true;
    }

    public boolean login() {
        we.waitForImage().pause(.5);
        return we.click().RIP(gilde.getGildeLogin());
    }

    public boolean loginAndMachQuest() {
        boolean questMachen = machGildenQuest();
        boolean loggedIn = login();
        return loggedIn || questMachen;
    }

}