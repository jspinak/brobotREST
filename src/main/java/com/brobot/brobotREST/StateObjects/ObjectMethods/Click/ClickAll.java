package com.brobot.brobotREST.StateObjects.ObjectMethods.Click;

import com.brobot.brobotREST.StateObjects.ObjectMethods.Find.FindStateImage;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.Click;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.ImageCollection;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.MockRegion;
import com.brobot.brobotREST.StateObjects.SikuliWrappers.Wait;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.Region;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ClickAll {

    private MockRegion mockRegion;
    private Click click;
    private FindStateImage findStateImage;
    private Wait wait;
    private ImageCollection imageCollection;

    public ClickAll(MockRegion mockRegion, Click click, FindStateImage findStateImage, Wait wait,
                    ImageCollection imageCollection) {
        this.mockRegion = mockRegion;
        this.click = click;
        this.findStateImage = findStateImage;
        this.wait = wait;
        this.imageCollection = imageCollection;
    }

    public boolean regions(StateRegion... stateRegions) {
        return regions(.5, stateRegions);
    }

    public boolean regions(double pauseBetweenClicks, StateRegion... stateRegions) {
        boolean mockSucceeds = mockRegion.doActionToChangeState(stateRegions);
        if (mockRegion.isUseMock()) return mockSucceeds;
        for (StateRegion stateRegion : stateRegions) {
            click.click(stateRegion.getStateRegion());
            wait.wait(pauseBetweenClicks);
        }
        return true;
    }

    public boolean imagesInRIP(StateRIP stateRIP) {
        return imagesInRIP(.5, stateRIP);
    }

    public boolean imagesInRIP(double pauseBetweenClicks, StateRIP stateRIP) {
        Match mockMatch = mockRegion.doActionToChangeState(stateRIP) ? mockRegion.getMockMatch() : null;
        if (mockRegion.isUseMock()) {
            System.out.print(stateRIP.getName()+"::clickAllImagesInRIP | ");
            return mockMatch != null;
        }
        boolean found = false;
        for (Map.Entry<Region, Image> pair : stateRIP.getPairs().entrySet()) {
            if (click.clickFirstMatch(stateRIP.getSearchRegion(), pair) != null) {
                wait.wait(pauseBetweenClicks);
                found = true;
            }
        }
        return found;
    }

    public boolean images(StateImage... images) {
        return images(.5, images);
    }

    public boolean images(double pauseBetweenClicks, StateImage... images) {
        Match mockMatch = mockRegion.doActionToChangeState(images) ? mockRegion.getMockMatch() : null;
        if (mockRegion.isUseMock()) {
            System.out.print("clickAll: ");
            for (StateImage image : images) System.out.print(image.getName()+" ");
            System.out.print("| ");
            return mockMatch != null;
        }
        boolean found = false;
        for (StateImage stateImage : images) {
            for (Match match : findStateImage.findAll(stateImage.getSearchRegion(),stateImage)) {
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
