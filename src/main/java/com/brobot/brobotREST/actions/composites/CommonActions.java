package com.brobot.brobotREST.actions.composites;

import com.brobot.brobotREST.actions.Action;
import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.actions.ObjectCollection;
import com.brobot.brobotREST.database.primitives.image.Image;
import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.location.Position;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateRegion;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import com.brobot.brobotREST.manageStates.StateMemory;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.BooleanSupplier;

import static com.brobot.brobotREST.actions.ActionOptions.Action.*;
import static com.brobot.brobotREST.manageStates.UnknownState.Enum.UNKNOWN;

@Component
public class CommonActions {

    private Action action;
    private StateService stateService;
    private StateMemory stateMemory;

    public CommonActions(Action action, StateService stateService, StateMemory stateMemory) {
        this.action = action;
        this.stateService = stateService;
        this.stateMemory = stateMemory;
    }

    public boolean click(double maxWait, StateImageObject... stateImageObjects) {
        return action.perform(
                new ActionOptions.Builder()
                        .setAction(ActionOptions.Action.CLICK)
                        .setMaxWait(maxWait)
                        .build(),
                stateImageObjects).isSuccess();
    }

    public boolean click(Region region) {
        return click(new Location(region.getTarget()));
    }

    public boolean click(Location location) {
        return action.perform(
                new ActionOptions.Builder()
                        .setAction(ActionOptions.Action.CLICK)
                        .build(),
                new ObjectCollection.Builder()
                        .withLocations(location)
                        .build())
                .isSuccess();
    }

    public boolean clickImageUntilItVanishes(int timesToClick, double pauseBetweenClicks, StateImageObject image) {
        ActionOptions actionOptions = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.CLICK)
                .clickUntil(ActionOptions.ClickUntil.OBJECTS1_VANISH)
                .setPauseBetweenActions(pauseBetweenClicks)
                .setPauseBetweenActionRepetitions(pauseBetweenClicks)
                .timesToRepeatActionUntilConditionIsMet(timesToClick)
                .build();
        ObjectCollection objectCollection = new ObjectCollection.Builder()
                .withImages(image)
                .build();
        return action.perform(actionOptions, objectCollection).isSuccess();
    }

    public boolean waitVanish(double wait, StateImageObject... images) {
        ActionOptions vanish = new ActionOptions.Builder()
                .setAction(VANISH)
                .setMaxWait(wait)
                .build();
        return action.perform(vanish, images).isSuccess();
    }

    public boolean find(double maxWait, Image image) {
        return find(maxWait, image.inNullState());
    }

    public boolean find(double maxWait, StateImageObject stateImageObject) {
        return action.perform(
                new ActionOptions.Builder()
                        .setAction(FIND)
                        .setMaxWait(maxWait)
                        .build(),
                stateImageObject).isSuccess();
    }

    public boolean waitState(double maxWait, StateEnum stateEnum, ActionOptions.Action actionType) {
        if (stateEnum == UNKNOWN) return true;
        Optional<State> state = stateService.findByName(stateEnum);
        if (state.isEmpty()) return false;
        return action.perform(
                new ActionOptions.Builder()
                        .setAction(actionType)
                        .setMaxWait(maxWait)
                        .build(),
                new ObjectCollection.Builder()
                        .withAllStateImages(state.get())
                        .build())
                .isSuccess();
    }

    public boolean findState(double maxWait, StateEnum stateEnum) {
        System.out.print("\n__findState:"+stateEnum+"__ ");
        stateService.findByName(stateEnum).ifPresent(
                state -> System.out.println("prob="+state.getProbabilityExists()));
        return waitState(maxWait, stateEnum, FIND);
    }

    public boolean waitVanishState(double maxWait, StateEnum stateEnum) {
        System.out.print("\n__waitVanishState:"+stateEnum+"__ ");
        return waitState(maxWait, stateEnum, VANISH);
    }

    public void highlightRegion(double seconds, StateRegion region) {
        highlightRegion(seconds, region.getSearchRegion());
    }
    public void highlightRegion(double seconds, Region region) {
        ActionOptions actionOptions = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.HIGHLIGHT)
                .setHighlightSeconds(seconds)
                .build();
        ObjectCollection objectCollection = new ObjectCollection.Builder()
                .withRegions(region)
                .build();
        action.perform(actionOptions, objectCollection);
    }

    public boolean clickUntilStateAppears(int repeatClickTimes, double pauseBetweenClicks,
                                          StateImageObject objectToClick, StateEnum state) {
        if (!stateService.findByName(state).isPresent()) return false;
        ActionOptions actionOptions = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.CLICK)
                .clickUntil(ActionOptions.ClickUntil.OBJECTS2_APPEAR)
                .timesToRepeatActionUntilConditionIsMet(repeatClickTimes)
                .setPauseBetweenActions(pauseBetweenClicks)
                .build();
        ObjectCollection objectsToClick = new ObjectCollection.Builder()
                .withImages(objectToClick)
                .build();
        ObjectCollection objectsToAppear = new ObjectCollection.Builder()
                .withAllStateImages(stateService.findByName(state).get())
                .build();
        return action.perform(actionOptions, objectsToClick, objectsToAppear).isSuccess();
    }

    public boolean clickUntilImageAppears(int repeatClickTimes, double pauseBetweenClicks,
                                          StateImageObject toClick, StateImageObject toAppear) {
        ActionOptions actionOptions = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.CLICK)
                .clickUntil(ActionOptions.ClickUntil.OBJECTS2_APPEAR)
                .timesToRepeatActionUntilConditionIsMet(repeatClickTimes)
                .setPauseBetweenActions(pauseBetweenClicks)
                .build();
        ObjectCollection objectsToClick = new ObjectCollection.Builder()
                .withImages(toClick)
                .build();
        ObjectCollection objectsToAppear = new ObjectCollection.Builder()
                .withImages(toAppear)
                .build();
        return action.perform(actionOptions, objectsToClick, objectsToAppear).isSuccess();
    }

    public boolean drag(Location from, Location to) {
        ActionOptions actionOptions = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.DRAG)
                .build();
        ObjectCollection fromOC = new ObjectCollection.Builder()
                .withLocations(from)
                .build();
        ObjectCollection toOC = new ObjectCollection.Builder()
                .withLocations(to)
                .build();
        return action.perform(actionOptions, fromOC, toOC).isSuccess();
    }

    public void dragCenterToOffset(Region region, int plusX, int plusY) {
        dragCenterOfRegion(region, region.getCenter().x + plusX, region.getCenter().y + plusY);
    }

    public void dragCenterOfRegion(Region region, int toX, int toY) {
        ActionOptions drag = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.DRAG)
                .fromObjectType(ActionOptions.ObjectType.REGION)
                .toObjectType(ActionOptions.ObjectType.LOCATION)
                .build();
        ObjectCollection from = new ObjectCollection.Builder()
                .withRegions(region)
                .build();
        ObjectCollection to = new ObjectCollection.Builder()
                .withLocations(new Location(region.getCenter(), toX, toY))
                .build();
        action.perform(drag, from, to);
    }

    public void dragCenterToOffsetStop(Region region, int plusX, int plusY) {
        dragCenterStop(region,region.getCenter().x + plusX, region.getCenter().y + plusY);
    }

    public void dragCenterStop(Region region, Location location) {
        dragCenterStop(region, location.getX(), location.getY());
    }

    public void dragCenterStop(Region region, int toX, int toY) {
        ActionOptions drag = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.DRAG)
                .fromObjectType(ActionOptions.ObjectType.REGION)
                .toObjectType(ActionOptions.ObjectType.LOCATION)
                .setDelayBeforeDrop(.8)
                .build();
        ObjectCollection from = new ObjectCollection.Builder()
                .withRegions(region)
                .build();
        ObjectCollection to = new ObjectCollection.Builder()
                .withLocations(new Location(region.getCenter(), toX, toY))
                .build();
        action.perform(drag, from, to);
    }

    public String getText(Region region) {
        return getText(region.inNullState());
    }

    public String getText(StateRegion region) {
        ActionOptions getText = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.GET_TEXT)
                .getTextUntil(ActionOptions.GetTextUntil.TEXT_APPEARS)
                .timesToRepeatActionUntilConditionIsMet(3)
                .setPauseBetweenActionRepetitions(.5)
                .build();
        ObjectCollection textRegion = new ObjectCollection.Builder()
                .withRegions(region)
                .build();
        return action.perform(getText, textRegion).getText();
    }

    public boolean clickXTimes(int times, double pause, StateImageObject objectToClick) {
        ActionOptions click = new ActionOptions.Builder()
                .setAction(CLICK)
                .setNumberOfActions(times)
                .setPauseBetweenActions(pause)
                .build();
        return action.perform(click, objectToClick).isSuccess();
    }

    public boolean clickXTimes(int times, double pause, Region reg) {
        ActionOptions click = new ActionOptions.Builder()
                .setAction(CLICK)
                .setNumberOfActions(times)
                .setPauseBetweenActions(pause)
                .build();
        ObjectCollection regs = new ObjectCollection.Builder()
                .withRegions(reg)
                .build();
        return action.perform(click, regs).isSuccess();
    }

    public boolean moveMouseTo(Location location) {
        ActionOptions move = new ActionOptions.Builder()
                .setAction(MOVE)
                .build();
        ObjectCollection loc = new ObjectCollection.Builder()
                .withLocations(location)
                .build();
        return action.perform(move, loc).isSuccess();
    }

    public boolean swipeToOppositePosition(Region region, Position grabPosition) {
        Location grabLocation = new Location(region, grabPosition);
        ActionOptions drag = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.DRAG)
                .pauseAfterEnd(.5)
                .build();
        ObjectCollection from = new ObjectCollection.Builder()
                .withLocations(grabLocation)
                .build();
        ObjectCollection to = new ObjectCollection.Builder()
                .withLocations(grabLocation.getOpposite())
                .build();
        return action.perform(drag, from, to).isSuccess();
    }

    public void setStateProbabilities(int probability, StateEnum... stateEnums) {
        for (StateEnum stateEnum : stateEnums) {
            stateService.findByName(stateEnum).ifPresent(state -> state.setProbabilityExists(probability));
        }
    }

    public boolean gotoPreviousState(StateEnum currentState, BooleanSupplier booleanSupplier) {
        if (!booleanSupplier.getAsBoolean()) return false;
        stateService.findByName(currentState)
                .flatMap(State::getPreviousState)
                .flatMap(prev -> stateService.findByName(prev))
                .ifPresent(prevState -> prevState.setProbabilityExists(100));
        return true;
    }

}
