package com.brobot.brobotREST.actions;

import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class NullState {

    // convert simple objects to state objects

    public enum Enum implements StateEnum {
        NULL
    }

    private State state = new State.Builder(Enum.NULL)
            .build();

    public NullState(StateService stateService) {
        stateService.save(state);
    }

}
