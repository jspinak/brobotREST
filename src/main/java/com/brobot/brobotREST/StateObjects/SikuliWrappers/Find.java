package com.brobot.brobotREST.StateObjects.SikuliWrappers;

import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.Region;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Find {

    public Match findBestMatch(Image... images) {
        return findBestMatch(new Region(), images);
    }

    public Match findBestMatch(Region region, Image... images) {
        return findBestMatch(region, (float) Settings.MinSimilarity, images);
    }

    public Match findBestMatch(Region region, float similarity, Image... images) {
        return findBestMatch(region, similarity, Arrays.asList(images));
    }

    public Match findBestMatch(Region region, float similarity, List<Image> images) {
        List<Match> matches = new ArrayList<>();
        for (Image image : images) {
            matches.addAll(oneMatchPerPattern(region, image, similarity));
        }
        return getBestMatch(matches);
    }

    public Match findFirstMatch(float similarity, StateImage... stateImages) {
        return findFirstMatch(similarity, Arrays.asList(stateImages));
    }

    public Match findFirstMatch(float similarity, List<StateImage> stateImages) {
        Match match;
        for (StateImage stateImage : stateImages) {
            match = findBestMatch(stateImage.getSearchRegion(), stateImage.getStateImage());
            if (match != null) return match;
        }
        return null;
    }

    private Match getBestMatch(List<Match> matches) {
        if (matches.isEmpty()) return null;
        Collections.sort(matches, Comparator.comparing(Match::getScore).reversed());
        return matches.get(0);
    }

    public List<Match> oneMatchPerPattern(Region region, Image image, float similarity) {
        //System.out.print(" ");
        List<Match> allMatches = new ArrayList<>();
        for (String imageName : image.getImageNames()) {
            Pattern pattern = new Pattern(imageName + ".png");
            pattern.similar(similarity);
            try {
                Match match = region.find(pattern);
                if (match != null) allMatches.add(match);
            } catch (FindFailed findFailed) {
                //System.out.print("findBestMatch all failed on "+imageName);
                //findFailed.printStackTrace();
            }
        }
        image.foundIf(!allMatches.isEmpty());
        return allMatches;
    }

    public List<Match> findAll(Region region, Image... images) {
        return findAll(region, (float) Settings.MinSimilarity, images);
    }

    public List<Match> findAll(Region region, float similarity, Image... images) {
        List<Match> matches = findAllWithOverlapping(region, similarity, images);
        return removeOverlapping(matches);
    }

    private List<Match> removeOverlapping(List<Match> matches) {
        Collections.sort(matches, Comparator.comparing(Match::getX));
        Match m1, m2;
        int i = 0;
        while (i + 1 < matches.size()) {
            m1 = matches.get(i);
            m2 = matches.get(i + 1);
            if (m2.x < m1.x + m1.w && m2.y < m1.y + m1.h) {
                matches.remove(m1.getScore() < m2.getScore() ? i : i + 1);
            } else {
                i++;
            }
        }
        return matches;
    }

    public List<Match> findAllWithOverlapping(Region region, float similarity, Image... images) {
        Iterator<Match> matches;
        List<Match> allMatches = new ArrayList<>();
        boolean found;
        for (Image image : images) {
            found = false;
            for (String imageName : image.getImageNames()) {
                Pattern pattern = new Pattern(imageName + ".png");
                pattern.similar(similarity);
                try {
                    matches = region.findAll(pattern);
                    while (matches.hasNext()) {
                        found = true;
                        allMatches.add(matches.next());
                    }
                } catch (FindFailed findFailed) {
                }
            }
            image.foundIf(found);
        }
        return allMatches;
    }

    public List<Match> findAllExcludeRegions(Region region, Image image, Region... excludeRegions) {
        return findAllExcludeRegions(region, image, (float) Settings.MinSimilarity, excludeRegions);
    }

    public List<Match> findAllExcludeRegions(Region region, Image image, float similarity, Region... excludeRegions) {
        List<Match> matches = findAll(region, similarity, image);
        for (Region exclude : excludeRegions) {
            matches.removeIf(m -> m.x < exclude.x + exclude.w && m.y < exclude.y + exclude.h);
        }
        return matches;
    }
}