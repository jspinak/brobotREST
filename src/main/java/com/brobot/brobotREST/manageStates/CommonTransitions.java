package com.brobot.brobotREST.manageStates;

import com.brobot.brobotREST.actions.composites.CommonActions;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import org.springframework.stereotype.Component;

@Component
public class CommonTransitions {

    private CommonActions commonActions;

    public CommonTransitions(CommonActions commonActions) {
        this.commonActions = commonActions;
    }

    public StateTransitions basicTransition(StateEnum stateEnum, StateEnum toState, StateImageObject toStateImage) {
        return new StateTransitions.Builder(stateEnum)
                .addTransitionFinish(() -> commonActions.findState(1, stateEnum))
                .addTransition(toState, () -> commonActions.find(1, toStateImage))
                .build();
    }
}
