package com.brobot.brobotREST.database.primatives;

import lombok.Data;
import org.sikuli.script.Pattern;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> imageNames = new HashSet<>();
    private double timesSearched = 0;
    private double timesFound = 0;

    public Image() {}

    public Image(String imageName) {
        imageNames.add(imageName);
    }

    public Image(String... imageNames) {
        this.imageNames.addAll(Arrays.asList(imageNames));
    }

    public Image(Image... images) {
        for (Image image : images) imageNames.addAll(image.getImageNames());
    }

    public Set<String> getImageNames() {
        return imageNames;
    }

    public List<String> getFilenames() {
        List<String> filenames = new ArrayList<>();
        getImageNames().forEach(name -> filenames.add(name+".png"));
        return filenames;
    }

    public List<Pattern> getPatterns() {
        List<Pattern> patterns = new ArrayList<>();
        getFilenames().forEach(name -> patterns.add(new Pattern(name)));
        return patterns;
    }

    public Pattern getFirstPattern() {
        List<Pattern> patterns = getPatterns();
        if (patterns.isEmpty()) return null;
        return patterns.get(0);
    }

    public String getAbsolutePathOfFirstFile() {
        return getFirstPattern().getFilename();
    }

    public String getAbsolutePath(int indexOfFilename) {
        if (indexOfFilename >= getImageNames().size()) return null;
        Pattern pattern = getPatterns().get(indexOfFilename);
        return pattern.getFilename();
    }

    public int getWidthOfFirstFile() {
        return getFirstPattern().getBImage().getWidth();
    }

    public int getHeightOfFirstFile() {
        return getFirstPattern().getBImage().getHeight();
    }

    public boolean contains(String... images) {
        for (String image : images) {
            if (getImageNames().contains(image)) return true;
        }
        return false;
    }

    public boolean contains(List<String> images) {
        for (String image : images) {
            if (getImageNames().contains(image)) return true;
        }
        return false;
    }

    public void print() {
        for (String imageName : getImageNames()) {
            System.out.print(imageName+" ");
        }
    }

    public void foundIf(boolean found) {
        timesSearched++;
        if (found) timesFound++;
    }

    public double getTimesSearched() {
        return timesSearched;
    }

    public double getFoundRatio() {
        if (timesSearched == 0) return 1;
        return timesFound/timesSearched;
    }
}
