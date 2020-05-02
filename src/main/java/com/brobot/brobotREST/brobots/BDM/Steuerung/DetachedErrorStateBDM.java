package com.brobot.brobotREST.brobots.BDM.Steuerung;

import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import com.brobot.brobotREST.database.state.StateStringData;
import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateImageData;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class DetachedErrorStateBDM {

    private StateData state;
    private StateRIPData exitState;
    private StateRIPData exitMenu;
    private StateImageData x;
    private StateStringData escape;
    private StateRIPData imDorfWiederbeleben;

    public DetachedErrorStateBDM(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.ERROR_STATE_BDM)
                .build();
        exitState = stateDirector
                .addStateRIP("exitState","exitState2")
                .build();
        exitMenu = stateDirector
                .addStateRIP("exitMenu")
                .build();
        x = stateDirector
                .addStateImage("X")
                .build();
        escape = stateDirector
                .addESC(StateEnumsBDM.WORLD); //just a guess
        imDorfWiederbeleben = stateDirector
                .addStateRIP("imDorfWiederbeleben")
                .addStatesToActivate(StateEnumsBDM.WORLD)
                .build();
        stateDirector.save();
    }
}
