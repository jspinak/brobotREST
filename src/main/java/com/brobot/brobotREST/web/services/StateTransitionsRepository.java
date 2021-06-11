package com.brobot.brobotREST.web.services;

import com.brobot.brobotREST.manageStates.StateTransitions;
import com.brobot.brobotREST.manageStates.StateTransitionsJointTable;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.brobot.brobotREST.manageStates.StateMemory.Enum.PREVIOUS;

@Component
public class StateTransitionsRepository {

    private StateTransitionsJointTable stateTransitionsJointTable;
    private Map<StateEnum, StateTransitions> repo = new HashMap<>();

    public StateTransitionsRepository(StateTransitionsJointTable stateTransitionsJointTable) {
        this.stateTransitionsJointTable = stateTransitionsJointTable;
    }

    public void add(StateTransitions stateTransitions) {
        for (StateEnum child : stateTransitions.getTransitions().keySet()) {
            stateTransitionsJointTable.add(child, stateTransitions.getStateName());
        }
        repo.put(stateTransitions.getStateName(), stateTransitions);
    }

    public Optional<StateTransitions> get(StateEnum stateEnum) {
        return Optional.ofNullable(repo.get(stateEnum));
    }

    public Set<StateEnum> getAllStates() {
        return repo.keySet();
    }

}
