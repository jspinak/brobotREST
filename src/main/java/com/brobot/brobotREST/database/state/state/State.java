package com.brobot.brobotREST.database.state.state;

import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateLocation;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateRegion;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateString;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.primatives.enums.StateGroup;
import com.brobot.brobotREST.web.services.StateService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
public class State {

    private StateEnum name;
    // StateText is text that appears on the screen and is a clue to look for images in this state.
    // Text search is a lot faster than image search, but cannot be used without an image search to identify a state.
    private Set<String> stateText = new HashSet<>();
    private Set<StateImageObject> stateImages = new HashSet<>();
    // StateStrings can change the expected state. They have associated regions where typing the string has an effect.
    private Set<StateString> stateStrings = new HashSet<>();
    // StateRegions can change the expected state when clicked or hovered over. They can also perform text retrieval.
    private Set<StateRegion> stateRegions = new HashSet<>();
    // CoexistingStates are states that can appear at the same time as this state, either over, under, or to the side.
    // CoexistingStates can always appear with one state when the reverse is not true. They should be defined for each state.
    private Set<StateLocation> stateLocations = new HashSet<>();
    private StateGroup group; // states in a group cannot coexist
    private CoexistingStates coexistingStates = new CoexistingStates();
    // previous state is set not necessarily with the 'from' state in the transition, but with
    // the state in the previous active states that is in the same StateGroup as the newly active state.
    // for example, a state calls a coexisting menu state that replaces it with a new state
    private StateEnum previousState;
    private int pathScore = 1; // larger path scores prohibit taking a path with this state
    private LocalDate lastAccessed;
    private LocalTime lastTimeAccessed;
    private int probabilityExists = 0; // probability that the state exists. used for mocks.

    public void setSearchRegionForAllImages(Region searchRegion) {
        stateImages.forEach(imageObj -> imageObj.setSearchRegion(searchRegion));
    }

    public void setPreviousState(StateEnum previousState) {
        this.previousState = previousState;
    }

    public Optional<StateEnum> getPreviousState() {
        return Optional.ofNullable(previousState);
    }

    public boolean hasCoexistingState(StateEnum stateEnum) {
        return coexistingStates.getAllStateEnums().contains(stateEnum);
    }

    public static class Builder {

        private StateEnum name;
        private Set<String> stateText = new HashSet<>();
        private Set<StateImageObject> stateImages = new HashSet<>();
        private Set<StateString> stateStrings = new HashSet<>();
        private Set<StateRegion> stateRegions = new HashSet<>();
        private Set<StateLocation> stateLocations = new HashSet<>();
        private StateGroup group;
        private CoexistingStates coexistingStates = new CoexistingStates();
        private int pathScore = 1;
        private LocalDate lastAccessed;
        private LocalTime lastTimeAccessed;

        public Builder(StateEnum stateEnum) {
            this.name = stateEnum;
        }

        public Builder withText(String... stateText) {
            Collections.addAll(this.stateText, stateText);
            return this;
        }

        public Builder withImages(StateImageObject... stateImageObjects) {
            Collections.addAll(this.stateImages, stateImageObjects);
            return this;
        }

        public Builder withStrings(StateString... stateStrings) {
            Collections.addAll(this.stateStrings, stateStrings);
            return this;
        }

        public Builder withRegions(StateRegion... stateRegions) {
            Collections.addAll(this.stateRegions, stateRegions);
            return this;
        }

        public Builder withLocations(StateLocation... stateLocations) {
            Collections.addAll(this.stateLocations, stateLocations);
            return this;
        }

        public Builder inGroup(StateGroup group) {
            this.group = group;
            return this;
        }

        public Builder withCoexistingState(StateEnum coexistingState, boolean alwaysPresent) {
            coexistingStates.addCoexistingState(coexistingState, alwaysPresent);
            return this;
        }

        public Builder blocksCoexistingStates() {
            coexistingStates.setBlocksCoexistingStates(true);
            return this;
        }

        public Builder setPathScore(int score) {
            pathScore = score;
            return this;
        }

        public State build() {
            State state = new State();
            state.name = name;
            state.stateText = stateText;
            for (StateImageObject image : stateImages) image.setOwnerStateName(name);
            for (StateString string : stateStrings) string.setOwnerStateName(name);
            for (StateRegion region : stateRegions) region.setOwnerStateName(name);
            state.stateImages = stateImages;
            state.stateStrings = stateStrings;
            state.stateRegions = stateRegions;
            state.stateLocations = stateLocations;
            state.group = group;
            state.coexistingStates = coexistingStates;
            return state;
        }
    }


}
