package com.brobot.brobotREST.database.primitives.regionImagePairs;

import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.primitives.image.Image;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class RegionImagePairs {

    private Set<RegionImagePair> pairs = new HashSet<>();
    private Region lastRegionFound = new Region();
    private Image lastImageFound = new Image();

    public RegionImagePairs() {}

    public RegionImagePairs(String... images) {
        for (String image : images) {
            RegionImagePair newRegImgPair = new RegionImagePair();
            newRegImgPair.setImage(new Image(image));
            pairs.add(newRegImgPair);
        }
    }

    public boolean defined() {
        return lastRegionFound.defined();
    }

    public Image getFirstImage() {
        if (pairs.isEmpty()) return null;
        return pairs.iterator().next().getImage();
    }

    public Set<String> getImageNames() {
        Set<String> names = new HashSet<>();
        pairs.forEach(pair -> names.addAll(pair.getImage().getImageNames()));
        return names;
    }

}
