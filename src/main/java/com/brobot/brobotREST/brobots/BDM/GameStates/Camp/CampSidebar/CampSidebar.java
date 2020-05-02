package com.brobot.brobotREST.brobots.BDM.GameStates.Camp.CampSidebar;

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
class CampSidebar {

    private StateData state;
    private StateRIPData schwarzsteinErhaltenButton;
    private StateRIPData postenTab;
    private StateRIPData campUnterstutzungTab;
    private StateRIPData viehstallTab;
    private StateStringData escSidebar;

    public CampSidebar(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.CAMP_SIDEBAR)
                .addStateText("Anlagen", "errichten", "structures")
                .build();
        schwarzsteinErhaltenButton = stateDirector
                .addStateRIP("schwarzsteinErhaltenButton")
                .addStatesToActivate(StateEnumsBDM.REFINERY)
                .build();
        postenTab = stateDirector
                .addStateRIP("postenTab")
                .addStatesToActivate(StateEnumsBDM.POSTEN_GEBAUDE)
                .build();
        campUnterstutzungTab = stateDirector
                .addStateRIP("campUnterstutzungTab")
                .addStatesToActivate(StateEnumsBDM.KOMMANDOPOSTEN)
                .build();
        viehstallTab = stateDirector
                .addStateRIP("viehstallTab")
                .addStatesToActivate(StateEnumsBDM.VIEHSTALL)
                .build();
        escSidebar = stateDirector
                .addStateString(Key.ESC)
                .addStatesToActivate(StateEnumsBDM.CAMP)
                .build();
        stateDirector.save();
    }

}