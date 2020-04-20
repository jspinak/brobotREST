package com.brobot.brobotREST.StatesSteuern;

import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
//import com.brobot.brobotREST.JavaFX.JavaFXApplication;
//import com.brobot.brobotREST.JavaFX.StateTree;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
//import javafx.scene.control.TreeItem;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PathFinder {

    private GameStateRepository gameStateRepository;
    private StateFinder stateFinder;

    //private TreeItem firstTreeItem;

    public PathFinder(GameStateRepository gameStateRepository,
                      StateFinder stateFinder) {
        this.gameStateRepository = gameStateRepository;
        this.stateFinder = stateFinder;
    }

    // finds a path backward from a state to all StateObjects needed to region/type in order to reach the state
    // the returned list starts with startState and ends with targetState
    public List<StateObject> getPathToState(List<State> startStates, GameStateEnum targetState) {
        System.out.println("find path: "+startStates.get(0).getName()+" -> "+targetState);
        List<StateObject> path = new ArrayList<>();
        recursePath(path, startStates, targetState, new HashSet<>());
        for (StateObject stateObject : path)
            System.out.print(stateObject.getCurrentState()+"."+stateObject.getName()+" -> ");
        if (!path.isEmpty()) System.out.println(targetState);
        return path;
    }

    private Map<StateObject, String> getStateObjectLists(GameStateEnum targetStateEnum,
                                                         List<StateObject> activatingObjects,
                                                         List<StateObject> stateObjects) {
        State targetState = gameStateRepository.get(targetStateEnum);
        activatingObjects = targetState.getActivatingObjects().getActivatingObjects();
        stateObjects = targetState.getAllStateObjects();
        HashMap<StateObject, String> allStateObjects = new HashMap<>();
        for (StateObject sO : stateObjects) allStateObjects.put(sO, "state object");
        for (StateObject aO : activatingObjects) allStateObjects.put(aO, "activating object");
        if (allStateObjects.isEmpty())
            System.out.println("State=="+targetState.getName()+": no further links for this state");
        return allStateObjects;
    }

    private boolean recursePath(List<StateObject> path, List<State> startStates,
                                GameStateEnum targetStateEnum, HashSet<GameStateEnum> gameStatesVisited) {
        gameStatesVisited.add(targetStateEnum);
        //System.out.println("target state: "+targetStateEnum);
        State targetState = gameStateRepository.get(targetStateEnum);
        List<StateObject> activatingObjects = targetState.getActivatingObjects().getActivatingObjects();
        if (activatingObjects.isEmpty()) return false;
        //System.out.print("...:"); for (StateObject sO : activatingObjects) sO.print(" ");
        return recurseState(path, activatingObjects, startStates, gameStatesVisited);
    }

    private boolean recurseState(List<StateObject> path, List<StateObject> links,
                                 List<State> startStates, HashSet<GameStateEnum> gameStatesVisited) {
        for (StateObject stateObject : links) {
            List<GameStateEnum> linkedStates = getActivatingStates(stateObject);
            //for (GameStateEnum g : linkedStates) System.out.print(g+" "); System.out.println();
            for (GameStateEnum linkedStateEnum : linkedStates) {
                //System.out.println("__"+linkedStateEnum+"__");
                 if (!gameStatesVisited.contains(linkedStateEnum)) {
                    if (startStates.contains(gameStateRepository.get(linkedStateEnum)) ||
                            recursePath(path, startStates, linkedStateEnum, gameStatesVisited)) {
                        path.add(stateObject);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void printTree(GameStateEnum startState) {
        List<State> startStates = stateFinder.getAllActiveStates(startState);
        //firstTreeItem = new TreeItem("GameState Tree");
        //startStates.forEach(sS -> {
        //    sS.setTreeItem(new TreeItem(sS.getName()));
        //    firstTreeItem.getChildren().add(sS.getTreeItem());
        //});
        //System.out.println("size of tree item = "+firstTreeItem.getChildren().size());
        prepareObjectListsAndRecurseTree(startStates, 0, new HashSet<>());
        //StateTree.setRootItem(firstTreeItem);
        //JavaFXApplication.main(new String[]{});
    }

    private void prepareObjectListsAndRecurseTree(List<State> startStates, int indent,
                                                  Set<GameStateEnum> gameStatesVisited) {
        for (State startState : startStates) {
            List<StateObject> stateObjects = startState.getAllStateObjects();
            if (!stateObjects.isEmpty()) {
                //stateObjects.forEach(sO -> {
                //    sO.setTreeItem(new TreeItem(sO.getName()));
                //    startState.getTreeItem().getChildren().add(sO.getTreeItem());
                //});
                recurseTree(startState.getName(), indent, gameStatesVisited, stateObjects);
            }
        }
    }

    private List<GameStateEnum> getActivatedStates(StateObject stateObject) {
        //System.out.print(" [activated]by "+stateObject.getStatesToActivateOnClick());
        //stateObject.print(); System.out.println();
        return stateObject.getStatesToActivateOnClick();
    }

    private List<GameStateEnum> getActivatingStates(StateObject stateObject) {
        List<GameStateEnum> activatingState = new ArrayList<>();
        activatingState.add(gameStateRepository.get(stateObject.getCurrentState()).getName());
        //System.out.print(" activatingState / stateObject : "); stateObject.print(); System.out.println();
        return activatingState;
    }

    private List<GameStateEnum> getNextStates(StateObject stateObject, String objectType) {
        //System.out.print("get next state: "); stateObject.print(" ");
        if (objectType.equals("state object")) return getActivatedStates(stateObject);
        else if (objectType.equals("activating object")) return getActivatingStates(stateObject);
        return new ArrayList<>();
    }

    private void recurseTree(GameStateEnum startState, int indent, Set<GameStateEnum> gameStatesVisited,
                             List<StateObject> stateObjects) {
        for (int i=0; i<indent; i++) if (i%2==0) System.out.print("|"); else System.out.print(" ");
        System.out.println("__"+startState+"__");
        gameStatesVisited.add(startState);
        indent++;
        for (StateObject stateObject : stateObjects) {
            for (int i=0; i<indent; i++) if (i%2==0) System.out.print("|"); else System.out.print(" ");
            System.out.print(".");
            System.out.print(stateObject.getName());
            List<GameStateEnum> nextStates = getNextStates(stateObject,"state object");
            if (nextStates.isEmpty()) System.out.println();
            for (GameStateEnum gameStateEnum : nextStates) {
                if (gameStatesVisited.contains(gameStateEnum)) {
                    System.out.println("->"+gameStateEnum);
                    //stateObject.getTreeItem().getChildren().add(new TreeItem(gameStateEnum)); //add just the name
                }
                else {
                    System.out.println();
                    //stateObject.getTreeItem().getChildren().add(gameStateRepository.get(gameStateEnum).newTreeItem());
                    prepareObjectListsAndRecurseTree(
                            stateFinder.getAllActiveStates(gameStateEnum), indent, gameStatesVisited);
                }
            }
        }
    }

    //public TreeItem getFirstTreeItem() {
    //    return firstTreeItem;
    //}
}
