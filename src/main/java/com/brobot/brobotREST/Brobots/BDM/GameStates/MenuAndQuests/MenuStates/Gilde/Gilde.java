package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.MenuStates.Gilde;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Gilde {

    private State state;
    private StateRIP gildeText;
    private StateRIP gildenQuest;
    private StateRIP gildeLogin;
    private StateImage gildenQuestAnnehmen;
    private StateRIP gildenQuestAnnehmenBestatigen;
    private StateRIP arbeitTab;
    private StateRIP sortieren;
    private StateImage belohnungErhalten;

    public Gilde(GameStateDirectorBDM gameStateDirectorBDM) {
        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.GILDE)
                .addStateText("Gilde")
                .build();
        gildeText = gameStateDirectorBDM
                .addStateRIP("gildeStateText")
                .build();
        gildenQuest = gameStateDirectorBDM
                .addStateRIP("gildenQuest")
                .build();
        gildeLogin = gameStateDirectorBDM
                .addStateRIP("gildeLogin")
                .build();
        gildenQuestAnnehmen = gameStateDirectorBDM
                .addStateImage("gildenQuestAnnehmen")
                .build();
        gildenQuestAnnehmenBestatigen = gameStateDirectorBDM
                .addStateRIP("gildenQuestAnnehmenBestatigen")
                .build();
        arbeitTab = gameStateDirectorBDM
                .addStateRIP("anArbeitOrientiertTab")
                .build();
        sortieren = gameStateDirectorBDM
                .addStateRIP("guildQuestSortieren")
                .build();
        belohnungErhalten = gameStateDirectorBDM
                .addStateImage("guildErhalten")
                .build();

    }
}