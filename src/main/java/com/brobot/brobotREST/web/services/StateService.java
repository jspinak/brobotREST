package com.brobot.brobotREST.web.services;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.dataAccess.StateRepository;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class StateService {

    private StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<StateData> findAll() {
        return stateRepository.findAll();
    }

    public StateData findByName(String stateName) {
        if (stateName == null) return null;
        return stateRepository.findByName(stateName);
    }

    public StateData findByName(StateEnum stateEnum) {
        if (stateEnum == null) return null;
        return stateRepository.findByName(stateEnum.toString());
    }

    public List<StateData> findByName(Set<String> stateNames) {
        List<StateData> states = new ArrayList<>();
        if (stateNames.isEmpty()) return states;
        stateNames.forEach(state -> states.add(findByName(state)));
        return states;
    }

    public void save(StateData stateData) {
        stateRepository.save(stateData);
    }

    public StateData initAndSaveIfDoesntExist(StateEnum stateEnum) {
        StateData tempState = findByName(stateEnum);
        if (tempState != null) return tempState;
        tempState = new StateData();
        tempState.setName(stateEnum.toString());
        save(tempState);
        return tempState;
    }

}
