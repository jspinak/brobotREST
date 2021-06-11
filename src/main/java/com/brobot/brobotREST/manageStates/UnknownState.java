package com.brobot.brobotREST.manageStates;

import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class UnknownState {

    public enum Enum implements StateEnum {
        UNKNOWN
    }

    private State state;

    public UnknownState(StateService stateService) {
        state = new State.Builder(Enum.UNKNOWN).build();
        stateService.save(state);
    }
}
