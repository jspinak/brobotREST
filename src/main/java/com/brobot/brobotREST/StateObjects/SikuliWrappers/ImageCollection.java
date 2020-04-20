package com.brobot.brobotREST.StateObjects.SikuliWrappers;

import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.Primatives.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageCollection {

    public Image[] getImageArray(StateImage... stateImages) {
        Image[] imageArray = new Image[stateImages.length];
        for (int i=0; i<imageArray.length; i++) imageArray[i] = stateImages[i].getStateImage();
        return imageArray;
    }
}
