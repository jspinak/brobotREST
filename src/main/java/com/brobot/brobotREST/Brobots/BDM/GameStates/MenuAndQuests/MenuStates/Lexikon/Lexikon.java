package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.MenuStates.Lexikon;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Lexikon {

    private State state;
    private StateRIP lexikonStateText;
    private StateRIP automatischRegistrieren;
    private StateRIP prologTab;
    private StateRIP graberTab;

    public Lexikon(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.LEXIKON)
                .addStateText("Lexikon")
                .build();
        lexikonStateText = gameStateDirectorBDM
                .addStateRIP("lexikonStateText")
                .build();
        automatischRegistrieren = gameStateDirectorBDM
                .addStateRIP("automatischRegistrieren")
                .build();
        prologTab = gameStateDirectorBDM
                .addStateRIP("prologTab")
                .build();
        graberTab = gameStateDirectorBDM
                .addStateRIP("graberTab")
                .build();

    }

}