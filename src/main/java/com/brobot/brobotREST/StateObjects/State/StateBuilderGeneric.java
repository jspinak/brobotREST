package com.brobot.brobotREST.StateObjects.State;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Exists.ExistsGameState;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Exists.ExistsStateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.*;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObjectsManager;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

@Component
public class StateBuilderGeneric {
    private final ExistsStateRIP existsStateRIP;
    private final ExistsGameState existsGameState;
    private StateObjectsManager stateObjectsManager;
    private GameStateRepository gameStateRepository;

    private StateImpl gameState;

    public StateBuilderGeneric(GameStateRepository gameStateRepository, ExistsStateRIP existsStateRIP,
                               ExistsGameState existsGameState, StateObjectsManager stateObjectsManager) {
        this.gameStateRepository = gameStateRepository;
        this.existsStateRIP = existsStateRIP;
        this.existsGameState = existsGameState;
        this.stateObjectsManager = stateObjectsManager;
    }

    public StateBuilderGeneric init(GameStateEnum name) {
        gameState = new StateImpl(existsStateRIP, existsGameState);
        gameState.setName(name);
        gameStateRepository.add(gameState);
        return this;
    }

    public StateBuilderGeneric addStateText(String... strings) {
        for (String string : strings) gameState.getStateText().add(string);
        return this;
    }

    public StateBuilderGeneric setOnTopOfActivatingState() {
        gameState.setOnTopOfActivatingState(true);
        return this;
    }

    public StateBuilderGeneric addCoexistingGameState(GameStateEnum coexistingGameState,
                                                      boolean coexistingStateAlwaysPresent) {
        gameState.addCoexistingGameState(coexistingGameState, coexistingStateAlwaysPresent);
        return this;
    }

    public State build() {
        stateObjectsManager.addAllObjects(gameState);
        return gameState;
    }

}
