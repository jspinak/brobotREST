package com.brobot.brobotREST.brobots.BDM.GameStates.Camp.Refinery;

import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;

public class RefineryActivities {

    private Refinery refinery;
    private AllStateObjectActions we;

    public RefineryActivities(Refinery refinery, AllStateObjectActions we) {
        this.refinery = refinery;
        this.we = we;
    }

    public boolean getBlackStones() {
        return we.click().RIP(refinery.getAllesAbholen());
    }

}
