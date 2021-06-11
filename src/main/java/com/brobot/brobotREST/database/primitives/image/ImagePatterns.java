package com.brobot.brobotREST.database.primitives.image;

import org.sikuli.script.Pattern;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImagePatterns {

    public List<Pattern> getPatterns(Image image) {
        List<Pattern> patterns = new ArrayList<>();
        image.getFilenames().forEach(name -> patterns.add(new Pattern(name)));
        return patterns;
    }

    public List<Pattern> getPatterns(Image image, double similarity) {
        List<Pattern> patterns = getPatterns(image);
        for (Pattern pattern : patterns) pattern.similar(similarity);
        return patterns;
    }

    public Pattern getFirstPattern(Image image) {
        List<Pattern> patterns = getPatterns(image);
        if (patterns.isEmpty()) return null;
        return patterns.get(0);
    }

    public List<org.sikuli.script.Image> getSikuliImages(Image image) {
        List<Pattern> patterns = getPatterns(image);
        List<org.sikuli.script.Image> sikuliImages = new ArrayList<>();
        patterns.forEach(pattern -> sikuliImages.add(pattern.getImage()));
        return sikuliImages;
    }
}
