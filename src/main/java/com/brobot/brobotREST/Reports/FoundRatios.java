package com.brobot.brobotREST.Reports;

import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.BaseObjects.GameStateRepository;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.Primatives.Image;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class FoundRatios {

    private GameStateRepository gameStateRepository;

    public FoundRatios(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    public void printRatios() {
        Collection<State> states = gameStateRepository.getAllGameStates();
        for (State state : states) {
            List<StateRIP> stateRIPs = state.getStateRIPs();
            for (StateRIP stateRIP : stateRIPs) {
                System.out.print("__"); stateRIP.print(":\n");
                Collection<Image> images = stateRIP.getPairs().values();
                for (Image image : images) {
                    printRatioIfExists(image);
                    testPrint(image);
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
