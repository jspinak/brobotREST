package com.brobot.brobotREST.brobots.BDM.GameStates.World;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class BDMWorld {

    private StateData state;
    private StateRIPData worldStateImageSt;
    private StateRIPData zumDorf;
    private StateRIPData pearlShopButton;
    private StateRIPData homeCampButton;
    private StateRIPData bestatigenCamp;

    public BDMWorld(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.WORLD)
                .addStateText("St.")
                .addCoexistingState(StateEnumsBDM.MAIN_MENU, true)
                .build();
        worldStateImageSt = stateDirector
                .addStateRIP("worldStateImageSt") // also found in Sparmodus
                .build();
        zumDorf = stateDirector
                .addStateRIP("zumDorf")
                .build();
        pearlShopButton = stateDirector
                .addStateRIP("pearlShopButton")
                .build();
        homeCampButton = stateDirector
                .addStateRIP("homeCampButton")
                .build();
        bestatigenCamp = stateDirector
                .addStateRIP("bestatigenCamp")
                .build();
        stateDirector.save();
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