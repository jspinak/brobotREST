package com.brobot.brobotREST.StateObjects.State;

import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.ParentObject.StateObject;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.Region.StateRegion;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class StateObjects {

    private List<StateRIP> stateRIPs = new ArrayList<>();
    private List<StateImage> stateImages = new ArrayList<>();
    private List<StateString> stateStrings = new ArrayList<>();
    private List<StateRegion> stateRegions = new ArrayList<>();

    public void addStateRIPs(StateRIP... stateRIPs) {
        //System.out.println("adding state RIPs .................."); ////////////////testing
        for (StateRIP stateRIP : stateRIPs) {
            this.stateRIPs.add(stateRIP);
            //System.out.println(stateRIP.getCurrentState()); ////////////////testing
        }
        //System.out.print("size of StateRIP list: "+this.stateRIPs.size()+" "); ////////////////testing
        //this.stateRIPs.forEach(s -> System.out.println()); ////////////////testing
    }

    public StateRIP getStateRIP(String name) {
        for (StateRIP stateRIP : stateRIPs) {
            if (stateRIP.getName().equals(name)) return stateRIP;
        }
        return null;
    }

    public void activateMock() {
        for (StateRIP stateRIP : stateRIPs) stateRIP.setProbabilityExists(100);
    }

    public void deactivateMock() {
        for (StateRIP stateRIP : stateRIPs) stateRIP.setProbabilityExists(0);
    }

    public void addStateImages(StateImage... stateImages) {
        for (StateImage stateImage : stateImages) {
            this.stateImages.add(stateImage);
        }
    }

    public StateImage getStateImage(String name) {
        for (StateImage stateImage : stateImages) {
            if (stateImage.getName().equals(name)) return stateImage;
        }
        return null;
    }

    public void addStateStrings(StateString... stateStrings) {
        for (StateString stateString : stateStrings) {
            this.stateStrings.add(stateString);
        }
    }

    public StateString getStateString(String name) {
        for (StateString stateString : stateStrings) {
            if (stateString.getName().equals(name)) return stateString;
        }
        return null;
    }

    public void addStateRegions(StateRegion... stateRegions) {
        for (StateRegion stateRegion : stateRegions) {
            this.stateRegions.add(stateRegion);
        }
    }

    public StateRegion getStateRegion(String name) {
        for (StateRegion stateRegion : stateRegions) {
            if (stateRegion.getName().equals(name)) return stateRegion;
        }
        return null;
    }

    public List<StateObject> getAllStateObjects() {
        List<StateObject> stateObjects = new ArrayList<>();
        stateRIPs.forEach(sR -> stateObjects.add(sR));
        stateStrings.forEach(sS -> stateObjects.add(sS));
        stateImages.forEach(sI -> stateObjects.add(sI));
        stateRegions.forEach(sR -> stateObjects.add(sR));
        return stateObjects;
    }

}
