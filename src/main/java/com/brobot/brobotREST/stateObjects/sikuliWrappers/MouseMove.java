package com.brobot.brobotREST.stateObjects.sikuliWrappers;

import com.brobot.brobotREST.database.primatives.Image;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.springframework.stereotype.Component;

@Component
public class MouseMove {

    public boolean mouseMove(Region region, Image... images) {
        System.out.print(" ");
        for (Image image : images) {
            for (Pattern pattern : image.getPatterns()) {
                try {
                    return region.mouseMove(pattern) != 0;
                } catch (FindFailed findFailed) {
                    System.out.print("ff|");
                }
            }
        }
        return false;
    }

}
