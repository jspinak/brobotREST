package com.brobot.brobotREST.StateObjects;

import com.brobot.brobotREST.StateObjects.State.StateBuilderGeneric;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImageBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIPBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegionBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateStringBuilder;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

@Component
public class ObjectBuilder {

    private final StateBuilderGeneric stateBuilderGeneric;
    private final StateRIPBuilder stateRIPBuilder;
    private final StateStringBuilder stateStringBuilder;
    private final StateImageBuilder stateImageBuilder;
    private final StateRegionBuilder stateRegionBuilder;

    public ObjectBuilder(StateBuilderGeneric stateBuilderGeneric,
                         StateRIPBuilder stateRIPBuilder,
                         StateStringBuilder stateStringBuilder,
                         StateImageBuilder stateImageBuilder,
                         StateRegionBuilder stateRegionBuilder) {

        this.stateBuilderGeneric = stateBuilderGeneric;
        this.stateRIPBuilder = stateRIPBuilder;
        this.stateStringBuilder = stateStringBuilder;
        this.stateImageBuilder = stateImageBuilder;
        this.stateRegionBuilder = stateRegionBuilder;
    }

    public StateBuilderGeneric newGameState(GameStateEnum name) {
        return stateBuilderGeneric.init(name);
    }

    public StateRIPBuilder newStateRIP(GameStateEnum currentGameState) {
        return stateRIPBuilder.init(currentGameState);
    }

    public StateStringBuilder newStateString(GameStateEnum currentGameState) {
        return stateStringBuilder.init(currentGameState);
    }

    public StateImageBuilder newStateImage(GameStateEnum currentGameState) {
        return stateImageBuilder.init(currentGameState);
    }

    public StateRegionBuilder newStateRegion(GameStateEnum currentGameState) {
        return stateRegionBuilder.init(currentGameState);
    }
}
