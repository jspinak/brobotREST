package com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects;

import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActivatingObjectsManager {
    private GameStateRepository gameStateRepository;

    private Map<GameStateEnum, ActivatingObjects> activatingObjectsMap = new HashMap<>();

    public ActivatingObjectsManager(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    public void addActivatingObject(GameStateEnum name, StateObject stateObject) {
        State state = gameStateRepository.get(name);
        if (state != null) {
            state.addActivatingGameObject(stateObject);
            return;
        }
        if (!activatingObjectsMap.containsKey(name))
            activatingObjectsMap.put(name, new ActivatingObjects());
        activatingObjectsMap.get(name).addActivatingObject(stateObject);
        /*for (GameStateEnum gameStateEnum : activatingObjectsMap.keySet())
            System.out.print(gameStateEnum+" "+activatingObjectsMap.get(gameStateEnum).getActivatingObjects().size()+" | ");
        System.out.println();*/
    }

    public ActivatingObjects getActivatingObjects(GameStateEnum name) {
        ActivatingObjects activatingObjects = activatingObjectsMap.get(name);
        if (activatingObjects == null) return new ActivatingObjects();
        return activatingObjects;
    }

}
