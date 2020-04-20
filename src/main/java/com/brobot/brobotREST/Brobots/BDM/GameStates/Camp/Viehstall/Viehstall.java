package com.brobot.brobotREST.Brobots.BDM.GameStates.Camp.Viehstall;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public @Data class Viehstall {

    private State state;
    private StateRIP viehZuchten;
    private StateString esc;

    public Viehstall(GameStateDirectorBDM gameStateDirectorBDM) {

        gameStateDirectorBDM
                .init(GameStateEnumsBDM.VIEHSTALL)
                .addStateText("Vieh","Viehstall")
                .build();
        viehZuchten = gameStateDirectorBDM
                .addStateRIP("viehZuchten")
                .build();
        esc = gameStateDirectorBDM
                .addStateString(Key.ESC)
                .addStatesToActivate(GameStateEnumsBDM.CAMP)
                .build();
    }


}