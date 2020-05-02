package com.brobot.brobotREST.stateData.methods;

import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.primatives.RegionImagePair;
import com.brobot.brobotREST.database.state.StateRIPData;
import org.springframework.stereotype.Component;

@Component
public class StateRIPMethods extends StateObjectDataMethods {

    public void add(StateRIPData stateRIPData, String string) {
        add(stateRIPData, new Image(string));
    }

    public void add(StateRIPData stateRIPData, Image image) {
        RegionImagePair newRegImgPair = new RegionImagePair();
        newRegImgPair.setImage(image);
        stateRIPData.getRegionImagePairs().getPairs().add(newRegImgPair);
    }

    public boolean isEmpty(StateRIPData stateRIPData) {
        return stateRIPData.getRegionImagePairs().getPairs().isEmpty();
    }
}
