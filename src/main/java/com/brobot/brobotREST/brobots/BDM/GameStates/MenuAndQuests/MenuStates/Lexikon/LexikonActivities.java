package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.Lexikon;

import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;
import org.springframework.stereotype.Component;

@Component
public class LexikonActivities {

    private final Lexikon lexikon;
    private final AllStateObjectActions we;

    public LexikonActivities(Lexikon lexikon, AllStateObjectActions we) {
        this.lexikon = lexikon;
        this.we = we;
    }

    public boolean registerCategory(StateRIPData category) {
        we.click().RIP(category);
        we.waitForImage().pause(.5);
        return we.click().RIP(lexikon.getAutomatischRegistrieren());
    }
    public boolean registrieren() {
        if (!we.click().RIP(lexikon.getAutomatischRegistrieren())) return false;
        return registerCategory(lexikon.getPrologTab()) && registerCategory(lexikon.getGraberTab());
    }

}