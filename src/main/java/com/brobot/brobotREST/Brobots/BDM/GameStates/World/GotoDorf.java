package com.brobot.brobotREST.Brobots.BDM.GameStates.World;

import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public @Data class GotoDorf {

    private State state;
    private StateRIP zumDorfKnopf;
    private StateRIP automatischHin;
    private StateString esc;

    public GotoDorf(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.GO_TO_DORF)
                .addStateText("Dorf")
                .build();
        zumDorfKnopf = gameStateDirectorBDM
                .addStateRIP("zumDorfKnopf")
                .addStatesToActivate(GameStateEnumsBDM.WORLD)
                .build();
        automatischHin = gameStateDirectorBDM
                .addStateRIP("automatischHin")
                .addStatesToActivate(GameStateEnumsBDM.WORLD)
                .build();
        esc = gameStateDirectorBDM
                .addStateString(Key.ESC)
                .build();
    }
/*
    public boolean gehZumDorf() {
        System.out.print(" geh zum Dorf | ");
        return openState.clickIconClickBestatigung(openIcon, zumDorfIcon);
    }

    public boolean gehZumGespeichertemOrt() {
        System.out.print(" geh zum gespeichertem Ort | ");
        return openState.clickIconClickBestatigung(openIcon, automatischHin);
    }

 */
}
