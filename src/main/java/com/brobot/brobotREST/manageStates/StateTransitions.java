package com.brobot.brobotREST.manageStates;

import com.brobot.brobotREST.primatives.enums.StateEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BooleanSupplier;

@Data
public class StateTransitions {

    private StateEnum stateName;
    private BooleanSupplier transitionFinish;
    private Map<StateEnum, BooleanSupplier> transitions = new HashMap<>();

    public boolean open(StateEnum stateToOpen) {
        System.out.format("\n\n%s -> %s\n",this.stateName, stateToOpen);
        if (!transitions.containsKey(stateToOpen)) return false;
        return transitions.get(stateToOpen).getAsBoolean();
    }

    public boolean finishOpen() {
        System.out.format("\n%s %s",this.stateName, "finish transition ");
        return transitionFinish.getAsBoolean();
    }

    public Optional<BooleanSupplier> getTransition(StateEnum to) {
        return Optional.ofNullable(transitions.get(to));
    }

    public static class Builder {

        private StateEnum stateName;
        private BooleanSupplier transitionFinish;
        private Map<StateEnum, BooleanSupplier> transitions = new HashMap<>();

        public Builder(StateEnum stateName) {
            this.stateName = stateName;
        }

        public Builder addTransition(StateEnum toState, BooleanSupplier transition) {
            transitions.put(toState, transition);
            return this;
        }

        public Builder addTransitionFinish(BooleanSupplier transitionFinish) {
            this.transitionFinish = transitionFinish;
            return this;
        }

        public StateTransitions build() {
            StateTransitions stateTransitions = new StateTransitions();
            stateTransitions.stateName = stateName;
            stateTransitions.transitionFinish = transitionFinish;
            stateTransitions.transitions = transitions;
            return stateTransitions;
        }
    }

}
