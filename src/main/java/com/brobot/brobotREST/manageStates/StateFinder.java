package com.brobot.brobotREST.manageStates;

import com.brobot.brobotREST.actions.CommonActions;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.brobot.brobotREST.manageStates.UnknownState.Enum.UNKNOWN;

@Component
public class StateFinder {

    private CommonActions commonActions;
    private StateService stateService;
    private StateMemory stateMemory;

    public StateFinder(CommonActions commonActions, StateService stateService, StateMemory stateMemory) {
        this.commonActions = commonActions;
        this.stateService = stateService;
        this.stateMemory = stateMemory;
    }

    // a person would notice a different state because she would see all images and classify them
    // a program could do this with machine learning, but not with the current functionality of brobot
    // brobot needs to search for images to classify states. searching for every image is costly (timewise).
    // to make brobot more efficient, it needs to search first for images that are likely to appear.
    // to make brobot effective, it needs to find the correct state and may have to search for all images at some point.
    // special case: looking for states on top of other states.
    // if we search for a state in a method (as opposed to StateFinder),
    // it will set the new current state as this state without looking for coexisting states
    // for this reason, StateFinder should always look for states potentially on top
    // - faster methods can be called directly within methods
    // - if there is a blocking state it needs to be acted on first
    public Set<StateEnum> getActiveStates() {
        Set<StateEnum> activeStates = new HashSet<>(stateMemory.getActiveStates());
        Set<StateEnum> activeBlockingStates = new HashSet<>();
        Set<StateEnum> nonActiveStates = new HashSet<>();
        new HashSet<>(stateMemory.getActiveStates()).forEach(state -> {
            if (!commonActions.findState(.2, state)) {
                activeStates.remove(state);
                nonActiveStates.add(state);
        }});
        Set<StateEnum> coexistingStates = stateService.getAllCoexistingStates(activeStates);
        stateService.getBlockingStates(coexistingStates).forEach(blockingState -> {
            if (commonActions.findState(.2, blockingState)) {
                activeStates.add(blockingState);
                activeBlockingStates.add(blockingState);
            } else nonActiveStates.add(blockingState);
        });
        if (!activeBlockingStates.isEmpty()) return activeBlockingStates;
        nonActiveStates.forEach(coexistingStates::remove);
        stateService.getNonBlockingStates(coexistingStates).forEach(nonBlockingState -> {
            if (commonActions.findState(.2, nonBlockingState)) {
                activeStates.add(nonBlockingState);
            } else nonActiveStates.add(nonBlockingState);
        });
        return activeStates;
    }

    public Set<StateEnum> getAllActiveStates() {
        Set<StateEnum> activeStates = stateMemory.getActiveStates(); //getActiveStates();
        if (!activeStates.isEmpty()) return activeStates;
        activeStates = searchAllImagesForCurrentStates();
        if (!activeStates.isEmpty()) return activeStates;
        activeStates.add(UNKNOWN);
        return activeStates;
    }

    // make app specific by including a variable <app> in StateRIP and a .filter method in StateRIPRepository
    // you could also have a priority value for states
    // some StateRIPs are standalone and don't define a state
    private Set<StateEnum> searchAllImagesForCurrentStates() {
        System.out.println("StateFinder: search all states| ");
        Set<StateEnum> activeStates = new HashSet<>();
        for (StateEnum state : stateService.findAllStateEnums()) {
            System.out.print(".");
            if (commonActions.findState(.2, state))
                activeStates.add(state);
        }
        return activeStates;
    }

}
