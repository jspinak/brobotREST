package com.brobot.brobotREST.stateObjects.sikuliWrappers;

import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.primatives.Region;
import org.sikuli.basics.Settings;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Wait {

    private Find find;
    private FindRIP findRIP;

    public Wait(Find find, FindRIP findRIP) {
        this.find = find;
        this.findRIP = findRIP;
    }

    public void wait(double timeout) {
        new Region().wait(timeout);
    }

    public Match wait(Region region, double timeout, Image image) {
        return wait(region, timeout, (float) Settings.MinSimilarity, image);
    }

    public Match wait(Region region, double timeout, float similarity, Image... images) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            match = find.findBestMatch(region, similarity, images);
            if (match != null) return match;
        }
        return null;
    }

    public Match wait(double timeout, float similarity, StateImageData... stateImages) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            for (StateImageData stateImageData : stateImages) {
                match = find.findBestMatch(
                        stateImageData.getSearchRegion(), similarity, stateImageData.getImage());
                if (match != null) return match;
            }
        }
        return null;
    }

    public Match wait(double timeout, StateRIPData stateRIP) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            match = findRIP.findFirstMatch(
                    stateRIP.getSearchRegion(), stateRIP.getRegionImagePairs().getPairs());
            if (match != null) return match;
        }
        return null;
    }

    public Match wait(double timeout, StateRIPData... stateRIPs) {
        return wait(timeout, new HashSet<>(Arrays.asList(stateRIPs)));
    }

    public Match wait(double timeout, Set<StateRIPData> stateRIPs) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            for (StateRIPData stateRIP : stateRIPs) {
                match = findRIP.findFirstMatch(
                        stateRIP.getSearchRegion(), stateRIP.getRegionImagePairs().getPairs());
                if (match != null) return match;
            }
        }
        return null;
    }

    public boolean waitVanish(double timeout, Image... images) {
        return waitVanish(new Region(), timeout, images);
    }

    public boolean waitVanish(Region region, double timeout, Image... images) {
        return waitVanish(region, timeout, (float) Settings.MinSimilarity, images);
    }

    public boolean waitVanish(Region region, double timeout, float similarity, Image... images) {
        return waitVanish(region, timeout, similarity, Arrays.asList(images));
    }

    public boolean waitVanish(Region region, double timeout, float similarity, List<Image> images) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            match = find.findBestMatch(region, similarity, images);
            if (match == null) return true;
        }
        return false;
    }

    public boolean waitVanish(double timeout, float similarity, StateImageData... stateImageData) {
        return waitVanish(timeout, similarity, Arrays.asList(stateImageData));
    }

    public boolean waitVanish(double timeout, float similarity, List<StateImageData> stateImages) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            for (StateImageData stateImageData : stateImages) {
                match = find.findFirstMatch(similarity, stateImageData);
                if (match == null) return true;
            }
        }
        return false;
    }

    public boolean waitVanish(double timeout, StateRIPData stateRIP) {
        return waitVanish(new Region(), timeout, stateRIP);
    }

    public boolean waitVanish(Region searchRegion, double timeout, StateRIPData stateRIP) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            match = findRIP.findFirstMatch(searchRegion, stateRIP.getRegionImagePairs().getPairs());
            if (match == null) return true;
        }
        return false;
    }

}