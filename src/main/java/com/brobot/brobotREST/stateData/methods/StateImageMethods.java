package com.brobot.brobotREST.stateData.methods;

import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.state.StateImageData;
import org.springframework.stereotype.Component;

@Component
public class StateImageMethods extends StateObjectDataMethods {

    public void add(StateImageData stateImage, String imageName) {
        stateImage.setImage(new Image(imageName));
    }

}
