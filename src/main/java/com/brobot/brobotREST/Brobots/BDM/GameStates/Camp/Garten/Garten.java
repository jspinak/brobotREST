package com.brobot.brobotREST.Brobots.BDM.GameStates.Camp.Garten;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public @Data class Garten {

    private State state;
    private StateRIP allesErnten;
    private StateRIP samenPflanzen;
    private StateRIP allesPflanzen;
    private StateRIP nachsteGarten;
    private StateString escGarten;

    public Garten(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.GARTEN)
                .addStateText("Samen","pflanzen")
                .build();
        allesErnten = gameStateDirectorBDM
                .addStateRIP("allesErnten")
                .build();
        samenPflanzen = gameStateDirectorBDM
                .addStateRIP("samenPflanzen")
                .build();
        allesPflanzen = gameStateDirectorBDM
                .addStateRIP("allesPflanzen")
                .build();
        nachsteGarten = gameStateDirectorBDM
                .addStateRIP("nachsteGarten")
                .build();
        escGarten = gameStateDirectorBDM
                .addStateString(Key.ESC)
                .addStatesToActivate(GameStateEnumsBDM.CAMP)
                .build();
    }

}