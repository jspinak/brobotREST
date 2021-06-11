package com.brobot.brobotREST.database.state.stateObject.stateImageObject;

import com.brobot.brobotREST.database.primitives.region.Region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchRegions {

    List<Region> regions = new ArrayList<>();
    {
        regions.add(new Region());  // initialize with an undefined region (representing the screen)
    }

    public Region getSearchRegion() {
        for (Region region : regions) {
            if (region.defined()) return region;
        }
        return regions.get(0);
    }

    public void setSearchRegions(List<Region> searchRegions) {
        regions = new ArrayList<>();
        addSearchRegions(searchRegions);
        if (regions.isEmpty()) regions.add(new Region());
    }

    public List<Region> getAllRegions() {
        return regions;
    }

    public void addSearchRegions(Region... searchRegions) {
        regions.addAll(Arrays.asList(searchRegions));
    }

    public void addSearchRegions(List<Region> searchRegions) {
        regions.addAll(searchRegions);
    }

    public boolean defined() {
        for (Region region : regions) {
            if (region.defined()) return true;
        }
        return false;
    }
}
