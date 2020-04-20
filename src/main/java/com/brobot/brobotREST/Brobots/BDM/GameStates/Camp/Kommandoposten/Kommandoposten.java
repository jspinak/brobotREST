package com.brobot.brobotREST.Brobots.BDM.GameStates.Camp.Kommandoposten;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public @Data class Kommandoposten {

    private State state;
    private StateRIP gelderEinholen;
    private StateString esc;

    public Kommandoposten(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.KOMMANDOPOSTEN)
                .addStateText("Kommandoposten")
                .build();
        gelderEinholen = gameStateDirectorBDM
                .addStateRIP("gelderEinholen")
                .build();
        esc = gameStateDirectorBDM
                .addStateString(Key.ESC)
                .addStatesToActivate(GameStateEnumsBDM.CAMP)
                .build();
    }

}