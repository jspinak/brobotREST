package com.brobot.brobotREST.brobots.BDM.StateBuilders;

import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import org.springframework.stereotype.Component;

@Component
public class StateDirectorBDM {

    private StateDirector stateDirector;

    public StateDirectorBDM(StateDirector stateDirector) {
        this.stateDirector = stateDirector;
    }

    public StateData newBestatigung(StateEnum name, StateEnum bestatigungActivates, StateEnum escActivates) {
        StateData state = stateDirector
                .initState(name)
                .addStateText("Nachricht")
                .setOnTopOfActivatingState()
                .build();
        stateDirector
                .addStateRIP("bestatigen")
                .addStatesToActivate(bestatigungActivates)
                .build();
        stateDirector
                .addESC(escActivates);
        stateDirector.save();
        return state;
    }

}
