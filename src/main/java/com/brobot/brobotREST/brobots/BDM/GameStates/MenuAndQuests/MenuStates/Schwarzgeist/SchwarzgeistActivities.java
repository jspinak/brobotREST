package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.Schwarzgeist;

import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;
import org.springframework.stereotype.Component;

@Component
public class SchwarzgeistActivities {

    private final Schwarzgeist schwarzgeist;
    private final AllStateObjectActions we;

    public SchwarzgeistActivities(Schwarzgeist schwarzgeist, AllStateObjectActions we) {
        this.schwarzgeist = schwarzgeist;
        this.we = we;
    }

    public boolean geistFuttern() {
        if (!we.click().RIP(schwarzgeist.getSchwarzenergie())) return false;
        we.waitForImage().pause(.4);
        futtern();
        we.click().RIP(schwarzgeist.getSchmuckTab());
        futtern();
        return true;
    }

    private boolean futtern() {
        we.waitForImage().pause(.8);
        if (!we.click().RIP(schwarzgeist.getAutoAuswahl())) return false;
        we.waitForImage().pause(.4);
        if (!we.click().RIP(schwarzgeist.getEnergieAbsorbieren())) return false;
        we.waitForImage().pause(.4);
        //return !we.click().RIP(schwarzgeist.getEnergieAbsorbierenBestatigen());
        return true;
    }

    public boolean dailyQuestAnnehmen() {
        if (!we.click().RIP(schwarzgeist.getSchwarzgeistQuest())) return false;
        we.waitForImage().pause(.5);
        if (!we.click().click(schwarzgeist.getQuestAnnehmen())) return false;
        we.waitForImage().pause(.5);
        //questBestatigung.advanceScreen();
        return true;
    }

}