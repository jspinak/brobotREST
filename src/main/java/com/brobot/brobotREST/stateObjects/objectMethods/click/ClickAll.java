package com.brobot.brobotREST.stateObjects.objectMethods.click;

import com.brobot.brobotREST.database.primatives.RegionImagePair;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.objectMethods.find.FindStateImage;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.Click;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.ImageCollection;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.Wait;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.state.StateRegionData;
import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.primatives.Region;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ClickAll {

    private MockRegion mockRegion;
    private final ClickStateImage clickStateImage;
    private final ClickStateRIP clickStateRIP;
    private final ClickStateRegion clickStateRegion;
    private Click click;
    private FindStateImage findStateImage;
    private Wait wait;
    private ImageCollection imageCollection;

    public ClickAll(MockRegion mockRegion,
                    ClickStateImage clickStateImage, ClickStateRIP clickStateRIP,
                    ClickStateRegion clickStateRegion,
                    Click click, FindStateImage findStateImage, Wait wait,
                    ImageCollection imageCollection) {
        this.mockRegion = mockRegion;
        this.clickStateImage = clickStateImage;
        this.clickStateRIP = clickStateRIP;
        this.clickStateRegion = clickStateRegion;
        this.click = click;
        this.findStateImage = findStateImage;
        this.wait = wait;
        this.imageCollection = imageCollection;
    }

    public boolean regions(StateRegionData... stateRegionData) {
        return regions(.5, stateRegionData);
    }

    public boolean regions(double pauseBetweenClicks, StateRegionData... stateRegionData) {
        return clickStateRegion.regions(pauseBetweenClicks, stateRegionData);
    }

    public boolean imagesInRIP(StateRIPData stateRIP) {
        return imagesInRIP(.5, stateRIP);
    }

    public boolean imagesInRIP(double pauseBetweenClicks, StateRIPData stateRIP) {
        Match mockMatch = mockRegion.doActionToChangeState(stateRIP) ? mockRegion.getMockMatch() : null;
        if (mockRegion.isUseMock()) {
            System.out.print(stateRIP.getName()+"::clickAllImagesInRIP | ");
            return mockMatch != null;
        }
        boolean found = false;
        for (RegionImagePair pair : stateRIP.getRegionImagePairs().getPairs()) {
            if (click.clickFirstMatch(stateRIP.getSearchRegion(), pair) != null) {
                wait.wait(pauseBetweenClicks);
                found = true;
            }
        }
        return found;
    }

    public boolean images(StateImageData... images) {
        return images(.5, images);
    }

    public boolean images(double pauseBetweenClicks, StateImageData... images) {
        Match mockMatch = mockRegion.doActionToChangeStates(images) ? mockRegion.getMockMatch() : null;
        if (mockRegion.isUseMock()) {
            System.out.print("clickAll: ");
            for (StateImageData image : images) System.out.print(image.getName()+" ");
            System.out.print("| ");
            return mockMatch != null;
        }
        boolean found = false;
        for (StateImageData stateImageData : images) {
            for (Match match : findStateImage.findAll(stateImageData.getSearchRegion(), stateImageData)) {
                if (match != null) {
                    match.click();
                    wait.wait(pauseBetweenClicks);
                    found = true;
                }
            }
        }
        return found;
    }

}
