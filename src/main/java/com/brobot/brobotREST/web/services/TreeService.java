package com.brobot.brobotREST.web.services;

import com.brobot.brobotREST.database.state.state.States;
import com.brobot.brobotREST.manageStates.StateFinder;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.reports.Tree;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TreeService {
    /*

    private StateService stateService;
    private StateObjectWrapperFactory stateObjectWrapperFactory;
    private StateFinder stateFinder;

    private Tree tree;

    public TreeService(StateService stateService,
                       StateObjectWrapperFactory stateObjectWrapperFactory,
                       StateFinder stateFinder) {
        this.stateService = stateService;
        this.stateObjectWrapperFactory = stateObjectWrapperFactory;
        this.stateFinder = stateFinder;
    }

    public Tree makeTree(StateEnum startingStateEnum) {
        States startStates = mockStateManagement.getOneLevelCoexistingStates(expectedMainState);
        tree = new Tree(startingStateEnum.toString());
        //startStates.forEach(sS -> {
        //    sS.setTreeItem(new TreeItem(sS.getName()));
        //    firstTreeItem.getChildren().add(sS.getTreeItem());
        //});
        //System.out.println("size of tree item = "+firstTreeItem.getChildren().size());
        prepareObjectListsAndRecurseTree(startStates, 0, new HashSet<>());
        //StateTree.setRootItem(firstTreeItem);
        //JavaFXApplication.main(new String[]{});
        return tree;
    }

    private void prepareObjectListsAndRecurseTree(Set<StateEnum> startStates, int indent,
                                                  Set<StateEnum> gameStatesVisited) {
        for (StateEnum startState : startStates) {
            List<StateObjectWrapper> stateObjects =
                    stateObjectWrapperFactory.getAllStateObjectWrappers(startState);
            if (!stateObjects.isEmpty()) {
                //stateObjects.forEach(sO -> {
                //    sO.setTreeItem(new TreeItem(sO.getName()));
                //    startState.getTreeItem().getChildren().add(sO.getTreeItem());
                //});
                recurseTree(startState.getName(), indent, gameStatesVisited, stateObjects);
            }
        }
    }

    private Set<StateEnum> getActivatedStates(StateObjectWrapper stateObjectWrapper) {
        //System.out.print(" [activated]by "+stateObject.getStatesToActivateOnClick());
        //stateObject.print(); System.out.println();
        return stateObjectWrapper.getStateObject().getStatesActivatedOnClick();
    }

    public Set<StateEnum> getActivatingStates(StateObjectWrapper stateObjectWrapper) {
        Set<StateEnum> activatingState = new HashSet<>();
        activatingState.add(stateObjectWrapper.getStateObject().getOwnerStateName());
        //System.out.print(" activatingState / stateObject : "); stateObject.print(); System.out.println();
        return activatingState;
    }

    private Set<StateEnum> getNextStates(StateObjectWrapper stateObjectWrapper, String objectType) {
        //System.out.print("get next state: "); stateObject.print(" ");
        if (objectType.equals("state object")) return getActivatedStates(stateObjectWrapper);
        else if (objectType.equals("activating object")) return getActivatingStates(stateObjectWrapper);
        return new HashSet<>();
    }

    private void recurseTree(StateEnum startState, int indent, Set<StateEnum> gameStatesVisited,
                             List<StateObjectWrapper> stateObjects) {
        for (int i = 0; i < indent; i++)
            if (i % 2 == 0) System.out.print("|");
            else System.out.print(" ");
        System.out.println("__" + startState + "__");
        gameStatesVisited.add(startState);
        indent++;
        for (StateObjectWrapper stateObject : stateObjects) {
            for (int i = 0; i < indent; i++)
                if (i % 2 == 0) System.out.print("|");
                else System.out.print(" ");
            System.out.print(".");
            System.out.print(stateObject.getStateObject().getName());
            Set<StateEnum> nextStates = getNextStates(stateObject, "state object");
            if (nextStates.isEmpty()) System.out.println();
            for (StateEnum stateName : nextStates) {
                if (gameStatesVisited.contains(stateName)) {
                    System.out.println("->" + stateName);
                    //stateObject.getTreeItem().getChildren().add(new TreeItem(gameStateEnum)); //add just the name
                } else {
                    System.out.println();
                    //stateObject.getTreeItem().getChildren().add(gameStateRepository.get(gameStateEnum).newTreeItem());
                    prepareObjectListsAndRecurseTree(
                            stateFinder.getAllActiveStates(stateName), indent, gameStatesVisited);
                }
            }
        }
    }

     */

}
