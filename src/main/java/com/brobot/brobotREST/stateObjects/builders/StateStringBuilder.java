package com.brobot.brobotREST.stateObjects.builders;

import com.brobot.brobotREST.dataAccess.StateStringRepository;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateStringData;
import com.brobot.brobotREST.stateData.methods.StateDataMethods;
import com.brobot.brobotREST.stateData.methods.StateStringMethods;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public class StateStringBuilder implements StateObjectBuilder {

    private final StateBuilderGeneric stateBuilderGeneric;
    private final StateDataMethods stateDataMethods;
    private final StateStringMethods stateStringMethods;
    private final StateService stateService;
    private StateStringRepository stateStringRepository;

    private StateStringData stateString;

    public StateStringBuilder(StateBuilderGeneric stateBuilderGeneric,
                              StateDataMethods stateDataMethods,
                              StateStringMethods stateStringMethods,
                              StateService stateService,
                              StateStringRepository stateStringRepository) {
        this.stateBuilderGeneric = stateBuilderGeneric;
        this.stateDataMethods = stateDataMethods;
        this.stateStringMethods = stateStringMethods;
        this.stateService = stateService;
        this.stateStringRepository = stateStringRepository;
    }

    public StateStringBuilder init(String stateName) {
        stateString = new StateStringData();
        stateStringMethods.setOwnerName(stateString, stateName);
        stateStringRepository.save(stateString);
        StateData newState = stateBuilderGeneric.getNewState();
        stateDataMethods.addStateStrings(newState, stateString);
        return this;
    }

    public StateStringBuilder setName(String name) {
        stateStringMethods.setName(stateString, name);
        return this;
    }

    public StateStringBuilder setString(String name) {
        stateString.setString(name);
        if (stateStringMethods.getName(stateString) == null) stateStringMethods.setName(stateString, name);
        if (name.equals(Key.ESC)) stateStringMethods.setName(stateString, "esc");
        return this;
    }

    public StateStringBuilder addStatesToActivate(StateEnum... statesToActivate) {
        for (StateEnum stateEnum : statesToActivate) {
            stateStringMethods.addStateToActivate(stateString, stateEnum.toString());
            StateData stateData = stateService.initAndSaveIfDoesntExist(stateEnum);
            stateDataMethods.addActivatingStrings(stateData, stateString);
            stateService.save(stateData); //not the same state as in stateBuilder
        }
        return this;
    }

    public StateStringBuilder setTimeToWaitAfterAction(double secondsToWaitAfterClick) {
        stateStringMethods.setTimeToWaitAfterAction(stateString, secondsToWaitAfterClick);
        return this;
    }

    public StateStringData build() {
        stateStringRepository.save(stateString);
        //stateBuilderGeneric.save();
        return stateString;
    }

    public StateStringData ESC(String name, StateEnum... activatedStates) {
        return init(name)
                .setString(Key.ESC)
                .addStatesToActivate(activatedStates)
                .build();
    }

}
