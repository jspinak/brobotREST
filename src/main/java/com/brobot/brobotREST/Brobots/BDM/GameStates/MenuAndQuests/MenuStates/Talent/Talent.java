package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.MenuStates.Talent;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Talent {

    private State state;
    private StateRIP talentIcon;
    private StateRIP talenStateText;
    private StateRIP alleTalentbucherVerwenden;
    private StateRIP verwendenBestatigung;

    public Talent(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.TALENT)
                .addStateText("Talent")
                .build();
        talentIcon = gameStateDirectorBDM
                .addStateRIP("talentIcon")
                .build();
        talenStateText = gameStateDirectorBDM
                .addStateRIP("talenStateText")
                .build();
        alleTalentbucherVerwenden = gameStateDirectorBDM
                .addStateRIP("alleTalentbucherVerwenden","alleTalentbucherVerwenden2")
                .build();
        verwendenBestatigung = gameStateDirectorBDM
                .addBestatigung()
                .build();
    }

}