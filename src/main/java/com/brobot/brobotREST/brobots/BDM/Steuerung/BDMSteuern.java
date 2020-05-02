package com.brobot.brobotREST.brobots.BDM.Steuerung;

import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.statesSteuern.StateManagement;
import org.springframework.stereotype.Component;

@Component
public class BDMSteuern {
    private StateManagement statesSteuern;
    private BDMErrors BDMErrors;

    public BDMSteuern(StateManagement gameStatesSteuernGeneric, BDMErrors BDMErrors) {
        this.statesSteuern = gameStatesSteuernGeneric;
        this.BDMErrors = BDMErrors;
    }

    public boolean openState(StateEnum stateEnum) {
        return statesSteuern.openState(stateEnum, BDMErrors);
    }

}
