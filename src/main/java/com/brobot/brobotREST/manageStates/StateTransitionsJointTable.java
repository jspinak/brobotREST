package com.brobot.brobotREST.manageStates;

import com.brobot.brobotREST.primatives.enums.StateEnum;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.brobot.brobotREST.manageStates.StateMemory.Enum.PREVIOUS;

@Component
public class StateTransitionsJointTable {

    Map<StateEnum, Set<StateEnum>> incomingTransitions = new HashMap<>();
    Map<StateEnum, Set<StateEnum>> outgoingTransitions = new HashMap<>();
    Map<StateEnum, Set<StateEnum>> incomingTransitionsToPREVIOUS = new HashMap<>(); // updated dynamically

    public void setPreviousIncomingTransition(StateEnum currentState, StateEnum previousState,
                                              Optional<StateEnum> formerPreviousState) {
        if (!incomingTransitionsToPREVIOUS.containsKey(previousState))
            incomingTransitionsToPREVIOUS.put(previousState, new HashSet<>());
        incomingTransitionsToPREVIOUS.get(previousState).add(currentState);
        formerPreviousState.ifPresent(former -> {
            if (former != previousState) incomingTransitionsToPREVIOUS.get(former).remove(currentState);
        });
    }

    public void add(StateEnum to, StateEnum from) {
        if (from != PREVIOUS) addIncomingTransition(to, from);
        addOutgoingTransition(to, from);
    }

    private void addIncomingTransition(StateEnum child, StateEnum parentToAdd) {
        if (incomingTransitions.containsKey(child)) incomingTransitions.get(child).add(parentToAdd);
        else {
            Set<StateEnum> stateEnums = new HashSet<>();
            stateEnums.add(parentToAdd);
            incomingTransitions.put(child, stateEnums);
        }
    }

    private void addOutgoingTransition(StateEnum childToAdd, StateEnum parent) {
        if (outgoingTransitions.containsKey(parent)) outgoingTransitions.get(parent).add(childToAdd);
        else {
            Set<StateEnum> stateEnums = new HashSet<>();
            stateEnums.add(childToAdd);
            outgoingTransitions.put(parent, stateEnums);
        }
    }

    public Set<StateEnum> getStatesWithTransitionsTo(StateEnum... children) {
        return getStatesWithTransitionsTo(new HashSet<>(Arrays.asList(children.clone())));
    }

    public Set<StateEnum> getStatesWithTransitionsTo(Set<StateEnum> children) {
        Set<StateEnum> parents = new HashSet<>();
        children.forEach(child -> {
            if (getUpdatedIncomingTransitions().containsKey(child))
                parents.addAll(getUpdatedIncomingTransitions().get(child));
        });
        return parents;
    }

    public Set<StateEnum> getStatesWithTransitionsFrom(StateEnum... parents) {
        return getStatesWithTransitionsFrom(new HashSet<>(Arrays.asList(parents.clone())));
    }

    public Set<StateEnum> getStatesWithTransitionsFrom(Set<StateEnum> parents) {
        Set<StateEnum> children = new HashSet<>();
        parents.forEach(parent -> {
            if (outgoingTransitions.get(parent) != null) children.addAll(outgoingTransitions.get(parent));
        });
        return children;
    }

    public Map<StateEnum, Set<StateEnum>> getUpdatedIncomingTransitions() {
        Map<StateEnum, Set<StateEnum>> allIncoming = new HashMap<>();
        incomingTransitions.forEach(allIncoming::put);
        incomingTransitionsToPREVIOUS.forEach((stateEnum, stateEnums) -> {
            if (allIncoming.containsKey(stateEnum)) allIncoming.get(stateEnum).addAll(stateEnums);
            else allIncoming.put(stateEnum, stateEnums);
        });
        return incomingTransitions;
    }

    public Map<StateEnum, Set<StateEnum>> getOutgoingTransitions() {
        return outgoingTransitions;
    }
}
