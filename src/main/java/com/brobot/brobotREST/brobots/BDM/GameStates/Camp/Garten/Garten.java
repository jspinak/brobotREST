package com.brobot.brobotREST.brobots.BDM.GameStates.Camp.Garten;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import com.brobot.brobotREST.database.state.StateStringData;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public @Data class Garten {

    private StateData state;
    private StateRIPData allesErnten;
    private StateRIPData samenPflanzen;
    private StateRIPData allesPflanzen;
    private StateRIPData nachsteGarten;
    private StateStringData escGarten;

    public Garten(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.GARTEN)
                .addStateText("Samen","pflanzen")
                .build();
        allesErnten = stateDirector
                .addStateRIP("allesErnten")
                .build();
        samenPflanzen = stateDirector
                .addStateRIP("samenPflanzen")
                .build();
        allesPflanzen = stateDirector
                .addStateRIP("allesPflanzen")
                .build();
        nachsteGarten = stateDirector
                .addStateRIP("nachsteGarten")
                .build();
        escGarten = stateDirector
                .addStateString(Key.ESC)
                .addStatesToActivate(StateEnumsBDM.CAMP)
                .build();
        stateDirector.save();
    }

}