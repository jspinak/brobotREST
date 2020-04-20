package com.brobot.brobotREST.Brobots.BDM.Tests;

import com.brobot.brobotREST.Brobots.BDM.Steuerung.BDMSteuern;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.StatesSteuern.PathFinder;
import com.brobot.brobotREST.StatesSteuern.StateFinder;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import com.brobot.brobotREST.Reports.FoundRatios;
import org.sikuli.script.ImagePath;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockBDM {

    private MockRegion mockRegion;
    private PathFinder pathFinder;
    private GameStateRepository gameStateRepository;
    private FoundRatios foundRatios;
    private StateFinder stateFinder;
    private BDMSteuern bdmSteuern;

    public MockBDM(MockRegion mockRegion, PathFinder pathFinder, GameStateRepository gameStateRepository,
                   FoundRatios foundRatios, StateFinder stateFinder, BDMSteuern bdmSteuern) {
        this.mockRegion = mockRegion;
        this.pathFinder = pathFinder;
        this.gameStateRepository = gameStateRepository;
        this.foundRatios = foundRatios;
        this.stateFinder = stateFinder;
        this.bdmSteuern = bdmSteuern;
        ImagePath.setBundlePath("BDO.sikuli"); // in case we want to check if the image files exist
        mockRegion.setUseMock(true);

    }

    public void setExpectedGameState(GameStateEnum gameStateEnum) {
        stateFinder.setExpectetStateAndAssociatedStates(gameStateEnum);
    }

    public void testFinder(GameStateEnum currentState, GameStateEnum targetState) {
        System.out.println();
        setExpectedGameState(currentState);
        List<StateObject> pathToState = pathFinder.getPathToState(
                mockRegion.getExpectedStates(), targetState);
    }

    public void gotoState(GameStateEnum gameStateEnum) {
        System.out.println();
        System.out.print("expected game state:");
        mockRegion.getExpectedStates().forEach(gS -> System.out.print(" "+gS.getName()));
        System.out.println();
        bdmSteuern.openState(gameStateEnum);
    }

    public void printTree(GameStateEnum startState) {
        System.out.println();
        pathFinder.printTree(startState);
    }

    public void printRatios() {
        System.out.println();
        foundRatios.printRatios();
    }

    public void printGameState(GameStateEnum name) {
        gameStateRepository.get(name).print();
    }
}
