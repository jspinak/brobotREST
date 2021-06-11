package com.brobot.brobotREST.web.services;

import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.manageStates.StateTransitions;
import com.brobot.brobotREST.manageStates.StateTransitionsJointTable;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.function.BooleanSupplier;

import static com.brobot.brobotREST.manageStates.StateMemory.Enum.PREVIOUS;
import static com.brobot.brobotREST.manageStates.UnknownState.Enum.UNKNOWN;

@Component
public class StateTransitionsService {

    private StateTransitionsRepository stateTransitionsRepository;
    private StateTransitionsJointTable stateTransitionsJointTable;
    private StateService stateService;

    public StateTransitionsService(StateTransitionsRepository stateTransitionsRepository,
                                   StateTransitionsJointTable stateTransitionsJointTable,
                                   StateService stateService) {
        this.stateTransitionsRepository = stateTransitionsRepository;
        this.stateTransitionsJointTable = stateTransitionsJointTable;
        this.stateService = stateService;
    }

    public boolean doTransition(StateEnum from, StateEnum to) {
        Optional<StateTransitions> transitionsFrom = getTransitions(from);
        return transitionsFrom.map(stateTransitions ->
                stateTransitions.open(getTransitionToEnum(from, to))).orElse(false);
    }

    // may be PREVIOUS
    private StateEnum getTransitionToEnum(StateEnum from, StateEnum to) {
        if (stateTransitionsJointTable.getStatesWithTransitionsFrom(from).contains(to)) return to;
        if (!stateTransitionsJointTable.getStatesWithTransitionsFrom(from).contains(PREVIOUS)) return UNKNOWN;
        if (!stateService.findByName(from).isPresent()) return UNKNOWN;
        Optional<StateEnum> optPrevious = stateService.findByName(from).get().getPreviousState();
        if (!optPrevious.isPresent()) return UNKNOWN;
        if (optPrevious.get() == to) return PREVIOUS;
        return UNKNOWN;
    }

    public Optional<StateTransitions> getTransitions(StateEnum stateEnum) {
        return stateTransitionsRepository.get(stateEnum);
    }

    public void add(StateTransitions activities) {
        stateTransitionsRepository.add(activities);
    }

    public void setPreviousState(StateEnum stateToSet, StateEnum previousState,
                                 Optional<StateEnum> formerPreviousState) {
        stateTransitionsJointTable.setPreviousIncomingTransition(stateToSet, previousState, formerPreviousState);
    }
}
