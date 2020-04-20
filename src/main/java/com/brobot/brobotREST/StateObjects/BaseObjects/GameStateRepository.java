package com.brobot.brobotREST.StateObjects.BaseObjects;

import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public @Data
class GameStateRepository {

    Map<GameStateEnum, State> gameStates = new HashMap<>();

    public void add(State state) {
        gameStates.put(state.getName(), state);
    }

    public List<State> get(List<GameStateEnum> gameStateEnums) {
        List<State> states = new ArrayList<>();
        for (GameStateEnum gameStateEnum : gameStateEnums) {
            State state = get(gameStateEnum);
            if (state != null) states.add(state);
        }
        return states;
    }

    public State get(GameStateEnum gameStateEnum) {
        if (!gameStates.containsKey(gameStateEnum)) return null;
        return gameStates.get(gameStateEnum);
    }

    public State getCurrentState(StateObject stateObject) {
        return get(stateObject.getCurrentState());
    }

    public Collection<State> getAllGameStates() {
        return gameStates.values();
    }
}
