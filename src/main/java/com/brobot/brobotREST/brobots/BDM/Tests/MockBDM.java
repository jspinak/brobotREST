package com.brobot.brobotREST.brobots.BDM.Tests;

import com.brobot.brobotREST.brobots.BDM.Steuerung.BDMSteuern;
import com.brobot.brobotREST.dataAccess.StateRIPRepository;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateData.methods.StateDataMethods;
import com.brobot.brobotREST.stateData.wrappers.StateObjectWrapper;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.statesSteuern.PathFinder;
import com.brobot.brobotREST.statesSteuern.StateFinder;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.reports.FoundRatios;
import com.brobot.brobotREST.web.services.StateService;
import com.brobot.brobotREST.web.services.TreeService;
import org.sikuli.script.ImagePath;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class MockBDM {

    private MockRegion mockRegion;
    private PathFinder pathFinder;
    private StateService stateService;
    private StateDataMethods stateDataMethods;
    private FoundRatios foundRatios;
    private StateFinder stateFinder;
    private BDMSteuern bdmSteuern;
    private TreeService treeService;
    private StateRIPRepository stateRIPRepository;

    public MockBDM(MockRegion mockRegion, PathFinder pathFinder, StateService stateService,
                   StateDataMethods stateDataMethods, FoundRatios foundRatios,
                   StateFinder stateFinder, BDMSteuern bdmSteuern,
                   TreeService treeService, StateRIPRepository stateRIPRepository) {
        this.mockRegion = mockRegion;
        this.pathFinder = pathFinder;
        this.stateService = stateService;
        this.stateDataMethods = stateDataMethods;
        this.foundRatios = foundRatios;
        this.stateFinder = stateFinder;
        this.bdmSteuern = bdmSteuern;
        this.treeService = treeService;
        this.stateRIPRepository = stateRIPRepository;
        ImagePath.setBundlePath("BDO.sikuli"); // in case we want to check if the image files exist
        mockRegion.setUseMock(true);
    }

    public void setExpectedGameState(StateEnum stateEnum) {
        stateFinder.setExpectetStateAndAssociatedStates(stateEnum);
    }

    public void testFinder(StateEnum currentState, StateEnum targetState) {
        System.out.println();
        setExpectedGameState(currentState);
        Set<StateObjectWrapper> pathToState = pathFinder.getPathToState(
                mockRegion.getExpectedStates(), targetState);
    }

    public void gotoState(StateEnum stateEnum) {
        System.out.println();
        System.out.print("expected game state:");
        mockRegion.getExpectedStates().forEach(gS -> System.out.print(" "+gS.getName()));
        System.out.println();
        bdmSteuern.openState(stateEnum);
    }

    public void printTree(StateEnum startState) {
        System.out.println();
        treeService.makeTree(startState);
    }

    public void printRatios() {
        System.out.println();
        foundRatios.printRatios();
    }

    public void printState(StateEnum name) {
        stateDataMethods.print(stateService.findByName(name));
    }

    public List<StateData> getAllStates() {
        return stateService.findAll();
    }

    public List<StateRIPData> getAllRIPs() { return stateRIPRepository.findAll();
    }
}
