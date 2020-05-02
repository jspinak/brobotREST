package com.brobot.brobotREST.brobots.BDM.StateBuilders;

import com.brobot.brobotREST.stateObjects.ObjectBuilder;
import com.brobot.brobotREST.stateObjects.builders.StateBuilderGeneric;
import com.brobot.brobotREST.stateObjects.builders.StateImageBuilder;
import com.brobot.brobotREST.stateObjects.builders.StateRIPBuilder;
import com.brobot.brobotREST.stateObjects.builders.StateRegionBuilder;
import com.brobot.brobotREST.stateObjects.builders.StateStringBuilder;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import org.springframework.stereotype.Component;

@Component
public class GameObjectBuilderBDM {

    private ObjectBuilder objectBuilder;
    private StateDirectorBDM stateDirectorBDM;

    public GameObjectBuilderBDM(ObjectBuilder objectBuilder,
                                StateDirectorBDM stateDirectorBDM) {
        this.objectBuilder = objectBuilder;
        this.stateDirectorBDM = stateDirectorBDM;
    }

    //methods in generic GameObjectBuilder

    public StateBuilderGeneric newGameState(StateEnum name) {
        return objectBuilder.newGameState(name);
    }

    public StateRIPBuilder newStateRIP(StateEnum currentGameState) {
        return objectBuilder.newStateRIP(currentGameState);
    }

    public StateStringBuilder newStateString(StateEnum currentGameState) {
        return objectBuilder.newStateString(currentGameState);
    }

    public StateImageBuilder newStateImage(StateEnum currentGameState) {
        return objectBuilder.newStateImage(currentGameState); }

    public StateRegionBuilder newStateRegion(StateEnum currentGameState) {
        return objectBuilder.newStateRegion(currentGameState);
    }

}
