package com.brobot.brobotREST.StateObjects.BaseObjects.RIP;

import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StateRIPsManager {
    private GameStateRepository gameStateRepository;

    private Map<GameStateEnum, List<StateRIP>> stateRIPsMap = new HashMap<>();

    public StateRIPsManager(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    public void addStateRIP(GameStateEnum name, StateRIP stateRIP) {
        State state = gameStateRepository.get(name);
        if (state != null) {
            state.addStateRIPs(stateRIP);
            return;
        }
        if (!stateRIPsMap.containsKey(name))
            stateRIPsMap.put(name, new ArrayList<>());
        stateRIPsMap.get(name).add(stateRIP);
    }

    public List<StateRIP> getStateRIPs(GameStateEnum name) {
        List<StateRIP> stateRIPs = stateRIPsMap.get(name);
        if (stateRIPs == null) return new ArrayList<>();
        return stateRIPs;
    }

}
