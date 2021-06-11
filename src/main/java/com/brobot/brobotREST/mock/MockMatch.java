package com.brobot.brobotREST.mock;

import com.brobot.brobotREST.database.primitives.image.Image;
import com.brobot.brobotREST.database.primitives.region.Region;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MockMatch {

    public Match makeMockMatch(Region searchRegion, Image image) {
        if (image == null) return null;
        org.sikuli.script.Image img = new Pattern(image.getFilenames().get(0)).getImage();
        return makeMockMatch(img.w, img.h, searchRegion);
    }

    public Match makeMockMatch(int matchW, int matchH, Region searchRegion) {
        int posInRegionX = searchRegion.x + new Random().nextInt(Math.max(1, searchRegion.w - matchW));
        int posInRegionY = searchRegion.y + new Random().nextInt(Math.max(1, searchRegion.h - matchH));
        return new Match(posInRegionX, posInRegionY, matchW, matchH, .99, new Screen());
    }

    public Match makeMockMatchInScreen() {
        return makeMockMatch(new Region());
    }

    public Match makeMockMatch(Region searchRegion) {
        int matchW = new Random().nextInt(searchRegion.w / 10);
        int matchH = new Random().nextInt(searchRegion.h / 10);
        return makeMockMatch(matchW, matchH, searchRegion);
    }
}
