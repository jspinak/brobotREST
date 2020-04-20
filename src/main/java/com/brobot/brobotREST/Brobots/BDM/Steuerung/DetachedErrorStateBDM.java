package com.brobot.brobotREST.Brobots.BDM.Steuerung;

import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class DetachedErrorStateBDM {

    private State state;
    private StateRIP exitState;
    private StateRIP exitMenu;
    private StateImage x;
    private StateString escape;
    private StateRIP imDorfWiederbeleben;

    public DetachedErrorStateBDM(GameStateDirectorBDM gameStateDirectorBDM, MockRegion mockRegion) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.ERROR_STATE_BDM)
                .build();
        exitState = gameStateDirectorBDM
                .addStateRIP("exitState","exitState2")
                .build();
        exitMenu = gameStateDirectorBDM
                .addStateRIP("exitMenu")
                .build();
        x = gameStateDirectorBDM
                .addStateImage("X")
                .build();
        escape = gameStateDirectorBDM
                .escActivates(GameStateEnumsBDM.WORLD); //just a guess
        imDorfWiederbeleben = gameStateDirectorBDM
                .addStateRIP("imDorfWiederbeleben")
                .addStatesToActivate(GameStateEnumsBDM.WORLD)
                .build();
    }
}
