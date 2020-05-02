package com.brobot.brobotREST.stateObjects.sikuliWrappers;

import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateObjectData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateData.methods.StateDataMethods;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MockStateManagement {

    private StateService stateService;
    private StateDataMethods stateDataMethods;

    public MockStateManagement(StateService stateService, StateDataMethods stateDataMethods) {
        this.stateService = stateService;
        this.stateDataMethods = stateDataMethods;
    }

    public void setProbabilitiesOnClick(StateObjectData stateObject) {
        String ownerStateName = stateObject.getOwnerStateName();
        StateData currentState = stateService.findByName(ownerStateName);
        stateDataMethods.deactivateMock(currentState);
        for (StateData activatedState : getStatesToActivate(stateObject)) {
            System.out.println(stateObject.getName()+" "+activatedState.getName()+"_activateMock | ");
            stateDataMethods.activateMock(activatedState);
        }
    }

    private List<StateData> getStatesToActivate(StateObjectData stateObject) {
        Set<String> stateNames = stateObject.getStatesActivatedOnClick();
        return stateService.findByName(stateNames);
    }

    public Set<StateRIPData> getAllActivatedRIPs(StateObjectData stateObject) {
        Set<StateRIPData> associatedRIPs = new HashSet<>();
        getStatesToActivate(stateObject).forEach(
                state -> associatedRIPs.addAll(state.getStateObjects().getStateRIPs()));
        return associatedRIPs;
    }

    public List<StateData> getAllPossibleActivatedGameStates(String stateName) {
        List<StateData> states = new ArrayList<>();
        StateData state = stateService.findByName(stateName);
        state.getStateObjects().getStateRIPs().forEach(
                RIP -> states.addAll(stateService.findByName(RIP.getStatesActivatedOnClick())));
        return states;
    }

    public List<StateData> getGameStatesPossiblyOnTop(String stateName) {
        List<StateData> allPossibleActivatedStates = getAllPossibleActivatedGameStates(stateName);
        for (StateData state : allPossibleActivatedStates) {
            if (!state.isOnTopOfActivatingState()) allPossibleActivatedStates.remove(state);
        }
        return allPossibleActivatedStates;
    }
}
