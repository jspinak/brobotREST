package com.brobot.brobotREST.database.state.state;

import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.primatives.enums.StateGroup;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CoexistingStates {

    /*
    __blocking__
        - blocking a state means that the blocked state can no longer be acted on, even though some images may exist
        - this is important to StateFinder because to go to another state the blocking state needs to be acted on first
    __examples of__
    blocks/always present: a pop-up that needs to be acted on blocks its underlying state
    blocks/not always present: a pop-up that needs to be acted on but can be called by different underlying states
    not blocking/always present: menu bar
    not blocking/not always present: inventory in a game that can be called with a key press from any screen
        - even if it's not blocking, i.e. the underlying state can be acted on, it could still block key
        images that are important for navigation
        - these cases need to be solved by the transition methods of the underlying state (if necessary look for the
        coexisting state and close it)
     */

    private Map<StateEnum, Boolean> coexistingStates = new HashMap<>();
    private boolean blocksCoexistingStates = false;

    public void addCoexistingState(StateEnum name, boolean alwaysPresent) {
        coexistingStates.put(name, alwaysPresent);
    }

    public boolean isAlwaysPresent(StateEnum name) {
        return coexistingStates.get(name);
    }

    public boolean contains(StateEnum name) {
        return coexistingStates.containsKey(name);
    }

    public Set<StateEnum> getAllStateEnums() {
        return coexistingStates.keySet();
    }

    public Set<StateEnum> getStatesAlwaysPresent() {
        return coexistingStates.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
