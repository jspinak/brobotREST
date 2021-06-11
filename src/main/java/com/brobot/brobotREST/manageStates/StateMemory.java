package com.brobot.brobotREST.manageStates;

import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.brobot.brobotREST.manageStates.UnknownState.Enum.UNKNOWN;

@Component
@Getter
public class StateMemory {
    private StateService stateService;

    /*
    currentMainStates (and previousMainState) are set whenever a StateImageObject is found. this happens for both
    live runs and mocks.
    previousMainState is used for transitions that go to the "PREVIOUS" state
     */

    public enum Enum implements StateEnum {
        PREVIOUS, CURRENT, EXPECTED
    }

    private Set<StateEnum> activeStates = new HashSet<>();

    public StateMemory(StateService stateService) {
        this.stateService = stateService;
    }

    public void adjustActiveStatesWithMatches(Matches matches) {
        matches.getMatchObjects().forEach(
                mO -> makeStateAndPresentCoexistingStatesActive(mO.getStateObject().getOwnerStateName()));
    }

    public void makeStateAndPresentCoexistingStatesActive(StateEnum activeState) {
        if (activeStates.contains(activeState)) return; // already active
        stateService.findByName(activeState).ifPresent(state -> {
            addActiveState(state.getName());
            state.getCoexistingStates().getStatesAlwaysPresent().forEach(
                    alwaysPresent -> {
                        if (!activeStates.contains(alwaysPresent))
                            makeStateAndPresentCoexistingStatesActive(alwaysPresent);
                    });
        });
    }

    public void addActiveState(StateEnum activeState) {
        System.out.print("\n+add "+activeState+" to active states");
        activeStates.add(activeState);
        stateService.findByName(activeState).ifPresent(state -> state.setProbabilityExists(100));
        removeNonCoexistingStatesAfterNewActiveStateIsAdded(activeState);
    }

    // a transition to a non-main state
    public void removeNonCoexistingStatesAfterNewActiveStateIsAdded(StateEnum newActiveState) {
        if (newActiveState != UNKNOWN) removeInactiveState(UNKNOWN);
        Optional<State> optNewState = stateService.findByName(newActiveState);
        if (!optNewState.isPresent()) return;
        new HashSet<>(activeStates).forEach(activeState -> {
            stateService.findByName(activeState).ifPresent(state -> {
                if (state != optNewState.get() && isSameGroup(state, optNewState.get())) {
                    //System.out.println(state.getName() + " " + optNewState.get().getName());
                    removeStateAndRelatedStates(state, optNewState.get());
                    System.out.print("\nactive states: "+activeStates);
                }
            });
        });
    }

    private void removeStateAndRelatedStates(State stateToRemove, State newlyAddedState) {
        removeInactiveState(stateToRemove.getName());
        removeStatesWithRemovedStateAsCoex(stateToRemove.getName(), newlyAddedState);
        //if (isSameGroup(stateToRemove, newlyAddedState)) return; // all connected states could remain active
        // remove states coexisting with the removed state and not with the new state
        for (StateEnum coexistingStateEnum : stateToRemove.getCoexistingStates().getAllStateEnums()) {
            if (!newlyAddedState.hasCoexistingState(coexistingStateEnum)) {
                removeInactiveState(coexistingStateEnum);
                removeStatesWithRemovedStateAsCoex(coexistingStateEnum, newlyAddedState);
            }
        }
    }

    // all states that have the removed state as an always present coexisting state
    private void removeStatesWithRemovedStateAsCoex(StateEnum removed, State newlyAddedState) {
        // remove states with the removed state as a coexisting state, if not coexisting with the new state
        Optional<State> optRemoved = stateService.findByName(removed);
        if (!optRemoved.isPresent()) return;
        for (StateEnum activeStateEnum : new HashSet<>(activeStates)) {
            Optional<State> optActive = stateService.findByName(activeStateEnum);
            optActive.ifPresent(state -> {
                if (state.hasCoexistingState(optRemoved.get().getName()) &&
                        !newlyAddedState.hasCoexistingState(activeStateEnum)) {
                    removeInactiveState(activeStateEnum);
                    removeStatesWithRemovedStateAsCoex(activeStateEnum, newlyAddedState);
                }
            });
        }
    }

    private boolean isSameGroup(State state1, State state2) {
        return state1.getGroup() != null &&
                state1.getGroup() == state2.getGroup();
    }

    public void removeInactiveState(StateEnum inactiveState) {
        if (!activeStates.contains(inactiveState)) return;
        System.out.print("\n-remove "+inactiveState+" from active states");
        activeStates.remove(inactiveState);
        stateService.findByName(inactiveState).ifPresent(state -> state.setProbabilityExists(0));
    }



}
