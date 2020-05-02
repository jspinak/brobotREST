package com.brobot.brobotREST.stateObjects.objectMethods.click;

import com.brobot.brobotREST.stateData.wrappers.StateObjectWrapper;
import com.brobot.brobotREST.stateObjects.objectMethods.exists.ExistsGameState;
import com.brobot.brobotREST.stateObjects.objectMethods.string.TypeStateString;
import com.brobot.brobotREST.stateObjects.objectMethods.wait.WaitGameState;
import com.brobot.brobotREST.stateObjects.objectMethods.wait.WaitStateObject;
import com.brobot.brobotREST.stateObjects.objectMethods.wait.WaitStateRIP;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.Wait;
import org.springframework.stereotype.Component;

@Component
public class ClickUntilStateImage {

    private ClickStateImage clickStateImage;
    private TypeStateString typeStateString;
    private WaitStateObject waitStateObject;
    private WaitStateRIP waitStateRIP;
    private WaitGameState waitGameState;
    private Wait wait;
    private ExistsGameState existsGameState;

    public ClickUntilStateImage(ClickStateImage clickStateImage, TypeStateString typeStateString,
                                WaitStateObject waitStateObject) {
        this.clickStateImage = clickStateImage;
        this.typeStateString = typeStateString;
        this.waitStateObject = waitStateObject;
        this.waitStateRIP = waitStateRIP;
        this.waitGameState = waitGameState;
        this.wait = wait;
        this.existsGameState = existsGameState;
    }
/*
    public boolean clickImageUntilDisappears(StateRegion stateRegion, StateImage stateImage,
                                             int maxClicks, double pauseSekDazwischen) {
        for (int i=0; i<maxClicks; i++) {
            if (clickStateImage.clickBestMatch(stateRegion, stateImage) == null) return false;
            pause.pause(pauseSekDazwischen);
        }
        return true;
    }

    public boolean clickRIPUntilDisappears(StateRIP stateRIP, int maxClicks, double pauseSekDazwischen) {
        for (int i=0; i<maxClicks; i++) {
            if (clickStateImage.clickFirstMatch(stateRIP) == null) return false;
            pause.pause(pauseSekDazwischen);
        }
        return true;
    }

    public boolean clickImageUntilRIPAppears(StateRegion stateRegion, StateImage stateImage,
                                              int maxClicks, double pauseSekDazwischen,
                                              List<StateRIP> toAppear) {
        for (int i=0; i<maxClicks; i++) {
            if (clickStateImage.clickBestMatch(stateRegion, stateImage) == null) return false;
            if (existsStateImage.exists(toAppear)) return true;
            pause.pause(pauseSekDazwischen);
        }
        return false;
    }
*/
    public boolean doActionUntilStateAppears(StateObjectWrapper stateObjectWrapper, int maxClicks) {
        for (int i=0; i<maxClicks; i++) {
            if (!stateObjectWrapper.doActionLeadingToStateChange()) return false;
            if (waitStateObject.waitForActivatedState(stateObjectWrapper.getStateObject())) return true;
        }
        return false;
    }

    public boolean objectDisappears(StateObjectWrapper stateObjectWrapper, int maxClicks) {
        for (int i=0; i<maxClicks; i++) {
            if (!stateObjectWrapper.doActionLeadingToStateChange()) return false;
            if (waitStateObject.waitVanish(
                    stateObjectWrapper,
                    stateObjectWrapper
                            .getStateObject()
                            .getTimeToWaitAfterAction()/maxClicks)) return true;
        }
        return false;
    }

/*
    public boolean clickRIPUntilRIPAppears(StateRIP stateRIP, int maxClicks, double pauseSekDazwischen,
                                           List<StateRIP> toAppear) {
        for (int i=0; i<maxClicks; i++) {
            if (clickStateImage.clickFirstMatch(stateRIP) == null) return false;
            if (existsStateImage.exists(stateRIP.getAssociatedStates().getAllAssociatedRIPs())) return true;
            pause.pause(pauseSekDazwischen);
        }
        return false;
    }

    public boolean clickImageUntilRIPDisappears(StateRegion stateRegion, StateImage stateImage,
                                          int maxClicks, double pauseSekDazwischen,
                                          List<StateRIP> toDisappear) {
        for (int i=0; i<maxClicks; i++) {
            if (clickStateImage.clickBestMatch(stateRegion, stateImage) == null) return false;
            if (!existsStateImage.exists(toDisappear)) return true;
            pause.pause(pauseSekDazwischen);
        }
        return false;
    }

    public boolean clickRIPUntilRIPDisappears(StateRIP stateRIP, int maxClicks, double pauseSekDazwischen,
                                        List<StateRIP> toDisappear) {
        for (int i=0; i<maxClicks; i++) {
            if (clickStateImage.clickFirstMatch(stateRIP) == null) return false;
            if (!existsStateImage.exists(toDisappear)) return true;
            pause.pause(pauseSekDazwischen);
        }
        return false;
    }

    public boolean clickImageUntilImageAppears(StateRegion stateRegion, StateImage stateImage,
                                               int maxClicks, double pauseSekDazwischen,
                                               StateRegion stateRegionAppearing,
                                               StateImage... stateImages) {
        for (int i=0; i<maxClicks; i++) {
            if (clickStateImage.clickBestMatch(stateRegion, stateImage) == null) return false;
            if (existsStateImage.exists(stateRegionAppearing, stateImages)) return true;
            pause.pause(pauseSekDazwischen);
        }
        return false;
    }

    public boolean clickRIPUntilImageAppears(StateRIP stateRIP, int maxClicks,
                                             double pauseSekDazwischen, StateRegion stateRegionAppearing,
                                             StateImage... stateImages) {
        for (int i=0; i<maxClicks; i++) {
            if (clickStateImage.clickFirstMatch(stateRIP) == null) return false;
            if (existsStateImage.exists(stateRegionAppearing, stateImages)) return true;
            pause.pause(pauseSekDazwischen);
        }
        return false;
    }

    public boolean clickImageUntilImageDisappears(StateRegion stateRegion, StateImage stateImage,
                                               int maxClicks, double pauseSekDazwischen,
                                                  StateRegion stateRegionDisappearing, StateImage... stateImages) {
        for (int i=0; i<maxClicks; i++) {
            if (clickStateImage.clickBestMatch(stateRegion, stateImage) == null) return false;
            if (!existsStateImage.exists(stateRegionDisappearing, stateImages)) return true;
            pause.pause(pauseSekDazwischen);
        }
        return false;
    }

    public boolean clickRIPUntilImageDisappears(StateRIP stateRIP, int maxClicks,
                                                double pauseSekDazwischen, StateRegion stateRegionDisappearing,
                                                StateImage... stateImages) {
        for (int i=0; i<maxClicks; i++) {
            if (clickStateImage.clickFirstMatch(stateRIP) == null) return false;
            if (!existsStateImage.exists(stateRegionDisappearing, stateImages)) return true;
            pause.pause(pauseSekDazwischen);
        }
        return false;
    }

    public boolean typeStringUntilRIPAppears(StateString stateString, int maxTyped,
                                             double pauseSekDazwischen) {
        for (int i=0; i<maxTyped; i++) {
            if (!typeStateString.type(stateString)) return false;
            if (existsStateImage.exists(stateString.getAssociatedStates().getAllAssociatedRIPs())) return true;
            pause.pause(pauseSekDazwischen);
        }
        return false;
    }

    public boolean typeStringUntilRIPDisappears(GameState gameState, StateString stateString, int maxTyped,
                                             double pauseSekDazwischen, List<StateRIP> toDisappear) {
        for (int i=0; i<maxTyped; i++) {
            if (!typeStateString.type(gameState, stateString)) return false;
            if (!existsStateImage.exists(toDisappear)) return true;
            pause.pause(pauseSekDazwischen);
        }
        return false;
    }

 */
}
