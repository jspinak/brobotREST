package com.brobot.brobotREST.statesSteuern;

import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.stateData.methods.StateDataMethods;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.stateData.wrappers.StateObjectWrapper;
import com.brobot.brobotREST.stateData.wrappers.StateObjectWrapperFactory;
import com.brobot.brobotREST.web.services.StateService;
import com.brobot.brobotREST.web.services.TreeService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PathFinder {

    private StateService stateService;
    private StateDataMethods stateDataMethods;
    private TreeService treeService;
    private StateObjectWrapperFactory stateObjectWrapperFactory;

    public PathFinder(StateService stateService, StateDataMethods stateDataMethods,
                      TreeService treeService, StateObjectWrapperFactory stateObjectWrapperFactory) {
        this.stateService = stateService;
        this.stateDataMethods = stateDataMethods;
        this.treeService = treeService;
        this.stateObjectWrapperFactory = stateObjectWrapperFactory;
    }

    // finds a path backward from a state to all StateObjects needed to region/type in order to reach the state
    // the returned list starts with startState and ends with targetState
    public Set<StateObjectWrapper> getPathToState(List<StateData> startStates, StateEnum targetState) {
        System.out.println("find path: "+startStates.get(0).getName()+" -> "+targetState);
        Set<StateObjectWrapper> path = new HashSet<>();
        recursePath(path, startStates, targetState.toString(), new HashSet<>());
        printPath(path);
        if (!path.isEmpty()) System.out.println(targetState);
        return path;
    }

    private Map<StateObjectWrapper, String> getStateObjectWrapperLists(
                                                StateEnum targetStateEnum,
                                                List<StateObjectWrapper> activatingObjectWrappers,
                                                List<StateObjectWrapper> stateObjectWrappers) {
        StateData targetState = stateService.findByName(targetStateEnum);
        activatingObjectWrappers = stateObjectWrapperFactory.getAllActivatingObjectWrappers(targetState);
        stateObjectWrappers = stateObjectWrapperFactory.getAllStateObjectWrappers(targetState);
        HashMap<StateObjectWrapper, String> allStateObjects = new HashMap<>();
        stateObjectWrappers.forEach(sOW -> allStateObjects.put(sOW, "state object"));
        activatingObjectWrappers.forEach(aOW -> allStateObjects.put(aOW, "activating object"));
        if (allStateObjects.isEmpty())
            System.out.println("State=="+targetState.getName()+": no further links for this state");
        return allStateObjects;
    }

    private boolean recursePath(Set<StateObjectWrapper> path, List<StateData> startStates,
                                String targetStateName, HashSet<String> gameStatesVisited) {
        gameStatesVisited.add(targetStateName);
        //System.out.println("target state: "+targetStateName);
        StateData targetState = stateService.findByName(targetStateName);
        List<StateObjectWrapper> activatingObjects = stateObjectWrapperFactory
                                                        .getAllActivatingObjectWrappers(targetState);
        if (activatingObjects.isEmpty()) {
            //System.out.println(targetStateName+" has no activating objects");
            return false;
        }
        //System.out.print("...:"); for (StateObjectWrapper sO : activatingObjects) sO.print(" ");
        return recurseState(path, activatingObjects, startStates, gameStatesVisited);
    }

    private boolean recurseState(Set<StateObjectWrapper> path, List<StateObjectWrapper> links,
                                 List<StateData> startStates, HashSet<String> gameStatesVisited) {
        for (StateObjectWrapper stateObjectWrapper : links) {
            Set<String> linkedStates = treeService.getActivatingStates(stateObjectWrapper);
            //for (GameStateEnum g : linkedStates) System.out.print(g+" "); System.out.println();
            for (String linkedState : linkedStates) {
                 //System.out.println("__"+linkedState+"__");
                 if (!gameStatesVisited.contains(linkedState)) {
                    if (startStates.contains(stateService.findByName(linkedState)) ||
                            recursePath(path, startStates, linkedState, gameStatesVisited)) {
                        path.add(stateObjectWrapper);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void printPath(Set<StateObjectWrapper> path) {
        for (StateObjectWrapper wrapper : path) {
            wrapper.print(" -> ");
        }
    }

}
