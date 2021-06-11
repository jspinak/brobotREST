package com.brobot.brobotREST.database.primitives.regionImagePairs;

import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.primitives.image.Image;
import lombok.Data;

@Data
public class RegionImagePair {

    private Region region = new Region();
    private Image image;


}
