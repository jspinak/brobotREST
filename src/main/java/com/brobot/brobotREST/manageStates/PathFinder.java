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
        path.remove(currentState);
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
