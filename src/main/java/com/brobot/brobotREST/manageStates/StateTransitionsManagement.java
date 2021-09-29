package com.brobot.brobotREST.manageStates;

import com.brobot.brobotREST.actions.composites.CommonActions;
import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import com.brobot.brobotREST.web.services.StateTransitionsService;
import org.springframework.stereotype.Component;

import java.util.*;

/* problem with the open function in state management is that you can't always go from one state
to another just by a simple click on the image. for this reason you might want to create an interface
that allows for a method call on a StateImage and StateRIP. this would make the StateImage and StateRIP
no longer a POJO, and would make it difficult to store as easily in a database.

in addition, there are some states that do not know where
their images will go because they are general states (such as Phone, which can be used for many apps). in
order for the state to know where it's object lead,
 you will need to create a new app specific phone state and include all
images important to path management in the specific state.

there is also variability in state management. clicking a button may lead to different outcomes. you'll need
to include probabilities with state paths. obviously, when you have a database a lot of this information can
be obtained by running the program (not on mock) and saved for future mock runs.

StateActivity interface: classes that implement this interface would need to provide:
- entry into a map of State:StateActivity (this class is then responsible for the methods acting on this State
    this map will be used by the stateFinder and pathFinder classes to retrieve the correct StateActivity class
    map can be StateEnum:StateActivity
- a method that takes you to one of the states we can reach from the associated State
this allows you to get rid of some of the wrapper methods - the ones that take you to a new state
with these changes the preferred method for writing a process flow would be to use the StateManagement methods
- goto method that checks the current state (and if not found, finds it), and takes you to the desired state
with this methodology you will always get to the state you want (or know that there is an unidentified error)

 */

@Component
public class StateTransitionsManagement {

    private StateFinder stateFinder;
    private StateTransitionsService stateTransitionsService;
    private PathFinder pathFinder;
    private StateMemory stateMemory;
    private StateService stateService;
    private CommonActions commonActions;

    Set<StateEnum> activeStates;
    List<Path> pathsVisited;

    public StateTransitionsManagement(StateFinder stateFinder,
                                      StateTransitionsService stateTransitionsService,
                                      PathFinder pathFinder,
                                      StateMemory stateMemory,
                                      StateService stateService,
                                      CommonActions commonActions) {
        this.stateFinder = stateFinder;
        this.stateTransitionsService = stateTransitionsService;
        this.pathFinder = pathFinder;
        this.stateMemory = stateMemory;
        this.stateService = stateService;
        this.commonActions = commonActions;
    }

    public boolean openState(StateEnum stateToOpen) {
        System.out.print("\nopen state "+stateToOpen);
        activeStates = stateFinder.getAllActiveStates();
        if (activeStates.contains(stateToOpen)) return true;
        List<Path> paths = pathFinder.getPathsToState(activeStates, stateToOpen);
        pathsVisited = new ArrayList<>();
        while (thereAreNewPaths(paths)) {
            if (moveThroughStateChain(paths, stateToOpen)) {
                stateMemory.getActiveStates().forEach(
                        activeState -> commonActions.findState(.1, activeState));
                System.out.print("\nactiveStates:"+stateMemory.getActiveStates());
                return true;
            }
            paths = pathFinder.getPathsToState(activeStates, stateToOpen);
        }
        System.out.println("\nAll paths tried, open failed.");
        return false;
    }

    private boolean thereAreNewPaths(List<Path> paths) {
        for (Path path : paths) if (!pathVisited(path)) return true;
        return false;
    }

    private Set<StateEnum> allStatesInAllPaths(List<List<StateEnum>> paths) {
        Set<StateEnum> allStatesInAllPaths = new HashSet<>();
        paths.forEach(allStatesInAllPaths::addAll);
        return allStatesInAllPaths;
    }

    private boolean pathVisited(Path path) {
        for (Path pathVisited : pathsVisited) {
            if (path.equals(pathVisited)) return true;
        }
        return false;
    }

    /* 1. get the path to the state
       2. do method in a StateActivities class to get to next state
       3. keep doing methods until desired state is reached
     */
    private boolean moveThroughStateChain(List<Path> paths, StateEnum stateToOpen) {
        if (paths == null || paths.isEmpty()) return false;
        for (Path path : paths) {
            if (!pathVisited(path)) { // if path visited, get the next path
                if (path.size() == 1 && path.get(0) == stateToOpen) return true;
                for (int i = 0; i < path.size() - 1; i++) {
                    //System.out.println("\npath size = "+path.size()+" current state = "+path.get(i));
                    if (!stateTransition(path.get(i), path.get(i + 1))) break;
                    activeStates.add(path.get(i + 1));
                    if (i + 2 == path.size()) return true;
                }
                pathsVisited.add(path);
                return false; // path traversed, but not successfully. check current states and do again.
            }
        }
        return false; // all paths were visited
    }

    // toStateEnum will never be PREVIOUS here: FindPath needs to know which state PREVIOUS stands for when
    // it makes the path. in order for the transition to occur, the named previous state must be
    // given as PREVIOUS to the transitionFrom method.
    private boolean stateTransition(StateEnum fromStateEnum, StateEnum toStateEnum) {
        if (fromStateEnum == null || toStateEnum == null) return true;
        Optional<StateTransitions> optTargetStateTransitions = stateTransitionsService.getTransitions(toStateEnum);
        if (!optTargetStateTransitions.isPresent()) {
            System.out.print("'to state' "+toStateEnum+" not a valid transition| ");
            return false;
        }
        Set<StateEnum> activeStatesBeforeTransition = new HashSet<>(stateFinder.getAllActiveStates());
        if (stateTransitionsService.doTransition(fromStateEnum, toStateEnum) &&
                optTargetStateTransitions.get().finishOpen()) {
            System.out.format("\ntransition %s->%s successful", fromStateEnum, toStateEnum);
            setPreviousState(toStateEnum, activeStatesBeforeTransition);
            return true;
        }
        System.out.format("\ntransition %s->%s not successful", fromStateEnum, toStateEnum);
        return false;
    }

    private boolean setPreviousState(StateEnum stateToSet, Set<StateEnum> activeStatesBeforeTransition) {
        Optional<State> optStateToSet = stateService.findByName(stateToSet);
        if (!optStateToSet.isPresent()) return false;
        if (optStateToSet.get().getGroup() == null) return false;
        Optional<StateEnum> formerPreviousState = optStateToSet.get().getPreviousState();
        Set<StateEnum> statesInGroup = stateService.getAllStateEnumsInGroup(stateToSet);
        //System.out.println("\nstates in group: "+statesInGroup);
        //System.out.println("\n new state = "+stateToSet);
        //System.out.println("\n active states before transition = "+activeStatesBeforeTransition);
        for (StateEnum previousState : activeStatesBeforeTransition) {
            if (statesInGroup.contains(previousState) && previousState != stateToSet) {
                optStateToSet.get().setPreviousState(previousState);
                stateTransitionsService.setPreviousState(stateToSet, previousState, formerPreviousState);
                return true;
            }
        }
        return false;
    }

}
