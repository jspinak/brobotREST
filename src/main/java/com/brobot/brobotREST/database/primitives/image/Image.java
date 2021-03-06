package com.brobot.brobotREST.database.primitives.image;

import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import lombok.Data;
import org.sikuli.script.Pattern;

import java.awt.image.BufferedImage;
import java.util.*;

import static com.brobot.brobotREST.actions.NullState.Enum.NULL;

@Data
public class Image {

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

    public StateImageObject inNullState() {
        return new StateImageObject.Builder()
                .inState(NULL)
                .withImage(getImageNames().toArray(new String[0]))
                .build();
    }

    public List<Pattern> getAllPatterns() {
        List<Pattern> patterns = new ArrayList<>();
        getFilenames().forEach(filename -> patterns.add(new Pattern(filename)));
        return patterns;
    }

    public Optional<Pattern> getPattern(int indexOfFilename) {
        if (indexOfFilename >= imageNames.size()) return Optional.empty();
        return Optional.of(new Pattern(getFilenames().get(indexOfFilename)));
    }

    public List<BufferedImage> getAllBufferedImages() {
        List<BufferedImage> bufferedImages = new ArrayList<>();
        for (int i=0; i<getFilenames().size(); i++) {
            Optional<BufferedImage> bufferedImage = getBufferedImage(i);
            bufferedImage.ifPresent(bufferedImages::add);
        }
        return bufferedImages;
    }

    public Optional<BufferedImage> getBufferedImage(int indexOfFilename) {
        Optional<Pattern> pattern = getPattern(indexOfFilename);
        return pattern.map(value -> value.getImage().get());
    }

    public int getWidth(int index) {
        Optional<BufferedImage> bufferedImage = getBufferedImage(index);
        return bufferedImage.map(BufferedImage::getWidth).orElse(0);
    }

    public int getHeight(int index) {
        Optional<BufferedImage> bufferedImage = getBufferedImage(index);
        return bufferedImage.map(BufferedImage::getHeight).orElse(0);
    }

}
