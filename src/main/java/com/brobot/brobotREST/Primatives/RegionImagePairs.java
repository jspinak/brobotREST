package com.brobot.brobotREST.Primatives;

import java.util.HashMap;
import java.util.Map;

public class RegionImagePairs {
    private Map<Region, Image> pairs = new HashMap<>();
    private Region lastRegionFound = new Region();
    private Image lastImageFound = new Image();

    public RegionImagePairs() {}

    public RegionImagePairs(Image... images) {
        for (Image image : images) {
            add(new Region(), image);
        }
    }

    public RegionImagePairs(String... imageLocations) {
        for (String string : imageLocations) {
            add(new Region(), new Image(string));
        }
    }

    public boolean isEmpty() {
        return pairs.isEmpty();
    }

    public void add(Region region, Image image) {
        pairs.put(region, image);
    }

    public Region getRegion() {
        return lastRegionFound;
    }

    public Image getLastImageFound() {
        return lastImageFound;
    }
}
