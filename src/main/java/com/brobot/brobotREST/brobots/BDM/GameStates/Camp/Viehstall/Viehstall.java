package com.brobot.brobotREST.brobots.BDM.GameStates.Camp.Viehstall;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import com.brobot.brobotREST.database.state.StateStringData;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public @Data class Viehstall {

    private StateData state;
    private StateRIPData viehZuchten;
    private StateStringData esc;

    public Viehstall(StateDirector stateDirector) {

        stateDirector
                .initState(StateEnumsBDM.VIEHSTALL)
                .addStateText("Vieh","Viehstall")
                .build();
        viehZuchten = stateDirector
                .addStateRIP("viehZuchten")
                .build();
        esc = stateDirector
                .addStateString(Key.ESC)
                .addStatesToActivate(StateEnumsBDM.CAMP)
                .build();
        stateDirector.save();
    }


}