package com.brobot.brobotREST.actions.methods.find;

import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import org.sikuli.script.Match;

import java.util.Optional;

public interface FindImageObject {

    Optional<Match> first(ActionOptions actionOptions, StateImageObject stateImage);
    Matches all(ActionOptions actionOptions, StateImageObject stateImage);
}
