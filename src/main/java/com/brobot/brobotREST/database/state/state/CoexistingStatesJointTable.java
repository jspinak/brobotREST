package com.brobot.brobotREST.database.state.state;

import com.brobot.brobotREST.primatives.enums.StateEnum;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CoexistingStatesJointTable {

    private Map<StateEnum, CoexistingStates> coexisting = new HashMap<>();
    private Map<StateEnum, CoexistingStates> reverseCoexisting = new HashMap<>();

    public void add(StateEnum mainState, StateEnum coexistingState, boolean alwaysPresent) {
        if (!coexisting.containsKey(mainState)) coexisting.put(mainState, new CoexistingStates());
        coexisting.get(mainState).addCoexistingState(coexistingState, alwaysPresent);
        if (!reverseCoexisting.containsKey(coexistingState))
            reverseCoexisting.put(coexistingState, new CoexistingStates());
        reverseCoexisting.get(coexistingState).addCoexistingState(mainState, alwaysPresent);
    }

    public Optional<CoexistingStates> getCoexistingStates(StateEnum stateEnum) {
        return Optional.ofNullable(coexisting.get(stateEnum));
    }

    public Optional<CoexistingStates> getReverseCoexistingStates(StateEnum stateEnum) {
        return Optional.ofNullable(reverseCoexisting.get(stateEnum));
    }

    public Set<StateEnum> getCoexistingAlwaysPresent(StateEnum stateEnum) {
        Set<StateEnum> coexistingAlwaysPresent = new HashSet<>();
        getCoexistingStates(stateEnum).ifPresent(
                coex -> coexistingAlwaysPresent.addAll(coex.getStatesAlwaysPresent()));
        return coexistingAlwaysPresent;
    }

    public Set<StateEnum> getReverseCoexistingAlwaysPresent(StateEnum stateEnum) {
        Set<StateEnum> coexistingAlwaysPresent = new HashSet<>();
        getReverseCoexistingStates(stateEnum).ifPresent(
                coex -> coexistingAlwaysPresent.addAll(coex.getStatesAlwaysPresent()));
        return coexistingAlwaysPresent;
    }

}
