package com.brobot.brobotREST.Brobots.BDM.StateBuilders;

import com.brobot.brobotREST.StateObjects.ObjectBuilder;
import com.brobot.brobotREST.StateObjects.State.StateBuilderGeneric;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImageBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIPBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegionBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateStringBuilder;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

@Component
public class GameObjectBuilderBDM {

    private ObjectBuilder objectBuilder;
    private GameStateBuilderBDMBestatigung gameStateBuilderBDMBestatigung;

    public GameObjectBuilderBDM(ObjectBuilder objectBuilder,
                                GameStateBuilderBDMBestatigung gameStateBuilderBDMBestatigung) {
        this.objectBuilder = objectBuilder;
        this.gameStateBuilderBDMBestatigung = gameStateBuilderBDMBestatigung;
    }

    //methods in generic GameObjectBuilder

    public StateBuilderGeneric newGameState(GameStateEnum name) {
        return objectBuilder.newGameState(name);
    }

    public StateRIPBuilder newStateRIP(GameStateEnum currentGameState) {
        return objectBuilder.newStateRIP(currentGameState);
    }

    public StateStringBuilder newStateString(GameStateEnum currentGameState) {
        return objectBuilder.newStateString(currentGameState);
    }

    public StateImageBuilder newStateImage(GameStateEnum currentGameState) {
        return objectBuilder.newStateImage(currentGameState); }

    public StateRegionBuilder newStateRegion(GameStateEnum currentGameState) {
        return objectBuilder.newStateRegion(currentGameState);
    }

    //methods specific to BDM

    public GameStateBuilderBDMBestatigung newBestatigung(GameStateEnum name) {
        return gameStateBuilderBDMBestatigung.init(name);
    }
}
