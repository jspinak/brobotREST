package com.brobot.brobotREST.StateObjects.BaseObjects.String;

import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StateStringsManager {
    private GameStateRepository gameStateRepository;

    private Map<GameStateEnum, List<StateString>> stateStringsMap = new HashMap<>();

    public StateStringsManager(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    public void addStateString(GameStateEnum name, StateString stateString) {
        State state = gameStateRepository.get(name);
        if (state != null) {
            state.addStateStrings(stateString);
            return;
        }
        if (!stateStringsMap.containsKey(name))
            stateStringsMap.put(name, new ArrayList<>());
        stateStringsMap.get(name).add(stateString);
    }

    public List<StateString> getStateStrings(GameStateEnum name) {
        List<StateString> stateStrings = stateStringsMap.get(name);
        if (stateStrings == null) return new ArrayList<>();
        return stateStrings;
    }

}
