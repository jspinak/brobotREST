package com.brobot.brobotREST.Primatives;

import com.brobot.brobotREST.Primatives.Enums.Language;
import org.sikuli.script.Pattern;

import java.util.*;

public class Image {
    private Map<Language, List<String>> imageNames = new HashMap<>();
    private static Language language = Language.DE;
    private double timesSearched = 0;
    private double timesFound = 0;

    //different computer-remoteapp-phone connections have a separate folder with all the same filenames
    //the filepath is specified elsewhere
    public Image() {}

    public Image(String imageName) {
        List<String> names = new ArrayList<>(Collections.singletonList(imageName));
        imageNames.put(language,names);
    }

    public Image(String... imageNames) {
        List<String> names = new ArrayList<>(Arrays.asList(imageNames));
        this.imageNames.put(language,names);
    }

    public Image(Image... images) {
        for (Image image : images) {
            addImage(image);
        }
    }

    public static void setLanguage(Language lang) {
        language = lang;
    }

    public void addImageNamesInLanguage(Language language, String... imageNames) {
        List<String> names = new ArrayList<>(Arrays.asList(imageNames));
        this.imageNames.put(language,names);
    }

    public void addImage(Image image) {
        List<String> namesToAdd = image.getImageNames();
        if (imageNames.containsKey(language)) {
            imageNames.get(language).addAll(namesToAdd);
        } else {
            List<String> names = new ArrayList<>(namesToAdd);
            imageNames.put(language, names);
        }
    }

    public List<String> getImageNames() {
        return imageNames.get(language);
    }

    public List<String> getFilenames() {
        List<String> filenames = new ArrayList<>();
        for (String imageName : getImageNames()) {
            filenames.add(imageName + ".png");
        }
        return filenames;
    }

    public ArrayList<Pattern> getPatterns() {
        String fileName;
        ArrayList<Pattern> patterns = new ArrayList<>();
        for (String imageName : getImageNames()) {
            fileName = imageName + ".png";
            patterns.add(new Pattern(fileName));
        }
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
