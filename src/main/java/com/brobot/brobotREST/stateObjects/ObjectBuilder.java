package com.brobot.brobotREST.stateObjects;

import com.brobot.brobotREST.stateObjects.builders.StateBuilderGeneric;
import com.brobot.brobotREST.stateObjects.builders.StateImageBuilder;
import com.brobot.brobotREST.stateObjects.builders.StateRIPBuilder;
import com.brobot.brobotREST.stateObjects.builders.StateRegionBuilder;
import com.brobot.brobotREST.stateObjects.builders.StateStringBuilder;
import com.brobot.brobotREST.primatives.enums.StateEnum;
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

    public StateBuilderGeneric newGameState(StateEnum name) {
        return stateBuilderGeneric.init(name.toString());
    }

    public StateRIPBuilder newStateRIP(StateEnum currentGameState) {
        return stateRIPBuilder.init(currentGameState.toString());
    }

    public StateStringBuilder newStateString(StateEnum currentGameState) {
        return stateStringBuilder.init(currentGameState.toString());
    }

    public StateImageBuilder newStateImage(StateEnum currentGameState) {
        return stateImageBuilder.init(currentGameState.toString());
    }

    public StateRegionBuilder newStateRegion(StateEnum currentGameState) {
        return stateRegionBuilder.init(currentGameState.toString());
    }
}
