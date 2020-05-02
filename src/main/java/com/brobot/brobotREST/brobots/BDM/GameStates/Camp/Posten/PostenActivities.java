package com.brobot.brobotREST.brobots.BDM.GameStates.Camp.Posten;

import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;

public class PostenActivities {

    private Posten posten;
    private AllStateObjectActions we;

    public PostenActivities(Posten posten, AllStateObjectActions we) {
        this.posten = posten;
        this.we = we;
    }

    private boolean aufgabeBeginnen() {
        we.click().RIP(posten.getAutoAuswahl());
        we.waitForImage().pause(.5);
        return we.click().RIP(posten.getArbeiterSenden());
    }

    private boolean sammelPostUndEntsenden() {
        if (!we.click().click(posten.getHandelFertig())) return false;
        we.waitForImage().pause(.5);
        we.clickAll().images(posten.getPostenErhalten());
        we.waitForImage().pause(.5);
        we.click().click(posten.getEntsenden());
        boolean entsendet = aufgabeBeginnen();
        we.type().string(posten.getEscArbeiterEntsenden());
        we.type().string(posten.getEscPostenVerwalten());
        return entsendet;
    }

    private boolean getRewards() {
        boolean gotReward = false;
        for (int i=0; i<3; i++) {
            if (!we.click().click(posten.getPostenReward())) break;
            gotReward = true;
            we.waitForImage().pause(1.0);
        }
        return gotReward;
    }

    public boolean postenVerwalten() {
        System.out.print(" posten verwalten | ");
        for (int i = 0; i < 5; i++) {
            if (!sammelPostUndEntsenden()) break;
        }
        getRewards();
        System.out.print(" posten fertig. ");
        return true;
    }
}
