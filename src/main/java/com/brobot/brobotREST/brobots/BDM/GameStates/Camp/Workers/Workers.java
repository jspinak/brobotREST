package com.brobot.brobotREST.brobots.BDM.GameStates.Camp.Workers;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.state.StateStringData;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Workers {

    private StateData state;
    private StateRIPData arbeiterListe;
    private StateRIPData arbeiterListeSchliessen;
    private StateRIPData alles;
    private StateStringData esc;

    private StateData abgeschlossenMessage;
    private StateImageData abgeschlossen;

    public Workers(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.WORKERS)
                .addStateText("Arbeiter-Liste")
                .build();
        arbeiterListe = stateDirector
                .addStateRIP("arbeiterListe")
                .build();
        arbeiterListeSchliessen = stateDirector
                .addStateRIP("arbeiterListeSchliessen")
                .addStatesToActivate(StateEnumsBDM.CAMP)
                .build();
        alles = stateDirector
                .addStateRIP("alles")
                .build();
        esc = stateDirector
                .addESC(StateEnumsBDM.CAMP);
        stateDirector.save();

        abgeschlossenMessage = stateDirector
                .initState(StateEnumsBDM.WORK_ABGESCHLOSSEN)
                .addStateText("abgeschlossen")
                .build();
        abgeschlossen = stateDirector
                .addStateImage("abgeschlossen")
                .addStatesToActivate(StateEnumsBDM.WORKERS)
                .build();
        stateDirector.save();
    }

}