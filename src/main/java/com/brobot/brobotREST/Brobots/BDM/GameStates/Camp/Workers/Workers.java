package com.brobot.brobotREST.Brobots.BDM.GameStates.Camp.Workers;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Workers {

    private State state;
    private StateRIP arbeiterListe;
    private StateRIP arbeiterListeSchliessen;
    private StateRIP alles;
    private StateString esc;

    private State abgeschlossenMessage;
    private StateImage abgeschlossen;

    public Workers(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.WORKERS)
                .addStateText("Arbeiter-Liste")
                .build();
        arbeiterListe = gameStateDirectorBDM
                .addStateRIP("arbeiterListe")
                .build();
        arbeiterListeSchliessen = gameStateDirectorBDM
                .addStateRIP("arbeiterListeSchliessen")
                .addStatesToActivate(GameStateEnumsBDM.CAMP)
                .build();
        alles = gameStateDirectorBDM
                .addStateRIP("alles")
                .build();
        esc = gameStateDirectorBDM
                .escActivates(GameStateEnumsBDM.CAMP);

        abgeschlossenMessage = gameStateDirectorBDM
                .init(GameStateEnumsBDM.WORK_ABGESCHLOSSEN)
                .addStateText("abgeschlossen")
                .build();
        abgeschlossen = gameStateDirectorBDM
                .addStateImage("abgeschlossen")
                .addStatesToActivate(GameStateEnumsBDM.WORKERS)
                .build();
    }

}