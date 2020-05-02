package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.Gilde;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Gilde {

    private StateData state;
    private StateRIPData gildeText;
    private StateRIPData gildenQuest;
    private StateRIPData gildeLogin;
    private StateImageData gildenQuestAnnehmen;
    private StateRIPData gildenQuestAnnehmenBestatigen;
    private StateRIPData arbeitTab;
    private StateRIPData sortieren;
    private StateImageData belohnungErhalten;

    public Gilde(StateDirector stateDirector) {
        state = stateDirector
                .initState(StateEnumsBDM.GILDE)
                .addStateText("Gilde")
                .build();
        gildeText = stateDirector
                .addStateRIP("gildeStateText")
                .build();
        gildenQuest = stateDirector
                .addStateRIP("gildenQuest")
                .build();
        gildeLogin = stateDirector
                .addStateRIP("gildeLogin")
                .build();
        gildenQuestAnnehmen = stateDirector
                .addStateImage("gildenQuestAnnehmen")
                .build();
        gildenQuestAnnehmenBestatigen = stateDirector
                .addStateRIP("gildenQuestAnnehmenBestatigen")
                .build();
        arbeitTab = stateDirector
                .addStateRIP("anArbeitOrientiertTab")
                .build();
        sortieren = stateDirector
                .addStateRIP("guildQuestSortieren")
                .build();
        belohnungErhalten = stateDirector
                .addStateImage("guildErhalten")
                .build();
        stateDirector.save();
    }
}