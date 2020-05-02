package com.brobot.brobotREST.database.primatives;

import com.brobot.brobotREST.stateObjects.objectMethods.click.ClickStateRIP;
import com.brobot.brobotREST.stateObjects.objectMethods.exists.ExistsStateRIP;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class RegionImagePairs {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RegionImagePair> pairs = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Region lastRegionFound = new Region();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Image lastImageFound = new Image();

    public RegionImagePairs() {}

    public RegionImagePairs(String image) {
        RegionImagePair newRegImgPair = new RegionImagePair();
        newRegImgPair.setImage(new Image(image));
        pairs.add(newRegImgPair);
    }
}
