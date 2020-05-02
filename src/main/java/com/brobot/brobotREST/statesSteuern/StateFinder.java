package com.brobot.brobotREST.statesSteuern;

import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.stateData.methods.StateDataMethods;
import com.brobot.brobotREST.stateObjects.objectMethods.exists.ExistsGameState;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockStateManagement;
import com.brobot.brobotREST.database.state.CoexistingState;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StateFinder {

    private StateService stateService;
    private MockRegion mockRegion;
    private ExistsGameState existsGameState;
    private MockStateManagement mockStateManagement;
    private StateDataMethods stateDataMethods;

    public StateFinder(StateService stateService, MockRegion mockRegion,
                       ExistsGameState existsGameState, MockStateManagement mockStateManagement,
                       StateDataMethods stateDataMethods) {
        this.stateService = stateService;
        this.mockRegion = mockRegion;
        this.existsGameState = existsGameState;
        this.mockStateManagement = mockStateManagement;
        this.stateDataMethods = stateDataMethods;
    }

    public List<StateData> getCurrentGameStates() {
        List<StateData> existingStates =
                existsGameState.getAllExistingGameStates(mockRegion.getExpectedStates());
        if (!existingStates.isEmpty()) return existingStates;
        return searchAllStateRIPsForCurrentStates();
    }

    // make game specific by including a variable <game> in StateRIP and a .filter method in StateRIPRepository
    // you could also have a priority value for states
    // when searching for the current state, you should only search StateRIPs
    // some StateRIPs are standalone and don't define a state
    private List<StateData> searchAllStateRIPsForCurrentStates() {
        System.out.println(" search all RIPs to find state ");
        for (StateData state : stateService.findAll()) {
            System.out.print(".");
            if (!state.isOnTopOfActivatingState() && existsGameState.exists(state)) {
                for (StateData stateOnTop : mockStateManagement.getGameStatesPossiblyOnTop(state.getName())) {
                    if (existsGameState.exists(stateOnTop)) return getAllActiveStates(stateOnTop);
                }
                return getAllActiveStates(state);
            }
        }
        return null;
    }

    public List<StateData> getAllActiveStates(StateEnum gameState) {
        return getAllActiveStates(stateService.findByName(gameState));
    }

    public List<StateData> getAllActiveStates(String gameState) {
        return getAllActiveStates(stateService.findByName(gameState));
    }

    public List<StateData> getAllActiveStates(StateData state) {
        List<StateData> activeStates = new ArrayList<>();
        activeStates.add(state);
        for (StateData activeState : getActiveCoexistingGameStates(state)) {
            activeStates.add(activeState);
            stateDataMethods.activateMock(activeState);
        }
        return activeStates;
    }

    private List<StateData> getActiveCoexistingGameStates(StateData state) {
        List<StateData> activeStates = new ArrayList<>();
        for (CoexistingState coexistingGameState : state.getCoexistingStates()) {
            StateData coexistingState = stateService.findByName(coexistingGameState.getCoexistingState());
            if (coexistingGameState.isAlwaysPresent() || existsGameState.exists(coexistingState)) {
                activeStates.add(coexistingState);
            }
        }
        return activeStates;
    }

    public List<String> setExpectetStateAndAssociatedStates(StateEnum stateEnum) {
        mockRegion.setExpectedStates(getAllActiveStates(stateEnum));
        List<String> expectedEnums = new ArrayList<>();
        mockRegion.getExpectedStates().forEach(gS -> expectedEnums.add(gS.getName()));
        return expectedEnums;
    }
}
