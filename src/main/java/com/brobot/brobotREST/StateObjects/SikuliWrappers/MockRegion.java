package com.brobot.brobotREST.StateObjects.SikuliWrappers;

import com.brobot.brobotREST.StateObjects.State.*;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import org.sikuli.script.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class MockRegion {
    private MockStateManagement mockStateManagement;

    private List<State> expectedStates;
    //private StateRIP expectedRIP; // sometimes RIPs are tied to a game state, such as a confirmation dialog box
    private boolean useMock = false;

    public MockRegion(MockStateManagement mockStateManagement) {
        this.mockStateManagement = mockStateManagement;
    }

    public boolean isUseMock() {
        return useMock;
    }

    public void setUseMock(boolean useMock) {
        this.useMock = useMock;
    }

    public List<State> getExpectedStates() {
        return expectedStates;
    }

    public void setExpectedStates(List<State> expectedStates) {
        for (State state : expectedStates) {
            state.activateMock();
        }
        this.expectedStates = expectedStates;
    }

   // 2. The image will be found based on RNG and the stated probability of existing
    public Match getMockMatch() {
        Region matchRegion = new Region(0,0,1,1);
        return new Match(matchRegion, 100.0);
    }

    public List<Match> getMockMatchList() {
        List<Match> mockMatches = new ArrayList<>();
        mockMatches.add(getMockMatch());
        return mockMatches;
    }

    public boolean doActionToChangeState(List<? extends StateObject> stateObjects) {
        for (StateObject stateObject : stateObjects) {
            if (doActionToChangeState(stateObject)) return true;
        }
        return false;
    }

    public boolean doActionToChangeState(StateObject... stateObjects) {
        return doActionToChangeState(Arrays.asList(stateObjects));
    }

    public boolean doActionToChangeState(StateObject stateObject) {
        if (!stateObjectExists(stateObject)) {
            System.out.print(stateObject.getName()+"_mock object not found | ");
            return false;
        }
        mockStateManagement.setProbabilitiesOnClick(stateObject);
        return true;
    }

    public boolean gameStateExists(State state) {
        for (StateObject stateObject : state.getStateRIPs()) {
            if (stateObjectExists(stateObject)) return true;
        }
        return false;
    }

    public boolean stateObjectsExist(StateObject... stateObjects) {
        return stateObjectsExist(Arrays.asList(stateObjects));
    }

    public boolean stateObjectsExist(List<StateObject> stateObjects) {
        for (StateObject stateObject : stateObjects) {
            if (stateObjectExists(stateObject)) return true;
        }
        return false;
    }

    public boolean stateObjectExists(StateObject stateObject) {
        return new Random().nextInt(100) < stateObject.getProbabilityExists();
    }

}
