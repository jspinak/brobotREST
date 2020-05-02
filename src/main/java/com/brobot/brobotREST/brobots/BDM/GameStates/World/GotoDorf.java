package com.brobot.brobotREST.brobots.BDM.GameStates.World;

import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.state.StateStringData;
import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public @Data class GotoDorf {

    private StateData state;
    private StateRIPData zumDorfKnopf;
    private StateRIPData automatischHin;
    private StateStringData esc;

    public GotoDorf(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.GO_TO_DORF)
                .addStateText("Dorf")
                .build();
        zumDorfKnopf = stateDirector
                .addStateRIP("zumDorfKnopf")
                .addStatesToActivate(StateEnumsBDM.WORLD)
                .build();
        automatischHin = stateDirector
                .addStateRIP("automatischHin")
                .addStatesToActivate(StateEnumsBDM.WORLD)
                .build();
        esc = stateDirector
                .addStateString(Key.ESC)
                .build();
        stateDirector.save();
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
