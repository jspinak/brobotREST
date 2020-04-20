package com.brobot.brobotREST.Brobots.BDM.GameStates.Camp.CampSidebar;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.*;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public @Data
class CampSidebar {

    private State state;
    private StateRIP schwarzsteinErhaltenButton;
    private StateRIP postenTab;
    private StateRIP campUnterstutzungTab;
    private StateRIP viehstallTab;
    private StateString escSidebar;

    public CampSidebar(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.CAMP_SIDEBAR)
                .addStateText("Anlagen","errichten","structures")
                .build();
        schwarzsteinErhaltenButton = gameStateDirectorBDM
                .addStateRIP("schwarzsteinErhaltenButton")
                .addStatesToActivate(GameStateEnumsBDM.REFINERY)
                .build();
        postenTab = gameStateDirectorBDM
                .addStateRIP("postenTab")
                .addStatesToActivate(GameStateEnumsBDM.POSTEN_GEBAUDE)
                .build();
        campUnterstutzungTab = gameStateDirectorBDM
                .addStateRIP("campUnterstutzungTab")
                .addStatesToActivate(GameStateEnumsBDM.KOMMANDOPOSTEN)
                .build();
        viehstallTab = gameStateDirectorBDM
                .addStateRIP("viehstallTab")
                .addStatesToActivate(GameStateEnumsBDM.VIEHSTALL)
                .build();
        escSidebar = gameStateDirectorBDM
                .addStateString(Key.ESC)
                .addStatesToActivate(GameStateEnumsBDM.CAMP)
                .build();
    }

}