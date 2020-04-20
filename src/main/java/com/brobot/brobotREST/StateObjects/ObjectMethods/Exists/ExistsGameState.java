package com.brobot.brobotREST.StateObjects.ObjectMethods.Exists;

import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExistsGameState {

    private ExistsStateRIP existsStateRIP;
    private GameStateRepository gameStateRepository;

    public ExistsGameState(ExistsStateRIP existsStateRIP, GameStateRepository gameStateRepository) {
        this.existsStateRIP = existsStateRIP;
        this.gameStateRepository = gameStateRepository;
    }

    public boolean exists(State state) {
        return existsStateRIP.exists(state.getStateRIPs());
    }

    public boolean exists(GameStateEnum gameState) {
        return existsStateRIP.exists(gameStateRepository.get(gameState).getStateRIPs());
    }

    public State getFirstGameStateFound(List<State> states) {
        for (State state : states) {
            if (exists(state)) return state;
        }
        return null;
    }

    public List<State> getAllExistingGameStates(List<State> states) {
        List<State> existingStates = new ArrayList<>();
        for (State state : states) {
            if (exists(state)) existingStates.add(state);
        }
        return existingStates;
    }

}
