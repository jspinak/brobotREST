package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.MenuStates.Lexikon;

import com.brobot.brobotREST.StateObjects.ObjectMethods.AllGameObjectActions;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import org.springframework.stereotype.Component;

@Component
public class LexikonActivities {

    private final Lexikon lexikon;
    private final AllGameObjectActions we;

    public LexikonActivities(Lexikon lexikon, AllGameObjectActions we) {
        this.lexikon = lexikon;
        this.we = we;
    }

    public boolean registerCategory(StateRIP category) {
        we.click().RIP(category);
        we.waitForImage().pause(.5);
        return we.click().RIP(lexikon.getAutomatischRegistrieren());
    }
    public boolean registrieren() {
        if (!we.click().RIP(lexikon.getAutomatischRegistrieren())) return false;
        return registerCategory(lexikon.getPrologTab()) && registerCategory(lexikon.getGraberTab());
    }

}