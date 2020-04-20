package com.brobot.brobotREST.StateObjects.SikuliWrappers;

import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.Region;
import org.sikuli.basics.Settings;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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

    public Match wait(double timeout, float similarity, StateImage... stateImages) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            for (StateImage stateImage : stateImages) {
                match = find.findBestMatch(stateImage.getSearchRegion(), similarity, stateImage.getStateImage());
                if (match != null) return match;
            }
        }
        return null;
    }

    public Match wait(double timeout, StateRIP stateRIP) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            match = findRIP.findFirstMatch(stateRIP.getSearchRegion(), stateRIP.getPairs());
            if (match != null) return match;
        }
        return null;
    }

    public Match wait(double timeout, StateRIP... stateRIPs) {
        return wait(timeout, Arrays.asList(stateRIPs));
    }

    public Match wait(double timeout, List<StateRIP> stateRIPs) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            for (StateRIP stateRIP : stateRIPs) {
                match = findRIP.findFirstMatch(stateRIP.getSearchRegion(), stateRIP.getPairs());
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

    public boolean waitVanish(double timeout, float similarity, StateImage... stateImages) {
        return waitVanish(timeout, similarity, Arrays.asList(stateImages));
    }

    public boolean waitVanish(double timeout, float similarity, List<StateImage> stateImages) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            for (StateImage stateImage : stateImages) {
                match = find.findFirstMatch(similarity, stateImages);
                if (match == null) return true;
            }
        }
        return false;
    }

    public boolean waitVanish(double timeout, StateRIP stateRIP) {
        return waitVanish(new Region(), timeout, stateRIP);
    }

    public boolean waitVanish(Region searchRegion, double timeout, StateRIP stateRIP) {
        LocalDateTime startTime = LocalDateTime.now();
        Match match;
        while (LocalDateTime.now().isBefore(startTime.plusNanos((long)(timeout* Math.pow(10,9))))) {
            match = findRIP.findFirstMatch(searchRegion, stateRIP.getPairs());
            if (match == null) return true;
        }
        return false;
    }

}