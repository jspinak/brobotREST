package com.brobot.brobotREST.stateObjects.builders;

import com.brobot.brobotREST.dataAccess.StateRegionRepository;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRegionData;
import com.brobot.brobotREST.stateData.methods.StateDataMethods;
import com.brobot.brobotREST.stateData.methods.StateRegionMethods;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.database.primatives.Region;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

@Component
public class StateRegionBuilder implements StateObjectBuilder {

    private final StateBuilderGeneric stateBuilderGeneric;
    private final StateDataMethods stateDataMethods;
    private final StateRegionMethods stateRegionMethods;
    private final StateService stateService;
    private StateRegionRepository stateRegionRepository;

    private StateRegionData stateRegion;

    public StateRegionBuilder(StateBuilderGeneric stateBuilderGeneric,
                              StateDataMethods stateDataMethods,
                              StateRegionMethods stateRegionMethods,
                              StateService stateService,
                              StateRegionRepository stateRegionRepository) {
        this.stateBuilderGeneric = stateBuilderGeneric;
        this.stateDataMethods = stateDataMethods;
        this.stateRegionMethods = stateRegionMethods;
        this.stateService = stateService;
        this.stateRegionRepository = stateRegionRepository;
    }

    public StateRegionBuilder init(String name) {
        stateRegion = new StateRegionData();
        stateRegionMethods.setOwnerName(stateRegion, name);
        stateRegionRepository.save(stateRegion);
        StateData newState = stateBuilderGeneric.getNewState();
        stateDataMethods.addStateRegions(newState, stateRegion);
        return this;
    }

    public StateRegionBuilder setName(String name) {
        stateRegionMethods.setName(stateRegion, name);
        return this;
    }

    public StateRegionBuilder setSearchRegion(Region defineRegion) {
        stateRegion.setStateRegion(defineRegion);
        return this;
    }

    public StateRegionBuilder addStatesToActivate(StateEnum... statesToActivate) {
        for (StateEnum stateEnum : statesToActivate) {
            stateRegionMethods.addStateToActivate(stateRegion, stateEnum.toString());
            StateData stateData = stateService.initAndSaveIfDoesntExist(stateEnum);
            stateDataMethods.addActivatingRegion(stateData, stateRegion);
            stateService.save(stateData); //not the same state as in stateBuilder
        }
        return this;
    }

    public StateRegionBuilder setTimeToWaitAfterAction(double secondsToWaitAfterClick) {
        stateRegionMethods.setTimeToWaitAfterAction(stateRegion, secondsToWaitAfterClick);
        return this;
    }

    public StateRegionData build() {
        stateRegionRepository.save(stateRegion);
        //stateBuilderGeneric.save();
        return stateRegion;
    }
}
