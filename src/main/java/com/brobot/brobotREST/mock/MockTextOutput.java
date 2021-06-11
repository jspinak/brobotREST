package com.brobot.brobotREST.mock;

import com.brobot.brobotREST.database.primitives.image.Image;
import com.brobot.brobotREST.database.primitives.regionImagePairs.RegionImagePairs;
import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.database.state.stateObject.StateObject;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class MockTextOutput {

    public void print(String methodName) {
        System.out.format("|%s| ", methodName);
    }

    public void print(String methodName, StateObject... stateObjects) {
        System.out.format("|%s: ", methodName);
        for (StateObject stateObject : stateObjects) System.out.print(stateObject.getName()+".");
        System.out.print("| ");
    }

    public void print(String methodName, Image... images) {
        System.out.format("|%s: ", methodName);
        for (Image image : images) System.out.print(image.getImageNames()+".");
        System.out.print("| ");
    }

    public void print(String methodName, RegionImagePairs... RIPs) {
        System.out.format("|%s: ", methodName);
        for (RegionImagePairs RIP : RIPs) System.out.print(RIP.getImageNames()+".");
        System.out.print("| ");
    }

    public void print(String methodName, State... states) {
        System.out.format("|%s: ", methodName);
        Stream.of(states).forEach(state -> System.out.print(state.getName()+"."));
        System.out.print("| ");
    }

    public void print(String methodName, String... names) {
        System.out.format("|%s: ", methodName);
        Stream.of(names).forEach(name -> System.out.print(name+"."));
        System.out.print("| ");
    }
}
