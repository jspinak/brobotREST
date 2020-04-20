package com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects;

import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import lombok.Data;

public @Data class CoexistingGameState {

    private GameStateEnum coexistingState;
    private boolean alwaysPresent = true;

    public CoexistingGameState(GameStateEnum coexistingState, boolean alwaysPresent) {
        this.coexistingState = coexistingState;
        this.alwaysPresent = alwaysPresent;
    }

}
