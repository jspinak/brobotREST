package com.brobot.brobotREST.Brobots.BDM.GameStates.Camp.Refinery;

import com.brobot.brobotREST.StateObjects.ObjectMethods.AllGameObjectActions;

public class RefineryActivities {

    private Refinery refinery;
    private AllGameObjectActions we;

    public RefineryActivities(Refinery refinery, AllGameObjectActions we) {
        this.refinery = refinery;
        this.we = we;
    }

    public boolean getBlackStones() {
        return we.click().RIP(refinery.getAllesAbholen());
    }

}
