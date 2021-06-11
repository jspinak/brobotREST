package com.brobot.brobotREST.reports;

import com.brobot.brobotREST.database.primitives.image.Image;
import com.brobot.brobotREST.database.primitives.regionImagePairs.RegionImagePair;
import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
public class FoundRatios {

    private StateService stateService;

    public FoundRatios(StateService stateService) {
        this.stateService = stateService;
    }

    public void printRatios() {
        Collection<State> states = stateService.findAllStates();
        for (State state : states) {
            Set<StateImageObject> stateImageObjects = state.getStateImages();
            for (StateImageObject stateImageObject : stateImageObjects) {
                System.out.print("__");
                System.out.println(stateImageObject.getName());
                System.out.print("Image found ratio: ");
                printRatioIfExists(stateImageObject.getImage());
                System.out.println();
                System.out.print("RIP found ratios: \n");
                Set<RegionImagePair> pairs = stateImageObject.getRegionImagePairs().getPairs();
                for (RegionImagePair pair : pairs) {
                    printRatioIfExists(pair.getImage());
                    testPrint(pair.getImage());
                }
            }
        }
    }

    private boolean printRatioIfExists(Image image) {
        if (image.getTimesSearched() == 0) return false;
        System.out.print(image.getFoundRatio() + " ");
        image.print();
        System.out.println();
        return true;
    }

    private boolean testPrint(Image image) {
        System.out.print(".95 ");
        image.print();
        System.out.println();
        return true;
    }
}
