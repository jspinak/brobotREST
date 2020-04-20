package com.brobot.brobotREST.Brobots.BDM.Steuerung;

import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import com.brobot.brobotREST.StatesSteuern.StateManagement;
import org.springframework.stereotype.Component;

@Component
public class BDMSteuern {
    private StateManagement statesSteuern;
    private BDMErrors BDMErrors;

    public BDMSteuern(StateManagement gameStatesSteuernGeneric, BDMErrors BDMErrors) {
        this.statesSteuern = gameStatesSteuernGeneric;
        this.BDMErrors = BDMErrors;
    }

    public boolean openState(GameStateEnum gameStateEnum) {
        return statesSteuern.openState(gameStateEnum, BDMErrors);
    }

}
