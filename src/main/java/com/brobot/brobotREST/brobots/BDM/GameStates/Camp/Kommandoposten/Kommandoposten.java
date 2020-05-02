package com.brobot.brobotREST.brobots.BDM.GameStates.Camp.Kommandoposten;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import com.brobot.brobotREST.database.state.StateStringData;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public @Data class Kommandoposten {

    private StateData state;
    private StateRIPData gelderEinholen;
    private StateStringData esc;

    public Kommandoposten(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.KOMMANDOPOSTEN)
                .addStateText("Kommandoposten")
                .build();
        gelderEinholen = stateDirector
                .addStateRIP("gelderEinholen")
                .build();
        esc = stateDirector
                .addStateString(Key.ESC)
                .addStatesToActivate(StateEnumsBDM.CAMP)
                .build();
        stateDirector.save();
    }

}