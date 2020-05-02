package com.brobot.brobotREST.stateObjects.builders;

import com.brobot.brobotREST.database.primatives.Region;
import com.brobot.brobotREST.database.state.StateStringData;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import org.springframework.stereotype.Component;

@Component
public class StateDirector {

    private StateRIPBuilder stateRIPBuilder;
    private StateStringBuilder stateStringBuilder;
    private StateImageBuilder stateImageBuilder;
    private StateRegionBuilder stateRegionBuilder;
    private StateBuilderGeneric stateBuilderGeneric;

    private String stateName;

    public StateDirector(StateRIPBuilder stateRIPBuilder, StateStringBuilder stateStringBuilder,
                         StateImageBuilder stateImageBuilder, StateRegionBuilder stateRegionBuilder,
                         StateBuilderGeneric stateBuilderGeneric) {
        this.stateRIPBuilder = stateRIPBuilder;
        this.stateStringBuilder = stateStringBuilder;
        this.stateImageBuilder = stateImageBuilder;
        this.stateRegionBuilder = stateRegionBuilder;
        this.stateBuilderGeneric = stateBuilderGeneric;
    }

    public StateBuilderGeneric initState(StateEnum stateEnum) {
        return initState(stateEnum.toString());
    }

    public StateBuilderGeneric initState(String name) {
        stateName = name;
        return stateBuilderGeneric.init(stateName);
    }

    public StateRIPBuilder addStateRIP(String... imageNames) {
        return stateRIPBuilder
                .init(stateName)
                .addRIPs(imageNames);
    }

    public StateImageBuilder addStateImage(String... imageNames) {
        return stateImageBuilder
                .init(stateName)
                .setImage(imageNames);
    }

    public StateStringBuilder addStateString(String string) {
        return stateStringBuilder
                .init(stateName)
                .setString(string);
    }

    public StateRegionBuilder addStateRegion(Region region) {
        return stateRegionBuilder
                .init(stateName)
                .setSearchRegion(region);
    }

    public StateStringData addESC(StateEnum... statesToActivate) {
        return stateStringBuilder.ESC(stateName, statesToActivate);
    }

    public void save() {
        stateBuilderGeneric.save();
    }
}
