package com.brobot.brobotREST.brobots.BDM.StateBuilders;

import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateBuilderGeneric;
import com.brobot.brobotREST.stateObjects.builders.StateImageBuilder;
import com.brobot.brobotREST.stateObjects.builders.StateObjectBuilder;
import com.brobot.brobotREST.stateObjects.builders.StateRIPBuilder;
import com.brobot.brobotREST.stateObjects.builders.StateRegionBuilder;
import com.brobot.brobotREST.database.state.StateStringData;
import com.brobot.brobotREST.stateObjects.builders.StateStringBuilder;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.database.primatives.Region;
import org.springframework.stereotype.Component;

@Component
public class GameStateDirectorBDM {
    private final StateRIPBuilder stateRIPBuilder;
    private final StateStringBuilder stateStringBuilder;
    private final StateImageBuilder stateImageBuilder;
    private final StateRegionBuilder stateRegionBuilder;
    private StateBuilderGeneric stateBuilderGeneric;
    private StateDirectorBDM stateDirectorBDM;

    private String stateName;
    private StateObjectBuilder builderUnderConstruction;
    private StateData state;

    public GameStateDirectorBDM(StateRIPBuilder stateRIPBuilder, StateStringBuilder stateStringBuilder,
                                StateImageBuilder stateImageBuilder, StateRegionBuilder stateRegionBuilder,
                                StateBuilderGeneric stateBuilderGeneric,
                                StateDirectorBDM stateDirectorBDM) {
        this.stateRIPBuilder = stateRIPBuilder;
        this.stateStringBuilder = stateStringBuilder;
        this.stateImageBuilder = stateImageBuilder;
        this.stateRegionBuilder = stateRegionBuilder;
        this.stateBuilderGeneric = stateBuilderGeneric;
        this.stateDirectorBDM = stateDirectorBDM;
    }

    public GameStateDirectorBDM init(StateEnum stateEnum) {
        this.stateName = stateEnum.toString();
        stateBuilderGeneric.init(stateName);
        return this;
    }

    public GameStateDirectorBDM addCoexistingGameState(StateEnum coexistingGameState, boolean alwaysPresent) {
        stateBuilderGeneric.addCoexistingState(coexistingGameState, alwaysPresent);
        return this;
    }

    public StateRIPBuilder addStateRIP(String... imageNames) {
        StateRIPData newRIP = new StateRIPData();
        state.getStateObjects().getStateRIPs().add(newRIP);
        //builderUnderConstruction = stateRIPBuilder;
        return stateRIPBuilder
                .init(stateName)
                .addRIPs(imageNames);
    }

    public StateImageBuilder addStateImage(String... imageNames) {
        builderUnderConstruction = stateImageBuilder;
        return stateImageBuilder
                .init(stateName)
                .setImage(imageNames);
    }

    public StateStringBuilder addStateString(String string) {
        builderUnderConstruction = stateStringBuilder;
        return stateStringBuilder
                .init(stateName)
                .setString(string);
    }

    public StateStringData escActivates(StateEnum... activatedStates) {
        return stateStringBuilder.ESC(stateName.toString(), activatedStates);
    }

    //public StateStringData escActivates(List<StateData> activatedStates) {
    //    StateEnum[] states = new StateEnum[activatedStates.size()];
    //    for (int i=0; i<activatedStates.size(); i++) states[i] = activatedStates.get(i).getName();
    //    return stateStringBuilder.buildESC(stateName, states);
    //}

    public StateRegionBuilder addStateRegion(Region region) {
        builderUnderConstruction = stateRegionBuilder;
        return stateRegionBuilder
                .init(stateName)
                .setSearchRegion(region);
    }

    public GameStateDirectorBDM addStatesToActivate(StateEnum... names) {
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

    public StateData build() {
        state = stateBuilderGeneric.build();
        return state;
    }

    public void save() {
        stateBuilderGeneric.save();
    }
}
