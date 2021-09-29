package com.brobot.brobotREST.manageStates;

import com.brobot.brobotREST.database.state.state.CoexistingStatesJointTable;
import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.brobot.brobotREST.manageStates.StateMemory.Enum.PREVIOUS;

@Component
public class PathFinder {

    /*
    the item-options-stars scenario:
    a series of states, of which only one is visible at a time, exists in different states
    they cannot be linked as 100% coexisting unless a separate copy is made for each coexisting state

    my chosen solution:
    create a State without identifying images (call it Stars)
    add Stars as a 100% always visible coexisting state to all the states it appears in
    the finishTransition method of Stars should transition to one of the specific star states
    based on recognizing the specific state images. the probability of these images appearing (for the mock)
    can be specified in the finishTransition method.

    other potential ways of dealing with this scenario:
    1) replicate the star-image-states for each coexisting state (duplicate code is almost always an ugly solution)
    2) a state group is linked to a state and can coexist 100% of the time for all coexisting states.
    in real execution, the correct state from the group will need to be found.
    in mocks, the correct state will be determined through probability and added to the transitions table.
    3) have different groups of linked states that do not connect to each other. instead of having 1
    PathFinder and 1 StateTransitionsManagement, have each of these classes for each group of linked states
    4) have code that imitates states and transitions but does not keep track of the current state. this code
    could be used for small independent networks

    item-options-stars is even complexer. there is no 5* for titans but there is one for relics.
    everything else is the same except for this, meaning that there is a transition ALL_STARS->FOUR_STARS
    for titans and a transition ALL_STARS->FIVE_STARS for relics.
    one possibility is to add a transition method that accepts a function parameter in addition to the standard
    StateEnum, and pass an equation that evaluates based on the active or recent states

    relic-titan-options-closed case: this is an example of a need for hierarchy or composition with states.
    the same images will open different options menus
    tita-options, after a titan type has been selected and then the options menu closed, has possibly the
    selected-titan-type icon visible as the open button. if the menu is open when searching for the closed menu,
    and this image is a fixed StateImageObject in the closed menu, it will find it in the wrong place.
     */

    private StateTransitionsJointTable stateTransitionsJointTable;
    private CoexistingStatesJointTable coexistingStatesJointTable;
    private StateService stateService;

    private Set<StateEnum> startStates;
    private List<Path> paths;

    public PathFinder(StateTransitionsJointTable stateTransitionsJointTable,
                      CoexistingStatesJointTable coexistingStatesJointTable, StateService stateService) {
        this.stateTransitionsJointTable = stateTransitionsJointTable;
        this.coexistingStatesJointTable = coexistingStatesJointTable;
        this.stateService = stateService;
    }

    // finds a path backward from targetState to a startState
    // Sets don't retain order, Lists do -> change to LinkedHashSet, which keeps order like a list
    public List<Path> getPathsToState(Set<StateEnum> startStates, StateEnum targetState) {
        System.out.print("\nfind path: " + startStates + " -> " + targetState);
        this.startStates = startStates;
        // target states include all states where the target state is always present
        Set<StateEnum> targetStates = coexistingStatesJointTable.getReverseCoexistingAlwaysPresent(targetState);
        targetStates.add(targetState);
        paths = new ArrayList<>();
        targetStates.forEach(target -> recursePath(new Path(), target));
        if (paths.isEmpty()) System.out.print("\npath to state not found| ");
        paths.sort(Comparator.comparing(Path::getScore));
        printPaths();
        return paths;
    }

    private void recursePath(Path path, StateEnum currentState) {
        if (!path.contains(currentState)) {
            path.add(currentState);
            if (startStates.contains(currentState)) { // a path is found
                Path successfulPath = path.getCopy();
                successfulPath.reverse();
                setPathScore(successfulPath);
                paths.add(successfulPath);
            } else { // continue searching
                Set<StateEnum> parentStates = stateTransitionsJointTable.getStatesWithTransitionsTo(currentState);
                for (StateEnum newState : parentStates) {
                    recursePath(path, newState);
                }
            }
        }
        if (path.get(path.size() - 1) == currentState) path.remove(currentState); // otherwise it's circular
    }

    private void setPathScore(Path path) {
        int score = 0;
        for (StateEnum stateEnum : path.getPath()) {
            Optional<State> state = stateService.findByName(stateEnum);
            if (state.isPresent()) score += state.get().getPathScore();
        }
        path.setScore(score);
    }

    public void printPaths() {
        if (paths.isEmpty()) return;
        System.out.println("\n__paths found__");
        paths.forEach(path -> {
            path.print();
            if (path != paths.get(paths.size()-1)) System.out.println();
        });
    }

}
