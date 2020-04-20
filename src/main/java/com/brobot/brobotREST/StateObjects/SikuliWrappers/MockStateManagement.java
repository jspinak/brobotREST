package com.brobot.brobotREST.StateObjects.SikuliWrappers;

import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockStateManagement {

    private GameStateRepository gameStateRepository;

    public MockStateManagement(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    public void setProbabilitiesOnClick(StateObject stateObject) {
        GameStateEnum currentStateEnum = stateObject.getCurrentState();
        State currentState = gameStateRepository.get(currentStateEnum);
        currentState.deactivateMock();
        for (State activatedState : getStatesToActivate(stateObject)) {
            System.out.println(stateObject.getName()+" "+activatedState.getName()+"_activateMock | ");
            activatedState.activateMock();
        }
    }

    private List<State> getStatesToActivate(StateObject stateObject) {
        List<GameStateEnum> stateEnums = stateObject.getStatesToActivateOnClick();
        return gameStateRepository.get(stateEnums);
    }

    public List<StateRIP> getAllActivatedRIPs(StateObject stateObject) {
        List<StateRIP> associatedRIPs = new ArrayList();
        getStatesToActivate(stateObject).forEach(gS -> associatedRIPs.addAll(gS.getStateRIPs()));
        return associatedRIPs;
    }

    public List<State> getAllPossibleActivatedGameStates(GameStateEnum gameStateEnum) {
        List<State> states = new ArrayList<>();
        State state = gameStateRepository.get(gameStateEnum);
        state.getStateRIPs().forEach(
                RIP -> states.addAll(gameStateRepository.get(RIP.getStatesToActivateOnClick())));
        return states;
    }

    public List<State> getGameStatesPossiblyOnTop(GameStateEnum gameStateEnum) {
        List<State> allPossibleActivatedStates = getAllPossibleActivatedGameStates(gameStateEnum);
        for (State gS : allPossibleActivatedStates) {
            if (!gS.isOnTopOfActivatingState()) allPossibleActivatedStates.remove(gS);
        }
        return allPossibleActivatedStates;
    }
}
