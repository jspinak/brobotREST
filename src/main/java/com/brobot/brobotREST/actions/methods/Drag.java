package com.brobot.brobotREST.actions.methods;

import com.brobot.brobotREST.actions.ActionInterface;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.actions.methods.find.Find;
import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.actions.methods.wrappers.DragLocation;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Drag implements ActionInterface {

    private final Find find;
    private final DragLocation dragLocation;

    private Matches matches;

    public Drag(Find find, DragLocation dragLocation) {
        this.find = find;
        this.dragLocation = dragLocation;
    }

    /*
    if images are supplied for both object collections, the action options will be applied to both find operations
    usually, a drag includes only one image: from dragging or for dropping.
    two images is a more complex problem, and it is recommended to do a find operation for the drop image,
    and then use the found location as the dragTo location for the drag operation.
     */

    public Matches perform(ActionOptions actionOptions, ObjectCollection... objectCollections) {
        matches = new Matches();
        if (objectCollections.length == 0) return matches;
        Optional<Location> dragFrom = getLocation(actionOptions.getFromType(), actionOptions, objectCollections[0]);
        Optional<Location> dragTo = Optional.empty();
        if (objectCollections.length > 1)
            dragTo = getLocation(actionOptions.getToType(), actionOptions, objectCollections[1]);
        if (objectCollections.length == 1) {
            if (!dragFrom.isPresent()) return matches;
            // if the dragTo point is be the same as the dragFrom point: it is like a click, but allow it
            dragTo = Optional.of(new Location(dragFrom.get()));
        }
        if (!dragFrom.isPresent() || !dragTo.isPresent()) return matches; // default status is false
        matches.setSuccess(dragLocation.drag(dragFrom.get(), dragTo.get(), actionOptions));
        return matches;
    }

    private Optional<Location> getLocation(ActionOptions.ObjectType objectType,
                                           ActionOptions actionOptions, ObjectCollection objectCollection) {
        if (objectType == ActionOptions.ObjectType.LOCATION && !objectCollection.getStateLocations().isEmpty())
            return Optional.of(objectCollection.getStateLocations().get(0).getLocation());
        // to use a point in a Region other than the middle, create a new Location and add it to the ObjectCollection
        if (objectType == ActionOptions.ObjectType.REGION && !objectCollection.getStateRegions().isEmpty())
            return Optional.of(new Location(objectCollection.getStateRegions().get(0).getSearchRegion(),
                    50, 50));
        if (objectType == ActionOptions.ObjectType.IMAGE && !objectCollection.getStateImages().isEmpty()) {
            Matches newMatches = find.perform(actionOptions, objectCollection);
            matches.addAll(newMatches);
            Optional<MatchObject> bestMatch = newMatches.getBestMatch();
            return bestMatch.map(MatchObject::getLocation);
        }
        return Optional.empty();
    }

}
