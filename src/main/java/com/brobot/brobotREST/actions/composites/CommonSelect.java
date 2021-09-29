package com.brobot.brobotREST.actions.composites;

import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.location.Position;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CommonSelect {

    private Select select;

    public CommonSelect(Select select) {
        this.select = select;
    }

    public SelectActionObject select(Region swipeRegion, ActionOptions.Find findType, int clicksPerImage,
                                     Position swipeDirection, int maxSwipes, StateImageObject... images) {
        return select(List.of(images), new ArrayList<>(), swipeRegion, findType, clicksPerImage,
                swipeDirection, maxSwipes);
    }

    public SelectActionObject select(List<StateImageObject> images, List<StateImageObject> confirmationImages,
                          Region swipeRegion, ActionOptions.Find findType, int clicksPerImage,
                          Position swipeDirection, int maxSwipes) {
        Location swipeTo = new Location(swipeRegion, swipeDirection);
        Location swipeFrom = swipeTo.getOpposite();
        ObjectCollection swipeFromObjColl = new ObjectCollection.Builder()
                .withLocations(swipeFrom)
                .build();
        ObjectCollection swipeToObjColl = new ObjectCollection.Builder()
                .withLocations(swipeTo)
                .build();
        ActionOptions swipeActionOptions = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.DRAG)
                .setDelayBeforeDrag(.5)
                .build();
        ObjectCollection findObjectCollection = new ObjectCollection.Builder()
                .withImages(images)
                .build();
        ActionOptions findActionOptions = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.FIND)
                .find(findType)
                .build();
        ActionOptions clickActionOptions = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.CLICK)
                .setNumberOfActions(clicksPerImage)
                .setPauseBetweenActions(.7)
                .build();
        ObjectCollection confirmationObjectCollection = new ObjectCollection.Builder()
                .withImages(confirmationImages)
                .build();
        ActionOptions confirmActionOptions = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.FIND)
                .build();
        SelectActionObject selectActionObject = new SelectActionObject.Builder()
                .setSwipeFromObjColl(swipeFromObjColl)
                .setSwipeToObjColl(swipeToObjColl)
                .setSwipeActionOptions(swipeActionOptions)
                .setFindObjectCollection(findObjectCollection)
                .setFindActionOptions(findActionOptions)
                .setClickActionOptions(clickActionOptions)
                .setConfirmationObjectCollection(confirmationObjectCollection)
                .setConfirmActionOptions(confirmActionOptions)
                .setMaxSwipes(maxSwipes)
                .build();
        select.select(selectActionObject);
        return selectActionObject;
    }
}
