package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.Lexikon;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Lexikon {

    private StateData state;
    private StateRIPData lexikonStateText;
    private StateRIPData automatischRegistrieren;
    private StateRIPData prologTab;
    private StateRIPData graberTab;

    public Lexikon(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.LEXIKON)
                .addStateText("Lexikon")
                .build();
        lexikonStateText = stateDirector
                .addStateRIP("lexikonStateText")
                .build();
        automatischRegistrieren = stateDirector
                .addStateRIP("automatischRegistrieren")
                .build();
        prologTab = stateDirector
                .addStateRIP("prologTab")
                .build();
        graberTab = stateDirector
                .addStateRIP("graberTab")
                .build();
        stateDirector.save();
    }

}