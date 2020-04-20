package com.brobot.brobotREST.StatesSteuern;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Exists.ExistsGameState;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockStateManagement;
import com.brobot.brobotREST.StateObjects.State.AssociatedStatesAndObjects.CoexistingGameState;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StateFinder {

    private MockRegion mockRegion;
    private ExistsGameState existsGameState;
    private MockStateManagement mockStateManagement;
    private GameStateRepository gameStateRepository;

    public StateFinder(GameStateRepository gameStateRepository, MockRegion mockRegion,
                       ExistsGameState existsGameState, MockStateManagement mockStateManagement) {
        this.gameStateRepository = gameStateRepository;
        this.mockRegion = mockRegion;
        this.existsGameState = existsGameState;
        this.mockStateManagement = mockStateManagement;
    }

    public List<State> getCurrentGameStates() {
        List<State> existingStates = existsGameState.getAllExistingGameStates(mockRegion.getExpectedStates());
        if (!existingStates.isEmpty()) return existingStates;
        return searchAllStateRIPsForCurrentStates();
    }

    // make game specific by including a variable <game> in StateRIP and a .filter method in StateRIPRepository
    // you could also have a priority value for states
    // when searching for the current state, you should only search StateRIPs
    // some StateRIPs are standalone and don't define a state
    private List<State> searchAllStateRIPsForCurrentStates() {
        System.out.println(" search all RIPs to find state ");
        for (State state : gameStateRepository.getAllGameStates()) {
            System.out.print(".");
            if (!state.isOnTopOfActivatingState() && state.exists()) {
                for (State stateOnTop : mockStateManagement.getGameStatesPossiblyOnTop(state.getName())) {
                    if (stateOnTop.exists()) return getAllActiveStates(stateOnTop);
                }
                return getAllActiveStates(state);
            }
        }
        return null;
    }

    public List<State> getAllActiveStates(GameStateEnum gameState) {
        return getAllActiveStates(gameStateRepository.get(gameState));
    }

    public List<State> getAllActiveStates(State state) {
        List<State> activeStates = new ArrayList<>();
        activeStates.add(state);
        for (State activeState : getActiveCoexistingGameStates(state)) {
            activeStates.add(activeState);
            activeState.activateMock();
        }
        return activeStates;
    }

    private List<State> getActiveCoexistingGameStates(State state) {
        List<State> activeStates = new ArrayList<>();
        for (CoexistingGameState coexistingGameState : state.getCoexistingGameStates().getCoexistingGameStateList()) {
            State coexistingState = gameStateRepository.get(coexistingGameState.getCoexistingState());
            if (coexistingGameState.isAlwaysPresent() || existsGameState.exists(coexistingState)) {
                activeStates.add(coexistingState);
            }
        }
        return activeStates;
    }

    public List<GameStateEnum> setExpectetStateAndAssociatedStates(GameStateEnum gameStateEnum) {
        mockRegion.setExpectedStates(getAllActiveStates(gameStateEnum));
        List<GameStateEnum> expectedEnums = new ArrayList<>();
        mockRegion.getExpectedStates().forEach(gS -> expectedEnums.add(gS.getName()));
        return expectedEnums;
    }
}
