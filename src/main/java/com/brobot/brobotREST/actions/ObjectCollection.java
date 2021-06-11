package com.brobot.brobotREST.actions;

import com.brobot.brobotREST.database.primitives.image.Image;
import com.brobot.brobotREST.database.primitives.location.Location;
import com.brobot.brobotREST.database.primitives.match.Matches;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.state.State;
import com.brobot.brobotREST.database.state.stateObject.StateObject;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateLocation;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateRegion;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateString;
import com.brobot.brobotREST.database.state.stateObject.stateImageObject.StateImageObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
public class ObjectCollection {

    private List<StateLocation> stateLocations = new ArrayList<>();
    private List<StateImageObject> stateImages = new ArrayList<>();
    private List<StateRegion> stateRegions = new ArrayList<>();
    private List<StateString> stateStrings = new ArrayList<>();
    private List<Matches> matches = new ArrayList<>();

    private ObjectCollection() {}

    public boolean empty() {
        return stateLocations.isEmpty()
                && stateImages.isEmpty()
                && stateRegions.isEmpty()
                && stateStrings.isEmpty()
                && matches.isEmpty();
    }

    public static class Builder {
        private List<StateLocation> stateLocations = new ArrayList<>();
        private List<StateImageObject> stateImages = new ArrayList<>();
        private List<StateRegion> stateRegions = new ArrayList<>();
        private List<StateString> stateStrings = new ArrayList<>();
        private List<Matches> matches = new ArrayList<>();

        public Builder withLocations(Location... locations) {
            for (Location location : locations) this.stateLocations.add(location.inNullState());
            return this;
        }

        public Builder withLocations(StateLocation... locations) {
            Collections.addAll(this.stateLocations, locations);
            return this;
        }

        public Builder withImages(Image... images) {
            for (Image image : images) this.stateImages.add(image.inNullState());
            return this;
        }

        public Builder withImages(List<StateImageObject> images) {
            this.stateImages.addAll(images);
            return this;
        }

        public Builder withImages(StateImageObject... images) {
            Collections.addAll(this.stateImages, images);
            return this;
        }

        public Builder withAllStateImages(State state) {
            if (state == null) System.out.print("null state passed| ");
            //else System.out.println(state.getName());
            stateImages.addAll(state.getStateImages());
            return this;
        }

        public Builder withRegions(Region... regions) {
            for (Region region : regions) this.stateRegions.add(region.inNullState());
            return this;
        }

        public Builder withRegions(StateRegion... regions) {
            Collections.addAll(this.stateRegions, regions);
            return this;
        }

        public Builder withGridSubregions(int rows, int columns, Region... regions) {
            for (Region region : regions) {
                for (Region gridRegion : region.getGridRegions(rows, columns))
                    this.stateRegions.add(gridRegion.inNullState());
            }
            return this;
        }

        // should the State info be kept if it's a subregion? this is not clear, so we're sticking with NullState for now
        public Builder withGridSubregions(int rows, int columns, StateRegion... regions) {
            for (StateRegion region : regions) {
                for (Region gridRegion : region.getSearchRegion().getGridRegions(rows, columns))
                    this.stateRegions.add(gridRegion.inNullState());
            }
            return this;
        }

        public Builder withStrings(String... strings) {
            for (String string : strings) this.stateStrings.add(new StateString.InNullState().withString(string));
            return this;
        }

        public Builder withStrings(StateString... strings) {
            Collections.addAll(this.stateStrings, strings);
            return this;
        }

        public Builder withMatches(Matches... matches) {
            Collections.addAll(this.matches, matches);
            return this;
        }

        public ObjectCollection build() {
            ObjectCollection objectCollection = new ObjectCollection();
            objectCollection.stateLocations = stateLocations;
            objectCollection.stateImages = stateImages;
            objectCollection.stateRegions = stateRegions;
            objectCollection.stateStrings = stateStrings;
            objectCollection.matches = matches;
            return objectCollection;
        }
    }
}
