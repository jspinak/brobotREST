package com.brobot.brobotREST.actions;

import com.brobot.brobotREST.database.primitives.match.Matches;

public interface ActionInterface {

    Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections);
}
