package com.brobot.brobotREST.brobots.BDM.GameStates.Camp.Refinery;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.state.StateStringData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public @Data
class Refinery {

    private StateData state;
    private StateRIPData refineryIcon;
    private StateRIPData allesAbholen;
    private StateStringData esc;

    public Refinery(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.REFINERY)
                .addStateText("Refinery")
                .build();
        refineryIcon = stateDirector
                .addStateRIP("refineryIcon")
                .build();
        allesAbholen = stateDirector
                .addStateRIP("allesAbholen")
                .build();
        esc = stateDirector
                .addStateString(Key.ESC)
                .addStatesToActivate(StateEnumsBDM.CAMP)
                .build();
        stateDirector.save();
    }

}