package com.brobot.brobotREST.stateObjects.builders;

import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.stateData.methods.StateDataMethods;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

@Component
public class StateBuilderGeneric {
    private StateService stateService;
    private StateDataMethods stateDataMethods;

    private StateData newState;

    public StateBuilderGeneric(StateService stateService,
                               StateDataMethods stateDataMethods) {
        this.stateService = stateService;
        this.stateDataMethods = stateDataMethods;
    }

    public StateBuilderGeneric init(String name) {
        StateData stateData = stateService.findByName(name);
        if (stateData == null) newState = new StateData();
        else newState = stateData;
        newState.setName(name);
        stateService.save(newState);
        return this;
    }

    public StateBuilderGeneric addStateText(String... strings) {
        stateDataMethods.addStateText(newState, strings);
        return this;
    }

    public StateBuilderGeneric setOnTopOfActivatingState() {
        newState.setOnTopOfActivatingState(true);
        return this;
    }

    public StateBuilderGeneric addCoexistingState(StateEnum coexistingState, boolean alwaysPresent) {
        stateDataMethods.addCoexistingState(newState, coexistingState.toString(), alwaysPresent);
        return this;
    }

    public StateData build() {
        return newState;
    }

    public void save() {
        stateService.save(newState);
    }

    public StateData getNewState() {
        return newState;
    }

}
