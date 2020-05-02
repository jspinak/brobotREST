package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.Talent;

import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;
import org.springframework.stereotype.Component;

@Component
public class TalentActivities {

    private Talent talent;
    private AllStateObjectActions we;

    public TalentActivities(Talent talent, AllStateObjectActions we) {
        this.talent = talent;
        this.we = we;
    }

    public boolean bucherVerwenden() {
        we.click().RIP(talent.getAlleTalentbucherVerwenden());
        we.click().RIP(talent.getVerwendenBestatigung());
        return true;//we.waitForRIP().wait(15.0, );
    }

}