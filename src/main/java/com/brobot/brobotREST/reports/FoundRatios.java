package com.brobot.brobotREST.reports;

import com.brobot.brobotREST.dataAccess.StateRepository;
import com.brobot.brobotREST.database.primatives.RegionImagePair;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateData.methods.StateObjectCollectionMethods;
import com.brobot.brobotREST.stateData.methods.StateObjectDataMethods;
import com.brobot.brobotREST.database.primatives.Image;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
public class FoundRatios {

    private StateRepository stateRepository;
    private StateObjectCollectionMethods stateObjectCollectionMethods;
    private StateObjectDataMethods stateObjectDataMethods;

    public FoundRatios(StateRepository stateRepository,
                       StateObjectCollectionMethods stateObjectCollectionMethods,
                       StateObjectDataMethods stateObjectDataMethods) {
        this.stateRepository = stateRepository;
        this.stateObjectCollectionMethods = stateObjectCollectionMethods;
        this.stateObjectDataMethods = stateObjectDataMethods;
    }

    public void printRatios() {
        Collection<StateData> states = stateRepository.findAll();
        for (StateData state : states) {
            Set<StateRIPData> stateRIPs = stateObjectCollectionMethods.getRIPs(state.getStateObjects());
            for (StateRIPData stateRIP : stateRIPs) {
                System.out.print("__"); stateObjectDataMethods.print(stateRIP,":\n");
                Set<RegionImagePair> pairs = stateRIP.getRegionImagePairs().getPairs();
                for (RegionImagePair pair : pairs) {
                    printRatioIfExists(pair.getImage());
                    testPrint(pair.getImage());
                }
            }
        }
    }

    private boolean printRatioIfExists(Image image) {
        if (image.getTimesSearched() == 0) return false;
        System.out.print(image.getFoundRatio()+" ");
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
