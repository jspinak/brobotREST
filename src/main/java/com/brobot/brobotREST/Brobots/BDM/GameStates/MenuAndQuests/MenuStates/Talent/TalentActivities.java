package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.MenuStates.Talent;

import com.brobot.brobotREST.StateObjects.ObjectMethods.AllGameObjectActions;
import org.springframework.stereotype.Component;

@Component
public class TalentActivities {

    private Talent talent;
    private AllGameObjectActions we;

    public TalentActivities(Talent talent, AllGameObjectActions we) {
        this.talent = talent;
        this.we = we;
    }

    public boolean bucherVerwenden() {
        we.click().RIP(talent.getAlleTalentbucherVerwenden());
        we.click().RIP(talent.getVerwendenBestatigung());
        return true;//we.waitForRIP().wait(15.0, );
    }

}