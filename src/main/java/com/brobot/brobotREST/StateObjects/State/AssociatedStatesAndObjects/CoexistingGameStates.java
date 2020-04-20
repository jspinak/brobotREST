package com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Exists.ExistsGameState;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data
class CoexistingGameStates {

    private ExistsGameState existsGameState;

    private List<CoexistingGameState> coexistingGameStateList = new ArrayList<>();

    public CoexistingGameStates(ExistsGameState existsGameState) {
        this.existsGameState = existsGameState;
    }

    public void addGameState(GameStateEnum gameStateEnum, boolean alwaysPresent) {
        coexistingGameStateList.add(new CoexistingGameState(gameStateEnum, alwaysPresent));
    }

}
