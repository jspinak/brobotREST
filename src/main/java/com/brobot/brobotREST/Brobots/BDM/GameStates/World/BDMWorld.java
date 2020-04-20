package com.brobot.brobotREST.Brobots.BDM.GameStates.World;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class BDMWorld {

    private State state;
    private StateRIP worldStateImageSt;
    private StateRIP zumDorf;
    private StateRIP pearlShopButton;
    private StateRIP homeCampButton;
    private StateRIP bestatigenCamp;

    public BDMWorld(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.WORLD)
                .addStateText("St.")
                .addCoexistingGameState(GameStateEnumsBDM.MAIN_MENU, true)
                .build();
        worldStateImageSt = gameStateDirectorBDM
                .addStateRIP("worldStateImageSt") // also found in Sparmodus
                .build();
        zumDorf = gameStateDirectorBDM
                .addStateRIP("zumDorf")
                .build();
        pearlShopButton = gameStateDirectorBDM
                .addStateRIP("pearlShopButton")
                .build();
        homeCampButton = gameStateDirectorBDM
                .addStateRIP("homeCampButton")
                .build();
        bestatigenCamp = gameStateDirectorBDM
                .addStateRIP("bestatigenCamp")
                .build();
    }
/*
    public boolean open() {
        //if we're not here then we're in the other base state: Camp
        if (exists()) return true;
        camp.close();
        return exists();
    }

 */

}