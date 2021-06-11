package com.brobot.brobotREST.actions;

import com.brobot.brobotREST.actions.methods.Click;
import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.SearchRegions;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import com.brobot.brobotREST.primatives.Pair;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import lombok.Data;
import org.sikuli.basics.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

@Data
public class ActionOptions {

    /*
    FIRST: first match found
    EACH: one match per Image (first match found)
    ALL: all matches for each filename in each Image, including overlapping matches
    BEST: the best match from all Images
     */
    public enum Find {
        FIRST, EACH, ALL, BEST, CUSTOM
    }

    /*
    FIRST: first match on each Image/RIP
    BEST: best match on each Image/RIP
     */
    public enum DoOnEach {
        FIRST, BEST
    }

    /*
    DRAG drags from Location/Region/RIP/Image to Location2/...
    DEFINE defines every StateRegion
     */
    public enum Action {
        FIND, CLICK, DRAG, DEFINE, TYPE, MOVE, VANISH, GET_TEXT, HIGHLIGHT
    }

    public enum ClickUntil {
        NO_CONDITION, OBJECTS2_APPEAR, OBJECTS1_VANISH, OBJECTS2_VANISH
    }

    public enum GetTextUntil {
        NO_CONDITION, TEXT_APPEARS, TEXT_VANISHES
    }

    public enum ObjectType {
        IMAGE, LOCATION, REGION
    }

    private Action action = Action.FIND;
    private BiFunction<ActionOptions, List<StateImageObject>, Matches> tempFind;
    private ClickUntil clickUntil = ClickUntil.NO_CONDITION;
    private Find find = Find.FIRST;
    private DoOnEach doOnEach = DoOnEach.FIRST;
    private Predicate<Matches> successEvaluation = matches -> !matches.isEmpty();

    private double similarity = Settings.MinSimilarity;
    private double delayBeforeMouseDown = Settings.DelayBeforeMouseDown;
    private double pauseAfterMouseUp = 0;
    private double clickDelay = Settings.ClickDelay;
    private Click.Type clickType = Click.Type.LEFT;
    private boolean moveMouseAfterClick = false;
    private Location locationAfterClick = new Location(0, 0);
    private Location offsetLocationBy = new Location(0, 0);
    private SearchRegions searchRegions = new SearchRegions(); // sets temporary search region(s) for the Images (but not RIPs)
    private Map<StateEnum, Integer> stateProbabilitiesAfterClick = new HashMap<>();

    private double pauseBeforeBegin = 0;
    private double pauseAfterEnd = 0;
    private double pauseBetweenActions = 0;
    private int numberOfActions = 1; // # of consecutive actions on the same match before moving to the next match
    private int timesToRepeatActionUntil = 1; // repeats the action group (i.e. the action group = 3 clicks per match)
    private double pauseBetweenActionRepetitions = 0; // action group: rename these to differentiate better between for example, clicks on different matches, and the repetition of clicks on different matches multiple times
    private boolean checkConditionAfterEachAction = true; // if there are multiple matches, check for the image to appear after each click. when false, it clicks all matches and then checks for the image.
    private double maxWait = 0;
    private int maxMatchesToActOn = 1; // for example, with click all, if max actions is 4 only the first 4 matches will be clicked.

    // __drag or move__
    private ObjectType fromType = ObjectType.IMAGE;
    private ObjectType toType = ObjectType.IMAGE;
    private double delayBeforeDrag = Settings.DelayBeforeDrag;
    private double delayBeforeDrop = Settings.DelayBeforeDrop;
    private float moveMouseDelay = Settings.MoveMouseDelay;
    private int dragToOffsetX; // offsets the match from objects1
    private int dragToOffsetY;

    // __define region__
    // INSIDE_ANCHORS defines the region as the smallest rectangle from the anchors found
    // OUTSIDE_ANCHORS defines the region as the largest rectangle from the anchors found
    public enum DefineAs {
        INSIDE_ANCHORS, OUTSIDE_ANCHORS, MATCH, BELOW_MATCH, ABOVE_MATCH, LEFT_OF_MATCH, RIGHT_OF_MATCH
    }

    private DefineAs defineAs = DefineAs.MATCH;
    private int addW = 0;
    private int addH = 0;
    private int absoluteW = -1; // when negative, not used (addW is used)
    private int absoluteH = -1;
    private int addX = 0; // also acts as a click location offset
    private int addY = 0; // also acts as a click location offset

    // __highlight__
    private boolean highlightAllAtOnce = false;
    private double highlightSeconds = 1;
    private String highlightColor = "red"; // see sikuli region.highlight() for more info

    // __get text__
    GetTextUntil getTextUntil = GetTextUntil.NO_CONDITION;

    // __type__
    private double typeDelay = Settings.TypeDelay;
    private boolean clickMatchBeforeTyping = false;

    private ActionOptions() {
    }

    public static class Builder {
        private Action action = Action.FIND;
        private BiFunction<ActionOptions, List<StateImageObject>, Matches> tempFind;
        private ClickUntil clickUntil = ClickUntil.NO_CONDITION;
        private Find find = Find.FIRST;
        private DoOnEach doOnEach = DoOnEach.FIRST;
        private Predicate<Matches> successEvaluation = matches -> !matches.isEmpty();
        private double similarity = Settings.MinSimilarity;
        private double delayBeforeMouseDown = Settings.DelayBeforeMouseDown;
        private double pauseAfterMouseUp = 0;
        private double clickDelay = Settings.ClickDelay;
        private Click.Type clickType = Click.Type.LEFT;
        private boolean moveMouseAfterClick = false;
        private Location locationAfterClick = new Location(0, 0);
        private Location offsetLocationBy = new Location(0, 0);
        private SearchRegions searchRegions = new SearchRegions();
        private Map<StateEnum, Integer> stateProbabilitiesAfterClick = new HashMap<>();
        private double pauseBeforeBegin = 0;
        private double pauseAfterEnd = 0;
        private double pauseBetweenActions = 0;
        private int numberOfActions = 1; // for example, clicks per match
        private int timesToRepeatActionUntil = 1;
        private double pauseBetweenActionRepetitions = 0; // action group: rename these to differentiate better between for example, clicks on different matches, and the repetition of clicks on different matches multiple times
        private boolean checkConditionAfterEachAction = true;
        private double maxWait = 0;
        private int maxMatchesToActOn = 1000;
        private int stateProbabilityOfObjects2IfActionSuccessful = -1;
        private ObjectType fromType = ObjectType.IMAGE;
        private ObjectType toType = ObjectType.IMAGE;
        private double delayBeforeDrag = Settings.DelayBeforeDrag;
        private double delayBeforeDrop = Settings.DelayBeforeDrop;
        private float moveMouseDelay = Settings.MoveMouseDelay;
        private int dragToOffsetX;
        private int dragToOffsetY;
        private DefineAs defineAs = DefineAs.MATCH;
        private int addW = 0;
        private int addH = 0;
        private int absoluteW = -1; // when negative, not used (addW is used)
        private int absoluteH = -1;
        private int addX = 0;
        private int addY = 0;
        private boolean highlightAllAtOnce = false;
        private double highlightSeconds = 1;
        private String highlightColor = "red";
        GetTextUntil getTextUntil = GetTextUntil.NO_CONDITION;
        private double typeDelay = Settings.TypeDelay;
        private boolean clickMatchBeforeTyping = false;

        public Builder() {
        }
        //public Builder(Action action) { this.action = action; }

        public Builder setAction(Action action) {
            this.action = action;
            return this;
        }

        public Builder useTempFind(BiFunction<ActionOptions, List<StateImageObject>, Matches> tempFind) {
            this.tempFind = tempFind;
            return this;
        }

        public Builder clickUntil(ClickUntil clickUntil) {
            this.clickUntil = clickUntil;
            return this;
        }

        public Builder find(Find find) {
            this.find = find;
            return this;
        }

        public Builder doOnEach(DoOnEach doOnEach) {
            this.doOnEach = doOnEach;
            return this;
        }

        public Builder setSuccessEvaluation(Predicate<Matches> successEvaluation) {
            this.successEvaluation = successEvaluation;
            return this;
        }

        public Builder setMinSimilarity(double similarity) {
            this.similarity = similarity;
            return this;
        }

        public Builder setDelayBeforeMouseDown(double delay) {
            this.delayBeforeMouseDown = delay;
            return this;
        }

        public Builder setPauseAfterMouseUp(double pause) {
            this.pauseAfterMouseUp = pause;
            return this;
        }

        public Builder setClickDelay(double delay) {
            this.clickDelay = delay;
            return this;
        }

        public Builder setClickType(Click.Type clickType) {
            this.clickType = clickType;
            return this;
        }

        public Builder moveMouseAfterClick() {
            this.moveMouseAfterClick = true;
            return this;
        }

        public Builder locationAfterClick(Location location) {
            this.locationAfterClick = location;
            return this;
        }

        public Builder setLocationAfterClickByOffset(int offsetX, int offsetY) {
            this.offsetLocationBy = new Location(offsetX, offsetY);
            return this;
        }

        public Builder addSearchRegion(Region searchRegion) {
            this.searchRegions.addSearchRegions(searchRegion);
            return this;
        }

        public Builder addStateProbabilityAfterClick(StateEnum stateEnum, int probability) {
            this.stateProbabilitiesAfterClick.put(stateEnum, probability);
            return this;
        }

        public Builder pauseBeforeBegin(double pause) {
            this.pauseBeforeBegin = pause;
            return this;
        }

        public Builder pauseAfterEnd(double pause) {
            this.pauseAfterEnd = pause;
            return this;
        }

        public Builder setPauseBetweenActions(double pause) {
            this.pauseBetweenActions = pause;
            return this;
        }

        public Builder setNumberOfActions(int numberOfActions) {
            this.numberOfActions = numberOfActions;
            return this;
        }

        public Builder timesToRepeatActionUntil(int repeats) {
            this.timesToRepeatActionUntil = repeats;
            return this;
        }

        public Builder setPauseBetweenActionRepetitions(double seconds) {
            this.pauseBetweenActionRepetitions = seconds;
            return this;
        }

        public Builder checkConditionOnlyAfterAllActionsInIterationAreComplete() {
            this.checkConditionAfterEachAction = false;
            return this;
        }

        public Builder setMaxWait(double maxWait) {
            this.maxWait = maxWait;
            return this;
        }

        public Builder setMaxMatchesToActOn(int maxMatchesToActOn) {
            this.maxMatchesToActOn = maxMatchesToActOn;
            return this;
        }

        public Builder fromObjectType(ObjectType objectType) {
            this.fromType = objectType;
            return this;
        }

        public Builder toObjectType(ObjectType objectType) {
            this.toType = objectType;
            return this;
        }

        public Builder setDelayBeforeDrag(double delay) {
            this.delayBeforeDrag = delay;
            return this;
        }

        public Builder setDelayBeforeDrop(double delay) {
            this.delayBeforeDrop = delay;
            return this;
        }

        public Builder setMoveMouseDelay(float delay) {
            this.moveMouseDelay = delay;
            return this;
        }

        public Builder dragToOffsetX(int offsetX) {
            this.dragToOffsetX = offsetX;
            return this;
        }

        public Builder dragToOffsetY(int offsetY) {
            this.dragToOffsetY = offsetY;
            return this;
        }

        public Builder defineAs(DefineAs defineAs) {
            this.defineAs = defineAs;
            return this;
        }

        public Builder addWidthToDefinedRegion(int addW) {
            this.addW = addW;
            return this;
        }

        public Builder addHeightToDefinedRegion(int addH) {
            this.addH = addH;
            return this;
        }

        public Builder setAbsoluteWidthOfDefinedRegion(int width) {
            this.absoluteW = width;
            return this;
        }

        public Builder setAbsoluteHeightOfDefinedRegion(int height) {
            this.absoluteH = height;
            return this;
        }

        public Builder shiftX(int addX) {
            this.addX = addX;
            return this;
        }

        public Builder shiftY(int addY) {
            this.addY = addY;
            return this;
        }

        public Builder highlightAllAtOnce() {
            this.highlightAllAtOnce = true;
            return this;
        }

        public Builder setHighlightSeconds(double seconds) {
            this.highlightSeconds = seconds;
            return this;
        }

        public Builder setHighlightColor(String color) {
            this.highlightColor = color;
            return this;
        }

        public Builder getTextUntil(GetTextUntil getTextUntil) {
            this.getTextUntil = getTextUntil;
            return this;
        }

        public Builder setTypeDelay(double typeDelay) {
            this.typeDelay = typeDelay;
            return this;
        }

        public Builder clickMatchBeforeTyping() {
            this.clickMatchBeforeTyping = true;
            return this;
        }

        public ActionOptions build() {
            ActionOptions actionOptions = new ActionOptions();
            actionOptions.action = action;
            actionOptions.tempFind = tempFind;
            actionOptions.clickUntil = clickUntil;
            actionOptions.find = find;
            actionOptions.doOnEach = doOnEach;
            actionOptions.successEvaluation = successEvaluation;
            actionOptions.similarity = similarity;
            actionOptions.delayBeforeMouseDown = delayBeforeMouseDown;
            actionOptions.pauseAfterMouseUp = pauseAfterMouseUp;
            actionOptions.clickDelay = clickDelay;
            actionOptions.clickType = clickType;
            actionOptions.moveMouseAfterClick = moveMouseAfterClick;
            actionOptions.locationAfterClick = locationAfterClick;
            actionOptions.offsetLocationBy = offsetLocationBy;
            actionOptions.searchRegions = searchRegions;
            actionOptions.stateProbabilitiesAfterClick = stateProbabilitiesAfterClick;
            actionOptions.pauseBeforeBegin = pauseBeforeBegin;
            actionOptions.pauseAfterEnd = pauseAfterEnd;
            actionOptions.pauseBetweenActions = pauseBetweenActions;
            actionOptions.numberOfActions = numberOfActions;
            actionOptions.timesToRepeatActionUntil = timesToRepeatActionUntil;
            actionOptions.pauseBetweenActionRepetitions = pauseBetweenActionRepetitions;
            actionOptions.checkConditionAfterEachAction = checkConditionAfterEachAction;
            actionOptions.maxWait = maxWait;
            actionOptions.maxMatchesToActOn = maxMatchesToActOn;
            actionOptions.fromType = fromType;
            actionOptions.toType = toType;
            actionOptions.delayBeforeDrag = delayBeforeDrag;
            actionOptions.delayBeforeDrop = delayBeforeDrop;
            actionOptions.moveMouseDelay = moveMouseDelay;
            actionOptions.dragToOffsetX = dragToOffsetX;
            actionOptions.dragToOffsetY = dragToOffsetY;
            actionOptions.defineAs = defineAs;
            actionOptions.addW = addW;
            actionOptions.addH = addH;
            actionOptions.absoluteW = absoluteW;
            actionOptions.absoluteH = absoluteH;
            actionOptions.addX = addX;
            actionOptions.addY = addY;
            actionOptions.highlightAllAtOnce = highlightAllAtOnce;
            actionOptions.highlightSeconds = highlightSeconds;
            actionOptions.highlightColor = highlightColor;
            actionOptions.getTextUntil = getTextUntil;
            actionOptions.typeDelay = typeDelay;
            actionOptions.clickMatchBeforeTyping = clickMatchBeforeTyping;
            return actionOptions;
        }
    }
}
