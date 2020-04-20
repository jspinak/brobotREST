package com.brobot.brobotREST.Brobots.BDM.StateBuilders;

import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.State.StateBuilderGeneric;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImageBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObjectBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIPBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegionBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateStringBuilder;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import com.brobot.brobotREST.Primatives.Region;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameStateDirectorBDM {
    private final StateRIPBuilder stateRIPBuilder;
    private final StateStringBuilder stateStringBuilder;
    private final StateImageBuilder stateImageBuilder;
    private final StateRegionBuilder stateRegionBuilder;
    private StateBuilderGeneric stateBuilderGeneric;
    private GameStateBuilderBDMBestatigung gameStateBuilderBDMBestatigung;

    private GameStateEnum stateName;
    private StateObjectBuilder builderUnderConstruction;
    private State state;

    public GameStateDirectorBDM(StateRIPBuilder stateRIPBuilder, StateStringBuilder stateStringBuilder,
                                StateImageBuilder stateImageBuilder, StateRegionBuilder stateRegionBuilder,
                                StateBuilderGeneric stateBuilderGeneric,
                                GameStateBuilderBDMBestatigung gameStateBuilderBDMBestatigung) {
        this.stateRIPBuilder = stateRIPBuilder;
        this.stateStringBuilder = stateStringBuilder;
        this.stateImageBuilder = stateImageBuilder;
        this.stateRegionBuilder = stateRegionBuilder;
        this.stateBuilderGeneric = stateBuilderGeneric;
        this.gameStateBuilderBDMBestatigung = gameStateBuilderBDMBestatigung;
    }

    public GameStateDirectorBDM init(GameStateEnum name) {
        this.stateName = name;
        stateBuilderGeneric.init(name);
        return this;
    }

    public GameStateDirectorBDM addCoexistingGameState(GameStateEnum coexistingGameState, boolean alwaysPresent) {
        stateBuilderGeneric.addCoexistingGameState(coexistingGameState, alwaysPresent);
        return this;
    }

    public StateRIPBuilder addStateRIP(String... imageNames) {
        builderUnderConstruction = stateRIPBuilder;
        return stateRIPBuilder
                .init(stateName)
                .addRIPs(imageNames);
    }

    public StateRIPBuilder addBestatigung() {
        return gameStateBuilderBDMBestatigung.addBestatigungRIP(stateName);
    }

    public StateImageBuilder addStateImage(String... imageNames) {
        builderUnderConstruction = stateImageBuilder;
        return stateImageBuilder
                .init(stateName)
                .addImage(imageNames);
    }

    public StateStringBuilder addStateString(String string) {
        builderUnderConstruction = stateStringBuilder;
        return stateStringBuilder
                .init(stateName)
                .setString(string);
    }

    public StateString escActivates(GameStateEnum... activatedStates) {
        return stateStringBuilder.buildESC(stateName, activatedStates);
    }

    public StateString escActivates(List<State> activatedStates) {
        GameStateEnum[] states = new GameStateEnum[activatedStates.size()];
        for (int i=0; i<activatedStates.size(); i++) states[i] = activatedStates.get(i).getName();
        return stateStringBuilder.buildESC(stateName, states);
    }

    public StateRegionBuilder addStateRegion(Region region) {
        builderUnderConstruction = stateRegionBuilder;
        return stateRegionBuilder
                .init(stateName)
                .setSearchRegion(region);
    }

    public GameStateDirectorBDM addStatesToActivate(GameStateEnum... names) {
        builderUnderConstruction.addStatesToActivate(names);
        return this;
    }

    public GameStateDirectorBDM setSecondsToWaitAfterClick(double seconds) {
        builderUnderConstruction.setTimeToWaitAfterAction(seconds);
        return this;
    }

    public GameStateDirectorBDM addStateText(String... strings) {
        stateBuilderGeneric.addStateText(strings);
        return this;
    }

    public GameStateDirectorBDM setName(String name) {
        builderUnderConstruction.setName(name);
        return this;
    }

    public State build() {
        state = stateBuilderGeneric.build();
        return state;
    }
}
