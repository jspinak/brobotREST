package com.brobot.brobotREST.stateObjects.builders;

import com.brobot.brobotREST.dataAccess.StateRIPRepository;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateData.methods.StateDataMethods;
import com.brobot.brobotREST.stateData.methods.StateRIPMethods;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.database.primatives.Region;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateRIPBuilder implements StateObjectBuilder {

    private final StateBuilderGeneric stateBuilderGeneric;
    private final StateDataMethods stateDataMethods;
    private final StateRIPMethods stateRIPMethods;
    private final StateService stateService;
    private StateRIPRepository stateRIPRepository;

    private StateRIPData stateRIP;

    public StateRIPBuilder(StateBuilderGeneric stateBuilderGeneric,
                           StateDataMethods stateDataMethods,
                           StateRIPMethods stateRIPMethods,
                           StateService stateService,
                           StateRIPRepository stateRIPRepository) {
        this.stateBuilderGeneric = stateBuilderGeneric;
        this.stateDataMethods = stateDataMethods;
        this.stateRIPMethods = stateRIPMethods;
        this.stateService = stateService;
        this.stateRIPRepository = stateRIPRepository;
    }

    public StateRIPBuilder init(String name) {
        stateRIP = new StateRIPData();
        stateRIPMethods.setOwnerName(stateRIP, name);
        List<StateRIPData> allRIPs = stateRIPRepository.findAll();
        StateData newState = stateBuilderGeneric.getNewState();
        stateDataMethods.addStateRIPs(newState, stateRIP);
        stateRIPRepository.save(stateRIP);
        return this;
    }

    public StateRIPBuilder setSearchRegion(Region defineRegion) {
        stateRIP.setSearchRegion(defineRegion);
        return this;
    }

    public StateRIPBuilder setName(String name) {
        stateRIPMethods.setName(stateRIP, name);
        return this;
    }

    public StateRIPBuilder addRIPs(List<String> strings) {
        strings.forEach(str -> addRIPs(str));
        return this;
    }

    public StateRIPBuilder addRIPs(String... strings) {
        for (String string : strings) {
            stateRIPMethods.add(stateRIP, string);
            stateRIPMethods.setNameIfNull(stateRIP, string);
        }
        return this;
    }

    public StateRIPBuilder setStaysVisibleAfterClicked() {
        stateRIPMethods.setStaysVisibleAfterClicked(stateRIP, true);
        return this;
    }

    public StateRIPBuilder addStatesToActivate(StateEnum... statesToActivate) {
        for (StateEnum stateEnum : statesToActivate) {
            stateRIPMethods.addStateToActivate(stateRIP, stateEnum.toString());
            StateData stateData = stateService.initAndSaveIfDoesntExist(stateEnum); //not the same state as in stateBuilder
            stateDataMethods.addActivatingRIP(stateData, stateRIP);
            stateService.save(stateData);
        }
        return this;
    }

    public StateRIPBuilder setTimeToWaitAfterAction(double secondsToWaitAfterClick) {
        stateRIPMethods.setTimeToWaitAfterAction(stateRIP, secondsToWaitAfterClick);
        return this;
    }

    public StateRIPData build() {
        stateRIPRepository.save(stateRIP);
        //stateBuilderGeneric.save();
        return stateRIP;
    }

}
