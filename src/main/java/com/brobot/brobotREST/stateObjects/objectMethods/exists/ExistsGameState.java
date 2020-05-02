package com.brobot.brobotREST.stateObjects.objectMethods.exists;

import com.brobot.brobotREST.dataAccess.StateRepository;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExistsGameState {

    private ExistsStateRIP existsStateRIP;
    private StateService stateService;

    public ExistsGameState(ExistsStateRIP existsStateRIP,
                           StateService stateService) {
        this.existsStateRIP = existsStateRIP;
        this.stateService = stateService;
    }

    public boolean exists(StateData state) {
        return existsStateRIP.exists(state.getStateObjects().getStateRIPs());
    }

    public boolean exists(StateEnum gameState) {
        StateData state = stateService.findByName(gameState);
        return existsStateRIP.exists(state.getStateObjects().getStateRIPs());
    }

    public StateData getFirstGameStateFound(List<StateData> states) {
        for (StateData state : states) {
            if (exists(state)) return state;
        }
        return null;
    }

    public List<StateData> getAllExistingGameStates(List<StateData> states) {
        List<StateData> existingStates = new ArrayList<>();
        for (StateData state : states) {
            if (exists(state)) existingStates.add(state);
        }
        return existingStates;
    }

}
