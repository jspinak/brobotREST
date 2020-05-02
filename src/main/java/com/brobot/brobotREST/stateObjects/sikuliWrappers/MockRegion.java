package com.brobot.brobotREST.stateObjects.sikuliWrappers;

import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateObjectData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateData.methods.StateDataMethods;
import org.sikuli.script.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class MockRegion {
    private MockStateManagement mockStateManagement;
    private StateDataMethods stateDataMethods;

    private List<StateData> expectedStates;
    //private StateRIP expectedRIP; // sometimes RIPs are tied to a game state, such as a confirmation dialog box
    private boolean useMock = false;

    public MockRegion(MockStateManagement mockStateManagement,
                      StateDataMethods stateDataMethods) {
        this.mockStateManagement = mockStateManagement;
        this.stateDataMethods = stateDataMethods;
    }

    public boolean isUseMock() {
        return useMock;
    }

    public void setUseMock(boolean useMock) {
        this.useMock = useMock;
    }

    public List<StateData> getExpectedStates() {
        return expectedStates;
    }

    public void setExpectedStates(List<StateData> expectedStates) {
        for (StateData state : expectedStates) {
            stateDataMethods.activateMock(state);
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

    public boolean doActionToChangeState(List<? extends StateObjectData> stateObjects) {
        for (StateObjectData stateObject : stateObjects) {
            if (doActionToChangeState(stateObject)) return true;
        }
        return false;
    }

    public boolean doActionToChangeStates(StateObjectData... stateObjects) {
        return doActionToChangeState(Arrays.asList(stateObjects));
    }

    public boolean doActionToChangeState(StateObjectData stateObject) {
        if (!stateObjectExists(stateObject)) {
            System.out.print(stateObject.getName()+"_mock object not found | ");
            return false;
        }
        mockStateManagement.setProbabilitiesOnClick(stateObject);
        return true;
    }

    public boolean gameStateExists(StateData state) {
        for (StateRIPData stateObject : state.getStateObjects().getStateRIPs()) {
            if (stateObjectExists(stateObject)) return true;
        }
        return false;
    }

    public boolean stateObjectsExist(StateObjectData... stateObjects) {
        return stateObjectsExist(Arrays.asList(stateObjects));
    }

    public boolean stateObjectsExist(List<StateObjectData> stateObjects) {
        for (StateObjectData stateObject : stateObjects) {
            if (stateObjectExists(stateObject)) return true;
        }
        return false;
    }

    public boolean stateObjectExists(StateObjectData stateObject) {
        return new Random().nextInt(100) < stateObject.getProbabilityExists();
    }

}
