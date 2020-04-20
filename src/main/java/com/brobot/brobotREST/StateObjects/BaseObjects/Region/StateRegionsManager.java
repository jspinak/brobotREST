package com.brobot.brobotREST.StateObjects.BaseObjects.Region;

import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StateRegionsManager {
    private GameStateRepository gameStateRepository;

    private Map<GameStateEnum, List<StateRegion>> stateRegionsMap = new HashMap<>();

    public StateRegionsManager(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    public void addStateRegion(GameStateEnum name, StateRegion stateRegion) {
        State state = gameStateRepository.get(name);
        if (state != null) {
            state.addStateRegions(stateRegion);
            return;
        }
        if (!stateRegionsMap.containsKey(name))
            stateRegionsMap.put(name, new ArrayList<>());
        stateRegionsMap.get(name).add(stateRegion);
    }

    public List<StateRegion> getStateRegions(GameStateEnum name) {
        List<StateRegion> stateRegions = stateRegionsMap.get(name);
        if (stateRegions == null) return new ArrayList<>();
        return stateRegions;
    }

}
