package com.brobot.brobotREST.StateObjects.BaseObjects.Image;

import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StateImagesManager {
    private GameStateRepository gameStateRepository;

    private Map<GameStateEnum, List<StateImage>> stateImagesMap = new HashMap<>();

    public StateImagesManager(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    public void addStateImage(GameStateEnum name, StateImage stateImage) {
        State state = gameStateRepository.get(name);
        if (state != null) {
            state.addStateImages(stateImage);
            return;
        }
        if (!stateImagesMap.containsKey(name))
            stateImagesMap.put(name, new ArrayList<>());
        stateImagesMap.get(name).add(stateImage);
    }

    public List<StateImage> getStateImages(GameStateEnum name) {
        List<StateImage> stateImages = stateImagesMap.get(name);
        if (stateImages == null) return new ArrayList<>();
        return stateImages;
    }

}
