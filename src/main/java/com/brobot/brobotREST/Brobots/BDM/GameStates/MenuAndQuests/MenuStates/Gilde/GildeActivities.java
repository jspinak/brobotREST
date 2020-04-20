package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.MenuStates.Gilde;

import com.brobot.brobotREST.StateObjects.ObjectMethods.AllGameObjectActions;
import org.springframework.stereotype.Component;

@Component
public class GildeActivities {

    private final Gilde gilde;
    private final AllGameObjectActions we;

    public GildeActivities(Gilde gilde, AllGameObjectActions we) {
        this.gilde = gilde;
        this.we = we;
    }

    public boolean machGildenQuest() {
        we.clickUntil().objectDisappears(gilde.getGildenQuest(), 2);
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
        if (!we.click().image(gilde.getBelohnungErhalten())) return false;
        we.waitForImage().pause(.5);
        if (!we.click().image(gilde.getBelohnungErhalten())) return false;
        we.waitForImage().pause(.5);
        //bdoGameRegion.clickSicher();
        return true;
    }

    private boolean questAnnehmen() {
        if (!we.click().image(gilde.getGildenQuestAnnehmen())) return false;
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