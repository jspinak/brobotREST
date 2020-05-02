package com.brobot.brobotREST.stateObjects.sikuliWrappers;

import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.primatives.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageCollection {

    public Image[] getImageArray(StateImageData... stateImageData) {
        Image[] imageArray = new Image[stateImageData.length];
        for (int i=0; i<imageArray.length; i++) imageArray[i] = stateImageData[i].getImage();
        return imageArray;
    }
}
