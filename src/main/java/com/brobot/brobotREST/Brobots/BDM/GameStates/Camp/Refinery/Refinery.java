package com.brobot.brobotREST.Brobots.BDM.GameStates.Camp.Refinery;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.*;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public @Data
class Refinery {

    private State state;
    private StateRIP refineryIcon;
    private StateRIP allesAbholen;
    private StateString esc;

    public Refinery(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.REFINERY)
                .addStateText("Refinery")
                .build();
        refineryIcon = gameStateDirectorBDM
                .addStateRIP("refineryIcon")
                .build();
        allesAbholen = gameStateDirectorBDM
                .addStateRIP("allesAbholen")
                .build();
        esc = gameStateDirectorBDM
                .addStateString(Key.ESC)
                .addStatesToActivate(GameStateEnumsBDM.CAMP)
                .build();
    }

}