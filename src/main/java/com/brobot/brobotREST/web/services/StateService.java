package com.brobot.brobotREST.web.services;

import com.brobot.brobotREST.database.state.state.CoexistingStatesJointTable;
import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.primatives.enums.StateGroup;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StateService {

    private Map<StateEnum, State> stateRepository = new HashMap<>();
    private CoexistingStatesJointTable coexistingStatesJointTable;

    public StateService(CoexistingStatesJointTable coexistingStatesJointTable) {
        this.coexistingStatesJointTable = coexistingStatesJointTable;
    }

    public Set<State> findAllStates() {
        return new HashSet<>(stateRepository.values());
    }

    public Set<StateEnum> findAllStateEnums() {
        return stateRepository.keySet();
    }

    public Optional<State> findByName(StateEnum stateName) {
        if (stateName == null) return Optional.empty();
        return Optional.ofNullable(stateRepository.get(stateName));
    }

    public Set<State> findSetByName(StateEnum... stateEnums) {
        Set<State> states = new HashSet<>();
        Stream.of(stateEnums).forEach(stateEnum -> findByName(stateEnum).ifPresent(states::add));
        return states;
    }

    public Set<State> findSetByName(Set<StateEnum> stateEnums) {
        return findSetByName(stateEnums.toArray(new StateEnum[0]));
    }

    public State[] findArrayByName(Set<StateEnum> stateEnums) {
        return findArrayByName(stateEnums.toArray(new StateEnum[0]));
    }

    public State[] findArrayByName(StateEnum... stateEnums) {
        List<State> states = new ArrayList<>();
        Stream.of(stateEnums).forEach(stateEnum -> findByName(stateEnum).ifPresent(states::add));
        return states.toArray(new State[0]);
    }

    public void save(State state) {
        if (state == null) return;
        stateRepository.put(state.getName(), state);
        state.getCoexistingStates().getCoexistingStates().forEach(
                (key, value) -> coexistingStatesJointTable.add(state.getName(), key, value));
    }

    public Set<StateEnum> getBlockingStates(Set<StateEnum> activeStates) {
        return activeStates.stream()
                .filter(stateEnum -> findByName(stateEnum).isPresent() &&
                        findByName(stateEnum).get().getCoexistingStates().isBlocksCoexistingStates())
                .collect(Collectors.toSet());
    }

    public Set<StateEnum> getNonBlockingStates(Set<StateEnum> activeStates) {
        return activeStates.stream()
                .filter(stateEnum -> findByName(stateEnum).isPresent() &&
                        !findByName(stateEnum).get().getCoexistingStates().isBlocksCoexistingStates())
                .collect(Collectors.toSet());
    }

    public Set<StateEnum> getAllCoexistingStates(StateEnum... activeStates) {
        return getAllCoexistingStates(Stream.of(activeStates).collect(Collectors.toSet()));
    }

    public Set<StateEnum> getAllCoexistingStates(Set<StateEnum> activeStates) {
        Set<StateEnum> allCoexistingStates = new HashSet<>();
        for (StateEnum stateEnum : activeStates) {
            allCoexistingStates.add(stateEnum);
            findByName(stateEnum).ifPresent(state ->
                    allCoexistingStates.addAll(state.getCoexistingStates().getAllStateEnums()));
        }
        return allCoexistingStates;
    }

    // find all coexisting states of all coexisting states (until no new states are added)
    public Set<StateEnum> getCoexistingStatesChain(Set<StateEnum> activeStates) {
        Set<StateEnum> coexistingStatesChain = new HashSet<>(activeStates);
        int previousStateSize = 0;
        int newStateSize = coexistingStatesChain.size();
        while (newStateSize > previousStateSize) {
            previousStateSize = newStateSize;
            coexistingStatesChain.addAll(getAllCoexistingStates(coexistingStatesChain));
            newStateSize = coexistingStatesChain.size();
        }
        return coexistingStatesChain;
    }

    public Set<StateEnum> getCoexistingStatesChain(StateEnum state) {
        return getCoexistingStatesChain(Collections.singleton(state));
    }

    public Set<State> getAllStatesInGroup(StateEnum stateEnum) {
        Optional<State> state = findByName(stateEnum);
        return state.map(value -> stateRepository.values().stream()
                .filter(v -> v.getGroup() == value.getGroup())
                .collect(Collectors.toSet())).orElseGet(HashSet::new);
    }

    public Set<StateEnum> getAllStateEnumsInGroup(StateEnum stateEnum) {
        return getAllStatesInGroup(stateEnum).stream().map(State::getName).collect(Collectors.toSet());
    }

    public void setProbabilityForAllOtherStatesInGroup(StateEnum stateEnum, int prob) {
        getAllStatesInGroup(stateEnum).forEach(state -> {
            if (state.getName() != stateEnum) state.setProbabilityExists(prob);
        });

    }

}
